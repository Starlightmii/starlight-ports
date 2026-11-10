package com.oplus.systemui.notification.mcs;

import android.content.Context;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationDismissibilityProviderExtImp extends NotificationDismissibilityProviderExt {
    private final Context context;

    public NotificationDismissibilityProviderExtImp(Context context) {
        this.context = context;
    }

    @Override // com.oplus.systemui.notification.mcs.NotificationDismissibilityProviderExt
    public boolean isCommercializeDismissAble(NotificationEntry notificationEntry) {
        return OplusNotificationCommercializeControl.isCommercializeDismissAble(this.context, notificationEntry);
    }
}
