package com.oplus.systemui.notification.data.limit;

import com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationLimiterController$reorderingAllowedListener$1 implements OnReorderingAllowedListener {
    public /* synthetic */ NotificationLimiterController this$0;

    public final void onReorderingAllowed() {
        NotificationLimiterController notificationLimiterController = this.this$0;
        notificationLimiterController.cancelNotificationsByLimit(notificationLimiterController.notifCollection);
    }
}
