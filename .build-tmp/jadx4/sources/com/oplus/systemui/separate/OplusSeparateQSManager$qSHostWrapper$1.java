package com.oplus.systemui.separate;

import com.android.systemui.plugins.qs.QSHostWrapper;
import java.lang.invoke.VarHandle;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$qSHostWrapper$1 implements QSHostWrapper {
    public /* synthetic */ OplusSeparateQSManager this$0;

    public Flow getCurrentTiles() {
        StateFlow currentTiles = this.this$0.currentTilesInteractor.getCurrentTiles();
        OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$map$1 oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$map$1 = new OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$map$1();
        oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$map$1.$this_unsafeTransform$inlined = currentTiles;
        VarHandle.storeStoreFence();
        return oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$map$1;
    }

    public Flow getRemoveTiles() {
        OplusSeparateQSManager oplusSeparateQSManager = this.this$0;
        return FlowKt.transformLatest(oplusSeparateQSManager.currentTilesInteractor.getUserId(), new OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1(null, oplusSeparateQSManager));
    }

    public StateFlow getUserId() {
        return this.this$0.currentTilesInteractor.getUserId();
    }
}
