package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import com.oplus.systemui.plugins.qs.customize.viewmodel.model.TileUiType;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileViewModel {
    public final boolean isSystem;
    private final QSTile qsTile;
    private final SepTileType sepTileType;
    private final String spec;
    private final TileUiType uiType;

    public TileViewModel(QSTile qSTile, boolean z, SepTileType sepTileType, String str, TileUiType tileUiType) {
        this.qsTile = qSTile;
        this.isSystem = z;
        this.sepTileType = sepTileType;
        this.spec = str;
        this.uiType = tileUiType;
    }

    public final QSTile component1() {
        return this.qsTile;
    }

    public final SepTileType component3() {
        return this.sepTileType;
    }

    public final String component4() {
        return this.spec;
    }

    public final TileUiType component5() {
        return this.uiType;
    }

    public final TileViewModel copy(QSTile qSTile, boolean z, SepTileType sepTileType, String str, TileUiType tileUiType) {
        return new TileViewModel(qSTile, z, sepTileType, str, tileUiType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileViewModel)) {
            return false;
        }
        TileViewModel tileViewModel = (TileViewModel) obj;
        return Intrinsics.areEqual(this.qsTile, tileViewModel.qsTile) && this.isSystem == tileViewModel.isSystem && this.sepTileType == tileViewModel.sepTileType && Intrinsics.areEqual(this.spec, tileViewModel.spec) && this.uiType == tileViewModel.uiType;
    }

    public final QSTile getQsTile() {
        return this.qsTile;
    }

    public final SepTileType getSepTileType() {
        return this.sepTileType;
    }

    public final String getSpec() {
        return this.spec;
    }

    public final TileUiType getUiType() {
        return this.uiType;
    }

    public final int hashCode() {
        return this.uiType.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((this.sepTileType.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.qsTile.hashCode() * 31, 31, this.isSystem)) * 31, 31, this.spec);
    }

    public String toString() {
        return "TileViewModel(qsTile=" + this.qsTile + ", isSystem=" + this.isSystem + ", sepTileType=" + this.sepTileType + ", spec=" + this.spec + ", uiType=" + this.uiType + ")";
    }
}
