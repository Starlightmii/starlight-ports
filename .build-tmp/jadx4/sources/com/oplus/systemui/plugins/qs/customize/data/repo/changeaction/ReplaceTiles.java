package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import android.text.TextUtils;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.model.LocCache;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ReplaceTiles extends BaseActionForAB {
    private final List<TileConfig> newTilesNonLoc;
    private final List<TileConfig> newTilesWithLoc;

    public ReplaceTiles(List<TileConfig> list, List<TileConfig> list2) {
        super(true);
        this.newTilesWithLoc = list;
        this.newTilesNonLoc = list2;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForAB
    public Object applyAB(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation) {
        List<TileConfig> list3 = this.newTilesWithLoc;
        if (list3 != null) {
            list = list3;
        }
        List<TileConfig> list4 = this.newTilesNonLoc;
        if (list4 != null) {
            list2 = list4;
        }
        List listPlus = CollectionsKt___CollectionsKt.plus(list, list2);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listPlus) {
            if (!TextUtils.isEmpty(((TileConfig) obj).getTileSpec())) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            if (hashSet.add(((TileConfig) obj2).getTileSpec())) {
                arrayList2.add(obj2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        int size2 = arrayList2.size();
        int i3 = 0;
        while (i3 < size2) {
            Object obj3 = arrayList2.get(i3);
            i3++;
            TileConfig tileConfig = (TileConfig) obj3;
            if (!Intrinsics.areEqual(tileConfig.getLocation(), LocCache.INSTANCE.getINVALID_LOC()) && tileConfig.getSize().getColSpan() > 1) {
                arrayList3.add(obj3);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        int size3 = arrayList2.size();
        while (i < size3) {
            Object obj4 = arrayList2.get(i);
            i++;
            if (Intrinsics.areEqual(((TileConfig) obj4).getLocation(), LocCache.INSTANCE.getINVALID_LOC())) {
                arrayList4.add(obj4);
            }
        }
        return new Pair(arrayList3, arrayList4);
    }

    public final List<TileConfig> component1() {
        return this.newTilesWithLoc;
    }

    public final List<TileConfig> component2() {
        return this.newTilesNonLoc;
    }

    public final ReplaceTiles copy(List<TileConfig> list, List<TileConfig> list2) {
        return new ReplaceTiles(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReplaceTiles)) {
            return false;
        }
        ReplaceTiles replaceTiles = (ReplaceTiles) obj;
        return Intrinsics.areEqual(this.newTilesWithLoc, replaceTiles.newTilesWithLoc) && Intrinsics.areEqual(this.newTilesNonLoc, replaceTiles.newTilesNonLoc);
    }

    public final List<TileConfig> getNewTilesNonLoc() {
        return this.newTilesNonLoc;
    }

    public final List<TileConfig> getNewTilesWithLoc() {
        return this.newTilesWithLoc;
    }

    public final int hashCode() {
        List<TileConfig> list = this.newTilesWithLoc;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<TileConfig> list2 = this.newTilesNonLoc;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "ReplaceTiles(newTilesWithLoc=" + this.newTilesWithLoc + ", newTilesNonLoc=" + this.newTilesNonLoc + ")";
    }
}
