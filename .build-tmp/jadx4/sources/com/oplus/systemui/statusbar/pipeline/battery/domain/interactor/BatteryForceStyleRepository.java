package com.oplus.systemui.statusbar.pipeline.battery.domain.interactor;

import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryForceStyleRepository {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BatteryForceStyleRepository";
    private final MutableStateFlow forceShowStyle;
    private final ForceShowStyleInteractor homeInteractor;
    private final Flow isFiltered;
    private final ForceShowStyleInteractor keyguardInteractor;
    private final ForceShowStyleInteractor qsInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BatteryForceStyleRepository(SysUiStateControllerImpl sysUiStateControllerImpl) {
        Flow flowFlowCombine = FlowKt.flowCombine(sysUiStateControllerImpl.isKeyguardShowing(), sysUiStateControllerImpl.isPanelExpanded(), new BatteryForceStyleRepository$isFiltered$1(3, null));
        this.isFiltered = flowFlowCombine;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(0);
        this.forceShowStyle = MutableStateFlow;
        this.homeInteractor = new ForceShowStyleInteractor(MutableStateFlow, flowFlowCombine, true, sysUiStateControllerImpl.isPanelExpanded());
        this.qsInteractor = new ForceShowStyleInteractor(MutableStateFlow, flowFlowCombine, 8);
        this.keyguardInteractor = new ForceShowStyleInteractor(null, null, 15);
    }

    public final ForceShowStyleInteractor getHomeInteractor() {
        return this.homeInteractor;
    }

    public final ForceShowStyleInteractor getKeyguardInteractor() {
        return this.keyguardInteractor;
    }

    public final ForceShowStyleInteractor getQsInteractor() {
        return this.qsInteractor;
    }
}
