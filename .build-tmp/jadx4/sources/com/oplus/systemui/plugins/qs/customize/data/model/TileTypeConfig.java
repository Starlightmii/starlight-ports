package com.oplus.systemui.plugins.qs.customize.data.model;

import com.oplus.systemui.plugins.qs.customize.data.SepTileType;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileTypeConfig {
    private final List<TilePreviewsData> tileList;
    private final SepTileType tileType;

    public TileTypeConfig(SepTileType sepTileType, List<TilePreviewsData> list) {
        this.tileType = sepTileType;
        this.tileList = list;
    }

    public final SepTileType component1() {
        return this.tileType;
    }

    public final List<TilePreviewsData> component2() {
        return this.tileList;
    }

    public final TileTypeConfig copy(SepTileType sepTileType, List<TilePreviewsData> list) {
        return new TileTypeConfig(sepTileType, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileTypeConfig)) {
            return false;
        }
        TileTypeConfig tileTypeConfig = (TileTypeConfig) obj;
        return this.tileType == tileTypeConfig.tileType && Intrinsics.areEqual(this.tileList, tileTypeConfig.tileList);
    }

    public final List<TilePreviewsData> getTileList() {
        return this.tileList;
    }

    public final SepTileType getTileType() {
        return this.tileType;
    }

    public final int hashCode() {
        return this.tileList.hashCode() + (this.tileType.hashCode() * 31);
    }

    public String toString() {
        return "TileTypeConfig(tileType=" + this.tileType + ", tileList=" + this.tileList + ")";
    }
}
