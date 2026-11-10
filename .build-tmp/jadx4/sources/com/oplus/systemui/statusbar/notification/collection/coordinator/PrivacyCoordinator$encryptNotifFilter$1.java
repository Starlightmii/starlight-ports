package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.OplusExpandableNotificationRowEx;
import com.oplus.systemui.statusbar.notification.row.OplusExpandableNotificationRowExImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyCoordinator$encryptNotifFilter$1 extends NotifFilter {
    public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
        OplusExpandableNotificationRowEx oplusExpandableNotificationRowEx;
        ExpandableNotificationRow row = notificationEntry.getRow();
        if (row == null || (oplusExpandableNotificationRowEx = row.mRowEx) == null) {
            return false;
        }
        ((OplusExpandableNotificationRowExImpl) oplusExpandableNotificationRowEx).updateShouldEncrypted();
        return false;
    }
}
