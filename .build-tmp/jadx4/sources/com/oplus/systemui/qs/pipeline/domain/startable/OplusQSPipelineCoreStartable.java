package com.oplus.systemui.qs.pipeline.domain.startable;

import com.android.systemui.CoreStartable;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.oplus.systemui.qs.pipeline.domain.interactor.AirplaneModeConfirmInteractor;
import com.oplus.systemui.qs.pipeline.domain.interactor.OplusTileModifyInteractor;
import com.oplus.systemui.qs.pipeline.domain.interactor.SysInlineServiceRepository;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPipelineCoreStartable implements CoreStartable {
    private final AirplaneModeConfirmInteractor airplaneModeConfirmInteractor;
    private final CurrentTilesInteractor currentTilesInteractor;
    private final SysInlineServiceRepository sysInlineServiceRepository;
    private final OplusTileModifyInteractor tileModifyInteractor;

    public OplusQSPipelineCoreStartable(CurrentTilesInteractor currentTilesInteractor, OplusTileModifyInteractor oplusTileModifyInteractor, AirplaneModeConfirmInteractor airplaneModeConfirmInteractor, SysInlineServiceRepository sysInlineServiceRepository) {
        this.currentTilesInteractor = currentTilesInteractor;
        this.tileModifyInteractor = oplusTileModifyInteractor;
        this.airplaneModeConfirmInteractor = airplaneModeConfirmInteractor;
        this.sysInlineServiceRepository = sysInlineServiceRepository;
    }

    public final void start() {
        this.tileModifyInteractor.init(this.currentTilesInteractor);
        this.sysInlineServiceRepository.init(this.currentTilesInteractor);
        this.airplaneModeConfirmInteractor.init();
    }
}
