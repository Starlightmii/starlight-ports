package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeformTileAlg implements ILocationAlg {
    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public boolean match(DeformInputData deformInputData) {
        List<TileConfig> tiles = deformInputData.getTiles();
        GridInfoEntity gridInfo = deformInputData.getGridInfo();
        return DeformAlgHelper.INSTANCE.isEndSizeValid$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(tiles.get(deformInputData.getTargetIndex()), deformInputData.getTargetSize(), gridInfo);
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public List<TileConfig> process(DeformInputData deformInputData) {
        List<TileConfig> tiles = deformInputData.getTiles();
        GridInfoEntity gridInfo = deformInputData.getGridInfo();
        TileConfig tileConfig = tiles.get(deformInputData.getTargetIndex());
        DeformAlgHelper.INSTANCE.deform$vendor__oplus__packages__apps__systemui__oplus_separate_qs__android_common__OplusSystemUISeparateQSLib_domestic(tiles, tileConfig, gridInfo, tileConfig.getSize(), deformInputData.getTargetSize(), true);
        return deformInputData.getTiles();
    }
}
