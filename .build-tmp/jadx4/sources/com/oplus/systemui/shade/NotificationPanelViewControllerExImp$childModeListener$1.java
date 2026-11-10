package com.oplus.systemui.shade;

import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$childModeListener$1 implements IObserverListener {
    public /* synthetic */ NotificationPanelViewControllerExImp this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        boolean z2 = ChildrenModeObserver.getInstance().mIsChildrenMode;
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        boolean zBooleanValue = ((Boolean) notificationPanelViewControllerExImp.shadeInteractor.isAnyExpanded().getValue()).booleanValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("NotificationPanelViewControllerExImp-->", "SystemUi--Notification", TextFieldImplKt$.ExternalSyntheticOutline0.m("childModeListener :", ", selfChange: ", " isAnyExpanded: ", z2, z), zBooleanValue);
        }
        if (z2 && zBooleanValue) {
            if (((OplusSeparateNotificationAndQSEx) notificationPanelViewControllerExImp.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS()) {
                ((OplusSeparateNotificationAndQSEx) notificationPanelViewControllerExImp.separateNotificationAndQSEx.get()).collapseQSPanel(false, false, false);
            }
            NotificationPanelViewController notificationPanelViewController = notificationPanelViewControllerExImp.panelViewController;
            if (notificationPanelViewController != null) {
                notificationPanelViewController.collapseNotifPanelWithoutAnimate();
            }
        }
    }
}
