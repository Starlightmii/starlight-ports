package com.oplus.systemui.plugins.qs.customize.view.animation.threestage;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ThreeStageIconAlphaSpringAnimator$1$2 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ThreeStageIconAlphaSpringAnimator this$0;

    public /* synthetic */ ThreeStageIconAlphaSpringAnimator$1$2(int i) {
        this.$r8$classId = i;
    }

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        switch (this.$r8$classId) {
            case 0:
                ThreeStageIconAlphaSpringAnimator threeStageIconAlphaSpringAnimator = this.this$0;
                threeStageIconAlphaSpringAnimator.currentColorProgress = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
                threeStageIconAlphaSpringAnimator.updateColor();
                break;
            default:
                ThreeStageIconAlphaSpringAnimator threeStageIconAlphaSpringAnimator2 = this.this$0;
                threeStageIconAlphaSpringAnimator2.currentAlphaProgress = RangesKt___RangesKt.coerceIn(f, 0.54f, threeStageIconAlphaSpringAnimator2.curActiveAlpha);
                threeStageIconAlphaSpringAnimator2.lottieView.setAlpha(threeStageIconAlphaSpringAnimator2.currentAlphaProgress);
                break;
        }
    }
}
