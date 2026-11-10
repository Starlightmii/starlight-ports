package com.oplus.systemui.statusbar.pipeline.battery.domain.interactor;

import com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel;
import com.oplus.systemui.statusbar.pipeline.battery.data.repository.BatteryRepository;
import com.oplusos.systemui.common.observer.WirelessReverseEnabledObserver;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatBatteryIconInteractorImpl implements StatBatteryIconInteractor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StatBatteryIconInteractorImpl";
    private final StateFlow batteryLevel;
    private final StateFlow batteryStyle;
    private final StateFlow chargeState;
    private final StateFlow isSavingMode;
    private final StateFlow speedModeTurnedOn;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatBatteryIconInteractorImpl(BatteryRepository batteryRepository, CoroutineScope coroutineScope) {
        this.batteryLevel = batteryRepository.getBatteryLevel();
        this.chargeState = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.flowCombine(batteryRepository.getChargeState(), WirelessReverseEnabledObserver.Companion.getInstance().getReverseChargeEnabled(), new StatBatteryIconInteractorImpl$chargeState$1(3, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(SharingStarted.Companion, 0L, 3), new ChargeStateModel());
        this.batteryStyle = batteryRepository.getBatteryStyle();
        this.isSavingMode = batteryRepository.isSavingMode();
        this.speedModeTurnedOn = batteryRepository.getSpeedModeTurnedOn();
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor
    public StateFlow getBatteryLevel() {
        return this.batteryLevel;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor
    public StateFlow getBatteryStyle() {
        return this.batteryStyle;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor
    public StateFlow getChargeState() {
        return this.chargeState;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor
    public StateFlow getSpeedModeTurnedOn() {
        return this.speedModeTurnedOn;
    }

    @Override // com.oplus.systemui.statusbar.pipeline.battery.domain.interactor.StatBatteryIconInteractor
    public StateFlow isSavingMode() {
        return this.isSavingMode;
    }
}
