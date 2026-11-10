package com.oplusos.systemui.common.util.collection;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ArrayMapExtKt {
    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    public static final <K, T extends Comparable<? super T>> T maxOrNull(ArrayMap arrayMap) {
        int i = ((SimpleArrayMap) arrayMap).size;
        T t = null;
        for (int i2 = 0; i2 < i; i2++) {
            Comparable comparable = (Comparable) arrayMap.valueAt(i2);
            if (t == null) {
                t = (T) comparable;
            } else {
                comparable.getClass();
                if (t.compareTo(comparable) < 0) {
                    t = (T) comparable;
                }
            }
        }
        return t;
    }
}
