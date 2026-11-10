package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupSummaryCoordinator implements Coordinator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "GroupSummaryCoordinator";
    private final GroupSummaryCoordinator$notifiFilter$1 notifiFilter = new GroupSummaryCoordinator$notifiFilter$1(TAG);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public void attach(NotifPipeline notifPipeline) {
        notifPipeline.addFinalizeFilter(this.notifiFilter);
    }
}
