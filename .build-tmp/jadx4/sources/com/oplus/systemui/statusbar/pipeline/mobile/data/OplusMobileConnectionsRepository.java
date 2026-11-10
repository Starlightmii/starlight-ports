package com.oplus.systemui.statusbar.pipeline.mobile.data;

import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusMobileConnectionsRepository {
    OplusMobileConnectionRepository getRepoForSubId(int i, Flow flow);

    StateFlow getSimState();

    StateFlow is5GNewCall();

    StateFlow isCMCCDataPlan();

    StateFlow isDualPsSim();

    StateFlow isSatelliteForbid();
}
