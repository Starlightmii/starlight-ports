package com.oplus.systemui.statusbar.notification.row.menupanel;

import com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenu;
import com.oplus.systemui.statusbar.notification.row.menupanel.popupmenu.NotificationPopupMenuOperation;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda2 implements Function0 {
    public /* synthetic */ NotificationPopupMenuControllerImpl f$0;
    public /* synthetic */ NotificationPopupMenuOperation f$1;

    public final Object invoke() {
        NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = this.f$0;
        return NotificationPopupMenu.Companion.newInstance(notificationPopupMenuControllerImpl.context, this.f$1, notificationPopupMenuControllerImpl.keyguardViewMediator, notificationPopupMenuControllerImpl.settingsPanelHelper);
    }
}
