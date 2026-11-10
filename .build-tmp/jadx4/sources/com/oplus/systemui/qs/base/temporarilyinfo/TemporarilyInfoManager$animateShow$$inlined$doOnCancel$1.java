package com.oplus.systemui.qs.base.temporarilyinfo;

import android.animation.Animator;
import android.view.ViewGroup;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager$animateShow$$inlined$doOnCancel$1 implements Animator.AnimatorListener {
    public /* synthetic */ TemporarilyInfoManager this$0;

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        ViewGroup statusBarContainerView = this.this$0.getStatusBarContainerView();
        if (statusBarContainerView != null) {
            statusBarContainerView.setTransitionAlpha(1.0f);
        }
        ViewGroup qsQuickEntranceContainerView = this.this$0.getQsQuickEntranceContainerView();
        if (qsQuickEntranceContainerView != null) {
            qsQuickEntranceContainerView.setTransitionAlpha(1.0f);
        }
        ViewGroup privacyContainerView = this.this$0.getPrivacyContainerView();
        if (privacyContainerView != null) {
            privacyContainerView.setTransitionAlpha(1.0f);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}
