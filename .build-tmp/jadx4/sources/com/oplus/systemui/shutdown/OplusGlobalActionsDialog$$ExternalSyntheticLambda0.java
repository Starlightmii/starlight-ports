package com.oplus.systemui.shutdown;

import androidx.lifecycle.Observer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusGlobalActionsDialog$$ExternalSyntheticLambda0 implements Observer {
    public /* synthetic */ OplusGlobalActionsDialog f$0;

    public final void onChanged(Object obj) {
        this.f$0.mHandler.sendEmptyMessage(1);
    }
}
