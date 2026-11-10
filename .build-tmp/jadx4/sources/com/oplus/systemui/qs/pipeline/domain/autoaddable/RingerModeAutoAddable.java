package com.oplus.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.data.repository.DefaultTilesRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplus.systemui.qs.tiles.RingerModeTile;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RingerModeAutoAddable extends PromoteTileAutoAddable {
    public final boolean isDefaultTile;

    public RingerModeAutoAddable(DefaultTilesRepository defaultTilesRepository) {
        super(TileSpec.Companion.create(RingerModeTile.TILE_SPEC));
        this.isDefaultTile = defaultTilesRepository.getDefaultTiles(false).contains(getSpec());
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public String getDescription() {
        return "RingerModeAutoAddable (" + getAutoAddTracking() + ")";
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public final boolean isPromoteTile() {
        if (!this.isDefaultTile) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isOplusAlertSlider()) {
            return false;
        }
        VolumeFeatureOption.INSTANCE.getClass();
        return !VolumeFeatureOption.isSupportsStealthMode();
    }

    @Override // com.oplus.systemui.qs.pipeline.domain.autoaddable.PromoteTileAutoAddable
    public /* bridge */ /* synthetic */ Object preProcessRestore(RestoreData restoreData, Continuation continuation) {
        return super.preProcessRestore(restoreData, continuation);
    }
}
