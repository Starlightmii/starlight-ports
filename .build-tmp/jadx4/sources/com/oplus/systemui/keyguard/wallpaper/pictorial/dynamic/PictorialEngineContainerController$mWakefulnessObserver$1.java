package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import com.android.keyguard.base.OplusKeyguardCtrl;
import com.android.systemui.keyguard.WakefulnessLifecycle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController$mWakefulnessObserver$1 implements WakefulnessLifecycle.Observer {
    public /* synthetic */ PictorialEngineContainerController this$0;

    public final void onFinishedWakingUp() {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.this$0.mOplusKeyguard;
        if (oplusKeyguardCtrl != null) {
            oplusKeyguardCtrl.onFinishedWakingUp();
        }
    }

    public final void onStartedGoingToSleep() {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.this$0.mOplusKeyguard;
        if (oplusKeyguardCtrl != null) {
            oplusKeyguardCtrl.onStartedGoingToSleep();
        }
    }
}
