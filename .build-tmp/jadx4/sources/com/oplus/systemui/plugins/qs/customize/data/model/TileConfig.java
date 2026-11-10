package com.oplus.systemui.plugins.qs.customize.data.model;

import androidx.core.view.DisplayShapeCompat$;
import com.oplusos.systemui.common.model.Loc;
import com.oplusos.systemui.common.model.LocCache;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileConfig {
    private final Loc location;
    private final SpanSize size;
    private final List<SpanSize> supportSizes;
    private final String tileSpec;

    public /* synthetic */ TileConfig(String str, SpanSize spanSize, List list, int i) {
        this(str, (i & 2) != 0 ? SpanCache.INSTANCE.spanSize(1, 1) : spanSize, LocCache.INSTANCE.getINVALID_LOC(), (List<SpanSize>) list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TileConfig copy$default(TileConfig tileConfig, String str, SpanSize spanSize, Loc loc, List list, int i) {
        if ((i & 1) != 0) {
            str = tileConfig.tileSpec;
        }
        if ((i & 2) != 0) {
            spanSize = tileConfig.size;
        }
        if ((i & 4) != 0) {
            loc = tileConfig.location;
        }
        if ((i & 8) != 0) {
            list = tileConfig.supportSizes;
        }
        return tileConfig.copy(str, spanSize, loc, list);
    }

    public final String component1() {
        return this.tileSpec;
    }

    public final SpanSize component2() {
        return this.size;
    }

    public final Loc component3() {
        return this.location;
    }

    public final List<SpanSize> component4() {
        return this.supportSizes;
    }

    public final TileConfig copy(String str, SpanSize spanSize, Loc loc, List<SpanSize> list) {
        return new TileConfig(str, spanSize, loc, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileConfig)) {
            return false;
        }
        TileConfig tileConfig = (TileConfig) obj;
        return Intrinsics.areEqual(this.tileSpec, tileConfig.tileSpec) && Intrinsics.areEqual(this.size, tileConfig.size) && Intrinsics.areEqual(this.location, tileConfig.location) && Intrinsics.areEqual(this.supportSizes, tileConfig.supportSizes);
    }

    public final Loc getLocation() {
        return this.location;
    }

    public final SpanSize getSize() {
        return this.size;
    }

    public final List<SpanSize> getSupportSizes() {
        return this.supportSizes;
    }

    public final String getTileSpec() {
        return this.tileSpec;
    }

    public final int hashCode() {
        return this.supportSizes.hashCode() + ((this.location.hashCode() + ((this.size.hashCode() + (this.tileSpec.hashCode() * 31)) * 31)) * 31);
    }

    public final String shortString() {
        String str = this.tileSpec;
        int colSpan = this.size.getColSpan();
        int rowSpan = this.size.getRowSpan();
        int pageIndex = this.location.getPageIndex();
        int startCol = this.location.getStartCol();
        int startRow = this.location.getStartRow();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" [");
        sb.append(colSpan);
        sb.append(" ");
        sb.append(rowSpan);
        DisplayShapeCompat$.ExternalSyntheticOutline0.m(pageIndex, startCol, "] [", " ", sb);
        sb.append(" ");
        sb.append(startRow);
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        return "TileConfig(tileSpec=" + this.tileSpec + ", size=" + this.size + ", location=" + this.location + ", supportSizes=" + this.supportSizes + ")";
    }

    public TileConfig(String str, SpanSize spanSize, Loc loc, List<SpanSize> list) {
        this.tileSpec = str;
        this.size = spanSize;
        this.location = loc;
        this.supportSizes = list;
    }
}
