package com.oplusos.systemui.common.viewholder;

import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.oplus.systemui.plugins.qs.customize.view.viewholder.preview.BasePreviewData;
import com.oplusos.systemui.common.model.SpanSize;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GridData implements IId {
    private final List<SpanSize> allValidSize;
    public final Object data;
    public final boolean isEnabled;
    private final SpanSize size;
    public final long uniqueId;

    public GridData(long j, SpanSize spanSize, Object obj, List<SpanSize> list, boolean z) {
        this.uniqueId = j;
        this.size = spanSize;
        this.data = obj;
        this.allValidSize = list;
        this.isEnabled = z;
    }

    public static /* synthetic */ GridData copy$default(GridData gridData, SpanSize spanSize, Object obj, int i) {
        long j = gridData.uniqueId;
        if ((i & 2) != 0) {
            spanSize = gridData.size;
        }
        SpanSize spanSize2 = spanSize;
        if ((i & 4) != 0) {
            obj = gridData.data;
        }
        return gridData.copy(j, spanSize2, obj, gridData.allValidSize, (i & 16) != 0 ? gridData.isEnabled : false);
    }

    public final SpanSize component2() {
        return this.size;
    }

    public final List<SpanSize> component4() {
        return this.allValidSize;
    }

    public final GridData copy(long j, SpanSize spanSize, Object obj, List<SpanSize> list, boolean z) {
        return new GridData(j, spanSize, obj, list, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GridData)) {
            return false;
        }
        GridData gridData = (GridData) obj;
        return this.uniqueId == gridData.uniqueId && Intrinsics.areEqual(this.size, gridData.size) && Intrinsics.areEqual(this.data, gridData.data) && Intrinsics.areEqual(this.allValidSize, gridData.allValidSize) && this.isEnabled == gridData.isEnabled;
    }

    public final List<SpanSize> getAllValidSize() {
        return this.allValidSize;
    }

    public final Object getData() {
        return this.data;
    }

    public final SpanSize getSize() {
        return this.size;
    }

    @Override // com.oplusos.systemui.common.viewholder.IId
    public long getUniqueId() {
        return this.uniqueId;
    }

    public final int hashCode() {
        int iHashCode = (this.size.hashCode() + (Long.hashCode(this.uniqueId) * 31)) * 31;
        Object obj = this.data;
        return Boolean.hashCode(this.isEnabled) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode + (obj == null ? 0 : obj.hashCode())) * 31, 31, this.allValidSize);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GridData(uniqueId=");
        sb.append(this.uniqueId);
        sb.append(", size=");
        sb.append(this.size);
        sb.append(", data=");
        sb.append(this.data);
        sb.append(", allValidSize=");
        sb.append(this.allValidSize);
        sb.append(", isEnabled=");
        return ChangeSize$.ExternalSyntheticOutline0.m(sb, this.isEnabled, ')');
    }

    public /* synthetic */ GridData(long j, SpanSize spanSize, BasePreviewData basePreviewData) {
        this(j, spanSize, basePreviewData, CollectionsKt__CollectionsKt.emptyList(), true);
    }
}
