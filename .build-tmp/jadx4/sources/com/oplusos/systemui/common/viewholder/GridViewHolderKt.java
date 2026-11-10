package com.oplusos.systemui.common.viewholder;

import com.oplusos.systemui.common.util.FNV1aHash;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class GridViewHolderKt {
    public static final long toUniqueLong(String str) {
        return FNV1aHash.INSTANCE.hash(str);
    }
}
