package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISepTileConfigRepository extends ISepTileConfigFlow, ISepTileConfigCtrlA, ISepTileConfigCtrlB, ISepTileConfigCtrlAB {
    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /* synthetic */ Object addTileWithLoc(String str, int i, Loc loc, SpanSize spanSize, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlB
    /* synthetic */ Object addTilesNonLoc(Collection collection, int i, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /* synthetic */ Object changeGridInfo(int i, int i2, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /* synthetic */ Object clipSize(String str, SpanSize spanSize, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /* synthetic */ Object deform(int i, SpanSize spanSize, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlB
    /* synthetic */ Object dragItem(int i, int i2, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /* synthetic */ Object dragItem(int i, Loc loc, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigFlow
    /* synthetic */ Object gridInfo(Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object moveTile(List list, int i, Loc loc, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object operateTiles(List list, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object prependDefault(Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object reconcileRestore(String str, boolean z, Set set, Set set2, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlA
    /* synthetic */ Object removeEmptyPage(Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object removeTiles(Collection collection, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object restoreTileConfig(List list, List list2, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object setTileConfigs(List list, List list2, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object setTiles(List list, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigCtrlAB
    /* synthetic */ Object swapTile(String str, String str2, boolean z, Continuation continuation);

    @Override // com.oplus.systemui.plugins.qs.customize.data.repo.ISepTileConfigFlow
    /* synthetic */ Object tileConfigs(Continuation continuation);
}
