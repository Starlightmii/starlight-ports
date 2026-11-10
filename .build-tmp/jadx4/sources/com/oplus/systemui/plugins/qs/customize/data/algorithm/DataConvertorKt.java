package com.oplus.systemui.plugins.qs.customize.data.algorithm;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class DataConvertorKt {
    public static final List<TileConfig> mergeTwoCols(List<TileConfig> list) {
        List<TileConfig> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        for (TileConfig tileConfig : list2) {
            Loc locMergeTwoCols = mergeTwoCols(tileConfig.getLocation());
            SpanSize spanSizeMergeTwoCols = mergeTwoCols(tileConfig.getSize());
            List<SpanSize> supportSizes = tileConfig.getSupportSizes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(supportSizes, 10));
            for (SpanSize spanSize : supportSizes) {
                arrayList2.add(SpanCache.INSTANCE.spanSize(spanSize.getColSpan() / 2, spanSize.getRowSpan()));
            }
            arrayList.add(TileConfig.copy$default(tileConfig, null, spanSizeMergeTwoCols, locMergeTwoCols, arrayList2, 1));
        }
        return CollectionsKt___CollectionsKt.toMutableList(arrayList);
    }

    public static final List<TileConfig> revertToTwoCols(List<TileConfig> list) {
        List<TileConfig> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        for (TileConfig tileConfig : list2) {
            Loc locRevertToTwoCols = revertToTwoCols(tileConfig.getLocation());
            SpanSize spanSizeRevertToTwoCols = revertToTwoCols(tileConfig.getSize());
            List<SpanSize> supportSizes = tileConfig.getSupportSizes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(supportSizes, 10));
            for (SpanSize spanSize : supportSizes) {
                arrayList2.add(SpanCache.INSTANCE.spanSize(spanSize.getColSpan() == 0 ? 1 : spanSize.getColSpan() * 2, spanSize.getRowSpan()));
            }
            arrayList.add(TileConfig.copy$default(tileConfig, null, spanSizeRevertToTwoCols, locRevertToTwoCols, arrayList2, 1));
        }
        return arrayList;
    }

    public static final Loc mergeTwoCols(Loc loc) {
        return LocCache.INSTANCE.loc(loc.getPageIndex(), loc.getStartCol() / 2, loc.getStartRow());
    }

    public static final SpanSize mergeTwoCols(SpanSize spanSize) {
        return SpanCache.INSTANCE.spanSize(spanSize.getColSpan() / 2, spanSize.getRowSpan());
    }

    public static final GridInfoEntity mergeTwoCols(GridInfoEntity gridInfoEntity) {
        return gridInfoEntity.copy(gridInfoEntity.userId, gridInfoEntity.getGridCol() / 2, gridInfoEntity.gridRow);
    }

    public static final Loc revertToTwoCols(Loc loc) {
        return LocCache.INSTANCE.loc(loc.getPageIndex(), loc.getStartCol() * 2, loc.getStartRow());
    }

    public static final SpanSize revertToTwoCols(SpanSize spanSize) {
        return SpanCache.INSTANCE.spanSize(spanSize.getColSpan() * 2, spanSize.getRowSpan());
    }
}
