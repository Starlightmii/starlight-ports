package com.oplus.systemui.qs.pipeline.data.repository;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.data.repository.DefaultTilesRepository;
import com.android.systemui.qs.pipeline.data.repository.TilesSettingConverter;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDefaultTilesQSHostRepository implements DefaultTilesRepository {
    private final String defaultSpecString;
    private final List<TileSpec> defaultTiles;
    private final Resources resources;

    public OplusDefaultTilesQSHostRepository(Resources resources) {
        int i;
        this.resources = resources;
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isApplyLightOSQsTile) {
            i = 2131955865;
        } else {
            FeatureOption.INSTANCE.getClass();
            i = FeatureOption.isTablet5G() ? 2131955862 : FeatureOption.isTabletWifi() ? 2131955863 : 2131955860;
        }
        String string = resources.getString(i);
        this.defaultSpecString = string;
        this.defaultTiles = TilesSettingConverter.INSTANCE.toTilesList(string);
    }

    public List<TileSpec> getDefaultTiles(boolean z) {
        return this.defaultTiles;
    }
}
