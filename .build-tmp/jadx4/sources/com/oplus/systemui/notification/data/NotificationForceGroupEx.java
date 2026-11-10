package com.oplus.systemui.notification.data;

import android.service.notification.StatusBarNotification;
import com.android.systemui.notification.NotificationEx;
import com.android.systemui.statusbar.notification.collection.EntryWithDismissStats;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationForceGroupEx implements NotificationEx {
    public void endGroupList() {
    }

    public String getGroupKey(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getGroupKey();
    }

    public NotificationEntry getOplusAutoGroupSummary(StatusBarNotification statusBarNotification) {
        return null;
    }

    public List<EntryWithDismissStats> includeOplusAutoGroupToDismiss(List<EntryWithDismissStats> list, Collection<? extends NotificationEntry> collection, Map<String, ? extends NotificationEntry> map) {
        return list;
    }

    public void startGroupNotifs(List<? extends PipelineEntry> list) {
    }
}
