package com.oplus.systemui.qs.tileex;

import android.os.customize.OplusCustomizeConnectivityManager;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class HotspotTileEx$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ HotspotTileEx f$0;

    public final Object invoke() {
        HotspotTileEx hotspotTileEx = this.f$0;
        int i = HotspotTileEx.$r8$clinit;
        return OplusCustomizeConnectivityManager.getInstance(hotspotTileEx.getContext());
    }
}
