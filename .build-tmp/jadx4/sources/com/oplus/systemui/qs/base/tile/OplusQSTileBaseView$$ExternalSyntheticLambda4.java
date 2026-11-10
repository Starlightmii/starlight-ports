package com.oplus.systemui.qs.base.tile;

import android.view.View;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplusos.systemui.common.view.ViewExtKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSTileBaseView$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public /* synthetic */ QSTile f$0;
    public /* synthetic */ Expandable f$1;
    public /* synthetic */ View f$2;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        QSTile qSTile = this.f$0;
        Expandable expandable = this.f$1;
        View view2 = this.f$2;
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            return;
        }
        qSTile.secondaryClick(expandable);
        if (view2 != null) {
            ViewExtKt.setPerformClickedFlag(view2, true);
        }
    }
}
