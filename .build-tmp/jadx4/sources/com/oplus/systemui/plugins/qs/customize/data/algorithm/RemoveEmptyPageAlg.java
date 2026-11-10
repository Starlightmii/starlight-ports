package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfigKt;
import com.oplusos.systemui.common.model.Loc;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoveEmptyPageAlg implements ILocationAlg {
    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public boolean match(BaseInputData baseInputData) {
        return true;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.algorithm.ILocationAlg
    public List<TileConfig> process(BaseInputData baseInputData) {
        List<TileConfig> tiles = baseInputData.getTiles();
        int pageCount = LocationAlgHelper.INSTANCE.getPageCount(tiles);
        boolean[] zArr = new boolean[pageCount];
        int i = 0;
        for (int i2 = 0; i2 < pageCount; i2++) {
            zArr[i2] = true;
        }
        int[] iArr = new int[pageCount];
        List<TileConfig> list = tiles;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            zArr[((TileConfig) it.next()).getLocation().getPageIndex()] = false;
        }
        boolean z = false;
        int i3 = 1;
        while (i3 < pageCount) {
            int i4 = i3 - 1;
            int i5 = iArr[i4] + (zArr[i4] ? 1 : 0);
            iArr[i3] = i5;
            if (i5 > 0) {
                z = true;
            }
            i3++;
            z = z;
        }
        if (z) {
            for (Object obj : list) {
                int i6 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                TileConfig tileConfig = (TileConfig) obj;
                int pageIndex = tileConfig.getLocation().getPageIndex();
                int i7 = iArr[pageIndex];
                if (i7 > 0) {
                    Loc location = tileConfig.getLocation();
                    tiles.set(i, TileConfigKt.replaceLoc(tileConfig, location.copy(pageIndex - i7, location.startCol, location.startRow)));
                }
                i = i6;
            }
        }
        return tiles;
    }
}
