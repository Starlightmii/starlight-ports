package com.oplus.systemui.notification.smallreply;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SmallWindowReplyController$notifListener$1 implements NotifCollectionListener {
    public /* synthetic */ SmallWindowReplyController this$0;

    public final void onEntryAdded(NotificationEntry notificationEntry) {
        this.this$0.onNotificationPosted(notificationEntry, false);
    }

    public final void onEntryUpdated(NotificationEntry notificationEntry) {
        this.this$0.onNotificationPosted(notificationEntry, true);
    }
}
