package com.oplusos.statistic;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatisticSamplingController {
    public static final OplusStatisticSamplingController INSTANCE = new OplusStatisticSamplingController();
    private static final Lazy randomPermillage$delegate = LazyKt__LazyJVMKt.lazy(new OplusStatisticSamplingController$$ExternalSyntheticLambda0());
    private static final Map<String, Integer> samplingEvents = new LinkedHashMap();

    private final int getRandomPermillage() {
        return ((Number) randomPermillage$delegate.getValue()).intValue();
    }

    public final void addSamplingEvent(String str, int i) {
        samplingEvents.put(str, Integer.valueOf(i));
    }

    public final boolean needSampling(String str) {
        Integer num = samplingEvents.get(str);
        if (num != null) {
            return INSTANCE.getRandomPermillage() <= num.intValue();
        }
        return true;
    }

    public final void removeSamplingEvent(String str) {
        samplingEvents.remove(str);
    }
}
