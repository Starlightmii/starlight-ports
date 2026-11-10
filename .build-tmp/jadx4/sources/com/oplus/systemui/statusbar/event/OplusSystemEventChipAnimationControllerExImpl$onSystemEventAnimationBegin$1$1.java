package com.oplus.systemui.statusbar.event;

import android.view.View;
import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationBegin$1$1 implements Animator.AnimatorUpdateListener {
    public /* synthetic */ ValueAnimator $this_apply;
    public /* synthetic */ OplusSystemEventChipAnimationControllerExImpl this$0;

    public final void onAnimationUpdate(Animator animator) {
        View view;
        View view2;
        View view3;
        ValueAnimator valueAnimator = this.$this_apply;
        OplusSystemEventChipAnimationControllerExImpl oplusSystemEventChipAnimationControllerExImpl = this.this$0;
        BackgroundAnimatableView backgroundAnimatableView = oplusSystemEventChipAnimationControllerExImpl.currentAnimatedView;
        if (backgroundAnimatableView != null && (view3 = backgroundAnimatableView.getView()) != null) {
            view3.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
        BackgroundAnimatableView backgroundAnimatableView2 = oplusSystemEventChipAnimationControllerExImpl.currentAnimatedView;
        if (backgroundAnimatableView2 != null && (view2 = backgroundAnimatableView2.getView()) != null) {
            view2.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
        BackgroundAnimatableView backgroundAnimatableView3 = oplusSystemEventChipAnimationControllerExImpl.currentAnimatedView;
        if (backgroundAnimatableView3 == null || (view = backgroundAnimatableView3.getView()) == null) {
            return;
        }
        view.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }
}
