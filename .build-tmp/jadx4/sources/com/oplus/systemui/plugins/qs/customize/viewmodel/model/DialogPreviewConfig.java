package com.oplus.systemui.plugins.qs.customize.viewmodel.model;

import com.oplusos.systemui.common.viewholder.GridData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DialogPreviewConfig {
    private final String tileType;
    private List<GridData> tiles;

    public DialogPreviewConfig(String str, List<GridData> list) {
        this.tileType = str;
        this.tiles = list;
    }

    public final String component1() {
        return this.tileType;
    }

    public final List<GridData> component2() {
        return this.tiles;
    }

    public final DialogPreviewConfig copy(String str, List<GridData> list) {
        return new DialogPreviewConfig(str, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DialogPreviewConfig)) {
            return false;
        }
        DialogPreviewConfig dialogPreviewConfig = (DialogPreviewConfig) obj;
        return Intrinsics.areEqual(this.tileType, dialogPreviewConfig.tileType) && Intrinsics.areEqual(this.tiles, dialogPreviewConfig.tiles);
    }

    public final String getTileType() {
        return this.tileType;
    }

    public final List<GridData> getTiles() {
        return this.tiles;
    }

    public final int hashCode() {
        return this.tiles.hashCode() + (this.tileType.hashCode() * 31);
    }

    public final void setTiles(List<GridData> list) {
        this.tiles = list;
    }

    public String toString() {
        return "DialogPreviewConfig(tileType=" + this.tileType + ", tiles=" + this.tiles + ")";
    }
}
