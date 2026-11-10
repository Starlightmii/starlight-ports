package com.oplus.systemui.volume.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.compose.animation.core.MonoSpline$;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.volume.utils.VolumeAnimUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SingleAppVolumeButtonAnimationController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SingleAppVolumeButtonAnimationController";
    private Animator currentAnimator;
    private FlingAnimation flingAnimationX;
    private FlingAnimation flingAnimationY;
    public final float flingFriction;
    private SpringAnimation springAnimationX;
    private SpringAnimation springAnimationY;
    public final float springDamping;
    public final float springStiffness;
    private final View view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SingleAppVolumeButtonAnimationController(View view, float f, float f2, float f3) {
        this.view = view;
        this.springStiffness = f;
        this.springDamping = f2;
        this.flingFriction = f3;
    }

    public static final void access$flingThenSpring$stopYFlingAndStartSpring(Ref.BooleanRef booleanRef, SingleAppVolumeButtonAnimationController singleAppVolumeButtonAnimationController) {
        if (booleanRef.element) {
            return;
        }
        booleanRef.element = true;
        FlingAnimation flingAnimation = singleAppVolumeButtonAnimationController.flingAnimationY;
        if (flingAnimation != null && ((DynamicAnimation) flingAnimation).mRunning) {
            flingAnimation.cancel();
        }
        singleAppVolumeButtonAnimationController.flingAnimationY = null;
    }

    public final void cancelAll() {
        Animator animator = this.currentAnimator;
        if (animator != null) {
            animator.cancel();
        }
        this.currentAnimator = null;
        FlingAnimation flingAnimation = this.flingAnimationX;
        if (flingAnimation != null) {
            flingAnimation.cancel();
        }
        this.flingAnimationX = null;
        FlingAnimation flingAnimation2 = this.flingAnimationY;
        if (flingAnimation2 != null) {
            flingAnimation2.cancel();
        }
        this.flingAnimationY = null;
        SpringAnimation springAnimation = this.springAnimationX;
        if (springAnimation != null) {
            springAnimation.cancel();
        }
        this.springAnimationX = null;
        SpringAnimation springAnimation2 = this.springAnimationY;
        if (springAnimation2 != null) {
            springAnimation2.cancel();
        }
        this.springAnimationY = null;
        this.view.animate().cancel();
    }

    public final void fadeout(Function0 function0) {
        cancelAll();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "SingleAppVolumeButtonAnimationController-->fadeout");
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(VolumeAnimUtil.DURATION_ROW_HIDE);
        valueAnimatorOfFloat.setInterpolator(VolumeAnimUtil.VOLUME_HIDE_INTERPOLATOR);
        flyIn.2.1 r1 = new flyIn.2.1(1);
        r1.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r1);
        valueAnimatorOfFloat.addListener(new flyIn.2.2(this, function0, 1));
        valueAnimatorOfFloat.start();
        this.currentAnimator = valueAnimatorOfFloat;
    }

    public final void flingThenSpring(float f, float f2, float f3, Rect rect, Rect rect2, Function2 function2) {
        cancelAll();
        float x = this.view.getX();
        float y = this.view.getY();
        Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = y;
        Ref.IntRef intRef = new Ref.IntRef();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        float f4 = rect.left;
        float f5 = rect.right;
        float f6 = rect.top;
        float f7 = rect.bottom;
        FlingAnimation flingAnimation = new FlingAnimation(DynamicAnimation.X, this.view);
        float f8 = this.flingFriction;
        flingAnimation.setFriction(f8);
        flingAnimation.setStartVelocity(f2);
        flingAnimation.setMinValue(Math.min(x, f4));
        flingAnimation.setMaxValue(Math.max(x, f5));
        flingThenSpring.1.1 r5 = new flingThenSpring.1.1();
        r5.$flingAnimMaxX = f5;
        r5.$flingAnimMinX = f4;
        r5.$yFlingStoppedByXBoundary = booleanRef;
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        flingAnimation.addUpdateListener(r5);
        flingThenSpring.1.2 r6 = new flingThenSpring.1.2();
        r6.this$0 = this;
        r6.$targetX = f;
        r6.$successCount = intRef;
        r6.$onFlingComplete = function2;
        r6.$endX = f;
        r6.$endY = floatRef;
        r6.$draggableBounds = rect2;
        VarHandle.storeStoreFence();
        flingAnimation.addEndListener(r6);
        flingAnimation.start();
        this.flingAnimationX = flingAnimation;
        FlingAnimation flingAnimation2 = new FlingAnimation(DynamicAnimation.Y, this.view);
        flingAnimation2.setFriction(f8);
        flingAnimation2.setStartVelocity(f3);
        flingAnimation2.setMinValue(Math.min(y, f6));
        flingAnimation2.setMaxValue(Math.max(y, f7));
        flingThenSpring.2.1 r7 = new flingThenSpring.2.1();
        r7.$yFlingStoppedByXBoundary = booleanRef;
        r7.$flingAnimMinY = f6;
        r7.$flingAnimMaxY = f7;
        r7.$endY = floatRef;
        r7.this$0 = this;
        r7.$successCount = intRef;
        r7.$onFlingComplete = function2;
        r7.$endX = f;
        r7.$draggableBounds = rect2;
        VarHandle.storeStoreFence();
        flingAnimation2.addEndListener(r7);
        flingAnimation2.start();
        this.flingAnimationY = flingAnimation2;
    }

    public final void flyIn(float f, float f2, float f3, Function0 function0) {
        cancelAll();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m("flyIn: fromX=", f, ", toX=", f2, ", toY="), f3, "SingleAppVolumeButtonAnimationController-->", "SystemUi--Volume");
        }
        this.view.setX(f);
        this.view.setY(f3);
        this.view.setAlpha(1.0f);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        valueAnimatorOfFloat.setDuration(VolumeAnimUtil.DURATION_ONE_ROW_SHOW);
        valueAnimatorOfFloat.setInterpolator(VolumeAnimUtil.VOLUME_SHOW_INTERPOLATOR);
        flyIn.2.1 r5 = new flyIn.2.1(0);
        r5.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r5);
        valueAnimatorOfFloat.addListener(new flyIn.2.2(this, function0, 0));
        valueAnimatorOfFloat.start();
        this.currentAnimator = valueAnimatorOfFloat;
    }

    public final void flyOut(float f, Function0 function0) {
        cancelAll();
        float x = this.view.getX();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("flyOut: startX=", x, ", toX=", f, "SingleAppVolumeButtonAnimationController-->"));
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(x, f);
        valueAnimatorOfFloat.setDuration(VolumeAnimUtil.DURATION_ROW_HIDE);
        valueAnimatorOfFloat.setInterpolator(VolumeAnimUtil.VOLUME_HIDE_INTERPOLATOR);
        flyIn.2.1 r0 = new flyIn.2.1(2);
        r0.this$0 = this;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(r0);
        valueAnimatorOfFloat.addListener(new flyIn.2.2(this, function0, 2));
        valueAnimatorOfFloat.start();
        this.currentAnimator = valueAnimatorOfFloat;
    }

    public final float getCurrentX() {
        return this.view.getX();
    }

    public final float getCurrentY() {
        return this.view.getY();
    }

    public final void moveTo(float f, float f2) {
        cancelAll();
        this.view.setX(f);
        this.view.setY(f2);
    }

    public final void setFlyingOut(boolean z) {
    }
}
