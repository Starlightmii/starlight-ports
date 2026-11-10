package com.oplusos.systemui.common.util;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FNV1aHash {
    public static final FNV1aHash INSTANCE = new FNV1aHash();

    public final long hash(String str) {
        int length = str.length();
        long jCharAt = -3750763034362895579L;
        for (int i = 0; i < length; i++) {
            jCharAt = (jCharAt ^ ((long) str.charAt(i))) * 1099511628211L;
        }
        return jCharAt;
    }
}
