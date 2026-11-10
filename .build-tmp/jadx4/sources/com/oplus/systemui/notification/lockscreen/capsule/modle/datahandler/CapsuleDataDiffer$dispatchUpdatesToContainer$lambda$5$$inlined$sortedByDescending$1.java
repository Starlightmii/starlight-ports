package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDataDiffer$dispatchUpdatesToContainer$lambda$5$$inlined$sortedByDescending$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt__ComparisonsKt.compareValues(Integer.valueOf(((CapsuleItemData) obj2).getOrderPriority()), Integer.valueOf(((CapsuleItemData) obj).getOrderPriority()));
    }
}
