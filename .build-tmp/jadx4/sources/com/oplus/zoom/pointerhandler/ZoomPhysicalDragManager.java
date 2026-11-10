package com.oplus.zoom.pointerhandler;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.collection.ArraySet;
import com.oplus.physicsengine.common.Debug;
import com.oplus.physicsengine.common.Vector;
import com.oplus.physicsengine.engine.AnimationListener;
import com.oplus.physicsengine.engine.AnimationUpdateListener;
import com.oplus.physicsengine.engine.AttachmentBehavior;
import com.oplus.physicsengine.engine.BaseBehavior;
import com.oplus.physicsengine.engine.DragBehavior;
import com.oplus.physicsengine.engine.FlingBehavior;
import com.oplus.physicsengine.engine.PhysicalAnimator;
import com.oplus.physicsengine.engine.Transform;
import com.oplus.physicsengine.engine.UIItem;
import com.oplus.zoom.common.LogD;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomInsetsController;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplus.zoom.common.ZoomUtil;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomPhysicalDragManager implements AnimationListener, AnimationUpdateListener {
    private static final float BOTTOM_LIMIT_EDGE_OF_MOVE_ZOOM_RATE = 0.7f;
    private static final float DRAG_CONSTRAINT_DAMPING_RATIO_MINI_ZOOM = 1.02f;
    private static final float DRAG_CONSTRAINT_DAMPING_RATIO_ZOOM = 0.7f;
    private static final float DRAG_CONSTRAINT_FREQUENCY_MINI_ZOOM = 18.0f;
    private static final float DRAG_CONSTRAINT_FREQUENCY_ZOOM = 19.0f;
    private static final float LEFT_LIMIT_EDGE_OF_MOVE_ZOOM_RATE = 0.667f;
    private static final float MINI_ZOOM_MOVE_TO_SHRINK_RATE = 1.01f;
    private static final float MINI_ZOOM_MOVE_TO_SHRINK_VELOCITY = 12000.0f;
    private static final float POSITION_CONSTRAINT_DAMPING_RATIO_MINI_ZOOM = 1.22f;
    private static final float POSITION_CONSTRAINT_DAMPING_RATIO_ZOOM = 1.22f;
    private static final float POSITION_CONSTRAINT_FREQUENCY_MINI_ZOOM = 6.0f;
    private static final float POSITION_CONSTRAINT_FREQUENCY_ZOOM = 6.0f;
    private static final float RIGHT_LIMIT_EDGE_OF_MOVE_ZOOM_RATE = 0.667f;
    private static final String TAG = "ZoomPointerHandler";
    private static final float VELOCITY_MAX = 24000.0f;
    private static final int VELOCITY_UNIT = 1000;
    private boolean mAnimating;
    private AttachmentBehavior mAttachBehavior;
    private final Context mContext;
    private DragBehavior mDragBehavior;
    private FlingBehavior mFlingBehavior;
    private boolean mIsDragging;
    private PhysicalAnimatorObserver mObserver;
    private PhysicalAnimator mPhysics;
    private UIItem mUIItem;
    private VelocityTracker mVelocityTracker;
    private Object mLock = new Object();
    private final Vector mHookPosition = new Vector(0.0f, 0.0f);

    public ZoomPhysicalDragManager(Context context) {
        this.mContext = context;
    }

    private Rect getExtendBoundInMiniZoom(Rect rect) {
        int screenWidth = ZoomDisplayController.getInstance().getScreenWidth();
        int screenHeight = ZoomDisplayController.getInstance().getScreenHeight();
        int topBottomLimitInMini = ZoomParameterHelper.getInstance().getTopBottomLimitInMini();
        Rect rect2 = new Rect((int) ((-rect.width()) * MINI_ZOOM_MOVE_TO_SHRINK_RATE), topBottomLimitInMini, (int) ((rect.width() * MINI_ZOOM_MOVE_TO_SHRINK_RATE) + screenWidth), screenHeight - topBottomLimitInMini);
        LogD.d("ZoomPointerHandler", "getExtendBoundInMiniZoom bound = " + rect2);
        return rect2;
    }

    private Rect getLimitBoundInMiniZoom(Rect rect) {
        Rect rect2 = new Rect();
        int screenWidth = ZoomDisplayController.getInstance().getScreenWidth();
        int screenHeight = ZoomDisplayController.getInstance().getScreenHeight();
        int leftRightLimitInMini = ZoomParameterHelper.getInstance().getLeftRightLimitInMini();
        int topBottomLimitInMini = ZoomParameterHelper.getInstance().getTopBottomLimitInMini();
        rect2.set(leftRightLimitInMini, topBottomLimitInMini, screenWidth - leftRightLimitInMini, screenHeight - topBottomLimitInMini);
        LogD.d("ZoomPointerHandler", "getLimitBoundInMiniZoom bound = " + rect2);
        return rect2;
    }

    private Rect getLimitBoundInZoom(Rect rect) {
        Rect rect2 = new Rect();
        int screenWidth = ZoomDisplayController.getInstance().getScreenWidth();
        int screenHeight = ZoomDisplayController.getInstance().getScreenHeight();
        float density = ZoomDisplayController.getInstance().getDensity();
        int topLimitInZoom = ZoomParameterHelper.getInstance().getTopLimitInZoom();
        boolean zIsImeVisible = ZoomInsetsController.getInstance().isImeVisible();
        int imeHeight = ZoomInsetsController.getInstance().getImeHeight();
        rect2.set((int) (-(rect.width() * 0.667f)), topLimitInZoom, screenWidth + ((int) (rect.width() * 0.667f)), (!zIsImeVisible || imeHeight <= 0) ? screenHeight + ((int) (rect.height() * 0.7f)) : (screenHeight - imeHeight) - ZoomUtil.dip2px(density, 24.0f));
        LogD.d("ZoomPointerHandler", "getLimitBoundInZoom bound = " + rect2);
        return rect2;
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private boolean isNotSelfBehavior(BaseBehavior baseBehavior) {
        boolean z;
        synchronized (this.mLock) {
            try {
                z = (baseBehavior == this.mDragBehavior || baseBehavior == this.mFlingBehavior || baseBehavior == this.mAttachBehavior) ? false : true;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    private RectF transferRectToRectF(Rect rect) {
        return new RectF(rect.left, rect.top, rect.right, rect.bottom);
    }

    private boolean willBeFlingOutScreen() {
        return false;
    }

    public void beginDrag(MotionEvent motionEvent, Rect rect) {
        synchronized (this.mLock) {
            try {
                if (this.mDragBehavior == null) {
                    LogD.e("ZoomPointerHandler", "beginDrag mDragBehavior is null");
                    return;
                }
                ZoomUtil.setAnimationThreadUx(true);
                ZoomUtil.setRenderThreadUx(true);
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                this.mIsDragging = true;
                this.mAnimating = true;
                initOrResetVelocityTracker();
                setHookPosition(rawX, rawY, rect);
                this.mDragBehavior.beginDrag(rawX, rawY, rect.left, rect.top);
                this.mVelocityTracker.addMovement(motionEvent);
                LogD.d("ZoomPointerHandler", "beginDrag startRect = " + rect + ", downX = " + rawX + ", downY = " + rawY);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void endDrag(MotionEvent motionEvent, Rect rect, boolean z) {
        float xVelocity;
        float yVelocity;
        synchronized (this.mLock) {
            try {
                if (this.mDragBehavior != null && this.mIsDragging) {
                    this.mIsDragging = false;
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.computeCurrentVelocity(1000, VELOCITY_MAX);
                        xVelocity = this.mVelocityTracker.getXVelocity();
                        yVelocity = this.mVelocityTracker.getYVelocity();
                        this.mVelocityTracker.recycle();
                        this.mVelocityTracker = null;
                    } else {
                        xVelocity = 0.0f;
                        yVelocity = 0.0f;
                    }
                    if (!z) {
                        FlingBehavior flingBehavior = new FlingBehavior(transferRectToRectF(getLimitBoundInZoom(rect)), 1);
                        flingBehavior.setSpringProperty(6.0f, 1.22f);
                        ((BaseBehavior) flingBehavior).mTarget = this.mUIItem;
                        flingBehavior.verifyBodyProperty();
                        this.mFlingBehavior = flingBehavior;
                        DragBehavior dragBehavior = this.mDragBehavior;
                        ZoomPhysicalDragManager$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ZoomPhysicalDragManager$.ExternalSyntheticLambda0(0);
                        externalSyntheticLambda0.f$0 = flingBehavior;
                        VarHandle.storeStoreFence();
                        ((BaseBehavior) dragBehavior).mStopAction = externalSyntheticLambda0;
                        this.mPhysics.addBehavior(flingBehavior);
                        this.mPhysics.addAnimationListener(this, new BaseBehavior[]{this.mFlingBehavior});
                        this.mPhysics.addAnimationUpdateListener(this, new BaseBehavior[]{this.mFlingBehavior});
                    } else if (willBeFlingOutScreen()) {
                        FlingBehavior flingBehavior2 = new FlingBehavior(transferRectToRectF(getExtendBoundInMiniZoom(rect)), 1);
                        flingBehavior2.setSpringProperty(6.0f, 1.22f);
                        ((BaseBehavior) flingBehavior2).mTarget = this.mUIItem;
                        flingBehavior2.verifyBodyProperty();
                        this.mFlingBehavior = flingBehavior2;
                        flingBehavior2.setLinearDamping(0.0f);
                        DragBehavior dragBehavior2 = this.mDragBehavior;
                        FlingBehavior flingBehavior3 = this.mFlingBehavior;
                        Objects.requireNonNull(flingBehavior3);
                        ZoomPhysicalDragManager$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new ZoomPhysicalDragManager$.ExternalSyntheticLambda0(0);
                        externalSyntheticLambda1.f$0 = flingBehavior3;
                        VarHandle.storeStoreFence();
                        ((BaseBehavior) dragBehavior2).mStopAction = externalSyntheticLambda1;
                        this.mPhysics.addBehavior(this.mFlingBehavior);
                        this.mPhysics.addAnimationListener(this, new BaseBehavior[]{this.mFlingBehavior});
                        this.mPhysics.addAnimationUpdateListener(this, new BaseBehavior[]{this.mFlingBehavior});
                    } else {
                        AttachmentBehavior attachmentBehavior = new AttachmentBehavior(transferRectToRectF(getLimitBoundInMiniZoom(rect)));
                        attachmentBehavior.setSpringProperty(6.0f, 1.22f);
                        ((BaseBehavior) attachmentBehavior).mTarget = this.mUIItem;
                        attachmentBehavior.verifyBodyProperty();
                        this.mAttachBehavior = attachmentBehavior;
                        DragBehavior dragBehavior3 = this.mDragBehavior;
                        ZoomPhysicalDragManager$.ExternalSyntheticLambda0 externalSyntheticLambda2 = new ZoomPhysicalDragManager$.ExternalSyntheticLambda0(1);
                        externalSyntheticLambda2.f$0 = attachmentBehavior;
                        VarHandle.storeStoreFence();
                        ((BaseBehavior) dragBehavior3).mStopAction = externalSyntheticLambda2;
                        this.mPhysics.addBehavior(attachmentBehavior);
                        this.mPhysics.addAnimationListener(this, new BaseBehavior[]{this.mAttachBehavior});
                        this.mPhysics.addAnimationUpdateListener(this, new BaseBehavior[]{this.mAttachBehavior});
                    }
                    this.mDragBehavior.endDrag(xVelocity, yVelocity);
                    LogD.d("ZoomPointerHandler", "endDrag xVel = " + xVelocity + ", yVel = " + yVelocity);
                    return;
                }
                LogD.e("ZoomPointerHandler", "endDrag mDragBehavior is null");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Vector getHookPosition() {
        return this.mHookPosition;
    }

    public void initPhysicsWorld(PhysicalAnimatorObserver physicalAnimatorObserver, Rect rect, boolean z) {
        HashMap map;
        AnimationListener animationListener;
        synchronized (this.mLock) {
            try {
                stopPhysicsAnimation();
                if (this.mPhysics == null) {
                    this.mPhysics = PhysicalAnimator.create(this.mContext);
                    this.mUIItem = new UIItem((Object) null);
                }
                UIItem uIItem = this.mUIItem;
                float fWidth = rect.width();
                float fHeight = rect.height();
                uIItem.mWidth = fWidth;
                uIItem.mHeight = fHeight;
                UIItem uIItem2 = this.mUIItem;
                float f = rect.left;
                float f2 = rect.top;
                Vector vector = uIItem2.mStartPosition;
                vector.mX = f;
                vector.mY = f2;
                if (z) {
                    DragBehavior dragBehavior = new DragBehavior();
                    dragBehavior.setSpringProperty(DRAG_CONSTRAINT_FREQUENCY_MINI_ZOOM, DRAG_CONSTRAINT_DAMPING_RATIO_MINI_ZOOM);
                    ((BaseBehavior) dragBehavior).mTarget = this.mUIItem;
                    dragBehavior.verifyBodyProperty();
                    this.mDragBehavior = dragBehavior;
                } else {
                    DragBehavior dragBehavior2 = new DragBehavior();
                    dragBehavior2.setSpringProperty(DRAG_CONSTRAINT_FREQUENCY_ZOOM, 0.7f);
                    ((BaseBehavior) dragBehavior2).mTarget = this.mUIItem;
                    dragBehavior2.verifyBodyProperty();
                    this.mDragBehavior = dragBehavior2;
                }
                this.mObserver = physicalAnimatorObserver;
                this.mPhysics.addBehavior(this.mDragBehavior);
                this.mPhysics.addAnimationListener(this, new BaseBehavior[]{this.mDragBehavior});
                this.mPhysics.addAnimationUpdateListener(this, new BaseBehavior[]{this.mDragBehavior});
                PhysicalAnimator physicalAnimator = this.mPhysics;
                ArraySet arraySet = physicalAnimator.mCurrentRunningBehaviors;
                if (physicalAnimator.mIsCancel) {
                    if (Debug.sDebug) {
                        Debug.logD("restart");
                    }
                    physicalAnimator.mIsCancel = false;
                    if (!physicalAnimator.mIsAnimatorRunning) {
                        physicalAnimator.mChoreographer.scheduleNextFrame();
                        physicalAnimator.mIsAnimatorRunning = true;
                    }
                    for (int i = 0; i < arraySet.getSize(); i++) {
                        BaseBehavior baseBehavior = (BaseBehavior) arraySet.getArray$collection()[i];
                        if (baseBehavior != null && (map = physicalAnimator.mAnimationListeners) != null && (animationListener = (AnimationListener) map.get(baseBehavior)) != null) {
                            animationListener.onAnimationStart(baseBehavior);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public /* bridge */ /* synthetic */ void onAnimationCancel(BaseBehavior baseBehavior) {
    }

    public void onAnimationEnd(BaseBehavior baseBehavior) {
        if (baseBehavior == null || baseBehavior.getTransform() == null) {
            LogD.d("ZoomPointerHandler", "behavior is null");
            return;
        }
        if (!this.mAnimating || isNotSelfBehavior(baseBehavior)) {
            LogD.d("ZoomPointerHandler", "animator has been stopped");
            return;
        }
        if (!(baseBehavior instanceof FlingBehavior) && !(baseBehavior instanceof AttachmentBehavior)) {
            LogD.d("ZoomPointerHandler", "behavior is not FlingBehavior or AttachmentBehavior");
            return;
        }
        Transform transform = baseBehavior.getTransform();
        PhysicalAnimatorObserver physicalAnimatorObserver = this.mObserver;
        if (transform != null && physicalAnimatorObserver != null) {
            physicalAnimatorObserver.onSteady(transform.x, transform.y);
        }
        ZoomUtil.setAnimationThreadUx(false);
        ZoomUtil.setRenderThreadUx(false);
    }

    public /* bridge */ /* synthetic */ void onAnimationStart(BaseBehavior baseBehavior) {
    }

    public void onAnimationUpdate(BaseBehavior baseBehavior) {
        if (baseBehavior == null || baseBehavior.getTransform() == null) {
            LogD.d("ZoomPointerHandler", "behavior is null");
            return;
        }
        if (!this.mAnimating || isNotSelfBehavior(baseBehavior)) {
            LogD.d("ZoomPointerHandler", "animator has been stopped");
            return;
        }
        Transform transform = baseBehavior.getTransform();
        PhysicalAnimatorObserver physicalAnimatorObserver = this.mObserver;
        if (transform == null || physicalAnimatorObserver == null) {
            return;
        }
        physicalAnimatorObserver.onMoving(transform.x, transform.y);
    }

    public void onDrag(MotionEvent motionEvent) {
        synchronized (this.mLock) {
            try {
                if (this.mDragBehavior != null && this.mIsDragging) {
                    this.mDragBehavior.dragTo(motionEvent.getRawX(), motionEvent.getRawY());
                    initVelocityTrackerIfNotExists();
                    this.mVelocityTracker.addMovement(motionEvent);
                    return;
                }
                LogD.e("ZoomPointerHandler", "onDrag mDragBehavior is null");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setHookPosition(float f, float f2, Rect rect) {
        Vector vector = this.mHookPosition;
        float f3 = f - rect.left;
        float f4 = f2 - rect.top;
        vector.mX = f3;
        vector.mY = f4;
    }

    public void stopPhysicsAnimation() {
        synchronized (this.mLock) {
            try {
                PhysicalAnimator physicalAnimator = this.mPhysics;
                if (physicalAnimator == null) {
                    LogD.w("ZoomPointerHandler", "stopPhysicsAnimation mPhysics null");
                    return;
                }
                physicalAnimator.cancel("stop-animator");
                DragBehavior dragBehavior = this.mDragBehavior;
                if (dragBehavior != null) {
                    PhysicalAnimator physicalAnimator2 = this.mPhysics;
                    HashMap map = physicalAnimator2.mAnimationListeners;
                    if (map != null) {
                        map.remove(dragBehavior);
                    }
                    HashMap map2 = physicalAnimator2.mUpdateListeners;
                    if (map2 != null) {
                        map2.remove(dragBehavior);
                    }
                    this.mPhysics.removeBehavior(this.mDragBehavior);
                }
                AttachmentBehavior attachmentBehavior = this.mAttachBehavior;
                if (attachmentBehavior != null) {
                    PhysicalAnimator physicalAnimator3 = this.mPhysics;
                    HashMap map3 = physicalAnimator3.mAnimationListeners;
                    if (map3 != null) {
                        map3.remove(attachmentBehavior);
                    }
                    HashMap map4 = physicalAnimator3.mUpdateListeners;
                    if (map4 != null) {
                        map4.remove(attachmentBehavior);
                    }
                    this.mPhysics.removeBehavior(this.mAttachBehavior);
                }
                FlingBehavior flingBehavior = this.mFlingBehavior;
                if (flingBehavior != null) {
                    PhysicalAnimator physicalAnimator4 = this.mPhysics;
                    HashMap map5 = physicalAnimator4.mAnimationListeners;
                    if (map5 != null) {
                        map5.remove(flingBehavior);
                    }
                    HashMap map6 = physicalAnimator4.mUpdateListeners;
                    if (map6 != null) {
                        map6.remove(flingBehavior);
                    }
                    this.mPhysics.removeBehavior(this.mFlingBehavior);
                }
                this.mAttachBehavior = null;
                this.mFlingBehavior = null;
                this.mDragBehavior = null;
                this.mIsDragging = false;
                this.mAnimating = false;
                this.mObserver = null;
                LogD.d("ZoomPointerHandler", "mPhysics has been stopped");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
