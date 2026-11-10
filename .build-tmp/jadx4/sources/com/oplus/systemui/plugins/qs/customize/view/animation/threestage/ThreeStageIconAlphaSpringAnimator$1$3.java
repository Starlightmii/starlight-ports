package com.oplus.systemui.plugins.qs.customize.view.animation.threestage;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageIconAlphaSpringAnimator$1$3 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ ThreeStageIconAlphaSpringAnimator this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        ThreeStageIconAlphaSpringAnimator threeStageIconAlphaSpringAnimator = this.this$0;
        threeStageIconAlphaSpringAnimator.onAnimationEnd.invoke(threeStageIconAlphaSpringAnimator.iconView, Boolean.valueOf(threeStageIconAlphaSpringAnimator.isSelected));
    }
}
