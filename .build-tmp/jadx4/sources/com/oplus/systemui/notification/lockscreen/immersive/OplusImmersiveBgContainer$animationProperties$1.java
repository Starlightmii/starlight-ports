package com.oplus.systemui.notification.lockscreen.immersive;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Property;
import android.view.View;
import com.android.systemui.statusbar.notification.stack.AnimationFilter;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusImmersiveBgContainer$animationProperties$1 extends AnimationProperties {
    public /* synthetic */ OplusImmersiveBgContainer this$0;

    public AnimationFilter getAnimationFilter() {
        return this.this$0.animationFilter;
    }

    public final AnimatorListenerAdapter getAnimationFinishListener(Property property) {
        final OplusImmersiveBgContainer oplusImmersiveBgContainer = this.this$0;
        return new AnimatorListenerAdapter() { // from class: com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveBgContainer$animationProperties$1$getAnimationFinishListener$1
            public boolean mWasCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.mWasCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                oplusImmersiveBgContainer.animatorSet.remove(animator);
                if (!oplusImmersiveBgContainer.animatorSet.isEmpty() || this.mWasCancelled) {
                    return;
                }
                oplusImmersiveBgContainer.onAnimationFinished();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                this.mWasCancelled = false;
                oplusImmersiveBgContainer.animatorSet.add(animator);
            }
        };
    }

    public final boolean wasAdded(View view) {
        ImmersiveBgEntry showingEntry = this.this$0.ambientState.getShowingEntry();
        return Intrinsics.areEqual(showingEntry != null ? showingEntry.getView() : null, view);
    }
}
