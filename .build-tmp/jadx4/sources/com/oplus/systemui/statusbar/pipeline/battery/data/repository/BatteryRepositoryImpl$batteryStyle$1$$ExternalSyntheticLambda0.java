package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class BatteryRepositoryImpl$batteryStyle$1$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ BatteryRepositoryImpl$batteryStyle$1$batteryStyleObserver$1 f$0;
    public /* synthetic */ BatteryRepositoryImpl f$1;
    public /* synthetic */ BatteryRepositoryImpl$batteryStyle$1$powerPercentObserver$1 f$2;
    public /* synthetic */ BatteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1 f$3;
    public /* synthetic */ BatteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1 f$4;
    public /* synthetic */ BatteryRepositoryImpl$batteryStyle$1$configurationListener$1 f$5;

    public final Object invoke() {
        BatteryRepositoryImpl$batteryStyle$1$batteryStyleObserver$1 batteryRepositoryImpl$batteryStyle$1$batteryStyleObserver$1 = this.f$0;
        BatteryRepositoryImpl batteryRepositoryImpl = this.f$1;
        BatteryRepositoryImpl$batteryStyle$1$powerPercentObserver$1 batteryRepositoryImpl$batteryStyle$1$powerPercentObserver$1 = this.f$2;
        BatteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1 batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1 = this.f$3;
        BatteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1 batteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1 = this.f$4;
        BatteryRepositoryImpl$batteryStyle$1$configurationListener$1 batteryRepositoryImpl$batteryStyle$1$configurationListener$1 = this.f$5;
        batteryRepositoryImpl$batteryStyle$1$batteryStyleObserver$1.unregister(batteryRepositoryImpl.context);
        batteryRepositoryImpl$batteryStyle$1$powerPercentObserver$1.unregister(batteryRepositoryImpl.context);
        batteryRepositoryImpl.userTracker.removeCallback(batteryRepositoryImpl$batteryStyle$1$userTrackerCallback$1);
        batteryRepositoryImpl.flavorManager.getMiniCapsuleManager().unregisterCapsuleShowListener(batteryRepositoryImpl$batteryStyle$1$capsuleShowListener$1);
        batteryRepositoryImpl.configurationController.removeCallback(batteryRepositoryImpl$batteryStyle$1$configurationListener$1);
        return Unit.INSTANCE;
    }
}
