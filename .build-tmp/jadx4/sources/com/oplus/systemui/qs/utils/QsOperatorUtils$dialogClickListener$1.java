package com.oplus.systemui.qs.utils;

import android.content.DialogInterface;
import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import com.verizon.loginenginesvc.clientsdk.MhsAuthorizedClient;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsOperatorUtils$dialogClickListener$1 implements DialogInterface.OnClickListener {
    public static final QsOperatorUtils$dialogClickListener$1 INSTANCE = new QsOperatorUtils$dialogClickListener$1();

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "QsOperatorUtils-->MHS cancelRequest");
            }
            MhsAuthorizedClient mhsAuthorizedClient = QsOperatorUtils.mMhsAuthorizedClient;
            if (mhsAuthorizedClient != null) {
                synchronized (mhsAuthorizedClient) {
                    mhsAuthorizedClient.cleanup();
                }
            }
            QsOperatorUtils.dismissMhsProgressDialog();
        }
    }
}
