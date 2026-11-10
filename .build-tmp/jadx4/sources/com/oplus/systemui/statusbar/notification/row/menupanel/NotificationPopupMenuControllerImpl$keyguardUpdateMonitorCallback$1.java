package com.oplus.systemui.statusbar.notification.row.menupanel;

import com.android.keyguard.KeyguardUpdateMonitorCallback;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ NotificationPopupMenuControllerImpl this$0;

    public final void onKeyguardOccludeChange(boolean z, boolean z2) {
        NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = this.this$0;
        notificationPopupMenuControllerImpl.isKeyguardShowing = notificationPopupMenuControllerImpl.keyguardUpdateMonitor.mKeyguardShowing;
    }

    public final void onKeyguardVisibilityChanged(boolean z) {
        NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = this.this$0;
        notificationPopupMenuControllerImpl.isKeyguardShowing = notificationPopupMenuControllerImpl.keyguardUpdateMonitor.mKeyguardShowing;
        boolean z2 = notificationPopupMenuControllerImpl.keyguardUpdateMonitor.mKeyguardOccluded;
        if (notificationPopupMenuControllerImpl.isKeyguardShowing && z2) {
            NotificationPopupMenuController.dismissPopupMenu$default(notificationPopupMenuControllerImpl, "LockScreenInOccluded", false, 4);
        }
    }
}
