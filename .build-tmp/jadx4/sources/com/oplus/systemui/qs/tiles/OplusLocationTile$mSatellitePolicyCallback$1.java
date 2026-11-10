package com.oplus.systemui.qs.tiles;

import com.oplus.systemui.statusbar.phone.policy.SatellitePolicy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLocationTile$mSatellitePolicyCallback$1 implements SatellitePolicy.IConnectionCallback {
    public /* synthetic */ OplusLocationTile this$0;

    @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
    public /* bridge */ /* synthetic */ void onCarrierNameChanged(String str) {
        super.onCarrierNameChanged(str);
    }

    @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
    public final void onSatelliteConnected() {
        this.this$0.refreshState((Object) null);
    }

    @Override // com.oplus.systemui.statusbar.phone.policy.SatellitePolicy.IConnectionCallback
    public final void onSatelliteDisConnected() {
        this.this$0.refreshState((Object) null);
    }
}
