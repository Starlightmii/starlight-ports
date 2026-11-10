package com.oplus.systemui.plugins.qs.customize.view.tile;

import android.text.TextUtils;
import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsColorUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSResizeableTileViewOneXOne$handleTileStateChange$lambda$6$$inlined$doOnAttach$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ QSTile.State $newState$inlined;
    public /* synthetic */ QsLabelView $this_apply$inlined;
    public /* synthetic */ QsLabelView $this_doOnAttach;
    public /* synthetic */ OplusQSResizeableTileViewOneXOne this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.$this_doOnAttach.removeOnAttachStateChangeListener(this);
        boolean z = true;
        this.$this_apply$inlined.setEnabled(!this.$newState$inlined.disabledByPolicy);
        this.$this_apply$inlined.setSelected(!TextUtils.isEmpty(this.$newState$inlined.label));
        QsLabelView qsLabelView = this.$this_apply$inlined;
        QSTile.State state = this.$newState$inlined;
        qsLabelView.setContentDescription(state.toggleable ? state.dualLabelContentDescription : null);
        this.$this_apply$inlined.setEnableExpandIndicator(this.$newState$inlined.dualTarget && !this.this$0.isEditMode);
        CharSequence charSequence = this.$newState$inlined.importantLabel;
        this.$this_apply$inlined.updateText((charSequence == null || charSequence.length() == 0) ? this.$newState$inlined.label : this.$newState$inlined.importantLabel);
        QsLabelView qsLabelView2 = this.$this_apply$inlined;
        int qsColorState = QsColorUtil.getQsColorState();
        int i = this.$newState$inlined.state;
        if (i != 0 && i != 101) {
            z = false;
        }
        qsLabelView2.updateColor(qsColorState, z, this.this$0.isEditMode);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
