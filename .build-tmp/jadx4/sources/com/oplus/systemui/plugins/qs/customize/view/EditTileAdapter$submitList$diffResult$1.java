package com.oplus.systemui.plugins.qs.customize.view;

import androidx.recyclerview.widget.DiffUtil;
import com.oplusos.systemui.common.viewholder.GridData;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditTileAdapter$submitList$diffResult$1 extends DiffUtil.Callback {
    public /* synthetic */ List $newData;
    public /* synthetic */ List $newLocList;
    public /* synthetic */ List $oldLocList;
    public /* synthetic */ EditTileAdapter this$0;

    public final boolean areContentsTheSame(int i, int i2) {
        return Intrinsics.areEqual(this.this$0.getDataList().get(i), this.$newData.get(i2));
    }

    public final boolean areItemsTheSame(int i, int i2) {
        return ((GridData) this.this$0.getDataList().get(i)).getUniqueId() == ((GridData) this.$newData.get(i2)).getUniqueId() && Intrinsics.areEqual(CollectionsKt___CollectionsKt.getOrNull(this.$oldLocList, i), CollectionsKt___CollectionsKt.getOrNull(this.$newLocList, i2));
    }

    public final Object getChangePayload(int i, int i2) {
        return this.this$0;
    }

    public int getNewListSize() {
        return this.$newData.size();
    }

    public int getOldListSize() {
        return this.this$0.getDataList().size();
    }
}
