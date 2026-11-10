package com.oplus.systemui.qs.tiles;

import com.android.systemui.plugins.qs.DetailAdapter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusCellularTile$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusCellularTile.AnonymousClass2 f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusCellularTile.AnonymousClass2 anonymousClass2 = this.f$0;
        switch (i) {
            case 0:
                OplusCellularTile oplusCellularTile = anonymousClass2.this$0;
                oplusCellularTile.updateCarrierText$1(false);
                oplusCellularTile.updateLockSimState();
                break;
            default:
                DetailAdapter detailAdapter = anonymousClass2.this$0.mDetailAdapter;
                if (detailAdapter instanceof OplusCellularTile.CellularDetailAdapter) {
                    ((OplusCellularTile.CellularDetailAdapter) detailAdapter).updateItem$1();
                }
                break;
        }
    }
}
