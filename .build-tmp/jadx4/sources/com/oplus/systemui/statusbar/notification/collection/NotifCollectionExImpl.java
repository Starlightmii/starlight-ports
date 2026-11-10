package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotifCollectionEx;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.oplus.systemui.notification.collection.progress.NotificationProgressOnlyUpdateHelper;
import com.oplus.systemui.notification.extend.OplusNotificationEx;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotifCollectionExImpl extends NotifCollectionEx implements OplusNotificationEx {
    public static final Companion Companion = new Companion();
    private static final String EXTRA_OPLUS_FORCE_TOP = "oplus_force_top";
    private static final String MCS_PUSH_PACKAGE_NAME = "android";
    private static final String PREPARATION_COORDINATOR_INFLATING = "PreparationCoordinatorInflating";
    private static final String TAG = "NotifCollectionExImpl";
    private final Context context;
    private final FluidReplaceNotificationManager fluidReplaceNotificationManager;
    private final NotificationBootSuppressor notificationBootSuppressor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotifCollectionExImpl(Context context, FluidReplaceNotificationManager fluidReplaceNotificationManager, NotificationBootSuppressor notificationBootSuppressor) {
        this.context = context;
        this.fluidReplaceNotificationManager = fluidReplaceNotificationManager;
        this.notificationBootSuppressor = notificationBootSuppressor;
    }

    public final void clearAllNotifications() {
        this.notificationBootSuppressor.clearAllNotifications();
    }

    public void handleEntryDataForCommercialization(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        OplusNotificationCommercializeControl.handleEntryDataForCommercialization(notificationEntry, statusBarNotification);
    }

    public boolean shouldKeepNotifcationWhenTopStyleNotShow(NotificationEntry notificationEntry, Bundle bundle) {
        if (notificationEntry != null && "android".equals(notificationEntry.getSbn().getOpPkg()) && bundle != null) {
            NotifFilter excludingFilter = notificationEntry.getExcludingFilter();
            String name = excludingFilter != null ? excludingFilter.getName() : null;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotifCollectionExImpl-->".concat("shouldKeepNotifcationWhenTopStyleNotShow isTop:" + (((OplusNotificationEntryExImpl) notificationEntry.mNotificationEntryEx).isMcsTop == 0) + " filterName:" + name));
            }
            if (((OplusNotificationEntryExImpl) notificationEntry.mNotificationEntryEx).isMcsTop == 0 && PREPARATION_COORDINATOR_INFLATING.equals(name)) {
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, FileProvider$.ExternalSyntheticOutline0.m("shouldKeepNotifcationWhenTopStyleNotShow force top mcs not seen, can't clean-->key:", notificationEntry.getSbn().getKey()), null, 8);
                return true;
            }
        }
        return false;
    }

    public boolean shouldSuppressPostBecauseFluidShowing(StatusBarNotification statusBarNotification) {
        return this.fluidReplaceNotificationManager.shouldSuppressNotificationPost(statusBarNotification);
    }

    public final boolean shouldSuppressPostInBootWindow() {
        return this.notificationBootSuppressor.shouldSuppressPostInBootWindow();
    }

    public boolean tryUpdateProgressOnly(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification) {
        return NotificationProgressOnlyUpdateHelper.INSTANCE.tryUpdateProgressOnly(notificationEntry, statusBarNotification);
    }
}
