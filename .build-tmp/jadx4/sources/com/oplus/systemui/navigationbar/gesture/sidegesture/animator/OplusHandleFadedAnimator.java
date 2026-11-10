package com.oplus.systemui.navigationbar.gesture.sidegesture.animator;

import android.content.Context;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.oplus.systemui.navigationbar.gesture.sidegesture.OplusNavigationHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHandleFadedAnimator {
    public static final Companion Companion = new Companion();
    private final SpringAnimation alphaChangeAnimator;
    private final Context context;
    private final OplusNavigationHandle oplusNavigationHandle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusHandleFadedAnimator(Context context, OplusNavigationHandle oplusNavigationHandle) {
        this.context = context;
        this.oplusNavigationHandle = oplusNavigationHandle;
        SpringAnimation springAnimation = new SpringAnimation(new OplusHandleFadedAnimator$alphaChangeAnimator$1("fadedAnimProgress"), oplusNavigationHandle);
        SpringForce springForce = new SpringForce();
        springAnimation.setStartValue(0.0f);
        springForce.setStiffness(3948.0f);
        springForce.setDampingRatio(1.0f);
        springAnimation.setMinimumVisibleChange(0.00390625f);
        springAnimation.setSpring(springForce);
        this.alphaChangeAnimator = springAnimation;
    }

    public final void end() {
        SpringAnimation springAnimation = this.alphaChangeAnimator;
        if (((DynamicAnimation) springAnimation).mRunning) {
            springAnimation.cancel();
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final OplusNavigationHandle getOplusNavigationHandle() {
        return this.oplusNavigationHandle;
    }

    public final boolean isRunning() {
        return ((DynamicAnimation) this.alphaChangeAnimator).mRunning;
    }

    public final void start() {
        SpringAnimation springAnimation = this.alphaChangeAnimator;
        if (((DynamicAnimation) springAnimation).mRunning) {
            springAnimation.cancel();
        } else {
            springAnimation.setStartValue(0.0f);
        }
        springAnimation.animateToFinalPosition(1.0f);
    }
}
