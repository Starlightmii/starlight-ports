package com.oplus.systemui.notification.icdf;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class IcdfHeadsUpDecisionCache {
    public static final Companion Companion = new Companion();
    private final ConcurrentHashMap<String, Boolean> cache = new ConcurrentHashMap<>(32);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void clear() {
        this.cache.clear();
    }

    public final void put(String str, boolean z) {
        this.cache.put(str, Boolean.valueOf(z));
    }

    public final Boolean take(String str) {
        return this.cache.remove(str);
    }
}
