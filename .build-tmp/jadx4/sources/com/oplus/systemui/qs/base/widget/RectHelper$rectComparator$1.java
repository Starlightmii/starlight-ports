package com.oplus.systemui.qs.base.widget;

import android.graphics.Rect;
import java.util.Comparator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RectHelper$rectComparator$1 implements Comparator {
    public /* synthetic */ RectHelper this$0;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Rect rect = (Rect) obj;
        Rect rect2 = (Rect) obj2;
        if (this.this$0.orientation == 1) {
            int i = rect.top;
            int i2 = rect2.top;
            if (i == i2) {
                if (rect.left < rect2.left) {
                    return -1;
                }
            } else if (i < i2) {
                return -1;
            }
        } else {
            int i3 = rect.left;
            int i4 = rect2.left;
            if (i3 == i4) {
                if (rect.top < rect2.top) {
                    return -1;
                }
            } else if (i3 < i4) {
                return -1;
            }
        }
        return 1;
    }
}
