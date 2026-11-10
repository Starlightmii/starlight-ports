package com.oplus.systemui.notification.data.limit;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTimeOutLimiter implements NotificationLimiter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationTimeOutLimiter";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    private final int getConfigLimitTimeInHour() {
        return NotificationFeatureOption.INSTANCE.getNotificationLimitTime();
    }

    @Override // com.oplus.systemui.notification.data.limit.NotificationLimiter
    public List<NotificationEntry> filterNotificationsByRule(List<? extends NotificationEntry> list) {
        if (list.isEmpty() || getConfigLimitTimeInHour() <= 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        int configLimitTimeInHour = getConfigLimitTimeInHour() * 3600000;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            NotificationEntry notificationEntry = (NotificationEntry) obj;
            if (jCurrentTimeMillis - notificationEntry.getEx().getUpdateTime() > configLimitTimeInHour && NotificationLimiterController.Companion.canLimit(notificationEntry, TAG)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
