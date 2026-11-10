package com.oplus.systemui.statusbar.seeding;

import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SeedlingPluginManager$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SeedlingPluginManager f$0;

    public /* synthetic */ SeedlingPluginManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        SeedlingPluginManager seedlingPluginManager = this.f$0;
        switch (i) {
            case 0:
                return SeedlingPluginManager.$r8$lambda$iiXG7Ic1ttEj1QtimIz022HG5V4(seedlingPluginManager);
            default:
                SeedlingPluginManager.Companion companion = SeedlingPluginManager.Companion;
                SeedlingPluginManager$mScreenLifeCycleObserver$2$1 seedlingPluginManager$mScreenLifeCycleObserver$2$1 = new SeedlingPluginManager$mScreenLifeCycleObserver$2$1();
                seedlingPluginManager$mScreenLifeCycleObserver$2$1.this$0 = seedlingPluginManager;
                VarHandle.storeStoreFence();
                return seedlingPluginManager$mScreenLifeCycleObserver$2$1;
        }
    }
}
