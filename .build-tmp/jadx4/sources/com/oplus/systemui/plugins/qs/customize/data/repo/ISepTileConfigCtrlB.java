package com.oplus.systemui.plugins.qs.customize.data.repo;

import java.util.Collection;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface ISepTileConfigCtrlB {
    Object addTilesNonLoc(Collection<String> collection, int i, boolean z, Continuation continuation);

    Object dragItem(int i, int i2, boolean z, Continuation continuation);
}
