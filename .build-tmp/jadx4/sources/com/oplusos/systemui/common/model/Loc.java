package com.oplusos.systemui.common.model;

import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import java.io.Serializable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Loc implements Serializable {
    public static final Companion Companion = new Companion();
    private static final long serialVersionUID = 1;
    private final int pageIndex;
    private final int startCol;
    private final int startRow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public Loc(int i, int i2, int i3) {
        this.pageIndex = i;
        this.startCol = i2;
        this.startRow = i3;
    }

    public final Loc copy(int i, int i2, int i3) {
        return new Loc(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Loc)) {
            return false;
        }
        Loc loc = (Loc) obj;
        return this.pageIndex == loc.pageIndex && this.startCol == loc.startCol && this.startRow == loc.startRow;
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final int getStartCol() {
        return this.startCol;
    }

    public final int getStartRow() {
        return this.startRow;
    }

    public final int hashCode() {
        return Integer.hashCode(this.startRow) + MonoSpline$.ExternalSyntheticOutline0.m(this.startCol, Integer.hashCode(this.pageIndex) * 31, 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Loc(pageIndex=");
        sb.append(this.pageIndex);
        sb.append(", startCol=");
        sb.append(this.startCol);
        sb.append(", startRow=");
        return FloatList$.ExternalSyntheticOutline0.m(sb, this.startRow, ')');
    }
}
