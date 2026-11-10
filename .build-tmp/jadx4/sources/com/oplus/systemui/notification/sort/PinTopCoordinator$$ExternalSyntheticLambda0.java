package com.oplus.systemui.notification.sort;

import com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop.NotificationTopListManage;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PinTopCoordinator$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ PinTopCoordinator f$0;

    public final Object invoke() {
        PinTopCoordinator pinTopCoordinator = this.f$0;
        PinTopCoordinator$userPinTopNotifSectioner$2$1 pinTopCoordinator$userPinTopNotifSectioner$2$1 = new PinTopCoordinator$userPinTopNotifSectioner$2$1("Pintop(User)", 18);
        pinTopCoordinator$userPinTopNotifSectioner$2$1.notificationTopListManage = NotificationTopListManage.Companion.newInstance(pinTopCoordinator.context);
        return pinTopCoordinator$userPinTopNotifSectioner$2$1;
    }
}
