package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.animation.Animator;
import android.util.Log;
import android.widget.ImageView;
import com.oplus.systemui.plugins.qs.customize.view.animation.threestage.ThreeStageIconAlphaSpringAnimator;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSThreeStageLottieView$initLottieAnimListener$1 implements Animator.AnimatorListener {
    public /* synthetic */ int $activeColor;
    public /* synthetic */ ThreeStageIconAlphaSpringAnimator $alphaAnimator;
    public /* synthetic */ int $defaultColor;
    public /* synthetic */ ImageView $iconView;
    public /* synthetic */ OplusQSThreeStageLottieView this$0;

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        ImageView imageView = this.$iconView;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSThreeStageIconView-->".concat("onAnimationCancel: " + imageView));
        }
        this.this$0.setVisibility(4);
        this.$iconView.setVisibility(0);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [android.widget.ImageView, com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSThreeStageLottieView] */
    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ImageView imageView = this.$iconView;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSThreeStageIconView-->".concat("onAnimationEnd: " + imageView));
        }
        ?? r3 = this.this$0;
        r3.animating = false;
        r3.setVisibility(4);
        this.$iconView.setVisibility(0);
        this.$alphaAnimator.setLottiViewSelected(false);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [android.widget.ImageView, com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSThreeStageLottieView] */
    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        ImageView imageView = this.$iconView;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSThreeStageIconView-->".concat("onAnimationStart: " + imageView));
        }
        ?? r4 = this.this$0;
        r4.animating = true;
        r4.setVisibility(0);
        this.$iconView.setVisibility(4);
        this.$alphaAnimator.setIconSelected(this.$activeColor, this.$defaultColor, true);
        this.$alphaAnimator.setLottiViewSelected(true);
    }
}
