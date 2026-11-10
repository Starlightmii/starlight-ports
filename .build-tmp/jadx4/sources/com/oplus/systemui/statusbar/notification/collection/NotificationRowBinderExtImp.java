package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationRowBinderExt;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.systemui.notification.blur.ViewBlurManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationRowBinderExtImp extends NotificationRowBinderExt {
    private final ViewBlurManager viewBlurManager;

    public NotificationRowBinderExtImp(Context context, ViewBlurManager viewBlurManager) {
        super(context);
        this.viewBlurManager = viewBlurManager;
    }

    public void bindRowForBlurController(ExpandableNotificationRow expandableNotificationRow, NotificationEntry notificationEntry) {
        ViewBlurManager.requireBlurProxyForView$default(this.viewBlurManager, ((ActivatableNotificationView) expandableNotificationRow).mBackgroundNormal, ViewBlurManager.CardType.NOTIFICATION, 0.0f, expandableNotificationRow.mRowEx.getNotificationBlurMode(), 4);
    }
}
