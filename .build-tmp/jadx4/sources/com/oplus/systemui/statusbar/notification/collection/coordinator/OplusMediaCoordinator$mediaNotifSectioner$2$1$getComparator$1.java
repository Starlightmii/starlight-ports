package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaCoordinator$mediaNotifSectioner$2$1$getComparator$1 extends NotifComparator {
    public final int compare(PipelineEntry pipelineEntry, PipelineEntry pipelineEntry2) {
        boolean z = pipelineEntry instanceof ListEntry;
        if (z && (pipelineEntry2 instanceof ListEntry)) {
            return Intrinsics.compare(((ListEntry) pipelineEntry).getCreationTime(), ((ListEntry) pipelineEntry2).getCreationTime());
        }
        if (z) {
            return 1;
        }
        return pipelineEntry2 instanceof ListEntry ? -1 : 0;
    }
}
