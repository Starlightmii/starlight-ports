package com.oplus.systemui.plugins.qs.customize.view.animation.threestage;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageScaleSpringAnimationHelper$springAnim$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ ThreeStageScaleSpringAnimationHelper this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        ThreeStageScaleSpringAnimationHelper threeStageScaleSpringAnimationHelper = this.this$0;
        threeStageScaleSpringAnimationHelper.iconView.setScaleX(f);
        threeStageScaleSpringAnimationHelper.iconView.setScaleY(f);
        threeStageScaleSpringAnimationHelper.lottieView.setScaleX(f);
        threeStageScaleSpringAnimationHelper.lottieView.setScaleY(f);
    }
}
