package com.oplus.systemui.plugins.qs.customize.data.repo;

import com.android.systemui.plugins.qs.TileSpecOperate;
import com.oplus.systemui.plugins.qs.customize.data.model.TileConfig;
import com.oplusos.systemui.common.model.Loc;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISepTileConfigCtrlAB {
    Object moveTile(List<String> list, int i, Loc loc, boolean z, Continuation continuation);

    Object operateTiles(List<? extends TileSpecOperate> list, Continuation continuation);

    Object prependDefault(Continuation continuation);

    Object reconcileRestore(String str, boolean z, Set<String> set, Set<String> set2, Continuation continuation);

    Object removeTiles(Collection<String> collection, Continuation continuation);

    Object restoreTileConfig(List<TileConfig> list, List<TileConfig> list2, boolean z, Continuation continuation);

    Object setTileConfigs(List<TileConfig> list, List<TileConfig> list2, Continuation continuation);

    Object setTiles(List<String> list, Continuation continuation);

    Object swapTile(String str, String str2, boolean z, Continuation continuation);
}
