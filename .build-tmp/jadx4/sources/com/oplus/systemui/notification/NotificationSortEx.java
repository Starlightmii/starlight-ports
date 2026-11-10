package com.oplus.systemui.notification;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationSortEx {
    public NotifSection createNotifSection(NotifSectioner notifSectioner, int i) {
        return new NotifSection(notifSectioner, i);
    }

    public Comparator<NotificationEntry> getGroupChildrenComparator(Comparator<NotificationEntry> comparator) {
        return comparator;
    }

    public void modifyOrderedSection(ConversationCoordinator conversationCoordinator, HeadsUpCoordinator headsUpCoordinator, List<NotifSectioner> list) {
    }
}
