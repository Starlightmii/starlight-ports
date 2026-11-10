package com.oplus.systemui.qs.pipeline.data.repository;

import com.android.systemui.plugins.qs.SepTileSpecRepository;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileSpecRepositoryStub implements SepTileSpecRepository {
    public Object addTile(int i, String str, int i2, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object reconcileRestore(int i, String str, boolean z, Set<String> set, Set<String> set2, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object removeTiles(int i, Collection<String> collection, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object setTiles(int i, List<String> list, Continuation continuation) {
        return Unit.INSTANCE;
    }

    public Object tilesSpecs(int i, Continuation continuation) {
        return FlowKt.flowOf(new List[0]);
    }
}
