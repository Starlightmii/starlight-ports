package com.oplus.systemui.qs.tiles;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SmallScreenTile$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ SmallScreenTile f$0;

    @Override // java.lang.Runnable
    public final void run() {
        SmallScreenTile smallScreenTile = this.f$0;
        if (smallScreenTile.mClickSuccessed) {
            return;
        }
        smallScreenTile.mClickSuccessed = true;
        smallScreenTile.refreshState((Object) null);
    }
}
