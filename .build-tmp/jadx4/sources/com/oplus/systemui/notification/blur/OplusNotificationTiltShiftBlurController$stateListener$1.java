package com.oplus.systemui.notification.blur;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutControllerExt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationTiltShiftBlurController$stateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusNotificationTiltShiftBlurController this$0;

    public final void onStateChanged(int i) {
        OplusNotificationTiltShiftBlurController oplusNotificationTiltShiftBlurController = this.this$0;
        oplusNotificationTiltShiftBlurController.currentStatusBarState = i;
        OplusNotificationTiltShiftBlurController.access$applyCurrentState(oplusNotificationTiltShiftBlurController, "onStateChanged");
        ((NotificationStackScrollLayoutControllerExt) oplusNotificationTiltShiftBlurController.notificationStackScrollLayoutControllerExt.get()).onStatusBarStateChanged(i);
    }
}
