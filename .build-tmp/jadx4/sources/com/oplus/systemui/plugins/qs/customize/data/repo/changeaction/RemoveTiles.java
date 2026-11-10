package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgManager;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.RearrangeInputData;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.lang.invoke.VarHandle;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoveTiles extends BaseActionForAB {
    public final boolean recalculate;
    private final Collection<String> tileSpecs;

    public RemoveTiles(Collection<String> collection, boolean z) {
        super(true);
        this.tileSpecs = collection;
        this.recalculate = z;
    }

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForAB
    public Object applyAB(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation) {
        List<TileConfig> mutableList = CollectionsKt___CollectionsKt.toMutableList(list);
        RemoveTiles$applyAB$changed$1 removeTiles$applyAB$changed$1 = new RemoveTiles$applyAB$changed$1(0);
        removeTiles$applyAB$changed$1.this$0 = this;
        VarHandle.storeStoreFence();
        boolean zRemoveIf = mutableList.removeIf(removeTiles$applyAB$changed$1);
        if (this.recalculate && zRemoveIf) {
            mutableList = LocationAlgManager.INSTANCE.startLocationAlgorithm(LocationAlgManager.ACTION_VACANCY_COMPLEMENT, new RearrangeInputData(mutableList, gridInfoEntity));
        }
        List mutableList2 = CollectionsKt___CollectionsKt.toMutableList(list2);
        RemoveTiles$applyAB$changed$1 removeTiles$applyAB$changed$2 = new RemoveTiles$applyAB$changed$1(1);
        removeTiles$applyAB$changed$2.this$0 = this;
        VarHandle.storeStoreFence();
        mutableList2.removeIf(removeTiles$applyAB$changed$2);
        return new Pair(mutableList, mutableList2);
    }

    public final Collection<String> component1() {
        return this.tileSpecs;
    }

    public final RemoveTiles copy(Collection<String> collection, boolean z) {
        return new RemoveTiles(collection, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RemoveTiles)) {
            return false;
        }
        RemoveTiles removeTiles = (RemoveTiles) obj;
        return Intrinsics.areEqual(this.tileSpecs, removeTiles.tileSpecs) && this.recalculate == removeTiles.recalculate;
    }

    public final Collection<String> getTileSpecs() {
        return this.tileSpecs;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.recalculate) + (this.tileSpecs.hashCode() * 31);
    }

    public String toString() {
        return "RemoveTiles(tileSpecs=" + this.tileSpecs + ", recalculate=" + this.recalculate + ")";
    }
}
