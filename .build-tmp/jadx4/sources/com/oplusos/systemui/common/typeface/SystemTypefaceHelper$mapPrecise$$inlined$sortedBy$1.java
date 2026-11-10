package com.oplusos.systemui.common.typeface;

import java.util.Comparator;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemTypefaceHelper$mapPrecise$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt__ComparisonsKt.compareValues((Integer) ((Pair) obj).getFirst(), (Integer) ((Pair) obj2).getFirst());
    }
}
