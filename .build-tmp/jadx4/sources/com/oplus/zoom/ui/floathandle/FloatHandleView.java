package com.oplus.zoom.ui.floathandle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.IOplusWindowStateObserver;
import android.view.MotionEvent;
import android.view.OplusWindowManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintSet$;
import com.android.wm.shell.R;
import com.coui.appcompat.slideview.COUISlideDeleteAnimation$;
import com.oplus.anim.EffectiveAnimationView;
import com.oplus.app.OplusWindowInfo;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplus.zoom.common.ZoomDisplayController;
import com.oplus.zoom.common.ZoomUtil;
import com.oplus.zoom.ui.baseview.IView;
import com.oplus.zoom.ui.baseview.ViewHook;
import com.oplus.zoom.ui.common.UiLogUtils;
import com.oplus.zoom.ui.common.UiUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FloatHandleView extends FrameLayout implements IView, View.OnTouchListener {
    private static final float ADJUST_POSITION = 0.5f;
    private static final float DEFAULT_SCALE = 0.5f;
    private static final int FLOAT_HANDLE_INACTIVE_TIME = 50;
    private static final List<String> GAME_BAR_WINDOW_LIST;
    private static final int GAP_BETWEEN_GAME_BAR_AND_FLOAT_HANDLE = 3;
    private static final float PATH_INTERPOLATOR_X1 = 0.3f;
    private static final float PATH_INTERPOLATOR_X2 = 0.1f;
    private static final float PATH_INTERPOLATOR_Y1 = 0.0f;
    private static final float PATH_INTERPOLATOR_Y2 = 1.0f;
    private static final int ROTATION_ARROW = 180;
    private static final String TAG = "FloatHandle";
    private boolean mArrowStyle;
    private EffectiveAnimationView mArrowView;
    private Context mContext;
    private int mCurrentMode;
    private int mCurrentSide;
    private View mFloatHandleView;
    private FrameLayout mFloatHandleViewContainer;
    private Rect mGameFloatBarFrame;
    private Handler mHandler;
    private ViewHook mHook;
    private ImageView mIconSwapView;
    private Animator mIconTransactionAnimator;
    private ImageView mIconView;
    private OplusWindowManager mOplusWindowManager;
    private IOplusWindowStateObserver mOplusWindowStateObserver;
    private Animator mPanAndReboundAnimator;
    private ValueAnimator mPanAnimator;
    private String mPendingLockPkg;
    private int mPendingMode;
    private int mPendingUserId;
    private ValueAnimator mReboundAnimator;
    private boolean mRemoving;
    private String mRemovingLockPkg;
    private int mRemovingUserId;
    private int mRotation;
    private FloatHandleStatus mStatus;
    private FloatHandleTouchHandler mTouchHandler;
    private boolean mVisible;
    private WindowManager.LayoutParams mWindowParams;

    static {
        ArrayList arrayList = new ArrayList();
        GAME_BAR_WINDOW_LIST = arrayList;
        arrayList.add("GameFloatBarView");
        arrayList.add("GamesFloatBar");
    }

    public FloatHandleView(Context context, int i, FloatHandleStatus floatHandleStatus, FloatHandleViewManager floatHandleViewManager) {
        super(context);
        this.mRemoving = false;
        this.mArrowStyle = true;
        this.mVisible = false;
        this.mGameFloatBarFrame = new Rect();
        this.mContext = context;
        this.mStatus = floatHandleStatus;
        this.mRotation = ZoomDisplayController.getInstance().getRotation();
        this.mTouchHandler = new FloatHandleTouchHandler(context, floatHandleViewManager, this, floatHandleStatus, i);
        this.mOplusWindowManager = new OplusWindowManager();
        initView(context, i);
        initWindowParams();
    }

    private boolean checkCurrentPositionMatchOrNot(int i) {
        int maxDistanceToScreenInNone;
        int i2 = this.mCurrentSide;
        if (i2 == 1) {
            maxDistanceToScreenInNone = -this.mWindowParams.x;
        } else {
            maxDistanceToScreenInNone = i2 == 2 ? this.mStatus.getMaxDistanceToScreenInNone() - (this.mStatus.getScreenWidth() - this.mWindowParams.x) : 0;
        }
        boolean z = i != 4 || maxDistanceToScreenInNone == this.mStatus.getMaxDistanceToScreenInHalfHidden();
        if (i == 2 && maxDistanceToScreenInNone != this.mStatus.getDistanceToRebound()) {
            z = false;
        }
        if (i != 1 || maxDistanceToScreenInNone == this.mStatus.getMaxDistanceToScreenInNone()) {
            return z;
        }
        return false;
    }

    private boolean checkForReboundAnimationNeedOrNot(int i, int i2) {
        boolean z = i2 == 2 && this.mArrowStyle;
        if (i2 != 4 || this.mArrowStyle) {
            return z;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0023  */
    private boolean checkStateBeforeNextAction(int i) {
        Animator animator = this.mPanAndReboundAnimator;
        boolean z = false;
        if (animator == null || !animator.isRunning()) {
            int i2 = this.mCurrentMode;
            if (i2 != i || !checkCurrentPositionMatchOrNot(i2)) {
                z = true;
            } else if (this.mCurrentMode == 2) {
                postDelayMsgToHalfHiddenState();
            }
        } else if (i != this.mPendingMode) {
            z = true;
        }
        if (z) {
            stopAllAnimator();
            clearMessage();
        }
        return z;
    }

    private int[] computeCurrentPosition(int i) {
        int distanceToRebound;
        int screenWidth;
        float scale;
        int containerHeight;
        int[] iArr = new int[2];
        if (i == 4) {
            distanceToRebound = this.mStatus.getMaxDistanceToScreenInHalfHidden();
        } else {
            FloatHandleStatus floatHandleStatus = this.mStatus;
            distanceToRebound = i == 2 ? floatHandleStatus.getDistanceToRebound() : floatHandleStatus.getMaxDistanceToScreenInNone();
        }
        int i2 = this.mCurrentSide;
        FloatHandleStatus floatHandleStatus2 = this.mStatus;
        if (i2 == 1) {
            screenWidth = -distanceToRebound;
            floatHandleStatus2.getScale(1);
            scale = this.mStatus.getScale(1) * this.mStatus.getScreenHeight();
            containerHeight = this.mStatus.getContainerHeight() / 2;
        } else {
            screenWidth = floatHandleStatus2.getScreenWidth() - (this.mStatus.getMaxDistanceToScreenInNone() - distanceToRebound);
            this.mStatus.getScale(2);
            scale = this.mStatus.getScale(2) * this.mStatus.getScreenHeight();
            containerHeight = this.mStatus.getContainerHeight() / 2;
        }
        int iMax = Math.max(Math.min((int) (scale - containerHeight), (this.mStatus.getScreenHeight() - this.mStatus.getMovingEdgeLimit()) - this.mStatus.getContainerHeight()), this.mStatus.getMovingEdgeLimit());
        iArr[0] = screenWidth;
        iArr[1] = iMax;
        Rect gameFloatBarFrame = getGameFloatBarFrame();
        this.mGameFloatBarFrame = gameFloatBarFrame;
        if (!gameFloatBarFrame.isEmpty()) {
            Rect rect = this.mGameFloatBarFrame;
            if (rect.bottom > iArr[1] && isSameSide(iArr[0], rect.left)) {
                iArr[1] = ZoomUtil.dip2px(this.mContext, 3.0f) + this.mGameFloatBarFrame.bottom;
            }
        }
        return iArr;
    }

    private Rect getGameFloatBarFrame() {
        try {
            if (!this.mGameFloatBarFrame.isEmpty()) {
                UiLogUtils.i("FloatHandle", "existing game float bar window frame is: " + this.mGameFloatBarFrame);
                return this.mGameFloatBarFrame;
            }
            List allVisibleWindowInfo = new OplusWindowManager().getAllVisibleWindowInfo();
            if (allVisibleWindowInfo != null) {
                for (int i = 0; i < allVisibleWindowInfo.size(); i++) {
                    OplusWindowInfo oplusWindowInfo = (OplusWindowInfo) allVisibleWindowInfo.get(i);
                    if (oplusWindowInfo != null && GAME_BAR_WINDOW_LIST.contains(oplusWindowInfo.windowName)) {
                        Rect rect = oplusWindowInfo.mFrame;
                        UiLogUtils.i("FloatHandle", "the game float bar window frame is: " + rect);
                        return rect;
                    }
                }
            }
            return new Rect();
        } catch (RemoteException e) {
            UiLogUtils.e("FloatHandle", "getGameFloatBarFrame RemoteException: ", e);
        }
    }

    private void initView(Context context, int i) {
        if (i == 1) {
            FrameLayout.inflate(context, R.layout.zoom_float_handle_view_left, this);
            this.mFloatHandleViewContainer = (FrameLayout) findViewById(R.id.zoom_float_handle_view_container_left);
            this.mFloatHandleView = findViewById(R.id.zoom_float_handle_view_left);
            this.mIconView = (ImageView) findViewById(R.id.zoom_app_icon_left);
            this.mIconSwapView = (ImageView) findViewById(R.id.zoom_app_icon_swap_left);
            EffectiveAnimationView effectiveAnimationViewFindViewById = findViewById(R.id.zoom_float_handle_arrow_left);
            this.mArrowView = effectiveAnimationViewFindViewById;
            effectiveAnimationViewFindViewById.setRotation(180.0f);
        } else {
            FrameLayout.inflate(context, R.layout.zoom_float_handle_view_right, this);
            this.mFloatHandleViewContainer = (FrameLayout) findViewById(R.id.zoom_float_handle_view_container_right);
            this.mFloatHandleView = findViewById(R.id.zoom_float_handle_view_right);
            this.mIconView = (ImageView) findViewById(R.id.zoom_app_icon_right);
            this.mIconSwapView = (ImageView) findViewById(R.id.zoom_app_icon_swap_right);
            this.mArrowView = findViewById(R.id.zoom_float_handle_arrow_right);
        }
        this.mArrowView.setAnimation(R.raw.float_handle_arrow);
        updateArrowStyle(true);
        this.mIconSwapView.setVisibility(4);
        this.mIconView.setVisibility(0);
        this.mArrowView.setVisibility(0);
        this.mCurrentSide = i;
        setOnTouchListener(this);
        this.mHandler = new UiHandler(this);
        this.mOplusWindowStateObserver = new 1(this);
    }

    private void initWindowParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2314);
        this.mWindowParams = layoutParams;
        layoutParams.flags |= 16778024;
        layoutParams.privateFlags = 16;
        layoutParams.setFitInsetsTypes(0);
        WindowManager.LayoutParams layoutParams2 = this.mWindowParams;
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.gravity = 8388659;
        layoutParams2.setTitle(IView.FLOAT_HANDLE_VIEW_TITLE);
        this.mWindowParams.format = 1;
    }

    private boolean isSameSide(int i, int i2) {
        boolean z = i <= 0 && i2 <= 0;
        boolean z2 = i > 0 && i2 > 0;
        UiLogUtils.d("FloatHandle", "isSameSide isLeft : " + z + " isRight : " + z2);
        return z || z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createAnimatorForRebound$0(ValueAnimator valueAnimator) {
        if (isAttachedToWindow()) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            WindowManager.LayoutParams layoutParams = this.mWindowParams;
            int i = layoutParams.x;
            updateCurrentPosition((int) (this.mCurrentSide == 1 ? fFloatValue + 0.5f : fFloatValue - 0.5f), layoutParams.y);
            relayout();
        }
    }

    private void registerWindowStateObserver() {
        try {
            UiLogUtils.d("FloatHandle", "registering window state observer");
            this.mOplusWindowManager.registerOplusWindowStateObserver(this.mOplusWindowStateObserver);
        } catch (Exception e) {
            UiLogUtils.e("FloatHandle", "Error registering window state observer: ", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemGestureExclusion() {
        if (this.mWindowParams != null) {
            ArrayList arrayList = new ArrayList();
            Rect rect = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
            UiLogUtils.d("FloatHandle", "setSystemGestureExclusion: rect = " + rect);
            arrayList.add(rect);
            try {
                setSystemGestureExclusionRects(arrayList);
            } catch (Exception e) {
                UiLogUtils.e("FloatHandle", "setSystemGestureExclusion", e);
            }
        }
    }

    private void stopAllAnimator() {
        Animator animator = this.mPanAndReboundAnimator;
        if (animator == null || !animator.isRunning()) {
            return;
        }
        this.mPanAndReboundAnimator.cancel();
        UiLogUtils.d("FloatHandle", "animator with pan is cancel");
    }

    private void unregisterWindowStateObserver() {
        try {
            UiLogUtils.d("FloatHandle", "unregistering window state observer");
            this.mOplusWindowManager.unregisterOplusWindowStateObserver(this.mOplusWindowStateObserver);
        } catch (Exception e) {
            UiLogUtils.e("FloatHandle", "Error unregistering window state observer: ", e);
        }
    }

    @Override // com.oplus.zoom.ui.baseview.IView
    public void animAdd(AnimatorListenerAdapter animatorListenerAdapter) {
        showFloatHandleViewWithAnim(this.mPendingMode, this.mPendingLockPkg, this.mPendingUserId);
    }

    public void avoidOverlappingBetweenGameBarAndFloatHandleAfterScroll() {
        if (this.mGameFloatBarFrame.isEmpty()) {
            this.mGameFloatBarFrame = getGameFloatBarFrame();
        }
        if (this.mGameFloatBarFrame.isEmpty()) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        int i = layoutParams.y;
        Rect rect = this.mGameFloatBarFrame;
        if (i >= rect.bottom || !isSameSide(layoutParams.x, rect.left)) {
            return;
        }
        this.mWindowParams.y = ZoomUtil.dip2px(this.mContext, 3.0f) + this.mGameFloatBarFrame.bottom;
        FloatHandleTouchHandler floatHandleTouchHandler = this.mTouchHandler;
        WindowManager.LayoutParams layoutParams2 = this.mWindowParams;
        floatHandleTouchHandler.setCurrentPosition(layoutParams2.x, layoutParams2.y);
        relayout();
        UiLogUtils.i("FloatHandle", "avoid overlapping, relayout ZoomFloatHandleView with params: " + this.mWindowParams.y + "-" + this.mWindowParams.x);
    }

    public void clearMessage() {
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(2);
    }

    public Animator createAnimatorForIconTransaction(String str, int i, String str2, int i2) {
        Drawable appDrawable = UiUtil.getAppDrawable(this.mContext, str, i);
        Drawable appDrawable2 = UiUtil.getAppDrawable(this.mContext, str2, i2);
        if (appDrawable != null && appDrawable2 != null) {
            ImageView imageView = this.mIconSwapView;
            ImageView imageView2 = this.mIconView;
            Animator newIconEnterAnimation = FloatHandleAnimationUtil.getNewIconEnterAnimation(imageView);
            Animator originIconDisappearAnimation = FloatHandleAnimationUtil.getOriginIconDisappearAnimation(imageView2);
            newIconEnterAnimation.setStartDelay(167L);
            newIconEnterAnimation.addListener(new 5(this, imageView, imageView2, appDrawable, appDrawable2, str));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(newIconEnterAnimation).with(originIconDisappearAnimation);
            return animatorSet;
        }
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(i, "load icon failed, newPkg = ", str, " userId = ", " , oriPkg = ");
        sbM.append(str2);
        sbM.append(" userId = ");
        sbM.append(i2);
        UiLogUtils.w("FloatHandle", sbM.toString());
        if (this.mHook != null) {
            stopAllAnimator();
            clearMessage();
            this.mStatus.setCurrentLockPkg(null);
            this.mHook.removeViewOnCallBack();
        }
        return null;
    }

    public ValueAnimator createAnimatorForPan(int i, int i2, boolean z) {
        ValueAnimator panAnimation = FloatHandleAnimationUtil.getPanAnimation(this.mStatus, this.mWindowParams.x, this.mCurrentSide, i, i2, z);
        panAnimation.addUpdateListener(new 3(this));
        panAnimation.addListener(new 4(this, i2, z));
        UiLogUtils.d("FloatHandle", "createAnimatorForPan successful");
        return panAnimation;
    }

    public Animator createAnimatorForPanAndRebound(int i, int i2) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (!checkForReboundAnimationNeedOrNot(i, i2)) {
            ValueAnimator valueAnimatorCreateAnimatorForPan = createAnimatorForPan(i, i2, false);
            this.mPanAnimator = valueAnimatorCreateAnimatorForPan;
            animatorSet.play(valueAnimatorCreateAnimatorForPan);
            return animatorSet;
        }
        this.mPanAnimator = createAnimatorForPan(i, i2, true);
        this.mReboundAnimator = createAnimatorForRebound(i, i2);
        if (i != 1 || i2 != 2) {
            animatorSet.play(this.mPanAnimator).before(this.mReboundAnimator);
            return animatorSet;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(550L);
        COUISlideDeleteAnimation$.ExternalSyntheticOutline0.m(PATH_INTERPOLATOR_X1, 0.0f, PATH_INTERPOLATOR_X2, 1.0f, objectAnimatorOfFloat);
        animatorSet.play(this.mPanAnimator).with(objectAnimatorOfFloat).before(this.mReboundAnimator);
        return animatorSet;
    }

    public ValueAnimator createAnimatorForRebound(int i, int i2) {
        boolean z = !this.mArrowStyle;
        ValueAnimator reboundAnimation = FloatHandleAnimationUtil.getReboundAnimation(this.mStatus, this.mWindowParams.x, this.mCurrentSide, i, i2, z);
        FloatHandleView$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new FloatHandleView$.ExternalSyntheticLambda1();
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        reboundAnimation.addUpdateListener(externalSyntheticLambda1);
        reboundAnimation.addListener(new 2(this, z));
        UiLogUtils.d("FloatHandle", "createAnimatorForRebound successful");
        return reboundAnimation;
    }

    @Override // com.oplus.zoom.ui.baseview.IView
    public View getView() {
        return this;
    }

    @Override // com.oplus.zoom.ui.baseview.IView
    public WindowManager.LayoutParams getWindowParams() {
        return this.mWindowParams;
    }

    public boolean initState(FloatHandleInfo floatHandleInfo, int i, int i2) {
        boolean zUpdateIconStyle;
        String currentLockPkg = this.mStatus.getCurrentLockPkg();
        int currentUserId = this.mStatus.getCurrentUserId();
        String str = floatHandleInfo.packageName;
        int i3 = floatHandleInfo.userId;
        if (currentLockPkg == null || (currentLockPkg.equals(str) && currentUserId == i3)) {
            this.mStatus.setCurrentLockPkg(str);
            this.mStatus.setCurrentUserId(i3);
            this.mPendingLockPkg = null;
            this.mPendingUserId = 0;
            zUpdateIconStyle = updateIconStyle(str, i3);
        } else {
            this.mPendingLockPkg = str;
            this.mPendingUserId = i3;
            zUpdateIconStyle = updateIconStyle(currentLockPkg, currentUserId);
        }
        int[] iArrComputeCurrentPosition = computeCurrentPosition(i);
        updateCurrentPosition(iArrComputeCurrentPosition[0], iArrComputeCurrentPosition[1]);
        this.mCurrentMode = i;
        this.mPendingMode = i2;
        UiLogUtils.i("FloatHandle", "initPositionAndMode: mWindowParams.x = " + this.mWindowParams.x + ", mWindowParams.y = " + this.mWindowParams.y);
        return zUpdateIconStyle;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewHook viewHook = this.mHook;
        if (viewHook != null) {
            viewHook.onAttachedToWindow();
        }
        FloatHandleView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleView$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        post(externalSyntheticLambda0);
        registerWindowStateObserver();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewHook viewHook = this.mHook;
        if (viewHook != null) {
            viewHook.onDetachedFromWindow();
            this.mHook = null;
        }
        FloatHandleView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FloatHandleView$.ExternalSyntheticLambda0();
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        removeCallbacks(externalSyntheticLambda0);
        unregisterWindowStateObserver();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.mRemoving && this.mPendingMode != 1) {
            this.mTouchHandler.processTouchEvent(motionEvent);
        }
        return true;
    }

    public void postDelayMsgToHalfHiddenState() {
        if (this.mTouchHandler.isTouching() || this.mRemoving) {
            UiLogUtils.d("FloatHandle", "float handle view is touching or removing");
            return;
        }
        UiLogUtils.d("FloatHandle", "postDelayMsgToHalfHiddenState, float handle view will go inactive after 1 second");
        this.mHandler.removeMessages(1);
        this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1), 50L);
    }

    public void prepareIconStateBeforeTransaction(ImageView imageView, ImageView imageView2, Drawable drawable, Drawable drawable2) {
        imageView.setImageDrawable(drawable);
        imageView2.setImageDrawable(drawable2);
        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.5f);
        imageView.setScaleY(0.5f);
        imageView.setVisibility(0);
    }

    public void refreshFloatHandleView(String str, int i) {
        ViewHook viewHook;
        if (this.mRemoving && (viewHook = this.mHook) != null) {
            viewHook.removeViewOnCallBack();
            return;
        }
        stopAllAnimator();
        clearMessage();
        int i2 = this.mCurrentMode;
        this.mPendingMode = 8;
        this.mCurrentMode = 1;
        this.mRotation = ZoomDisplayController.getInstance().getRotation();
        this.mGameFloatBarFrame.setEmpty();
        int[] iArrComputeCurrentPosition = computeCurrentPosition(1);
        updateCurrentPosition(iArrComputeCurrentPosition[0], iArrComputeCurrentPosition[1]);
        updateArrowStyle(true);
        showFloatHandleViewWithAnim(i2, str, i);
        relayout();
    }

    public void relayout() {
        boolean z = true;
        if ((this.mCurrentSide != 1 || Math.abs(this.mWindowParams.x) < this.mStatus.getMaxDistanceToScreenInNone()) && (this.mCurrentSide == 2 || this.mWindowParams.x > this.mStatus.getScreenWidth())) {
            z = false;
        }
        if (this.mVisible != z) {
            setVisibility(z ? 0 : 4);
            this.mVisible = z;
            UiLogUtils.d("FloatHandle", "setVisibility:" + z);
        }
        ViewHook viewHook = this.mHook;
        if (viewHook != null) {
            viewHook.relayout();
        }
    }

    public void removeFloatHandleImmediately() {
        stopAllAnimator();
        clearMessage();
        ViewHook viewHook = this.mHook;
        if (viewHook != null) {
            viewHook.removeViewOnCallBack();
        }
    }

    public void removeFloatHandleViewWithAnim(boolean z) {
        this.mRemoving = true;
        clearMessage();
        stopAllAnimator();
        ValueAnimator panAnimation = FloatHandleAnimationUtil.getPanAnimation(this.mStatus, this.mWindowParams.x, this.mCurrentSide, this.mCurrentMode, 1, false);
        this.mPanAnimator = panAnimation;
        panAnimation.addUpdateListener(new 6(this));
        this.mPanAnimator.addListener(new 7(this, z));
        ValueAnimator valueAnimator = this.mPanAnimator;
        this.mPanAndReboundAnimator = valueAnimator;
        valueAnimator.start();
    }

    public void restoreIconStateAfterTransaction(ImageView imageView, ImageView imageView2, Drawable drawable) {
        imageView2.setImageDrawable(drawable);
        imageView2.setScaleX(1.0f);
        imageView2.setScaleY(1.0f);
        imageView2.setAlpha(1.0f);
        imageView.setVisibility(4);
    }

    @Override // com.oplus.zoom.ui.baseview.IView
    public void setHook(ViewHook viewHook) {
        this.mHook = viewHook;
    }

    public void showFloatHandleViewWithAnim(int i, String str, int i2) {
        String str2;
        String currentLockPkg = this.mStatus.getCurrentLockPkg();
        int currentUserId = this.mStatus.getCurrentUserId();
        if (currentLockPkg == null && (str2 = this.mRemovingLockPkg) != null) {
            this.mStatus.setCurrentLockPkg(str2);
            this.mStatus.setCurrentUserId(this.mRemovingUserId);
            currentLockPkg = this.mRemovingLockPkg;
            currentUserId = this.mRemovingUserId;
        }
        if (currentLockPkg == null && this.mHook != null) {
            UiLogUtils.d("FloatHandle", "something is wrong, just remove the view");
            stopAllAnimator();
            clearMessage();
            this.mHook.removeViewOnCallBack();
            return;
        }
        boolean zCheckStateBeforeNextAction = checkStateBeforeNextAction(i);
        if (!TextUtils.isEmpty(currentLockPkg) && !TextUtils.isEmpty(str) && (!currentLockPkg.equals(str) || currentUserId != i2)) {
            UiLogUtils.d("FloatHandle", "pkg is changed, need to startAnimationForIconTransaction");
            this.mPendingLockPkg = str;
            this.mPendingUserId = i2;
            int[] iArrComputeCurrentPosition = computeCurrentPosition(1);
            updateCurrentPosition(iArrComputeCurrentPosition[0], iArrComputeCurrentPosition[1]);
            ValueAnimator valueAnimator = this.mPanAnimator;
            if (valueAnimator != null && !valueAnimator.isRunning() && !zCheckStateBeforeNextAction) {
                startAnimationForIconTransaction();
            }
        }
        StringBuilder sbM = ConstraintSet$.ExternalSyntheticOutline0.m(currentUserId, "current pkg = ", currentLockPkg, " current userId = ", " , new pkg = ");
        sbM.append(str);
        sbM.append(" new userId = ");
        sbM.append(i2);
        UiLogUtils.d("FloatHandle", sbM.toString());
        if (zCheckStateBeforeNextAction) {
            Animator animatorCreateAnimatorForPanAndRebound = createAnimatorForPanAndRebound(this.mCurrentMode, i);
            this.mPanAndReboundAnimator = animatorCreateAnimatorForPanAndRebound;
            animatorCreateAnimatorForPanAndRebound.start();
            UiLogUtils.d("FloatHandle", "animator with pan to " + i + " is ready, now start the animator");
        }
    }

    public void startAnimationForIconTransaction() {
        UiLogUtils.i("FloatHandle", "startAnimationForIconTransaction begin");
        Animator animator = this.mIconTransactionAnimator;
        if (animator != null && animator.isRunning()) {
            this.mIconTransactionAnimator.cancel();
        }
        Animator animatorCreateAnimatorForIconTransaction = createAnimatorForIconTransaction(this.mPendingLockPkg, this.mPendingUserId, this.mStatus.getCurrentLockPkg(), this.mStatus.getCurrentUserId());
        this.mIconTransactionAnimator = animatorCreateAnimatorForIconTransaction;
        if (animatorCreateAnimatorForIconTransaction != null) {
            animatorCreateAnimatorForIconTransaction.start();
        }
    }

    public void updateArrowStyle(boolean z) {
        this.mArrowView.setMinAndMaxFrame(0, 59);
        EffectiveAnimationView effectiveAnimationView = this.mArrowView;
        if (z) {
            effectiveAnimationView.setFrame(59);
            this.mArrowStyle = true;
        } else {
            effectiveAnimationView.setFrame(27);
            this.mArrowStyle = false;
        }
    }

    public void updateCurrentPosition(float f, float f2) {
        int movingEdgeLimit = this.mStatus.getMovingEdgeLimit();
        int screenHeight = (this.mStatus.getScreenHeight() - this.mStatus.getMovingEdgeLimit()) - this.mStatus.getContainerHeight();
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.x = (int) f;
        layoutParams.y = Math.max(Math.min((int) f2, screenHeight), movingEdgeLimit);
        FloatHandleTouchHandler floatHandleTouchHandler = this.mTouchHandler;
        WindowManager.LayoutParams layoutParams2 = this.mWindowParams;
        floatHandleTouchHandler.setCurrentPosition(layoutParams2.x, layoutParams2.y);
    }

    public boolean updateIconStyle(String str, int i) {
        Drawable appDrawable = UiUtil.getAppDrawable(this.mContext, str, i);
        if (appDrawable == null) {
            UiLogUtils.w("FloatHandle", "load icon failed, pkg = " + str);
            return false;
        }
        this.mIconView.setImageDrawable(appDrawable);
        this.mIconView.setContentDescription(str + "/" + i);
        StringBuilder sb = new StringBuilder(" pkg = ");
        sb.append(str);
        sb.append(" is show in float handle now");
        UiLogUtils.d("FloatHandle", sb.toString());
        return true;
    }
}
