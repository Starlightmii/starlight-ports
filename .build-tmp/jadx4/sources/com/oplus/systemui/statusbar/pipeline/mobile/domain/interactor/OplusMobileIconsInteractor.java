package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.oplus.systemui.statusbar.policy.MobileIconSets;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusMobileIconsInteractor {
    StateFlow getActiveMobileDataSubscriptionId();

    MobileIconSets getCurrentMobileIconSets();

    StateFlow getDefaultConnections();

    StateFlow getForceHiddenHDPlus();

    StateFlow getMobileIconInteractors();

    StateFlow getMobileIconSets();

    StateFlow getSignalIconDisplayMode();

    StateFlow getSimState();

    StateFlow getWifiIcon();

    StateFlow is5GNewCall();

    StateFlow isAirplaneMode();

    StateFlow isDualPsOpen();

    StateFlow isDualSim();

    StateFlow isSatelliteForbid();

    StateFlow isUnstackedUseTheme17();
}
