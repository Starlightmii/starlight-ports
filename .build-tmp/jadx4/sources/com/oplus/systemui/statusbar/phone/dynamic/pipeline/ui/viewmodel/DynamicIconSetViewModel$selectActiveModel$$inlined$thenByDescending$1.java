package com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.viewmodel;

import com.oplus.systemui.statusbar.phone.dynamic.pipeline.ui.model.DynamicIconModel;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicIconSetViewModel$selectActiveModel$$inlined$thenByDescending$1 implements Comparator {
    public /* synthetic */ DynamicIconSetViewModel$selectActiveModel$$inlined$compareBy$1 $this_thenByDescending;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int iCompare = this.$this_thenByDescending.compare(obj, obj2);
        return iCompare != 0 ? iCompare : ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((DynamicIconModel) obj2).getUpdateTime()), Long.valueOf(((DynamicIconModel) obj).getUpdateTime()));
    }
}
