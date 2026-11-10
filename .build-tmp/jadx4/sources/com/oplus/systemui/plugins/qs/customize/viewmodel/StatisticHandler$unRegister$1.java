package com.oplus.systemui.plugins.qs.customize.viewmodel;

import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatisticHandler$unRegister$1 implements Runnable {
    public /* synthetic */ StatisticHandler this$0;

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.this$0.getContextInstance().getSysUiContext().unregisterReceiver(this.this$0.receiver);
        } catch (IllegalArgumentException e) {
            LogUtil.i$default("StatisticHandler", "Receiver not registered::" + e, null, null, 12);
        }
    }
}
