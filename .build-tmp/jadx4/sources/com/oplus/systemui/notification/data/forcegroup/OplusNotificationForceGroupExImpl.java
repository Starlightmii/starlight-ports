package com.oplus.systemui.notification.data.forcegroup;

import android.service.notification.StatusBarNotification;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.notification.collection.EntryWithDismissStats;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.oplus.systemui.notification.data.NotificationForceGroupEx;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationForceGroupExImpl extends NotificationForceGroupEx {
    public static final Companion Companion = new Companion();
    private static final String OPLUS_AUTOGROUP_KEY = "oplus_ranker_group";
    private static final String TAG = "OplusNotificationForceGroupExImpl";
    private final NotifCollectionLogger logger;
    private Map<String, NotificationEntry> oplusAutoGroupSummary = new LinkedHashMap(0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotificationForceGroupExImpl(NotifCollectionLogger notifCollectionLogger) {
        this.logger = notifCollectionLogger;
    }

    public static String getAutoGroupKey(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getUserId() + "|" + statusBarNotification.getPackageName() + "|og:oplus_ranker_group";
    }

    @Override // com.oplus.systemui.notification.data.NotificationForceGroupEx
    public final void endGroupList() {
        this.oplusAutoGroupSummary.clear();
    }

    @Override // com.oplus.systemui.notification.data.NotificationForceGroupEx
    public String getGroupKey(StatusBarNotification statusBarNotification) {
        StatusBarNotification sbn;
        NotificationEntry notificationEntry = this.oplusAutoGroupSummary.get(getAutoGroupKey(statusBarNotification));
        if (notificationEntry != null && (sbn = notificationEntry.getSbn()) != null) {
            statusBarNotification = sbn;
        }
        return statusBarNotification.getGroupKey();
    }

    @Override // com.oplus.systemui.notification.data.NotificationForceGroupEx
    public NotificationEntry getOplusAutoGroupSummary(StatusBarNotification statusBarNotification) {
        return this.oplusAutoGroupSummary.get(getAutoGroupKey(statusBarNotification));
    }

    @Override // com.oplus.systemui.notification.data.NotificationForceGroupEx
    public List<EntryWithDismissStats> includeOplusAutoGroupToDismiss(List<EntryWithDismissStats> list, Collection<? extends NotificationEntry> collection, Map<String, ? extends NotificationEntry> map) {
        ArrayList arrayList = new ArrayList(list);
        for (EntryWithDismissStats entryWithDismissStats : list) {
            NotificationEntry notificationEntry = map.get(entryWithDismissStats.getKey());
            StatusBarNotification sbn = notificationEntry != null ? notificationEntry.getSbn() : null;
            if (sbn != null && Intrinsics.areEqual(sbn.getGroup(), getAutoGroupKey(sbn))) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : collection) {
                    NotificationEntry notificationEntry2 = (NotificationEntry) obj;
                    if (!Intrinsics.areEqual(notificationEntry2, notificationEntry) && Intrinsics.areEqual(notificationEntry2.getSbn().getPackageName(), sbn.getPackageName()) && Intrinsics.areEqual(notificationEntry2.getSbn().getUser(), sbn.getUser())) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    arrayList3.add(entryWithDismissStats.copyForEntry((NotificationEntry) obj2));
                }
                arrayList.addAll(arrayList3);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(arrayList3.size(), "remove auto entry: size=", "OplusNotificationForceGroupExImpl-->", "SystemUi--Notification");
                }
            }
        }
        return arrayList;
    }

    @Override // com.oplus.systemui.notification.data.NotificationForceGroupEx
    public void startGroupNotifs(List<? extends PipelineEntry> list) {
        StatusBarNotification sbn;
        this.oplusAutoGroupSummary.clear();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            NotificationEntry notificationEntry = (PipelineEntry) it.next();
            NotificationEntry notificationEntry2 = notificationEntry instanceof NotificationEntry ? notificationEntry : null;
            if (notificationEntry2 != null && (sbn = notificationEntry2.getSbn()) != null && sbn.getNotification().isGroupSummary() && getAutoGroupKey(sbn).equals(sbn.getNotification().getGroup())) {
                this.oplusAutoGroupSummary.put(getAutoGroupKey(sbn), notificationEntry);
            }
        }
    }
}
