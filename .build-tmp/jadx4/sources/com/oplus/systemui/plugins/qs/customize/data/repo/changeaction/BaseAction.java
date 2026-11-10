package com.oplus.systemui.plugins.qs.customize.data.repo.changeaction;

import com.oplus.systemui.plugins.qs.customize.data.model.GridInfoEntity;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import java.util.List;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BaseAction {
    public final boolean needPersist;

    public BaseAction(boolean z) {
        this.needPersist = z;
    }

    public abstract Object apply(List<TileConfig> list, GridInfoEntity gridInfoEntity, List<TileConfig> list2, Continuation continuation);

    public boolean getNeedPersist() {
        return this.needPersist;
    }
}
