package com.oplus.systemui.statusbar.seeding;

import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IScaledAncestorViewKt$applyAncestorViewScale$animator$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ ValueAnimator.AnimatorUpdateListener $animatorUpdateListener;
    public /* synthetic */ View $this_applyAncestorViewScale;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.$this_applyAncestorViewScale.setScaleX(fFloatValue);
        this.$this_applyAncestorViewScale.setScaleY(fFloatValue);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.$animatorUpdateListener;
        if (animatorUpdateListener != null) {
            animatorUpdateListener.onAnimationUpdate(valueAnimator);
        }
    }
}
