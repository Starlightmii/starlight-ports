package com.oplus.systemui.plugins.qs.animator;

import androidx.compose.animation.core.MonoSpline$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelTranslationY$translationYAnimation$2$1$1 implements COUIDynamicAnimation.OnAnimationUpdateListener {
    public /* synthetic */ Ref.FloatRef $lastResponse;
    public /* synthetic */ COUISpringAnimation $this_apply;
    public /* synthetic */ QSPanelTranslationY this$0;

    public final void onAnimationUpdate(COUIDynamicAnimation cOUIDynamicAnimation, float f, float f2) {
        COUISpringAnimation cOUISpringAnimation = this.$this_apply;
        QSPanelTranslationY qSPanelTranslationY = this.this$0;
        float f3 = qSPanelTranslationY.maxResponse;
        qSPanelTranslationY.setTranslationYValue(f);
        if (qSPanelTranslationY.isOnFling && qSPanelTranslationY.panelClosedOnDown) {
            float fM = 0.35f;
            if (qSPanelTranslationY.yVelocity > 1000.0f) {
                cOUISpringAnimation.setMinimumVisibleChange(0.01f);
                fM = MonoSpline$.ExternalSyntheticOutline0.m(f3, 0.35f, RangesKt___RangesKt.coerceIn(Math.abs((Math.abs(qSPanelTranslationY.translationYValue) - Math.abs(qSPanelTranslationY.getDestTranslationY())) / (qSPanelTranslationY.getDestTranslationY() * 0.1f)), 0.0f, 1.0f), f3);
            } else {
                cOUISpringAnimation.setMinimumVisibleChange(1.0f);
            }
            Ref.FloatRef floatRef = this.$lastResponse;
            if (floatRef.element == fM) {
                return;
            }
            floatRef.element = fM;
            AnimatorUtils.tryUpdateStiffness(cOUISpringAnimation, AnimatorUtils.responseToStiffness(fM));
        }
    }
}
