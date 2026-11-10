package com.oplus.systemui.plugins.qs.quickentrance.dialog;

import android.util.Log;
import com.android.systemui.plugins.qs.QSFgs;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFgsDialogController$appListAdapter$1 implements OplusQSFgsDialogController.Callback {
    public /* synthetic */ OplusQSFgsDialogController this$0;

    @Override // com.oplus.systemui.plugins.qs.quickentrance.dialog.OplusQSFgsDialogController.Callback
    public final void handleClick(QSFgs.QSRunningApp qSRunningApp) {
        OplusQSFgsDialogController oplusQSFgsDialogController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SeparateQSFgsDialogController-->".concat("handleClick runningApp: " + qSRunningApp));
        }
        oplusQSFgsDialogController.stopPackage(qSRunningApp.getUserId(), qSRunningApp.getPackageName());
        oplusQSFgsDialogController.quickEntrance.handleFgsStopOnClick();
    }
}
