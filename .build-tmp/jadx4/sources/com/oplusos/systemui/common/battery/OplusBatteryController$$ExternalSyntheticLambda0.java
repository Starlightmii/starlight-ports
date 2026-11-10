package com.oplusos.systemui.common.battery;

import com.android.systemui.statusbar.OplusStatusBarDependencyInstance;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusBatteryController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return new OplusBatteryController();
            default:
                return OplusStatusBarDependencyInstance.INSTANCE.getOplusStatusBarDependencyEx().getOplusBatteryControllerLogger();
        }
    }
}
