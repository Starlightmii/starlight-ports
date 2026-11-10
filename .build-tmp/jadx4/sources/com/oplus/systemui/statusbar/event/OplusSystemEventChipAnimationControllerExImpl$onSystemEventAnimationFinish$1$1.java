package com.oplus.systemui.statusbar.event;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.animation.Animator;
import androidx.core.animation.ValueAnimator;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$1$1 implements Animator.AnimatorUpdateListener {
    public /* synthetic */ View $actualAnimatedView;
    public /* synthetic */ int $startHeight;
    public /* synthetic */ int $startWidth;
    public /* synthetic */ ValueAnimator $this_apply;
    public /* synthetic */ OplusSystemEventChipAnimationControllerExImpl this$0;

    public final void onAnimationUpdate(Animator animator) {
        View view;
        float fFloatValue = ((Float) this.$this_apply.getAnimatedValue()).floatValue();
        BackgroundAnimatableView backgroundAnimatableView = this.this$0.currentAnimatedView;
        if (backgroundAnimatableView != null && (view = backgroundAnimatableView.getView()) != null) {
            view.setAlpha(fFloatValue);
        }
        View view2 = this.$actualAnimatedView;
        if (view2 != null) {
            int i = (int) (this.$startWidth * fFloatValue);
            int i2 = (int) (this.$startHeight * fFloatValue);
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            view2.setLayoutParams(layoutParams);
        }
    }
}
