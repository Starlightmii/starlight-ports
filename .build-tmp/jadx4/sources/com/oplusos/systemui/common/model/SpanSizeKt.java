package com.oplusos.systemui.common.model;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class SpanSizeKt {
    public static final int getArea(SpanSize spanSize) {
        return spanSize.getRowSpan() * spanSize.getColSpan();
    }

    public static final boolean isFourXTwo(SpanSize spanSize) {
        return spanSize.getColSpan() == 4 && spanSize.getRowSpan() == 2;
    }

    public static final boolean isLessThan(SpanSize spanSize, SpanSize spanSize2) {
        if (spanSize.getColSpan() >= spanSize2.getColSpan() || spanSize.getRowSpan() > spanSize2.getRowSpan()) {
            return spanSize.getColSpan() <= spanSize2.getColSpan() && spanSize.getRowSpan() < spanSize2.getRowSpan();
        }
        return true;
    }

    public static final boolean isOneXOne(SpanSize spanSize) {
        return spanSize.getColSpan() == 1 && spanSize.getRowSpan() == 1;
    }

    public static final boolean isTwoXOne(SpanSize spanSize) {
        return spanSize.getColSpan() == 2 && spanSize.getRowSpan() == 1;
    }

    public static final boolean isTwoXTwo(SpanSize spanSize) {
        return spanSize.getColSpan() == 2 && spanSize.getRowSpan() == 2;
    }
}
