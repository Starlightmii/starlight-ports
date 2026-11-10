package com.oplus.systemui.media.seedling;

import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingController$wakefulnessObserver$1 implements WakefulnessLifecycle.Observer {
    public /* synthetic */ OplusMediaSeedlingController this$0;

    public final void onFinishedGoingToSleep() {
        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onFinishedGoingToSleep");
        this.this$0.setScreenOn(false);
    }

    public final void onStartedWakingUp() {
        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onStartedWakingUp");
        this.this$0.setScreenOn(true);
    }
}
