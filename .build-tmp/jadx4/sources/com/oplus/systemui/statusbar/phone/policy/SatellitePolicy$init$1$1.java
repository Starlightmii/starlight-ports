package com.oplus.systemui.statusbar.phone.policy;

import android.os.Handler;
import com.oplus.evolution.SatelliteReadyCallback;
import com.oplus.evolution.SatelliteReadyInfo;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SatellitePolicy$init$1$1 extends SatelliteReadyCallback {
    public final /* synthetic */ SatellitePolicy this$0;

    public SatellitePolicy$init$1$1(SatellitePolicy satellitePolicy) {
        this.this$0 = satellitePolicy;
    }

    public final void onSatelliteReady(int i, SatelliteReadyInfo satelliteReadyInfo) {
        Handler handler = this.this$0.mainHandler;
        SatellitePolicy satellitePolicy = this.this$0;
        onSatelliteReady.1 r0 = new onSatelliteReady.1();
        r0.$type = i;
        r0.this$0 = satellitePolicy;
        VarHandle.storeStoreFence();
        handler.post(r0);
    }
}
