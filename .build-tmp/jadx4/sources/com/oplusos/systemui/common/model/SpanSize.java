package com.oplusos.systemui.common.model;

import androidx.collection.FloatList$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SpanSize {
    public final int colSpan;
    public final int rowSpan;

    public SpanSize(int i, int i2) {
        this.colSpan = i;
        this.rowSpan = i2;
    }

    public final SpanSize copy$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(int i, int i2) {
        return new SpanSize(i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpanSize)) {
            return false;
        }
        SpanSize spanSize = (SpanSize) obj;
        return this.colSpan == spanSize.colSpan && this.rowSpan == spanSize.rowSpan;
    }

    public final int getColSpan() {
        return this.colSpan;
    }

    public final int getRowSpan() {
        return this.rowSpan;
    }

    public final int hashCode() {
        return Integer.hashCode(this.rowSpan) + (Integer.hashCode(this.colSpan) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SpanSize(colSpan=");
        sb.append(this.colSpan);
        sb.append(", rowSpan=");
        return FloatList$.ExternalSyntheticOutline0.m(sb, this.rowSpan, ')');
    }
}
