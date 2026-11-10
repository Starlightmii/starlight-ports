package com.oplus.systemui.plugins.qs.quickentrance.dialog;

import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.qs.QSFgs;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFgsListAdapter$clickListener$1 implements View.OnClickListener {
    public /* synthetic */ OplusQSFgsDialogController.Callback $call;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object tag = view.getTag();
        QSFgs.QSRunningApp qSRunningApp = tag instanceof QSFgs.QSRunningApp ? (QSFgs.QSRunningApp) tag : null;
        if (qSRunningApp != null) {
            OplusQSFgsDialogController.Callback callback = this.$call;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "SeparateQSFgsListAdapter-->".concat("handleClick it: " + qSRunningApp));
            }
            ((OplusQSFgsDialogController$appListAdapter$1) callback).handleClick(qSRunningApp);
        }
    }
}
