package com.oplusos.systemui.common.viewholder;

import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CellInfo {
    public final int cellHeight;
    public final int cellWidth;
    public final int horizontalSpace;
    public final int verticalSpace;

    public CellInfo(int i, int i2, int i3, int i4) {
        this.cellWidth = i;
        this.cellHeight = i2;
        this.horizontalSpace = i3;
        this.verticalSpace = i4;
    }

    public final CellInfo copy(int i, int i2, int i3, int i4) {
        return new CellInfo(i, i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CellInfo)) {
            return false;
        }
        CellInfo cellInfo = (CellInfo) obj;
        return this.cellWidth == cellInfo.cellWidth && this.cellHeight == cellInfo.cellHeight && this.horizontalSpace == cellInfo.horizontalSpace && this.verticalSpace == cellInfo.verticalSpace;
    }

    public final int getCellHeight() {
        return this.cellHeight;
    }

    public final int getCellWidth() {
        return this.cellWidth;
    }

    public final int getHorizontalSpace() {
        return this.horizontalSpace;
    }

    public final int getVerticalSpace() {
        return this.verticalSpace;
    }

    public final int hashCode() {
        return Integer.hashCode(this.verticalSpace) + MonoSpline$.ExternalSyntheticOutline0.m(this.horizontalSpace, MonoSpline$.ExternalSyntheticOutline0.m(this.cellHeight, Integer.hashCode(this.cellWidth) * 31, 31), 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CellInfo(cellWidth=");
        sb.append(this.cellWidth);
        sb.append(", cellHeight=");
        sb.append(this.cellHeight);
        sb.append(", horizontalSpace=");
        sb.append(this.horizontalSpace);
        sb.append(", verticalSpace=");
        return FloatList$.ExternalSyntheticOutline0.m(sb, this.verticalSpace, ')');
    }
}
