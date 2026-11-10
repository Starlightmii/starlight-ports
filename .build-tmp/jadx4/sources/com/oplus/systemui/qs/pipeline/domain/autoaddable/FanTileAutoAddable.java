package com.oplus.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.DefaultTilesRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplusos.systemui.common.feature.FeatureOption;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FanTileAutoAddable extends PromoteTileAutoAddable {
    public final boolean isDefaultTile;

    public FanTileAutoAddable(DefaultTilesRepository defaultTilesRepository) {
        super(TileSpec.Companion.create("custom(com.oplus.devicesettings/.FanTileService)"));
        this.isDefaultTile = defaultTilesRepository.getDefaultTiles(false).contains(getSpec());
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public String getDescription() {
        return "FanTileAutoAddable (" + getAutoAddTracking() + ")";
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public final boolean isPromoteTile() {
        return this.isDefaultTile && FeatureOption.isMiscFanSupport();
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public /* bridge */ /* synthetic */ Object preProcessRestore(RestoreData restoreData, Continuation continuation) {
        return super.preProcessRestore(restoreData, continuation);
    }
}
