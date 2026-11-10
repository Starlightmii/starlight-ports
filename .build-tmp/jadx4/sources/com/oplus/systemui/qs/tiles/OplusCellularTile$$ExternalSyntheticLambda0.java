package com.oplus.systemui.qs.tiles;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusCellularTile$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusCellularTile f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusCellularTile oplusCellularTile = this.f$0;
        switch (i) {
            case 0:
                oplusCellularTile.mKeyguardUpdateMonitor.registerCallback(oplusCellularTile.mCallback);
                break;
            case 1:
                oplusCellularTile.executeMobileDataToggle();
                break;
            default:
                oplusCellularTile.mKeyguardUpdateMonitor.removeCallback(oplusCellularTile.mCallback);
                break;
        }
    }
}
