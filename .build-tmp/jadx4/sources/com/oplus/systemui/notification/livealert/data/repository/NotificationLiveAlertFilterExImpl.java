package com.oplus.systemui.notification.livealert.data.repository;

import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.oplus.systemui.notification.livealertfilter.NotificationLiveAlertFilterEx;
import com.oplus.systemui.statusbar.notification.collection.coordinator.AodNotificationCoordinator;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationLiveAlertFilterExImpl extends NotificationLiveAlertFilterEx {
    private final AodNotificationCoordinator aodNotificationCoordinator;
    private final List<String> notificationFilterName = CollectionsKt__CollectionsKt.mutableListOf(new String[]{"PrivacyCoordinatorAppHide", "SuperPowerSaveCoordinatorSuperPowerSave", "DeviceProvisionedCoordinator", "HideLocallyDismissedNotifsFilter", "NotCurrentUserFilter", "MediaCoordinator", "IsSuspendedFilter", "DndSuppressingVisualEffects", "SmartspaceDedupingFilter", "GroupSummaryCoordinator", "OplusMediaCoordinator", "KeyguardCoordinator"});
    private final OplusLiveAlertFilterByNotification oplusLiveAlertFilterByNotification;
    private final NotificationLiveAlertFilterExImpl$panoramicAodCustomNotifiFilter$1 panoramicAodCustomNotifiFilter;
    private final NotificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1 rebuildNotificationFilterListener;
    private final Lazy statusBarStateController;

    /* JADX WARN: Type inference failed for: r13v4, types: [com.oplus.systemui.notification.livealert.data.repository.NotificationLiveAlertFilterExImpl$panoramicAodCustomNotifiFilter$1] */
    public NotificationLiveAlertFilterExImpl(OplusLiveAlertFilterByNotification oplusLiveAlertFilterByNotification, Lazy lazy, AodNotificationCoordinator aodNotificationCoordinator) {
        this.oplusLiveAlertFilterByNotification = oplusLiveAlertFilterByNotification;
        this.statusBarStateController = lazy;
        this.aodNotificationCoordinator = aodNotificationCoordinator;
        NotificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1 notificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1 = new NotificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1();
        notificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.rebuildNotificationFilterListener = notificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1;
        this.panoramicAodCustomNotifiFilter = new NotifFilter() { // from class: com.oplus.systemui.notification.livealert.data.repository.NotificationLiveAlertFilterExImpl$panoramicAodCustomNotifiFilter$1
            {
                super("panoramicAodCustomNotifiFilter");
            }

            public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
                NotificationLiveAlertFilterExImpl notificationLiveAlertFilterExImpl = this.this$0;
                return NotificationLiveAlertFilterExImpl.access$isKeyguardOrShadeLocked(notificationLiveAlertFilterExImpl) && notificationLiveAlertFilterExImpl.aodNotificationCoordinator.isHidePanoramicAodCustomNotification(notificationEntry);
            }
        };
    }

    public static final boolean access$isKeyguardOrShadeLocked(NotificationLiveAlertFilterExImpl notificationLiveAlertFilterExImpl) {
        int currentOrUpcomingState = ((SysuiStatusBarStateController) notificationLiveAlertFilterExImpl.statusBarStateController.get()).getCurrentOrUpcomingState();
        return currentOrUpcomingState == 1 || currentOrUpcomingState == 2;
    }

    @Override // com.oplus.systemui.notification.livealertfilter.NotificationLiveAlertFilterEx
    public void addNotificationFilter(NotifFilter notifFilter) {
        if (this.notificationFilterName.contains(notifFilter.getName())) {
            boolean zAreEqual = Intrinsics.areEqual(notifFilter.getName(), "KeyguardCoordinator");
            OplusLiveAlertFilterByNotification oplusLiveAlertFilterByNotification = this.oplusLiveAlertFilterByNotification;
            if (zAreEqual) {
                oplusLiveAlertFilterByNotification.addNotificationFilter(this.panoramicAodCustomNotifiFilter);
            } else {
                oplusLiveAlertFilterByNotification.addNotificationFilter(notifFilter);
            }
            notifFilter.setLiveAlertInvalidationListener(this.rebuildNotificationFilterListener);
        }
    }
}
