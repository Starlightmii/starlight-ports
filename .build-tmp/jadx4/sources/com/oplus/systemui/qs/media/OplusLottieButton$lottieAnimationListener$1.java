package com.oplus.systemui.qs.media;

import android.animation.Animator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLottieButton$lottieAnimationListener$1 implements Animator.AnimatorListener {
    public /* synthetic */ OplusLottieButton this$0;

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.this$0.getImageView().getVisibility() != 0) {
            this.this$0.handleAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.this$0.getImageView().setVisibility(4);
    }
}
