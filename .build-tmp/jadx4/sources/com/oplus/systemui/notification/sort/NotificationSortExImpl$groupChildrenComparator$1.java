package com.oplus.systemui.notification.sort;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSortExImpl$groupChildrenComparator$1 implements Comparator {
    public static final NotificationSortExImpl$groupChildrenComparator$1 INSTANCE = new NotificationSortExImpl$groupChildrenComparator$1();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        NotificationEntry notificationEntry = (NotificationEntry) obj;
        NotificationEntry notificationEntry2 = (NotificationEntry) obj2;
        int iCompare = Intrinsics.compare(notificationEntry.getEx().getUpdateTime(), notificationEntry2.getEx().getUpdateTime()) * (-1);
        if (iCompare != 0) {
            return iCompare;
        }
        NotificationEntry representativeEntry = notificationEntry.getRepresentativeEntry();
        NotificationEntry representativeEntry2 = notificationEntry2.getRepresentativeEntry();
        if (representativeEntry == null || representativeEntry2 == null) {
            return iCompare;
        }
        int iCompare2 = Intrinsics.compare(representativeEntry.getRanking().getRank(), representativeEntry2.getRanking().getRank());
        return iCompare2 != 0 ? iCompare2 : Intrinsics.compare(representativeEntry.getSbn().getNotification().getWhen(), representativeEntry2.getSbn().getNotification().getWhen()) * (-1);
    }
}
