package com.oplus.systemui.qs.media;

import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AlphaSpringAnimatorHelper$1$2 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ AlphaSpringAnimatorHelper this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        AlphaSpringAnimatorHelper alphaSpringAnimatorHelper = this.this$0;
        alphaSpringAnimatorHelper.currentAlphaProgress = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        AlphaSpringAnimatorHelper.access$updateLottieViewAlpha(alphaSpringAnimatorHelper);
    }
}
