package com.oplus.systemui.qs.base.widget;

import com.oplusos.systemui.common.model.SpanSize;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SpannedGridLayoutManagerKt {
    public static final boolean isFullSpan(SpanSize spanSize, int i, int i2) {
        if (i2 == 1) {
            return spanSize.getColSpan() == i;
        }
        return spanSize.getRowSpan() == i;
    }
}
