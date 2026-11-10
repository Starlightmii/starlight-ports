package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.oplus.systemui.plugins.qs.customize.data.algorithm.BaseInputData;
import com.oplus.systemui.plugins.qs.customize.data.algorithm.LocationAlgManager;
import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RemoveEmptyPage extends BaseActionForA {
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForA
    public Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, Continuation continuation) {
        return LocationAlgManager.INSTANCE.startLocationAlgorithm(LocationAlgManager.ACTION_REMOVE_EMPTY_PAGE, new BaseInputData(CollectionsKt___CollectionsKt.toMutableList(list), gridInfoEntity));
    }
}
