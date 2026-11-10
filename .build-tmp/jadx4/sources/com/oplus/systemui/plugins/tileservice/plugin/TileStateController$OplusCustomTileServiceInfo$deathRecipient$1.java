package com.oplus.systemui.plugins.tileservice.plugin;

import android.os.IBinder;
import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileStateController$OplusCustomTileServiceInfo$deathRecipient$1 implements IBinder.DeathRecipient {
    public /* synthetic */ TileStateController.OplusCustomTileServiceInfo this$0;

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        Log.d("TileStateController", "target process died");
        this.this$0.setBindAppWhenListening(true);
    }
}
