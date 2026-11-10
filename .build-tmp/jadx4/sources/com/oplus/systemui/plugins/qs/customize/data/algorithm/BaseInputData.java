package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class BaseInputData {
    private final GridInfoEntity gridInfo;
    private final List<TileConfig> tiles;

    public BaseInputData(List<TileConfig> list, GridInfoEntity gridInfoEntity) {
        this.tiles = list;
        this.gridInfo = gridInfoEntity;
    }

    public final GridInfoEntity getGridInfo() {
        return this.gridInfo;
    }

    public final List<TileConfig> getTiles() {
        return this.tiles;
    }

    public BaseInputData mergeTwoCols() {
        return new BaseInputData(DataConvertorKt.mergeTwoCols(this.tiles), DataConvertorKt.mergeTwoCols(this.gridInfo));
    }
}
