package com.oplus.systemui.qs.tileex;

import android.os.customize.OplusCustomizeRestrictionManager;
import android.os.customize.OplusCustomizeSecurityManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CellularTileEx$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ CellularTileEx f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        CellularTileEx cellularTileEx = this.f$0;
        switch (i) {
            case 0:
                CellularTileEx.Companion companion = CellularTileEx.Companion;
                return OplusCustomizeRestrictionManager.getInstance(cellularTileEx.getContext());
            default:
                CellularTileEx.Companion companion2 = CellularTileEx.Companion;
                return OplusCustomizeSecurityManager.getInstance(cellularTileEx.getContext());
        }
    }
}
