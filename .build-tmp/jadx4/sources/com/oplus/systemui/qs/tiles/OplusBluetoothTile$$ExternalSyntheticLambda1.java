package com.oplus.systemui.qs.tiles;

import com.android.systemui.qs.tiles.BluetoothTile;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusBluetoothTile$$ExternalSyntheticLambda1 implements Runnable {
    public /* synthetic */ OplusBluetoothTile f$0;
    public /* synthetic */ boolean f$1;

    @Override // java.lang.Runnable
    public final void run() {
        OplusBluetoothTile oplusBluetoothTile = this.f$0;
        boolean z = this.f$1;
        int i = OplusBluetoothTile.$r8$clinit;
        ((BluetoothTile) oplusBluetoothTile).mController.setBluetoothEnabled(z);
    }
}
