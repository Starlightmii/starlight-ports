package com.oplus.systemui.plugins.qs.customize.data.model;

import androidx.car.app.hardware.common.CarZone$;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileStaticConfig {
    public static final Companion Companion = new Companion();
    private static final SpanSize DEFAULT_SIZE;
    private static final List<SpanSize> DEFAULT_SUPPORT_SIZES;
    private final SpanSize defaultSize;
    private final List<SpanSize> supportSizes;
    private final String tileSpec;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final TileStaticConfig createDefault(String str) {
            return new TileStaticConfig(str, getDEFAULT_SIZE(), getDEFAULT_SUPPORT_SIZES());
        }

        public final SpanSize getDEFAULT_SIZE() {
            return TileStaticConfig.DEFAULT_SIZE;
        }

        public final List<SpanSize> getDEFAULT_SUPPORT_SIZES() {
            return TileStaticConfig.DEFAULT_SUPPORT_SIZES;
        }
    }

    static {
        SpanCache spanCache = SpanCache.INSTANCE;
        SpanSize spanSize = spanCache.spanSize(2, 1);
        DEFAULT_SIZE = spanSize;
        DEFAULT_SUPPORT_SIZES = CollectionsKt__CollectionsKt.listOf(new SpanSize[]{spanSize, spanCache.spanSize(1, 1), spanCache.spanSize(2, 2)});
    }

    public TileStaticConfig(String str, SpanSize spanSize, List<SpanSize> list) {
        this.tileSpec = str;
        this.defaultSize = spanSize;
        this.supportSizes = list;
    }

    public final String component1() {
        return this.tileSpec;
    }

    public final SpanSize component2() {
        return this.defaultSize;
    }

    public final List<SpanSize> component3() {
        return this.supportSizes;
    }

    public final TileStaticConfig copy(String str, SpanSize spanSize, List<SpanSize> list) {
        return new TileStaticConfig(str, spanSize, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileStaticConfig)) {
            return false;
        }
        TileStaticConfig tileStaticConfig = (TileStaticConfig) obj;
        return Intrinsics.areEqual(this.tileSpec, tileStaticConfig.tileSpec) && Intrinsics.areEqual(this.defaultSize, tileStaticConfig.defaultSize) && Intrinsics.areEqual(this.supportSizes, tileStaticConfig.supportSizes);
    }

    public final SpanSize getDefaultSize() {
        return this.defaultSize;
    }

    public final List<SpanSize> getSupportSizes() {
        return this.supportSizes;
    }

    public final String getTileSpec() {
        return this.tileSpec;
    }

    public final int hashCode() {
        return this.supportSizes.hashCode() + ((this.defaultSize.hashCode() + (this.tileSpec.hashCode() * 31)) * 31);
    }

    public final boolean isValid() {
        return this.supportSizes.contains(this.defaultSize);
    }

    public String toString() {
        String str = this.tileSpec;
        SpanSize spanSize = this.defaultSize;
        List<SpanSize> list = this.supportSizes;
        StringBuilder sb = new StringBuilder("TileStaticConfig(tileSpec=");
        sb.append(str);
        sb.append(", defaultSize=");
        sb.append(spanSize);
        sb.append(", supportSizes=");
        return CarZone$.ExternalSyntheticOutline0.m(sb, list, ")");
    }
}
