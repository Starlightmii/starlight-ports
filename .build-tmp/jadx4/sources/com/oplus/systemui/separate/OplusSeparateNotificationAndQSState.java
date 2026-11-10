package com.oplus.systemui.separate;

import com.oplus.systemui.separate.data.repository.OplusPanelModeRepository;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateNotificationAndQSState {
    private final StateFlow enableSeparateNotificationAndQS;

    public OplusSeparateNotificationAndQSState(OplusPanelModeRepository oplusPanelModeRepository) {
        this.enableSeparateNotificationAndQS = oplusPanelModeRepository.getEnableSeparateNotificationAndQS();
    }

    public final StateFlow getEnableSeparateNotificationAndQS() {
        return this.enableSeparateNotificationAndQS;
    }
}
