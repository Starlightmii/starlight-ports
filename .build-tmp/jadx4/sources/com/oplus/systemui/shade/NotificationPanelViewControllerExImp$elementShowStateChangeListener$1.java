package com.oplus.systemui.shade;

import android.view.ViewGroup;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.PanelType;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ NotificationPanelViewControllerExImp this$0;

    public final void onElementShowingChanged() {
        ViewGroup npv;
        CentralSurfaces centralSurfaces;
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        if (notificationPanelViewControllerExImp.panelAnimationEx.isElementShowing()) {
            return;
        }
        NotificationPanelViewController base = notificationPanelViewControllerExImp.getBase();
        Float fValueOf = null;
        OplusSeparateNotificationAndQSEx separateNotificationAndQS = (base == null || (centralSurfaces = base.mCentralSurfaces) == null) ? null : centralSurfaces.getSeparateNotificationAndQS();
        if (separateNotificationAndQS == null || !separateNotificationAndQS.enableSeparateNotificationAndQS()) {
            return;
        }
        NotificationPanelViewController notificationPanelViewController = notificationPanelViewControllerExImp.panelViewController;
        if (notificationPanelViewController != null && (npv = notificationPanelViewController.getNpv()) != null) {
            fValueOf = Float.valueOf(npv.getTranslationX());
        }
        if (Intrinsics.areEqual(fValueOf, 0.0f)) {
            separateNotificationAndQS.stopHorizontalSlideSwitchOnCollapse(PanelType.NOTIFICATION_PANEL);
        }
    }
}
