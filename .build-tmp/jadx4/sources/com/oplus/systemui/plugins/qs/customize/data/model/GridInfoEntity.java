package com.oplus.systemui.plugins.qs.customize.data.model;

import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GridInfoEntity {
    public final int gridCol;
    public final int gridRow;
    public final int userId;

    public GridInfoEntity(int i, int i2, int i3) {
        this.userId = i;
        this.gridCol = i2;
        this.gridRow = i3;
    }

    public final GridInfoEntity copy(int i, int i2, int i3) {
        return new GridInfoEntity(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GridInfoEntity)) {
            return false;
        }
        GridInfoEntity gridInfoEntity = (GridInfoEntity) obj;
        return this.userId == gridInfoEntity.userId && this.gridCol == gridInfoEntity.gridCol && this.gridRow == gridInfoEntity.gridRow;
    }

    public final int getGridCol() {
        return this.gridCol;
    }

    public final int getGridRow() {
        return this.gridRow;
    }

    public final int getUserId() {
        return this.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.gridRow) + MonoSpline$.ExternalSyntheticOutline0.m(this.gridCol, Integer.hashCode(this.userId) * 31, 31);
    }

    public String toString() {
        return SurfaceContainer$.ExternalSyntheticOutline0.m(this.gridRow, ")", FloatList$.ExternalSyntheticOutline0.m("GridInfoEntity(userId=", this.userId, this.gridCol, ", gridCol=", ", gridRow="));
    }
}
