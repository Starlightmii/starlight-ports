package com.oplus.systemui.notification.headsup;

import android.view.ViewGroup;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.shade.ShadeHeadsUpTracker;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusHeadsUp {
    public HeadsUpTouchHelper createHeadsUpTouchHelper(HeadsUpManager headsUpManager, IStatusBarService iStatusBarService, HeadsUpTouchHelper.Callback callback, HeadsUpTouchHelper.HeadsUpNotificationViewController headsUpNotificationViewController) {
        return new HeadsUpTouchHelper(headsUpManager, iStatusBarService, callback, headsUpNotificationViewController);
    }

    public NotificationListContainer createNsslNotificationListContainer(NotificationListContainer notificationListContainer) {
        return notificationListContainer;
    }

    public HeadsUpManager getPaneViewHeadsUpManager(HeadsUpManager headsUpManager) {
        return headsUpManager;
    }

    public ShadeHeadsUpTracker getShadeHeadsUpTracker(ShadeHeadsUpTracker shadeHeadsUpTracker) {
        return shadeHeadsUpTracker;
    }

    public ViewGroup getWindowRootView() {
        return null;
    }

    public void reuseSwipeHelper(NotificationSwipeHelper.Builder builder, NotificationSwipeHelper.NotificationCallback notificationCallback, NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener) {
    }

    public abstract void updateSensitiveness(boolean z, boolean z2);

    public Runnable wrapOnFinishAnimationCallback(Runnable runnable) {
        return runnable;
    }
}
