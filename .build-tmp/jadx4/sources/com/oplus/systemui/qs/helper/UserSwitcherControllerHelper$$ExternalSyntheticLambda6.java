package com.oplus.systemui.qs.helper;

import android.content.Context;
import android.content.Intent;
import com.oplusos.systemui.common.receiver.IReceiverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UserSwitcherControllerHelper$$ExternalSyntheticLambda6 implements IReceiverListener {
    public /* synthetic */ UserSwitcherControllerHelper f$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        this.f$0.dismissAllDialog();
    }
}
