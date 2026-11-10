package com.oplus.systemui.qs.slider.detail.data;

import com.oplus.systemui.qs.slider.detail.tile.QsDetailTile;
import com.oplus.systemui.qs.slider.detail.tile.volume.MediaAudioMuteDetailTile;
import com.oplus.systemui.qs.slider.detail.tile.volume.NoiseReductionDetailTile;
import com.oplus.systemui.qs.slider.detail.tile.volume.SpatialAudioDetailTile;
import com.oplus.systemui.qs.tiles.AutoScreenTemperatureTile;
import com.oplus.systemui.qs.tiles.DarkModeTile;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsDetailTileFactory {
    private final Provider autoBrightnessDetailTile;
    private final Provider autoScreenTemperatureDetailTile;
    private final Provider darkModeDetailTile;
    private final Provider mediaAudioMuteDetailTile;
    private final Provider noiseReductionDetailTile;
    private final Provider spatialAudioDetailTile;

    public OplusQsDetailTileFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6) {
        this.autoBrightnessDetailTile = provider;
        this.darkModeDetailTile = provider2;
        this.autoScreenTemperatureDetailTile = provider3;
        this.mediaAudioMuteDetailTile = provider4;
        this.spatialAudioDetailTile = provider5;
        this.noiseReductionDetailTile = provider6;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final QsDetailTile createTile(String str) {
        switch (str.hashCode()) {
            case -2135292399:
                if (str.equals(MediaAudioMuteDetailTile.TILE_SPEC)) {
                    return (QsDetailTile) this.mediaAudioMuteDetailTile.get();
                }
                return null;
            case -1138589358:
                if (str.equals(SpatialAudioDetailTile.TILE_SPEC)) {
                    return (QsDetailTile) this.spatialAudioDetailTile.get();
                }
                return null;
            case -919209152:
                if (str.equals("autobrightness")) {
                    return (QsDetailTile) this.autoBrightnessDetailTile.get();
                }
                return null;
            case 578953145:
                if (str.equals(AutoScreenTemperatureTile.TILE_SPEC)) {
                    return (QsDetailTile) this.autoScreenTemperatureDetailTile.get();
                }
                return null;
            case 1564403865:
                if (str.equals(NoiseReductionDetailTile.TILE_SPEC)) {
                    return (QsDetailTile) this.noiseReductionDetailTile.get();
                }
                return null;
            case 1741782553:
                if (str.equals(DarkModeTile.TILE_SPEC)) {
                    return (QsDetailTile) this.darkModeDetailTile.get();
                }
                return null;
            default:
                return null;
        }
    }
}
