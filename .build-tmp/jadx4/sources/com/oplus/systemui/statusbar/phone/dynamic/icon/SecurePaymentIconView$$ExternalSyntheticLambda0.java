package com.oplus.systemui.statusbar.phone.dynamic.icon;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SecurePaymentIconView$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ SecurePaymentIconView f$0;

    /* JADX WARN: Type inference failed for: r1v1, types: [android.widget.ImageView, com.oplus.systemui.statusbar.phone.dynamic.icon.SecurePaymentIconView] */
    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ?? r1 = this.f$0;
        int i = SecurePaymentIconView.$r8$clinit;
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        r1.mCircleAlpha = (int) (255.0f * fFloatValue);
        r1.mShieldAlpha = ((int) ((1.0f - fFloatValue) * 77.0f)) + 178;
        r1.postInvalidate();
    }
}
