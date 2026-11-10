package com.oplusos.systemui.common.model;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpanCache {
    public static final SpanCache INSTANCE = new SpanCache();
    public static final SpanSize EMPTY_SPANSIZE = new SpanSize(0, 0);
    public static final SpanSize ONE_X_ONE = new SpanSize(1, 1);
    public static final SpanSize ONE_X_TWO = new SpanSize(1, 2);
    public static final SpanSize TWO_X_ONE = new SpanSize(2, 1);
    public static final SpanSize TWO_X_TWO = new SpanSize(2, 2);
    private static final HashSet<SpanSize> constantCache = new HashSet<>();

    public final SpanSize spanSize(int i, int i2) {
        Object next;
        SpanSize spanSize;
        if (i == 1 && i2 == 1) {
            return ONE_X_ONE;
        }
        if (i == 1 && i2 == 2) {
            return ONE_X_TWO;
        }
        if (i == 2 && i2 == 1) {
            return TWO_X_ONE;
        }
        if (i == 2 && i2 == 2) {
            return TWO_X_TWO;
        }
        synchronized (this) {
            try {
                Iterator<T> it = constantCache.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    SpanSize spanSize2 = (SpanSize) next;
                    if (spanSize2.getColSpan() == i && spanSize2.getRowSpan() == i2) {
                        break;
                    }
                }
                spanSize = (SpanSize) next;
                if (spanSize == null) {
                    spanSize = new SpanSize(i, i2);
                    constantCache.add(spanSize);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return spanSize;
    }
}
