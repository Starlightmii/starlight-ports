package com.oplus.systemui.notification.data.limit;

import android.util.Log;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.collection.EntryAdapter;
import com.android.systemui.statusbar.notification.collection.EntryWithDismissStats;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.shared.NotificationLocationHelperKt;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationLimiterController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationLimiterController";
    private final ActiveNotificationsInteractor activeNotificationsInteractor;
    private final NotifCollection notifCollection;
    private final NotificationsController notificationsController;
    private final OnReorderingAllowedListener reorderingAllowedListener;
    private final VisualStabilityCoordinator visualStabilityCoordinator;
    private final VisualStabilityProvider visualStabilityProvider;
    private final NotificationTimeOutLimiter notificationTimeOutLimiter = new NotificationTimeOutLimiter();
    private final NotificationNumLimiter notificationNumLimiter = new NotificationNumLimiter();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean canLimit(NotificationEntry notificationEntry, String str) {
            ExpandableNotificationRow row;
            EntryAdapter entryAdapter;
            return !(notificationEntry.getRow() == null || notificationEntry.getSbn().getNotification().isGroupSummary() || !(((row = notificationEntry.getRow()) == null || (entryAdapter = row.getEntryAdapter()) == null || entryAdapter.isBlockable()) && !notificationEntry.getRow().areGutsExposed() && notificationEntry.getRow().canViewBeDismissed()));
        }
    }

    public NotificationLimiterController(VisualStabilityProvider visualStabilityProvider, VisualStabilityCoordinator visualStabilityCoordinator, NotificationsController notificationsController, NotifCollection notifCollection, ActiveNotificationsInteractor activeNotificationsInteractor) {
        this.visualStabilityProvider = visualStabilityProvider;
        this.visualStabilityCoordinator = visualStabilityCoordinator;
        this.notificationsController = notificationsController;
        this.notifCollection = notifCollection;
        this.activeNotificationsInteractor = activeNotificationsInteractor;
        NotificationLimiterController$reorderingAllowedListener$1 notificationLimiterController$reorderingAllowedListener$1 = new NotificationLimiterController$reorderingAllowedListener$1();
        notificationLimiterController$reorderingAllowedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.reorderingAllowedListener = notificationLimiterController$reorderingAllowedListener$1;
    }

    public final void applyToLimiter() {
        if (this.visualStabilityCoordinator.isReorderingAllowed()) {
            cancelNotificationsByLimit(this.notifCollection);
        } else {
            this.visualStabilityProvider.addTemporaryReorderingAllowedListener(this.reorderingAllowedListener);
        }
    }

    public final EntryWithDismissStats buildEntryWithDismissStats(NotificationEntry notificationEntry, String str) {
        EntryWithDismissStats entryWithDismissStats = new EntryWithDismissStats(new DismissedByUserStats(3, NotificationVisibility.obtain(notificationEntry.getKey(), notificationEntry.getRanking().getRank(), this.activeNotificationsInteractor.getAllNotificationsCountValue(), true, NotificationLocationHelperKt.getNotificationLocation(notificationEntry))), notificationEntry.getKey(), notificationEntry.hashCode());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("cancelNotificationsByLimit: notification dismiss by ", str, " limit--", notificationEntry.getEx().getEncryptionKey(), "NotificationLimiterController-->"));
        }
        return entryWithDismissStats;
    }

    public final void cancelNotificationsByLimit(NotifCollection notifCollection) {
        List<? extends NotificationEntry> list = CollectionsKt___CollectionsKt.toList(notifCollection.getAllNotifs());
        if (list.isEmpty()) {
            return;
        }
        List<NotificationEntry> listFilterNotificationsByRule = this.notificationTimeOutLimiter.filterNotificationsByRule(list);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFilterNotificationsByRule, 10));
        Iterator<T> it = listFilterNotificationsByRule.iterator();
        while (it.hasNext()) {
            arrayList.add(buildEntryWithDismissStats((NotificationEntry) it.next(), "timeout"));
        }
        List<NotificationEntry> listFilterNotificationsByRule2 = this.notificationNumLimiter.filterNotificationsByRule(CollectionsKt___CollectionsKt.toList(CollectionsKt___CollectionsKt.subtract(list, CollectionsKt___CollectionsKt.toSet(listFilterNotificationsByRule))));
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listFilterNotificationsByRule2, 10));
        Iterator<T> it2 = listFilterNotificationsByRule2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(buildEntryWithDismissStats((NotificationEntry) it2.next(), "num"));
        }
        List listPlus = CollectionsKt___CollectionsKt.plus(arrayList2, arrayList);
        if (listPlus.isEmpty()) {
            return;
        }
        notifCollection.dismissNotifications(listPlus, false);
    }
}
