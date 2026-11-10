package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.oplus.systemui.util.NotificationUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutControllerExtImpl$notifListener$1 implements NotifCollectionListener {
    public /* synthetic */ NotificationStackScrollLayoutControllerExtImpl this$0;

    public final void onEntryAdded(NotificationEntry notificationEntry) {
        NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = this.this$0;
        if (notificationStackScrollLayoutControllerExtImpl.isScrolling) {
            if (NotificationUtil.isMscTopNotification(NotificationUtil.getForceTop(notificationEntry.getSbn()), notificationEntry.getSbn())) {
                return;
            }
            notificationStackScrollLayoutControllerExtImpl.getStrategy().startWaitAction(5);
        }
    }
}
