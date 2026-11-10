package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationsRepository$notifTracker$1 implements OnBeforeRenderListListener {
    public /* synthetic */ OplusLiveAlertNotificationsRepository this$0;

    public final void onBeforeRenderList(List list) {
        OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository = this.this$0;
        oplusLiveAlertNotificationsRepository.entries = list;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GroupEntry groupEntry = (PipelineEntry) it.next();
            if (groupEntry instanceof NotificationEntry) {
                OplusLiveAlertNotificationsRepository.access$processNotificationEntry(oplusLiveAlertNotificationsRepository, (NotificationEntry) groupEntry);
            } else if (groupEntry instanceof GroupEntry) {
                for (NotificationEntry notificationEntry : groupEntry.getChildren()) {
                    notificationEntry.getClass();
                    OplusLiveAlertNotificationsRepository.access$processNotificationEntry(oplusLiveAlertNotificationsRepository, notificationEntry);
                }
            }
        }
    }
}
