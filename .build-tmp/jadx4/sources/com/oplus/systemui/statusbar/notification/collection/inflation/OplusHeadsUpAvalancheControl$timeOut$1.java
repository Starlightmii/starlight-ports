package com.oplus.systemui.statusbar.notification.collection.inflation;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpAvalancheControl$timeOut$1 implements Runnable {
    public /* synthetic */ OplusHeadsUpAvalancheControl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        if (this.this$0.getNext() != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusHeadsUpAvalancheControl-->time out: but has next");
            }
            this.this$0.showNext();
        }
    }
}
