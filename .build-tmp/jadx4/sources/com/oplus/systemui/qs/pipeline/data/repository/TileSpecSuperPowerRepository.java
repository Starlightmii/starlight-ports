package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.data.repository.SuperPowerSaveRepository;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.qs.pipeline.shared.QSPipelineLoggerEx;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecSuperPowerRepository extends TileSpecSpecialModeRepository {
    private final StateFlow isAvailable;
    private final QSPipelineLoggerEx loggerEx;

    public TileSpecSuperPowerRepository(Resources resources, TileSpecSettingsRepository tileSpecSettingsRepository, SuperPowerSaveRepository superPowerSaveRepository, QSPipelineLoggerEx qSPipelineLoggerEx) {
        super(resources, tileSpecSettingsRepository, 2131955869);
        this.loggerEx = qSPipelineLoggerEx;
        this.isAvailable = superPowerSaveRepository.getSuperPowerSaveMode();
    }

    @Override // com.oplus.systemui.qs.pipeline.data.repository.TileSpecSpecialModeRepository
    public StateFlow isAvailable() {
        return this.isAvailable;
    }

    @Override // com.oplus.systemui.qs.pipeline.data.repository.TileSpecSpecialModeRepository
    public Object tilesSpecs(int i, Continuation continuation) {
        this.loggerEx.logUsingPowerSaveTiles(i, getSpecialModeTiles());
        return super.tilesSpecs(i, continuation);
    }
}
