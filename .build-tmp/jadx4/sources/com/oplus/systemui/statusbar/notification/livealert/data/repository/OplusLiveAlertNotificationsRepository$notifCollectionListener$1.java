package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import android.app.NotificationChannel;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.UpdateSource;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.oplus.systemui.statusbar.notification.livealert.data.model.SbnModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertNotificationsRepository$notifCollectionListener$1 implements NotifCollectionListener {
    public /* synthetic */ OplusLiveAlertNotificationsRepository this$0;

    public final void onEntryAdded(NotificationEntry notificationEntry) {
        String logReason;
        boolean shouldInterrupt;
        OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository = this.this$0;
        int iFilterNotificationEntry = oplusLiveAlertNotificationsRepository.filterNotificationEntry(notificationEntry, "onEntryAdded");
        if ((iFilterNotificationEntry & 1) > 0) {
            VisualInterruptionDecisionProvider.FullScreenIntentDecision fullScreenIntentDecisionMakeUnloggedFullScreenIntentDecision = oplusLiveAlertNotificationsRepository.visualInterruptionDecisionProvider.makeUnloggedFullScreenIntentDecision(notificationEntry);
            if (fullScreenIntentDecisionMakeUnloggedFullScreenIntentDecision.getShouldInterrupt()) {
                logReason = "";
                shouldInterrupt = false;
            } else {
                VisualInterruptionDecisionProvider.Decision decisionMakeUnLoggableAndUnFluidConditionDecision = oplusLiveAlertNotificationsRepository.visualInterruptionDecisionProvider.makeUnLoggableAndUnFluidConditionDecision(notificationEntry);
                logReason = decisionMakeUnLoggableAndUnFluidConditionDecision.getLogReason();
                shouldInterrupt = decisionMakeUnLoggableAndUnFluidConditionDecision.getShouldInterrupt();
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "OplusLiveAlertNotificationsRepository-->".concat("onEntryAdded: " + fullScreenIntentDecisionMakeUnloggedFullScreenIntentDecision.getLogReason() + " " + ((Object) logReason)));
            }
            oplusLiveAlertNotificationsRepository.addLiveAlert(notificationEntry, iFilterNotificationEntry, OplusLiveAlertNotificationsRepository.access$entryToReminderType(oplusLiveAlertNotificationsRepository, notificationEntry, shouldInterrupt), true);
        }
        notificationEntry.mNotificationEntryEx.setNotifyState(0);
    }

    public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
        OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository = this.this$0;
        OplusLiveAlertFilters oplusLiveAlertFilters = oplusLiveAlertNotificationsRepository.filters;
        if (oplusLiveAlertFilters != null) {
            oplusLiveAlertFilters.removeCache(notificationEntry.getKey());
        }
        OplusLiveAlertNotificationsRepository.access$removeLiveAlert(oplusLiveAlertNotificationsRepository, notificationEntry);
        OplusLiveAlertNotificationsRepository.access$notifyNotificationRemove(oplusLiveAlertNotificationsRepository, notificationEntry.getSbn());
        notificationEntry.mNotificationEntryEx.setNotifyState(-1);
    }

    public final void onEntryUpdated(NotificationEntry notificationEntry) {
        Object next;
        boolean z;
        OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository = this.this$0;
        int iFilterNotificationEntry = oplusLiveAlertNotificationsRepository.filterNotificationEntry(notificationEntry, "onEntryUpdated");
        Iterator it = oplusLiveAlertNotificationsRepository.liveAlertList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SbnModel) next).getSbn().getKey(), notificationEntry.getKey()));
        boolean z2 = next != null;
        boolean z3 = (iFilterNotificationEntry & 1) > 0;
        if (!z2 && z3) {
            VisualInterruptionDecisionProvider.Decision decisionMakeUnLoggableAndUnFluidConditionDecision = oplusLiveAlertNotificationsRepository.visualInterruptionDecisionProvider.makeUnLoggableAndUnFluidConditionDecision(notificationEntry);
            boolean shouldInterrupt = decisionMakeUnLoggableAndUnFluidConditionDecision.getShouldInterrupt();
            z = (notificationEntry.getSbn().getNotification().flags & 8) == 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onEntryUpdated: ", decisionMakeUnLoggableAndUnFluidConditionDecision.getLogReason(), "OplusLiveAlertNotificationsRepository-->", "SystemUi--Notification");
            }
            oplusLiveAlertNotificationsRepository.addLiveAlert(notificationEntry, iFilterNotificationEntry, OplusLiveAlertNotificationsRepository.access$entryToReminderType(oplusLiveAlertNotificationsRepository, notificationEntry, shouldInterrupt), z);
            return;
        }
        if (z2 && !z3) {
            OplusLiveAlertNotificationsRepository.access$removeLiveAlert(oplusLiveAlertNotificationsRepository, notificationEntry);
            return;
        }
        if (z2 && z3) {
            VisualInterruptionDecisionProvider.Decision decisionMakeUnLoggableAndUnFluidConditionDecision2 = oplusLiveAlertNotificationsRepository.visualInterruptionDecisionProvider.makeUnLoggableAndUnFluidConditionDecision(notificationEntry);
            boolean shouldInterrupt2 = decisionMakeUnLoggableAndUnFluidConditionDecision2.getShouldInterrupt();
            z = (notificationEntry.getSbn().getNotification().flags & 8) == 0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onEntryUpdated: ", decisionMakeUnLoggableAndUnFluidConditionDecision2.getLogReason(), "OplusLiveAlertNotificationsRepository-->", "SystemUi--Notification");
            }
            OplusLiveAlertNotificationsRepository.access$updateLiveAlert(oplusLiveAlertNotificationsRepository, notificationEntry, iFilterNotificationEntry, OplusLiveAlertNotificationsRepository.access$entryToReminderType(oplusLiveAlertNotificationsRepository, notificationEntry, shouldInterrupt2), z);
        }
    }

    public final void onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusLiveAlertNotificationsRepository", "onNotificationChannelModified: " + str + " " + (userHandle != null ? Integer.valueOf(userHandle.getIdentifier()) : null) + " " + ((Object) (notificationChannel != null ? notificationChannel.getName() : null)) + " " + i, null, 8);
    }

    public final void onRankingApplied() {
        int iFilterEntry;
        OplusLiveAlertNotificationsRepository oplusLiveAlertNotificationsRepository = this.this$0;
        Collection allNotifs = oplusLiveAlertNotificationsRepository.notifCollection.getAllNotifs();
        ArrayList arrayList = new ArrayList();
        Iterator it = allNotifs.iterator();
        while (true) {
            iFilterEntry = 0;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            NotificationEntry notificationEntry = (NotificationEntry) next;
            OplusLiveAlertFilters oplusLiveAlertFilters = oplusLiveAlertNotificationsRepository.filters;
            if (oplusLiveAlertFilters != null) {
                notificationEntry.getClass();
                iFilterEntry = oplusLiveAlertFilters.filterEntry(notificationEntry);
            }
            if ((iFilterEntry & 1) > 0) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        int size = arrayList.size();
        while (iFilterEntry < size) {
            Object obj = arrayList.get(iFilterEntry);
            iFilterEntry++;
            arrayList2.add(((NotificationEntry) obj).getKey());
        }
        Set set = CollectionsKt___CollectionsKt.toSet(arrayList2);
        List list = oplusLiveAlertNotificationsRepository.liveAlertList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList3.add(((SbnModel) it2.next()).getSbn().getKey());
        }
        if (Intrinsics.areEqual(set, CollectionsKt___CollectionsKt.toSet(arrayList3))) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusLiveAlertNotificationsRepository", "onRankingApplied: liveAlert list changed", null, 8);
        oplusLiveAlertNotificationsRepository.setLiveAlerts(arrayList);
    }

    public final void onEntryUpdated(NotificationEntry notificationEntry, UpdateSource updateSource) {
        onEntryUpdated(notificationEntry);
        if (updateSource == UpdateSource.App) {
            notificationEntry.mNotificationEntryEx.setNotifyState(1);
        }
    }
}
