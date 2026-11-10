package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import com.oplus.systemui.statusbar.observer.PowerSavingModeObserver;
import com.oplusos.systemui.common.battery.OplusBatteryController;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ BatteryRepositoryImpl$chargeState$1$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                OplusBatteryController.Companion.getInstance().unregisterIBatteryStateChanged((BatteryRepositoryImpl$chargeState$1$listener$1) obj);
                break;
            case 1:
                OplusBatteryController.Companion.getInstance().unregisterIBatteryStateChanged((BatteryRepositoryImpl$batteryLevel$1$listener$1) obj);
                break;
            case 2:
                PowerSavingModeObserver.Companion.getInstance().removeListener((BatteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1) obj);
                break;
            default:
                ObserverDispatcher.getInstance().unregisterObserver((BatteryRepositoryImpl$speedModeTurnedOn$1$speedModeObserver$1) obj);
                break;
        }
        return Unit.INSTANCE;
    }
}
