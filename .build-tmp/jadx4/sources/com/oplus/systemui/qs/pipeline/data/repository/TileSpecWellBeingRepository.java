package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.qs.pipeline.data.repository.WellBeingAssistantRepository;
import com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecWellBeingRepository extends TileSpecSpecialModeRepository {
    private final StateFlow isAvailable;
    private final QSPipelineLoggerEx loggerEx;

    public TileSpecWellBeingRepository(Resources resources, TileSpecSettingsRepository tileSpecSettingsRepository, WellBeingAssistantRepository wellBeingAssistantRepository, QSPipelineLoggerEx qSPipelineLoggerEx) {
        super(resources, tileSpecSettingsRepository, 2131955870);
        this.loggerEx = qSPipelineLoggerEx;
        this.isAvailable = wellBeingAssistantRepository.getWellBeingAssistantMode();
    }

    @Override // com.oplus.systemui.qs.pipeline.data.repository.TileSpecSpecialModeRepository
    public StateFlow isAvailable() {
        return this.isAvailable;
    }

    @Override // com.oplus.systemui.qs.pipeline.data.repository.TileSpecSpecialModeRepository
    public Object tilesSpecs(int i, Continuation continuation) {
        this.loggerEx.logWellBeingAssistantTiles(i, getSpecialModeTiles());
        return super.tilesSpecs(i, continuation);
    }
}
