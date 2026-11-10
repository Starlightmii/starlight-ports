package com.oplus.systemui.qs.base.temporarilyinfo;

import android.animation.ValueAnimator;
import android.view.ViewGroup;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager$animateShow$1 implements ValueAnimator.AnimatorUpdateListener {
    public /* synthetic */ TemporarilyInfoView $localShownTextView;
    public /* synthetic */ TemporarilyInfoManager this$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        TemporarilyInfoView temporarilyInfoView = this.$localShownTextView;
        if (temporarilyInfoView != null) {
            temporarilyInfoView.setAlpha(fFloatValue);
            return;
        }
        ViewGroup statusBarContainerView = this.this$0.getStatusBarContainerView();
        if (statusBarContainerView != null) {
            statusBarContainerView.setTransitionAlpha(fFloatValue);
        }
        ViewGroup qsQuickEntranceContainerView = this.this$0.getQsQuickEntranceContainerView();
        if (qsQuickEntranceContainerView != null) {
            qsQuickEntranceContainerView.setTransitionAlpha(fFloatValue);
        }
        ViewGroup privacyContainerView = this.this$0.getPrivacyContainerView();
        if (privacyContainerView != null) {
            privacyContainerView.setTransitionAlpha(fFloatValue);
        }
    }
}
