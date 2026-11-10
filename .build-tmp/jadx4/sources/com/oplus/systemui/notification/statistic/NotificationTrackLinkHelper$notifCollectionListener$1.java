package com.oplus.systemui.notification.statistic;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTrackLinkHelper$notifCollectionListener$1 implements NotifCollectionListener {
    public /* synthetic */ NotificationTrackLinkHelper this$0;

    public final void onEntryAdded(NotificationEntry notificationEntry) {
        NotificationTrackLinkHelper.access$onNotificationPost(this.this$0, notificationEntry);
    }

    public final void onEntryUpdated(NotificationEntry notificationEntry) {
        NotificationTrackLinkHelper.access$onNotificationPost(this.this$0, notificationEntry);
    }
}
