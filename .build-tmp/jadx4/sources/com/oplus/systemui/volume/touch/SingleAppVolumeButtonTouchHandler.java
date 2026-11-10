package com.oplus.systemui.volume.touch;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ImageButton;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.volume.OplusSingleAppVolumeButtonViewController;
import com.oplus.systemui.volume.OplusSingleAppVolumeButtonViewController$createDragCallback$1;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.anim.SingleAppVolumeButtonAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAppVolumeButtonTouchHandler {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SingleAppVolumeButtonTouchHandler";
    private final SingleAppVolumeButtonAnimationController animationController;
    private DragCallback dragCallback;
    public boolean inTouchSequence;
    public boolean isDragging;
    public final int touchSlop;
    public final int velocityTimeUnit;
    private VelocityTracker velocityTracker;
    public float viewStartX;
    public float viewStartY;
    public boolean enabled = true;
    private final PointF downPoint = new PointF();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface DragCallback {
    }

    public SingleAppVolumeButtonTouchHandler(int i, int i2, SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController) {
        this.touchSlop = i;
        this.velocityTimeUnit = i2;
        this.animationController = singleAppVolumeButtonAnimationController;
    }

    public final void cleanup() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.velocityTracker = null;
        this.isDragging = false;
    }

    public final DragCallback getDragCallback() {
        return this.dragCallback;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ImageButton imageButton;
        DragCallback dragCallback;
        if (this.enabled) {
            VelocityTracker velocityTracker = this.velocityTracker;
            if (velocityTracker != null) {
                float rawX = motionEvent.getRawX() - motionEvent.getX();
                float rawY = motionEvent.getRawY() - motionEvent.getY();
                motionEvent.offsetLocation(rawX, rawY);
                velocityTracker.addMovement(motionEvent);
                motionEvent.offsetLocation(-rawX, -rawY);
            }
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1) {
                    if (this.isDragging) {
                        VelocityTracker velocityTracker2 = this.velocityTracker;
                        if (velocityTracker2 != null) {
                            velocityTracker2.computeCurrentVelocity(this.velocityTimeUnit);
                        }
                        VelocityTracker velocityTracker3 = this.velocityTracker;
                        float xVelocity = velocityTracker3 != null ? velocityTracker3.getXVelocity() : 0.0f;
                        VelocityTracker velocityTracker4 = this.velocityTracker;
                        float yVelocity = velocityTracker4 != null ? velocityTracker4.getYVelocity() : 0.0f;
                        DragCallback dragCallback2 = this.dragCallback;
                        if (dragCallback2 != null) {
                            ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback2).onDragEnd(xVelocity, yVelocity);
                        }
                    } else {
                        DragCallback dragCallback3 = this.dragCallback;
                        if (dragCallback3 != null && (imageButton = ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback3).this$0.buttonView) != null) {
                            imageButton.performClick();
                        }
                    }
                    DragCallback dragCallback4 = this.dragCallback;
                    if (dragCallback4 != null) {
                        ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback4).onUp();
                    }
                    this.inTouchSequence = false;
                    cleanup();
                    return true;
                }
                if (actionMasked == 2) {
                    float rawX2 = motionEvent.getRawX() - this.downPoint.x;
                    float rawY2 = motionEvent.getRawY() - this.downPoint.y;
                    float fHypot = (float) Math.hypot(rawX2, rawY2);
                    if (!this.isDragging && fHypot > this.touchSlop) {
                        this.isDragging = true;
                        if (this.dragCallback != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->onDragStart");
                        }
                    }
                    if (this.isDragging) {
                        float f = this.viewStartX + rawX2;
                        float f2 = this.viewStartY + rawY2;
                        DragCallback dragCallback5 = this.dragCallback;
                        if (dragCallback5 != null) {
                            OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController = ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback5).this$0;
                            Rect rect = oplusSingleAppVolumeButtonViewController.draggableBounds;
                            float f3 = (rect == null ? null : rect).left;
                            if (rect == null) {
                                rect = null;
                            }
                            float fCoerceIn = RangesKt___RangesKt.coerceIn(f, f3, rect.right);
                            Rect rect2 = oplusSingleAppVolumeButtonViewController.draggableBounds;
                            PointF pointF = new PointF(fCoerceIn, RangesKt___RangesKt.coerceIn(f2, (rect2 == null ? null : rect2).top, (rect2 != null ? rect2 : null).bottom));
                            SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController = oplusSingleAppVolumeButtonViewController.animationController;
                            if (singleAppVolumeButtonAnimationController != null) {
                                singleAppVolumeButtonAnimationController.moveTo(pointF.x, pointF.y);
                                return true;
                            }
                        }
                    }
                } else if (actionMasked == 3) {
                    if (this.isDragging && (dragCallback = this.dragCallback) != null) {
                        ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback).onDragEnd(0.0f, 0.0f);
                    }
                    DragCallback dragCallback6 = this.dragCallback;
                    if (dragCallback6 != null) {
                        ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback6).onUp();
                    }
                    this.inTouchSequence = false;
                    cleanup();
                    return true;
                }
            } else {
                this.animationController.cancelAll();
                VelocityTracker velocityTracker5 = this.velocityTracker;
                if (velocityTracker5 != null) {
                    velocityTracker5.recycle();
                }
                this.velocityTracker = VelocityTracker.obtain();
                this.downPoint.set(motionEvent.getRawX(), motionEvent.getRawY());
                this.viewStartX = this.animationController.getCurrentX();
                this.viewStartY = this.animationController.getCurrentY();
                this.inTouchSequence = true;
                this.isDragging = false;
                DragCallback dragCallback7 = this.dragCallback;
                if (dragCallback7 != null) {
                    OplusSingleAppVolumeButtonViewController oplusSingleAppVolumeButtonViewController2 = ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback7).this$0;
                    oplusSingleAppVolumeButtonViewController2.setStatusBarExpandable(false);
                    OplusSingleAppVolumeButtonViewController.SingleAppVolumeButtonCallback singleAppVolumeButtonCallback = oplusSingleAppVolumeButtonViewController2.callback;
                    if (singleAppVolumeButtonCallback != null) {
                        ((OplusVolumeDialogImpl.AnonymousClass2) singleAppVolumeButtonCallback).this$0.mHandler.removeMessages(2);
                    }
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                        Log.d("SystemUi--Volume", "SingleAppVolumeButtonVC-->onDown");
                    }
                }
            }
        } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) {
            this.inTouchSequence = false;
            cleanup();
            return true;
        }
        return true;
    }

    public final void release() {
        cleanup();
        this.dragCallback = null;
    }

    public final void setDragCallback(DragCallback dragCallback) {
        this.dragCallback = dragCallback;
    }

    public final void setEnabled(boolean z) {
        DragCallback dragCallback;
        if (this.enabled == z) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setEnabled: ", "SingleAppVolumeButtonTouchHandler-->", "SystemUi--Volume", z);
        }
        this.enabled = z;
        if (z) {
            return;
        }
        if ((this.inTouchSequence || this.isDragging) && (dragCallback = this.dragCallback) != null) {
            ((OplusSingleAppVolumeButtonViewController$createDragCallback$1) dragCallback).onUp();
        }
        this.inTouchSequence = false;
        cleanup();
    }
}
