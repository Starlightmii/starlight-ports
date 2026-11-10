package com.oplus.systemui.notification.color;

import android.animation.ValueAnimator;
import com.google.android.material.animation.ArgbEvaluatorCompat;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaterialColorManager$startColorTransition$2$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ int $capsuleStart;
    public /* synthetic */ Integer $capsuleTargetColor;
    public /* synthetic */ ArgbEvaluatorCompat $evaluator;
    public /* synthetic */ int $materialStart;
    public /* synthetic */ int $targetMaterialColor;
    public /* synthetic */ MaterialColorManager this$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        MaterialColorManager materialColorManager = this.this$0;
        ArgbEvaluatorCompat argbEvaluatorCompat = this.$evaluator;
        Integer numValueOf = Integer.valueOf(this.$materialStart);
        Integer numValueOf2 = Integer.valueOf(this.$targetMaterialColor);
        argbEvaluatorCompat.getClass();
        materialColorManager.setCurrentMaterialColor(ArgbEvaluatorCompat.evaluate(animatedFraction, numValueOf, numValueOf2).intValue());
        Integer num = this.$capsuleTargetColor;
        if (num != null) {
            MaterialColorManager materialColorManager2 = this.this$0;
            ArgbEvaluatorCompat argbEvaluatorCompat2 = this.$evaluator;
            Integer numValueOf3 = Integer.valueOf(this.$capsuleStart);
            argbEvaluatorCompat2.getClass();
            materialColorManager2.setCurrentCapsuleMaterialColor(ArgbEvaluatorCompat.evaluate(animatedFraction, numValueOf3, num).intValue());
        }
        this.this$0.notifyMaterialColorChanged();
    }
}
