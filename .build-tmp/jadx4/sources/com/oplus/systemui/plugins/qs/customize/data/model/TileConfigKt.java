package com.oplus.systemui.plugins.qs.customize.data.model;

import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.SpanSize;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileConfigKt {
    public static final TileConfig replaceLoc(TileConfig tileConfig, Loc loc) {
        return TileConfig.copy$default(tileConfig, null, null, loc, null, 11);
    }

    public static final TileConfig replaceSize(TileConfig tileConfig, SpanSize spanSize) {
        return TileConfig.copy$default(tileConfig, null, spanSize, null, null, 13);
    }
}
