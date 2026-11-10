package com.oplus.systemui.util;

import android.content.Context;
import android.os.SystemProperties;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.dynamicanimation.animation.FloatValueHolder;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKgdSwipeUpTouchProgressTracker {
    private static final Companion Companion = new Companion();
    private static final String TAG = "OplusKgdSwipeUpTouchProgressTracker";
    public boolean active;
    private final Context context;
    public float currentProgress;
    public float currentVel;
    public float maxDistance;
    private final Function2 onProgressChanged;
    private final FloatValueHolder progressHolder;
    private final COUISpringAnimation progressSpring;
    public float touchDownY;
    private VelocityTracker velocityTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusKgdSwipeUpTouchProgressTracker(Context context, Function2 function2) {
        this.context = context;
        this.onProgressChanged = function2;
        FloatValueHolder floatValueHolder = new FloatValueHolder(0.0f);
        this.progressHolder = floatValueHolder;
        float f = SystemProperties.getInt("debug.oneshot.swipe.response", 15) / 100.0f;
        float f2 = SystemProperties.getInt("debug.oneshot.swipe.bounce", 15) / 100.0f;
        float f3 = SystemProperties.getInt("debug.oneshot.swipe.minstep", 1) / 1000.0f;
        SystemProperties.getInt("debug.oneshot.swipe.fastfractor", 120);
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(floatValueHolder);
        cOUISpringAnimation.setSpring(new COUISpringForce().setResponse(f).setBounce(f2));
        cOUISpringAnimation.setMinimumVisibleChange(f3);
        OplusKgdSwipeUpTouchProgressTracker$progressSpring$1$1 oplusKgdSwipeUpTouchProgressTracker$progressSpring$1$1 = new OplusKgdSwipeUpTouchProgressTracker$progressSpring$1$1();
        oplusKgdSwipeUpTouchProgressTracker$progressSpring$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(oplusKgdSwipeUpTouchProgressTracker$progressSpring$1$1);
        OplusKgdSwipeUpTouchProgressTracker$progressSpring$1$2 oplusKgdSwipeUpTouchProgressTracker$progressSpring$1$2 = new OplusKgdSwipeUpTouchProgressTracker$progressSpring$1$2();
        oplusKgdSwipeUpTouchProgressTracker$progressSpring$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(oplusKgdSwipeUpTouchProgressTracker$progressSpring$1$2);
        this.progressSpring = cOUISpringAnimation;
    }

    public static float computeEasedProgressFromY(float f, float f2, float f3) {
        float f4 = f - f2;
        if (f3 <= 0.0f || f4 <= 0.0f) {
            return 0.0f;
        }
        float f5 = f4 / f3;
        if (f5 >= 1.0f) {
            return 1.0f;
        }
        return (float) Math.sin(RangesKt___RangesKt.coerceIn(f5, 0.0f, 1.0f) * 1.5707964f);
    }

    public final void dispatchProgress(float f, float f2) {
        this.currentProgress = f;
        Function2 function2 = this.onProgressChanged;
        if (function2 != null) {
            function2.invoke(Float.valueOf(f), Float.valueOf(f2));
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x005a  */
    /* JADX WARN: Code duplicated, block: B:38:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:44:0x0084  */
    /* JADX WARN: Code duplicated, block: B:45:0x0089  */
    /* JADX WARN: Code duplicated, block: B:48:0x0090  */
    /* JADX WARN: Code duplicated, block: B:51:0x009c  */
    /* JADX WARN: Code duplicated, block: B:54:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:55:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:56:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:62:0x00d6  */
    public final float onTouchEvent(MotionEvent motionEvent, boolean z) {
        VelocityTracker velocityTracker;
        VelocityTracker velocityTracker2;
        float fComputeEasedProgressFromY;
        VelocityTracker velocityTracker3;
        float yVelocity;
        COUISpringAnimation cOUISpringAnimation;
        VelocityTracker velocityTracker4;
        float fCoerceIn;
        float f;
        COUISpringAnimation cOUISpringAnimation2;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                if (!this.active && z) {
                    velocityTracker = this.velocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.addMovement(motionEvent);
                    }
                    velocityTracker2 = this.velocityTracker;
                    if (velocityTracker2 != null) {
                        velocityTracker2.computeCurrentVelocity(1000);
                    }
                    fComputeEasedProgressFromY = computeEasedProgressFromY(this.touchDownY, motionEvent.getY(), this.maxDistance);
                    velocityTracker3 = this.velocityTracker;
                    if (velocityTracker3 != null) {
                        yVelocity = velocityTracker3.getYVelocity();
                    } else {
                        yVelocity = 0.0f;
                    }
                    this.currentVel = yVelocity;
                    if (yVelocity > 0.0f) {
                        fCoerceIn = RangesKt___RangesKt.coerceIn(0.0f, 0.0f, 1.0f);
                        float f2 = this.maxDistance;
                        f = (-yVelocity) / (f2 >= 1.0f ? f2 : 1.0f);
                        cOUISpringAnimation2 = this.progressSpring;
                        if (((COUIDynamicAnimation) cOUISpringAnimation2).mRunning) {
                            cOUISpringAnimation2.animateToFinalPosition(fCoerceIn);
                        } else {
                            cOUISpringAnimation2.cancel();
                            this.progressSpring.setStartValue(this.currentProgress);
                            this.progressSpring.setStartVelocity(f);
                            this.progressSpring.animateToFinalPosition(fCoerceIn);
                        }
                    } else {
                        cOUISpringAnimation = this.progressSpring;
                        if (((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
                            cOUISpringAnimation.cancel();
                        }
                        float fCoerceIn2 = RangesKt___RangesKt.coerceIn(fComputeEasedProgressFromY, 0.0f, 1.0f);
                        this.progressHolder.setValue(fCoerceIn2);
                        dispatchProgress(fCoerceIn2, yVelocity);
                    }
                    velocityTracker4 = this.velocityTracker;
                    if (velocityTracker4 != null) {
                        velocityTracker4.recycle();
                    }
                    this.velocityTracker = null;
                    this.active = false;
                }
                return this.currentProgress;
            }
            if (actionMasked != 2) {
                if (actionMasked == 3) {
                    if (!this.active) {
                        return this.currentProgress;
                    }
                    velocityTracker = this.velocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.addMovement(motionEvent);
                    }
                    velocityTracker2 = this.velocityTracker;
                    if (velocityTracker2 != null) {
                        velocityTracker2.computeCurrentVelocity(1000);
                    }
                    fComputeEasedProgressFromY = computeEasedProgressFromY(this.touchDownY, motionEvent.getY(), this.maxDistance);
                    velocityTracker3 = this.velocityTracker;
                    if (velocityTracker3 != null) {
                        yVelocity = velocityTracker3.getYVelocity();
                    } else {
                        yVelocity = 0.0f;
                    }
                    this.currentVel = yVelocity;
                    if (yVelocity > 0.0f) {
                        fCoerceIn = RangesKt___RangesKt.coerceIn(0.0f, 0.0f, 1.0f);
                        float f3 = this.maxDistance;
                        f = (-yVelocity) / (f3 >= 1.0f ? f3 : 1.0f);
                        cOUISpringAnimation2 = this.progressSpring;
                        if (((COUIDynamicAnimation) cOUISpringAnimation2).mRunning) {
                            cOUISpringAnimation2.animateToFinalPosition(fCoerceIn);
                        } else {
                            cOUISpringAnimation2.cancel();
                            this.progressSpring.setStartValue(this.currentProgress);
                            this.progressSpring.setStartVelocity(f);
                            this.progressSpring.animateToFinalPosition(fCoerceIn);
                        }
                    } else {
                        cOUISpringAnimation = this.progressSpring;
                        if (((COUIDynamicAnimation) cOUISpringAnimation).mRunning) {
                            cOUISpringAnimation.cancel();
                        }
                        float fCoerceIn3 = RangesKt___RangesKt.coerceIn(fComputeEasedProgressFromY, 0.0f, 1.0f);
                        this.progressHolder.setValue(fCoerceIn3);
                        dispatchProgress(fCoerceIn3, yVelocity);
                    }
                    velocityTracker4 = this.velocityTracker;
                    if (velocityTracker4 != null) {
                        velocityTracker4.recycle();
                    }
                    this.velocityTracker = null;
                    this.active = false;
                }
            } else {
                if (!this.active) {
                    return this.currentProgress;
                }
                VelocityTracker velocityTracker5 = this.velocityTracker;
                if (velocityTracker5 != null) {
                    velocityTracker5.addMovement(motionEvent);
                }
                VelocityTracker velocityTracker6 = this.velocityTracker;
                if (velocityTracker6 != null) {
                    velocityTracker6.computeCurrentVelocity(1000);
                }
                float fComputeEasedProgressFromY2 = computeEasedProgressFromY(this.touchDownY, motionEvent.getY(), this.maxDistance);
                VelocityTracker velocityTracker7 = this.velocityTracker;
                float yVelocity2 = velocityTracker7 != null ? velocityTracker7.getYVelocity() : 0.0f;
                this.currentVel = yVelocity2;
                COUISpringAnimation cOUISpringAnimation3 = this.progressSpring;
                if (((COUIDynamicAnimation) cOUISpringAnimation3).mRunning) {
                    cOUISpringAnimation3.cancel();
                }
                float fCoerceIn4 = RangesKt___RangesKt.coerceIn(fComputeEasedProgressFromY2, 0.0f, 1.0f);
                this.progressHolder.setValue(fCoerceIn4);
                dispatchProgress(fCoerceIn4, yVelocity2);
            }
        } else {
            this.active = true;
            this.touchDownY = motionEvent.getY();
            this.progressSpring.cancel();
            this.progressHolder.setValue(0.0f);
            this.currentProgress = 0.0f;
            VelocityTracker velocityTracker8 = this.velocityTracker;
            if (velocityTracker8 != null) {
                velocityTracker8.recycle();
            }
            VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
            velocityTrackerObtain.addMovement(motionEvent);
            this.velocityTracker = velocityTrackerObtain;
        }
        return this.currentProgress;
    }

    public final void reset() {
        this.active = false;
        this.progressSpring.cancel();
        this.progressHolder.setValue(0.0f);
        this.currentProgress = 0.0f;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.velocityTracker = null;
    }

    public final void setMaxDistance(float f) {
        this.maxDistance = f;
    }
}
