package com.oplus.systemui.notification.lockscreen.statistic;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract /* synthetic */ class LockScreenStatisticUtil$$ExternalSyntheticOutline0 {
    public static void m(AtomicInteger atomicInteger, int i, HashMap map, String str) {
        map.put(str, String.valueOf(atomicInteger.getAndSet(i)));
    }
}
