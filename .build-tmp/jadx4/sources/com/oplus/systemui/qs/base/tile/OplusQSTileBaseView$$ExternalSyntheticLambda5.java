package com.oplus.systemui.qs.base.tile;

import android.view.View;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSTileBaseView$$ExternalSyntheticLambda5 implements View.OnLongClickListener {
    public /* synthetic */ QSTile f$0;
    public /* synthetic */ Expandable f$1;

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        QSTile qSTile = this.f$0;
        Expandable expandable = this.f$1;
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            return true;
        }
        qSTile.longClick(expandable);
        return true;
    }
}
