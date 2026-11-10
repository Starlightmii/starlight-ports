package com.oplus.systemui.notification.sort;

import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManageImpl;
import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PinTopCoordinator$userPinTopNotifSectioner$2$1 extends NotifSectioner {
    public NotificationTopListManage notificationTopListManage;

    /* JADX WARN: Code duplicated, block: B:15:0x0037  */
    public final boolean isInSection(PipelineEntry pipelineEntry) {
        int notificationTopIndex;
        NotificationEntry representativeEntry;
        StatusBarNotification sbn;
        if (pipelineEntry == null) {
            return false;
        }
        ListEntry listEntryAsListEntry = pipelineEntry.asListEntry();
        if (listEntryAsListEntry == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null || (sbn = representativeEntry.getSbn()) == null) {
            notificationTopIndex = -1;
        } else {
            String notificationTopKey = NotificationTopUtils.getNotificationTopKey(sbn.getPackageName(), Integer.valueOf(UserHandle.getUserId(sbn.getUid())));
            NotificationTopListManage notificationTopListManage = this.notificationTopListManage;
            if (notificationTopListManage != null) {
                notificationTopIndex = ((NotificationTopListManageImpl) notificationTopListManage).getNotificationTopIndex(notificationTopKey);
            } else {
                notificationTopIndex = -1;
            }
        }
        return notificationTopIndex != -1;
    }
}
