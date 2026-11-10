package com.oplus.systemui.notification.notificationcenter;

import com.oplus.systemui.statusbar.notification.helper.DndAlertHelper;
import dagger.Lazy;
import dagger.MembersInjector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationCenterManager_MembersInjector implements MembersInjector {
    public static void injectMAonAuthManager(NotificationCenterManager notificationCenterManager, Lazy lazy) {
        notificationCenterManager.mAonAuthManager = lazy;
    }

    public static void injectMDndAlertHelper(NotificationCenterManager notificationCenterManager, DndAlertHelper dndAlertHelper) {
        notificationCenterManager.mDndAlertHelper = dndAlertHelper;
    }
}
