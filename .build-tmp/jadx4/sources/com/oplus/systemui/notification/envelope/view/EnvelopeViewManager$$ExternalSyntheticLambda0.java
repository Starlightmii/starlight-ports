package com.oplus.systemui.notification.envelope.view;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class EnvelopeViewManager$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ EnvelopeViewManager f$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        EnvelopeViewManager envelopeViewManager = this.f$0;
        switch (i) {
            case 0:
                envelopeViewManager.mEnvelopeFrame.setTranslationY(((Float) envelopeViewManager.mInTranslateAnim.getAnimatedValue()).floatValue());
                break;
            case 1:
                envelopeViewManager.mEnvelopeFrame.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 2:
                envelopeViewManager.mEnvelopeFrame.setTranslationY(((Float) envelopeViewManager.mOutTranslateAnim.getAnimatedValue()).floatValue());
                break;
            case 3:
                envelopeViewManager.mEnvelopeFrame.setTranslationX(((Float) envelopeViewManager.mOutLandTranslateAnim.getAnimatedValue()).floatValue());
                break;
            default:
                envelopeViewManager.mEnvelopeFrame.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
