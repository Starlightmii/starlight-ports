package com.oplus.systemui.statusbar.notification.row.menupanel;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationPopupMenuControllerImpl f$0;

    public /* synthetic */ NotificationPopupMenuControllerImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        NotificationPopupMenuControllerImpl notificationPopupMenuControllerImpl = this.f$0;
        switch (i) {
            case 0:
                return NotificationPopupMenuControllerImpl.$r8$lambda$OEMqLOqEQXwOThYI9yfgdLlj19Y(notificationPopupMenuControllerImpl);
            default:
                return NotificationViewCloner.Companion.newInstance(notificationPopupMenuControllerImpl.context, notificationPopupMenuControllerImpl.getWindowManager(), notificationPopupMenuControllerImpl.viewBlurManager);
        }
    }
}
