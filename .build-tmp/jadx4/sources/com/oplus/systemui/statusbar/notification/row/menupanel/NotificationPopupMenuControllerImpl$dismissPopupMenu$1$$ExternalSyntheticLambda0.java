package com.oplus.systemui.statusbar.notification.row.menupanel;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationPopupMenuControllerImpl$dismissPopupMenu$1$$ExternalSyntheticLambda0 implements Function1 {
    public /* synthetic */ NotificationPopupMenuControllerImpl f$0;
    public /* synthetic */ Function0 f$1;

    public final Object invoke(Object obj) {
        NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = this.f$0;
        Function0 function0 = this.f$1;
        NotificationPopupMenuControllerImpl.access$removeMenuPanel(notificationPopupMenuControllerImpl, (NotificationMenuPanelView) obj);
        if (function0 != null) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }
}
