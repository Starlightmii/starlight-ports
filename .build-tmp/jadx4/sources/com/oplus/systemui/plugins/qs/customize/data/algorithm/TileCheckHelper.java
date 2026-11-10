package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileCheckHelper {
    public static final TileCheckHelper INSTANCE = new TileCheckHelper();
    private static final String TAG = "TileCheckHelper";

    public final boolean checkTiles(List<TileConfig> list, GridInfoEntity gridInfoEntity) {
        int i;
        boolean z;
        boolean z2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TileConfig tileConfig : list) {
            int pageIndex = tileConfig.getLocation().getPageIndex();
            int startCol = tileConfig.getLocation().getStartCol();
            int startRow = tileConfig.getLocation().getStartRow();
            int colSpan = tileConfig.getSize().getColSpan();
            int rowSpan = tileConfig.getSize().getRowSpan();
            if (startCol < 0 || startRow < 0) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "[checkTiles] fail 1 tile: " + tileConfig, null, 8);
                return false;
            }
            int i2 = colSpan + startCol;
            if (i2 > gridInfoEntity.getGridCol() || (i = rowSpan + startRow) > gridInfoEntity.getGridRow()) {
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "[checkTiles] fail 2 tile: " + tileConfig, null, 8);
                return false;
            }
            Integer numValueOf = Integer.valueOf(pageIndex);
            Object obj = linkedHashMap.get(numValueOf);
            if (obj == null) {
                int gridRow = gridInfoEntity.getGridRow();
                boolean[][] zArr = new boolean[gridRow][];
                z = true;
                for (int i3 = 0; i3 < gridRow; i3++) {
                    zArr[i3] = new boolean[gridInfoEntity.getGridCol()];
                }
                z2 = false;
                linkedHashMap.put(numValueOf, zArr);
                obj = zArr;
            } else {
                z = true;
                z2 = false;
            }
            boolean[][] zArr2 = (boolean[][]) obj;
            while (startRow < i) {
                for (int i4 = startCol; i4 < i2; i4++) {
                    boolean[] zArr3 = zArr2[startRow];
                    if (zArr3[i4]) {
                        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[checkTiles] fail 3 tile: " + tileConfig + " row:" + startRow + " col:" + startRow, null, 8);
                        return z2;
                    }
                    zArr3[i4] = z;
                }
                startRow++;
            }
        }
        return true;
    }
}
