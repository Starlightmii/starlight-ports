package com.oplus.systemui.viewdialog.ui.animation;

import com.android.systemui.animation.TransitionAnimator;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DetailAnimationManager$starAnimation$animListener$1 implements DetailAnimationManager.AnimationListener {
    public /* synthetic */ Ref.IntRef $endBottom;
    public /* synthetic */ float $endBottomCornerRadius;
    public /* synthetic */ Ref.FloatRef $endCenterX;
    public /* synthetic */ float $endCornerWeight;
    public /* synthetic */ Ref.IntRef $endLeft;
    public /* synthetic */ Ref.IntRef $endRight;
    public /* synthetic */ TransitionAnimator.State $endState;
    public /* synthetic */ Ref.IntRef $endTop;
    public /* synthetic */ float $endTopCornerRadius;
    public /* synthetic */ Ref.IntRef $endWidth;
    public /* synthetic */ DetailAnimationManager.Interpolators $interpolators;
    public /* synthetic */ DetailAnimationManager$$ExternalSyntheticLambda2 $onAnimationEnd;
    public /* synthetic */ DetailAnimationManager$$ExternalSyntheticLambda1 $onAnimationProgress;
    public /* synthetic */ DetailAnimationManager$$ExternalSyntheticLambda0 $onAnimationStart;
    public /* synthetic */ float $startAlpha;
    public /* synthetic */ Ref.IntRef $startBottom;
    public /* synthetic */ float $startBottomCornerRadius;
    public /* synthetic */ Ref.FloatRef $startCenterX;
    public /* synthetic */ float $startCornerWeight;
    public /* synthetic */ Ref.IntRef $startLeft;
    public /* synthetic */ float $startPanelAlpha;
    public /* synthetic */ Ref.IntRef $startRight;
    public /* synthetic */ TransitionAnimator.State $startState;
    public /* synthetic */ Ref.IntRef $startTop;
    public /* synthetic */ float $startTopCornerRadius;
    public /* synthetic */ Ref.IntRef $startWidth;
    public /* synthetic */ TransitionAnimator.State $state;
    public /* synthetic */ DetailAnimationManager.Timings $timings;
    public boolean canceled;
    public /* synthetic */ DetailAnimationManager this$0;

    public final boolean getCanceled() {
        return this.canceled;
    }

    public final void onAnimationEnd() {
        DetailAnimationManager detailAnimationManager = this.this$0;
        if (this.canceled && detailAnimationManager.skipToEnd) {
            LogUtil.i$default(LogUtil.TAG_COMMON, "DetailAnimationManager", "onAnimationEnd: isLaunching: " + detailAnimationManager.isLaunching + ", skipToEnd = true + progress = " + detailAnimationManager.animation.getAnimatedFraction(), null, 8);
            DetailAnimationManager.access$starAnimation$onProgressChanged(this.$interpolators, this.$startCenterX, this.$endCenterX, this.$startWidth, this.$endWidth, this.$state, this.$startTop, this.$endTop, this.$startBottom, this.$endBottom, this.$startTopCornerRadius, this.$endTopCornerRadius, this.$startBottomCornerRadius, this.$endBottomCornerRadius, this.$startCornerWeight, this.$endCornerWeight, this.$timings, detailAnimationManager, this.$startAlpha, this.$startPanelAlpha, this.$onAnimationProgress, this.$endState, this.$endLeft, this.$endRight, this.$startState, this.$startLeft, this.$startRight, 1.0f);
        }
        this.$onAnimationEnd.invoke(Boolean.valueOf(this.canceled));
    }

    public final void onAnimationUpdate(float f) {
        DetailAnimationManager.access$starAnimation$onProgressChanged(this.$interpolators, this.$startCenterX, this.$endCenterX, this.$startWidth, this.$endWidth, this.$state, this.$startTop, this.$endTop, this.$startBottom, this.$endBottom, this.$startTopCornerRadius, this.$endTopCornerRadius, this.$startBottomCornerRadius, this.$endBottomCornerRadius, this.$startCornerWeight, this.$endCornerWeight, this.$timings, this.this$0, this.$startAlpha, this.$startPanelAlpha, this.$onAnimationProgress, this.$endState, this.$endLeft, this.$endRight, this.$startState, this.$startLeft, this.$startRight, f);
    }

    public final void setCanceled(boolean z) {
        this.canceled = z;
    }
}
