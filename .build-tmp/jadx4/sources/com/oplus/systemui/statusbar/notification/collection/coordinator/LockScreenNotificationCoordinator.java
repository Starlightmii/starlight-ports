package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationCoordinator implements Coordinator {
    private final OnBeforeRenderListListener mNotifTracker = LockScreenNotificationCoordinator$mNotifTracker$1.INSTANCE;

    public void attach(NotifPipeline notifPipeline) {
        notifPipeline.addOnBeforeRenderListListener(this.mNotifTracker);
    }
}
