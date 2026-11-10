package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.service.notification.StatusBarNotification;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationTopListManageImpl$$ExternalSyntheticLambda0 implements Function1 {
    public /* synthetic */ NotificationTopListManageImpl f$0;

    public final Object invoke(Object obj) {
        StatusBarNotification statusBarNotification = (StatusBarNotification) obj;
        return Boolean.valueOf(!this.f$0.isNotificationTop(statusBarNotification != null ? statusBarNotification.getPackageName() : null, statusBarNotification != null ? Integer.valueOf(statusBarNotification.getUid()) : null));
    }
}
