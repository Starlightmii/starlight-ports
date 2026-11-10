package com.oplus.systemui.notification.row.material;

import com.android.systemui.CoreStartable;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialCoreStartable implements CoreStartable {
    private final Lazy materialColorModeController;
    private final Lazy materialUiModeController;

    public NotificationMaterialCoreStartable(Lazy lazy, Lazy lazy2) {
        this.materialColorModeController = lazy;
        this.materialUiModeController = lazy2;
    }

    public final void start() {
        NotificationMaterialColorConfig.INSTANCE.loadConfig("initConfig");
        ((NotificationMaterialColorModeController) this.materialColorModeController.get()).start();
        ((NotificationMaterialUiModeController) this.materialUiModeController.get()).start();
    }
}
