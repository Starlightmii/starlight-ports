package com.oplus.systemui.plugins.qs.customize.data.model;

import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileEntityCache {
    public static final SepTileEntityCache INSTANCE = new SepTileEntityCache();
    private static final HashMap<Integer, Map<String, SepTileEntity>> sepTiltEntityCache = new HashMap<>();

    public final void fillCache(int i, List<SepTileEntity> list) {
        HashMap<Integer, Map<String, SepTileEntity>> map = sepTiltEntityCache;
        map.remove(Integer.valueOf(i));
        Integer numValueOf = Integer.valueOf(i);
        List<SepTileEntity> list2 = list;
        int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        if (iMapCapacity < 16) {
            iMapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
        for (Object obj : list2) {
            linkedHashMap.put(((SepTileEntity) obj).getTileSpec(), obj);
        }
        map.put(numValueOf, linkedHashMap);
    }

    public final SepTileEntity sepTileEntity(int i, String str) {
        Map<String, SepTileEntity> map;
        HashMap<Integer, Map<String, SepTileEntity>> map2 = sepTiltEntityCache;
        Map<String, SepTileEntity> map3 = map2.get(Integer.valueOf(i));
        if (map3 == null || (map = MapsKt__MapsKt.toMutableMap(map3)) == null) {
            map = new HashMap<>();
            map2.put(Integer.valueOf(i), map);
        }
        SepTileEntity sepTileEntity = map.get(str);
        if (sepTileEntity != null) {
            return sepTileEntity;
        }
        SepTileEntity sepTileEntity2 = new SepTileEntity(i, str, LocCache.loc$default(LocCache.INSTANCE, 0, 0, 7), SpanCache.INSTANCE.spanSize(1, 1), null, null, null);
        map.put(str, sepTileEntity2);
        return sepTileEntity2;
    }
}
