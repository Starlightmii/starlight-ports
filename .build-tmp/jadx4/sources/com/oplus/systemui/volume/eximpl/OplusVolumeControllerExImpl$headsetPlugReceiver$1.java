package com.oplus.systemui.volume.eximpl;

import android.content.Context;
import android.content.Intent;
import com.oplusos.systemui.common.receiver.IReceiverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeControllerExImpl$headsetPlugReceiver$1 implements IReceiverListener {
    public /* synthetic */ OplusVolumeControllerExImpl this$0;

    @Override // com.oplusos.systemui.common.receiver.IReceiverListener
    public final void onReceive(Context context, Intent intent) {
        OplusVolumeControllerExImpl.access$notifyStateChanged(this.this$0);
    }
}
