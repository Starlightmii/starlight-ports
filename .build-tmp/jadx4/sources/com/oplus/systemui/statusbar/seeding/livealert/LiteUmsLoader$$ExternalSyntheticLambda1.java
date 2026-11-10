package com.oplus.systemui.statusbar.seeding.livealert;

import com.oplus.systemui.statusbar.seeding.livealert.LiteUmsLoader.NetworkMonitor;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LiteUmsLoader$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ LiteUmsLoader f$0;

    public /* synthetic */ LiteUmsLoader$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        LiteUmsLoader liteUmsLoader = this.f$0;
        switch (i) {
            case 0:
                return liteUmsLoader.new NetworkMonitor();
            default:
                LiteUmsLoader$userListener$2$1 liteUmsLoader$userListener$2$1 = new LiteUmsLoader$userListener$2$1();
                liteUmsLoader$userListener$2$1.this$0 = liteUmsLoader;
                VarHandle.storeStoreFence();
                return liteUmsLoader$userListener$2$1;
        }
    }
}
