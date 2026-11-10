package com.oplus.systemui.notification.data.limit;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationNumLimiter implements NotificationLimiter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationNumLimiter";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    private final int getMaxNotification() {
        return NotificationFeatureOption.INSTANCE.getNotificationLimitNum();
    }

    @Override // com.oplus.systemui.notification.data.limit.NotificationLimiter
    public List<NotificationEntry> filterNotificationsByRule(List<? extends NotificationEntry> list) {
        if (list.size() <= getMaxNotification() || getMaxNotification() <= 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size() - getMaxNotification();
        for (NotificationEntry notificationEntry : CollectionsKt___CollectionsKt.sortedWith(list, new NotificationNumLimiter$filterNotificationsByRule$.inlined.sortedBy.1())) {
            if (arrayList.size() >= size) {
                break;
            }
            if (NotificationLimiterController.Companion.canLimit(notificationEntry, TAG)) {
                arrayList.add(notificationEntry);
            }
        }
        return arrayList;
    }
}
