package com.oplus.systemui.qs.base.widget;

import android.util.SparseArray;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SpanSizeLookUp {
    public boolean catchSpanSizeIndices;
    private final Function1 getSpanSizeLookup;
    private final SparseArray<SpanSize> spanSizeCache = new SparseArray<>();

    public SpanSizeLookUp(Function1 function1) {
        this.getSpanSizeLookup = function1;
    }

    public final boolean getCatchSpanSizeIndices() {
        return this.catchSpanSizeIndices;
    }

    public SpanSize getSpanSize(int i, int i2, int i3) {
        if (!this.catchSpanSizeIndices) {
            return getSpanSizeInner(i, i2, i3);
        }
        SpanSize spanSize = this.spanSizeCache.get(i);
        if (spanSize != null) {
            return spanSize;
        }
        SpanSize spanSizeInner = getSpanSizeInner(i, i2, i3);
        this.spanSizeCache.put(i, spanSizeInner);
        return spanSizeInner;
    }

    public final SpanSize getSpanSizeInner(int i, int i2, int i3) {
        SpanSize spanSize;
        Function1 function1 = this.getSpanSizeLookup;
        if (function1 == null || (spanSize = (SpanSize) function1.invoke(Integer.valueOf(i))) == null) {
            return SpanCache.INSTANCE.spanSize(1, 1);
        }
        if (i3 != 1 || spanSize.getColSpan() <= i2) {
            return (i3 != 0 || spanSize.getRowSpan() <= i2) ? spanSize : SpanCache.INSTANCE.spanSize(spanSize.getColSpan(), i2);
        }
        return SpanCache.INSTANCE.spanSize(i2, spanSize.getRowSpan());
    }

    public Integer getSpecialSizeForFullSpan(int i) {
        return null;
    }

    public final void invalidateSpanSizeCache() {
        this.spanSizeCache.clear();
    }

    public final void setCatchSpanSizeIndices(boolean z) {
        this.catchSpanSizeIndices = z;
        if (z) {
            return;
        }
        this.spanSizeCache.clear();
    }
}
