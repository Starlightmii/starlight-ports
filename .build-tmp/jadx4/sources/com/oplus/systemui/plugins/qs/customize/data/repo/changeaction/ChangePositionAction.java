package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ChangePositionAction extends BaseActionForB {
    public int fromPosition;
    public int toPosition;

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.changeaction.BaseActionForB
    public Object apply(List<TileConfig> list, Continuation continuation) {
        int i = this.toPosition;
        int i2 = this.fromPosition;
        if (i == i2) {
            return list;
        }
        List mutableList = CollectionsKt___CollectionsKt.toMutableList(list);
        TileConfig tileConfig = (TileConfig) mutableList.remove(i2);
        int size = mutableList.size();
        if (i > size) {
            i = size;
        }
        mutableList.add(i, tileConfig);
        return CollectionsKt___CollectionsKt.toList(mutableList);
    }
}
