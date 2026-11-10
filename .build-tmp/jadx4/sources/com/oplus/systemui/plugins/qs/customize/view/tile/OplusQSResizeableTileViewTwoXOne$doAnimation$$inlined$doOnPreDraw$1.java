package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextSwitcher;
import com.android.systemui.plugins.qs.QSTile;
import com.oplusos.systemui.common.view.TextSwitcherExtKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1 implements Runnable {
    public /* synthetic */ boolean $isOpen$inlined;
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSResizeableTileViewTwoXOne this$0;

    public /* synthetic */ OplusQSResizeableTileViewTwoXOne$doAnimation$$inlined$doOnPreDraw$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                int measuredHeight = this.this$0.labelDesc.getMeasuredHeight();
                OplusQSResizeableTileViewTwoXOne oplusQSResizeableTileViewTwoXOne = this.this$0;
                float f = (measuredHeight + oplusQSResizeableTileViewTwoXOne.labelPadding) / 2.0f;
                OplusQSHighlightTileViewAnimatorLabel oplusQSHighlightTileViewAnimatorLabel = oplusQSResizeableTileViewTwoXOne.animatorForLabel;
                TextSwitcher textSwitcher = this.this$0.labelTitle;
                TextSwitcher textSwitcher2 = this.this$0.labelDesc;
                boolean z = this.$isOpen$inlined;
                final boolean z2 = this.$isOpen$inlined;
                final OplusQSResizeableTileViewTwoXOne oplusQSResizeableTileViewTwoXOne2 = this.this$0;
                oplusQSHighlightTileViewAnimatorLabel.startLabelAnim(textSwitcher, textSwitcher2, f, z, new AnimatorListenerAdapter() { // from class: com.oplus.systemui.plugins.qs.customize.view.tile.OplusQSResizeableTileViewTwoXOne$doAnimation$1$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        QSTile.State tileState;
                        OplusQSResizeableTileViewTwoXOne oplusQSResizeableTileViewTwoXOne3 = oplusQSResizeableTileViewTwoXOne2;
                        oplusQSResizeableTileViewTwoXOne3.isAnimating = false;
                        if (z2 || (tileState = oplusQSResizeableTileViewTwoXOne3.getTileState()) == null) {
                            return;
                        }
                        oplusQSResizeableTileViewTwoXOne2.updateLabelDescText(tileState);
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator) {
                        QSTile.State tileState;
                        if (!z2 || (tileState = oplusQSResizeableTileViewTwoXOne2.getTileState()) == null) {
                            return;
                        }
                        oplusQSResizeableTileViewTwoXOne2.updateLabelDescText(tileState);
                    }
                });
                break;
            default:
                boolean z3 = this.$isOpen$inlined && TextSwitcherExtKt.getCurrentText(this.this$0.labelDesc).length() > 0;
                int measuredHeight2 = this.this$0.labelDesc.getMeasuredHeight();
                OplusQSResizeableTileViewTwoXOne oplusQSResizeableTileViewTwoXOne3 = this.this$0;
                float f2 = (measuredHeight2 + oplusQSResizeableTileViewTwoXOne3.labelPadding) / 2.0f;
                oplusQSResizeableTileViewTwoXOne3.labelTitle.setTranslationY(z3 ? 0.0f : f2);
                TextSwitcher textSwitcher3 = this.this$0.labelDesc;
                if (z3) {
                    f2 = 0.0f;
                }
                textSwitcher3.setTranslationY(f2);
                this.this$0.labelDesc.setAlpha(this.$isOpen$inlined ? 1.0f : 0.0f);
                break;
        }
    }
}
