package com.oplus.systemui.media.seedling.dagger;

import com.android.systemui.CoreStartable;
import com.oplus.systemui.media.seedling.OplusMediaSeedlingController;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingStarter implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusMediaSeedlingStarter";
    private final OplusMediaSeedlingController mediaSeedlingController;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusMediaSeedlingStarter(OplusMediaSeedlingController oplusMediaSeedlingController) {
        this.mediaSeedlingController = oplusMediaSeedlingController;
    }

    public final void start() {
        OplusNotificationLogger.logMediaSeedling(TAG, "start");
        this.mediaSeedlingController.start();
    }
}
