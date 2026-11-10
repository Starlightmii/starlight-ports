package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfigKt;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AddOneTileAlg implements ILocationAlg {
    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public boolean match(AddInputData addInputData) {
        return addInputData.getAddTiles().size() == 1 && addInputData.getPageIndex() >= 0;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public List<TileConfig> process(AddInputData addInputData) {
        Loc loc;
        List<TileConfig> tiles = addInputData.getTiles();
        TileConfig tileConfig = (TileConfig) CollectionsKt___CollectionsKt.first(addInputData.getAddTiles());
        int pageIndex = addInputData.getPageIndex();
        GridInfoEntity gridInfo = addInputData.getGridInfo();
        SpanSize size = tileConfig.getSize();
        Loc location = tileConfig.getLocation();
        if (!Intrinsics.areEqual(location, LocCache.INSTANCE.getINVALID_LOC()) && !LocationAlgHelper.isLocationUsed(location, size)) {
            tiles.add(tileConfig);
            return tiles;
        }
        int pageIndex2 = tiles.isEmpty() ? 1 : ((TileConfig) CollectionsKt___CollectionsKt.last(tiles)).getLocation().getPageIndex() + 1;
        int gridRow = gridInfo.getGridRow();
        int gridCol = gridInfo.getGridCol();
        int i = pageIndex;
        loop0: while (true) {
            if (i < pageIndex2) {
                for (int i2 = 0; i2 < gridRow; i2++) {
                    for (int i3 = 0; i3 < gridCol; i3++) {
                        loc = LocCache.INSTANCE.loc(i, i3, i2);
                        if (!LocationAlgHelper.isLocationUsed(loc, size)) {
                            break loop0;
                        }
                    }
                }
                i++;
            } else {
                if (pageIndex2 != 1) {
                    loc = LocCache.INSTANCE.loc(pageIndex2, 0, 0);
                    break;
                }
                int i4 = 0;
                loop3: while (true) {
                    if (i4 >= pageIndex) {
                        loc = null;
                        break;
                    }
                    int i5 = 0;
                    while (true) {
                        if (i5 < gridRow) {
                            int i6 = 0;
                            while (true) {
                                if (i6 < gridCol) {
                                    loc = LocCache.INSTANCE.loc(i4, i6, i5);
                                    if (!LocationAlgHelper.isLocationUsed(loc, size)) {
                                        break;
                                    }
                                    i6++;
                                } else {
                                    i5++;
                                }
                            }
                        } else {
                            i4++;
                        }
                    }
                }
            }
        }
        if (loc != null) {
            tiles.add(TileConfigKt.replaceLoc(tileConfig, loc));
        }
        return tiles;
    }
}
