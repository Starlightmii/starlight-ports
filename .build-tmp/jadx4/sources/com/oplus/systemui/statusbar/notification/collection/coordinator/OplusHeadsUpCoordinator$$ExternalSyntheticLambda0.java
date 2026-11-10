package com.oplus.systemui.statusbar.notification.collection.coordinator;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusHeadsUpCoordinator$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusHeadsUpCoordinator f$0;

    public /* synthetic */ OplusHeadsUpCoordinator$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        OplusHeadsUpCoordinator oplusHeadsUpCoordinator = this.f$0;
        switch (i) {
            case 0:
                return Boolean.valueOf(OplusHeadsUpCoordinator.$r8$lambda$8uGWlZ4QqxTilioZZi1BT2YTVD0(oplusHeadsUpCoordinator));
            default:
                oplusHeadsUpCoordinator.tryCloseAonCamera();
                return Unit.INSTANCE;
        }
    }
}
