package com.oplus.systemui.notification.row.stacking;

import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardStackingBackgroundControl$restartAnimation$1$1 implements Animator.AnimatorUpdateListener {
    public /* synthetic */ KeyguardStackingBackgroundControl this$0;

    public final void onAnimationUpdate(Animator animator) {
        this.this$0.currentAlpha = ((Integer) ((ValueAnimator) animator).getAnimatedValue()).intValue();
    }
}
