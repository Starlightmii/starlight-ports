package com.oplus.systemui.shade;

import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationsQSContainerController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ NotificationPanelViewControllerExImp this$0;

    public final void onStateChanged(int i) {
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationPanelViewControllerExImp", TextFieldBuffer$.ExternalSyntheticOutline0.m(notificationPanelViewControllerExImp.currentBarState, i, "onStateChanged, oldState: ", " -> newState: "), null, 8);
        notificationPanelViewControllerExImp.currentBarState = i;
        ((NotificationsQSContainerController) notificationPanelViewControllerExImp.notificationsQSContainerController.get()).updateBottomSpacing$1();
    }
}
