package com.oplus.systemui.statusbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStatusBarClockController$animateAppear$1$1$1 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ OplusKeyguardStatusBarClockController this$0;

    public /* synthetic */ OplusKeyguardStatusBarClockController$animateAppear$1$1$1(OplusKeyguardStatusBarClockController oplusKeyguardStatusBarClockController, int i) {
        this.$r8$classId = i;
        this.this$0 = oplusKeyguardStatusBarClockController;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.showClockInternal();
                break;
            default:
                this.this$0.hideClockInternal();
                break;
        }
    }
}
