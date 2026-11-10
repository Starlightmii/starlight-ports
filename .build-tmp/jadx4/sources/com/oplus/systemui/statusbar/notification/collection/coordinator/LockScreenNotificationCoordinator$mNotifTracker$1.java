package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.LockScreenNotificationDispatcher;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.helper.NotificationCommHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenNotificationCoordinator$mNotifTracker$1 implements OnBeforeRenderListListener {
    public static final LockScreenNotificationCoordinator$mNotifTracker$1 INSTANCE = new LockScreenNotificationCoordinator$mNotifTracker$1();

    public final void onBeforeRenderList(List list) {
        ArrayList arrayList = new ArrayList();
        if (!NotificationCommHelper.Companion.getInstance().isKeyguardShowing) {
            list = arrayList;
        }
        ((LockScreenNotificationDispatcher) DependencyEx.sDependency.getDependency(LockScreenNotificationDispatcher.class)).updateNotificationListOnKg(list);
    }
}
