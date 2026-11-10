package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.text.TextUtils;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsColorUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileViewTwoXOne$handleTileStateChange$1$1 implements Runnable {
    public /* synthetic */ QSTile.State $newState;
    public /* synthetic */ QsLabelView $this_apply;
    public /* synthetic */ OplusQSResizeableTileViewTwoXOne this$0;

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = true;
        this.$this_apply.setEnabled(!this.$newState.disabledByPolicy);
        this.$this_apply.setSelected(!TextUtils.isEmpty(this.$newState.label));
        QsLabelView qsLabelView = this.$this_apply;
        QSTile.State state = this.$newState;
        qsLabelView.setContentDescription(state.toggleable ? state.dualLabelContentDescription : null);
        QsLabelView qsLabelView2 = this.$this_apply;
        int qsColorState = QsColorUtil.getQsColorState();
        int i = this.$newState.state;
        if (i != 0 && i != 101) {
            z = false;
        }
        qsLabelView2.updateColor(qsColorState, z, this.this$0.isEditMode);
    }
}
