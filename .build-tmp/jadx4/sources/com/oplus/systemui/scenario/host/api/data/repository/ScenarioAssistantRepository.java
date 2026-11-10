package com.oplus.systemui.scenario.host.api.data.repository;

import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ScenarioAssistantRepository {
    StateFlow getScenarioInfo();

    StateFlow isMirrorShowing();

    StateFlow isScenarioSupport();

    void setMirrorShowing(boolean z);
}
