package com.oplus.systemui.notification.lockscreen.capsule;

import android.animation.Animator;
import com.oplus.systemui.notification.lockscreen.capsule.stroke.StrokeView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StokeViewControl$createAnimator$lambda$8$$inlined$addListener$default$1 implements Animator.AnimatorListener {
    public /* synthetic */ StokeViewControl this$0;

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        StrokeView strokeView = this.this$0.strokeView;
        if (strokeView != null) {
            strokeView.setVisibility(8);
        }
        this.this$0.getBindDataInterceptor().unIntercept();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        StrokeView strokeView = this.this$0.strokeView;
        if (strokeView != null) {
            strokeView.setVisibility(8);
        }
        this.this$0.getBindDataInterceptor().unIntercept();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        StrokeView strokeView = this.this$0.strokeView;
        if (strokeView != null) {
            strokeView.setVisibility(0);
        }
    }
}
