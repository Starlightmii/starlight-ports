package com.oplus.systemui.statusbar.event;

import android.widget.FrameLayout;
import androidx.core.animation.Animator;
import com.android.systemui.statusbar.events.BackgroundAnimatableView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSystemEventChipAnimationControllerExImpl$onSystemEventAnimationFinish$lambda$7$$inlined$doOnEnd$1 implements Animator.AnimatorListener {
    public /* synthetic */ OplusSystemEventChipAnimationControllerExImpl this$0;

    public final void onAnimationCancel(Animator animator) {
    }

    public final void onAnimationEnd(Animator animator) {
        FrameLayout frameLayout;
        OplusSystemEventChipAnimationControllerExImpl oplusSystemEventChipAnimationControllerExImpl = this.this$0;
        BackgroundAnimatableView backgroundAnimatableView = oplusSystemEventChipAnimationControllerExImpl.currentAnimatedView;
        if (backgroundAnimatableView == null || (frameLayout = oplusSystemEventChipAnimationControllerExImpl.animationWindowView) == null) {
            return;
        }
        frameLayout.removeView(backgroundAnimatableView.getView());
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationStart(Animator animator) {
    }
}
