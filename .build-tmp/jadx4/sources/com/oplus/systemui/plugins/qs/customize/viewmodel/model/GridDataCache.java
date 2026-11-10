package com.oplus.systemui.plugins.qs.customize.viewmodel.model;

import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.TileViewModel;
import com.oplusos.systemui.common.model.SpanSize;
import com.oplusos.systemui.common.viewholder.GridData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GridDataCache {
    private static final String TAG = "GridDataCache";
    public static final GridDataCache INSTANCE = new GridDataCache();
    private static final HashMap<Long, GridData> constantCache = new HashMap<>();
    private static final HashMap<String, TileViewModel> tileViewModelCache = new HashMap<>();

    public static void clear() {
        constantCache.clear();
        tileViewModelCache.clear();
    }

    public final GridData gridData(long j, SpanSize spanSize, Object obj, List<SpanSize> list, boolean z) {
        boolean z2;
        if (-1 == j) {
            return new GridData(j, spanSize, obj, list, z);
        }
        GridData gridData = constantCache.get(Long.valueOf(j));
        if (gridData == null) {
            gridData = new GridData(j, spanSize, obj, list, z);
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            z2 = Intrinsics.areEqual(gridData.getSize(), spanSize) && Intrinsics.areEqual(gridData.getData(), obj) && Intrinsics.areEqual(gridData.getAllValidSize(), list) && gridData.isEnabled == z;
        }
        return z2 ? gridData : new GridData(j, spanSize, obj, list, z);
    }

    public final void refreshCache(List<GridData> list) {
        HashMap<Long, GridData> map = constantCache;
        map.clear();
        List<GridData> list2 = list;
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Object obj : list2) {
            linkedHashMap.put(Long.valueOf(((GridData) obj).getUniqueId()), obj);
        }
        map.putAll(linkedHashMap);
        tileViewModelCache.clear();
        for (GridData gridData : list2) {
            if (gridData.getData() instanceof TileViewModel) {
                TileViewModel tileViewModel = (TileViewModel) gridData.getData();
                tileViewModelCache.put(tileViewModel.getSpec(), tileViewModel);
            }
        }
    }

    public final TileViewModel tileViewModel(QSTile qSTile, boolean z, SepTileType sepTileType, String str, TileUiType tileUiType) {
        boolean z2;
        TileViewModel tileViewModel = tileViewModelCache.get(str);
        if (tileViewModel == null) {
            tileViewModel = new TileViewModel(qSTile, z, sepTileType, str, tileUiType);
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            z2 = Intrinsics.areEqual(tileViewModel.getQsTile(), qSTile) && tileViewModel.isSystem == z && tileViewModel.getSepTileType() == sepTileType && tileViewModel.getUiType() == tileUiType;
        }
        return z2 ? tileViewModel : new TileViewModel(qSTile, z, sepTileType, str, tileUiType);
    }
}
