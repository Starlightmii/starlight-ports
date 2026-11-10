package com.oplus.systemui.qs.base.widget;

import android.graphics.Rect;
import android.util.SparseArray;
import com.oplusos.systemui.common.model.SpanSize;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RectHelper {
    private List<Rect> freeRects = new ArrayList();
    private final SparseArray<Rect> itemSpanRects = new SparseArray<>();
    public final int orientation;
    private final Comparator<Rect> rectComparator;
    public final int spanCount;

    public RectHelper(int i, int i2) {
        this.orientation = i;
        this.spanCount = i2;
        RectHelper$rectComparator$1 rectHelper$rectComparator$1 = new RectHelper$rectComparator$1();
        rectHelper$rectComparator$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.rectComparator = rectHelper$rectComparator$1;
        this.freeRects.add(i == 1 ? new Rect(0, 0, i2, Integer.MAX_VALUE) : new Rect(0, 0, Integer.MAX_VALUE, i2));
    }

    public final Pair<Rect, Rect> getItemRect(int i, SpanSize spanSize) {
        if (this.itemSpanRects.get(i) != null) {
            return new Pair<>(this.itemSpanRects.get(i), new Rect());
        }
        if (SpannedGridLayoutManagerKt.isFullSpan(spanSize, this.spanCount, this.orientation)) {
            List<Rect> list = this.freeRects;
            this.freeRects = list.subList(list.size() - 1, this.freeRects.size());
        }
        for (Rect rect : this.freeRects) {
            int i2 = rect.left;
            if (rect.contains(new Rect(i2, rect.top, spanSize.getColSpan() + i2, spanSize.getRowSpan() + rect.top))) {
                int i3 = rect.left;
                return new Pair<>(new Rect(i3, rect.top, spanSize.getColSpan() + i3, spanSize.getRowSpan() + rect.top), rect);
            }
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m("Collection contains no element matching the predicate.");
        return null;
    }

    public final void pushRect(int i, Rect rect, Rect rect2) {
        this.itemSpanRects.put(i, rect);
        this.freeRects.remove(rect2);
        if (rect2.right > rect.right) {
            this.freeRects.add(new Rect(rect.right, rect.top, rect2.right, rect.bottom));
        }
        if (rect2.bottom > rect.bottom) {
            this.freeRects.add(new Rect(rect2.left, rect.bottom, rect2.right, rect2.bottom));
        }
        CollectionsKt__MutableCollectionsJVMKt.sortWith(this.freeRects, this.rectComparator);
    }
}
