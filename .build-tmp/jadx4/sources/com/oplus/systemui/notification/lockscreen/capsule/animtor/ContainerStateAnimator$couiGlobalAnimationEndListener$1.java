package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ContainerStateAnimator$couiGlobalAnimationEndListener$1 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ ContainerStateAnimator this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        ContainerStateAnimator containerStateAnimator = this.this$0;
        containerStateAnimator.animatorSet.remove(cOUIDynamicAnimation);
        if (containerStateAnimator.animatorSet.isEmpty()) {
            containerStateAnimator.onAnimationFinished();
        }
        containerStateAnimator.mCouiAnimationEndPool.push(this);
    }
}
