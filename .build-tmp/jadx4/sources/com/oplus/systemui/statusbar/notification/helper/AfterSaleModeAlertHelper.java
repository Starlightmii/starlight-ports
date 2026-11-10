package com.oplus.systemui.statusbar.notification.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AfterSaleModeAlertHelper {
    public final Context mContext;
    public NotificationManager mNotificationManager;
    public final AnonymousClass1 mRepairModeReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.statusbar.notification.helper.AfterSaleModeAlertHelper.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                int intExtra = intent.getIntExtra("android.intent.extra.user_handle", 0);
                AfterSaleModeAlertHelper.this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "AfterSaleModeAlertHelper", KeyguardLogger$.ExternalSyntheticOutline0.m("RepairModeReceiver!  action = ", action, " userId = ", intExtra));
                if ("android.intent.action.USER_SWITCHED".equals(action)) {
                    AfterSaleModeAlertHelper afterSaleModeAlertHelper = AfterSaleModeAlertHelper.this;
                    if (intExtra == 888) {
                        afterSaleModeAlertHelper.mNotificationManager = (NotificationManager) afterSaleModeAlertHelper.mContext.getSystemService("notification");
                        Intent intent2 = new Intent("android.aftersalemode.notice");
                        intent2.setPackage(afterSaleModeAlertHelper.mContext.getPackageName());
                        intent2.addFlags(536870912);
                        Context context2 = afterSaleModeAlertHelper.mContext;
                        UserHandle userHandle = UserHandle.CURRENT;
                        afterSaleModeAlertHelper.mNotificationManager.notifyAsUser(null, 10009, new Notification.Builder(afterSaleModeAlertHelper.mContext, "after_sale_mode_notice").setContentIntent(PendingIntent.getActivityAsUser(context2, 0, intent2, 201326592, null, userHandle)).setSmallIcon(2131236741).setContentTitle(afterSaleModeAlertHelper.mContext.getString(2131954440)).setVisibility(1).setCategory("sys").setOngoing(true).build(), userHandle);
                        return;
                    }
                }
                AfterSaleModeAlertHelper.this.mNotificationManager.cancelAsUser(null, 10009, UserHandle.CURRENT);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.statusbar.notification.helper.AfterSaleModeAlertHelper$1] */
    public AfterSaleModeAlertHelper(Context context) {
        this.mContext = context;
    }

    public final boolean isAfterSaleModeNoticeChannelNotification(NotificationEntry notificationEntry) {
        return (notificationEntry == null || notificationEntry.getSbn() == null || notificationEntry.getSbn().getNotification() == null || !this.mContext.getPackageName().equals(notificationEntry.getSbn().getPackageName()) || !"after_sale_mode_notice".equals(notificationEntry.getSbn().getNotification().getChannelId()) || notificationEntry.getSbn().isClearable()) ? false : true;
    }
}
