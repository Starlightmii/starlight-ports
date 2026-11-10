package com.oplus.systemui.qs.base.temporarilyinfo;

import android.animation.ValueAnimator;
import android.view.ViewGroup;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager$animateHide$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $targetHide;

    public /* synthetic */ TemporarilyInfoManager$animateHide$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                TemporarilyInfoView temporarilyInfoView = (TemporarilyInfoView) this.$targetHide;
                if (temporarilyInfoView != null) {
                    temporarilyInfoView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
                break;
            case 1:
                TemporarilyInfoView temporarilyInfoView2 = (TemporarilyInfoView) this.$targetHide;
                if (temporarilyInfoView2 != null) {
                    temporarilyInfoView2.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
                break;
            default:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ViewGroup statusBarContainerView = ((TemporarilyInfoManager) this.$targetHide).getStatusBarContainerView();
                if (statusBarContainerView != null) {
                    statusBarContainerView.setTransitionAlpha(fFloatValue);
                }
                ViewGroup qsQuickEntranceContainerView = ((TemporarilyInfoManager) this.$targetHide).getQsQuickEntranceContainerView();
                if (qsQuickEntranceContainerView != null) {
                    qsQuickEntranceContainerView.setTransitionAlpha(fFloatValue);
                }
                ViewGroup privacyContainerView = ((TemporarilyInfoManager) this.$targetHide).getPrivacyContainerView();
                if (privacyContainerView != null) {
                    privacyContainerView.setTransitionAlpha(fFloatValue);
                }
                break;
        }
    }
}
