package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import android.os.Handler;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.base.OplusKeyguardCtrl;
import com.android.systemui.keyguard.ScreenLifecycle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController$mScreenObserver$1 implements ScreenLifecycle.Observer {
    public /* synthetic */ PictorialEngineContainerController this$0;

    public final void onScreenTurnedOff() {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.this$0.mOplusKeyguard;
        if (oplusKeyguardCtrl != null) {
            oplusKeyguardCtrl.onScreenTurnedOff();
        }
    }

    public final void onScreenTurnedOn() {
        this.this$0.showOrHideOplusKeyguard();
    }

    public final void onScreenTurningOff() {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        OplusLockIconView$.ExternalSyntheticOutline0.m("onScreenTurningOff mReloadOplusKeyguardLater:", "PictorialEngineContainerController", pictorialEngineContainerController.mReloadOplusKeyguardLater);
        if (pictorialEngineContainerController.mReloadOplusKeyguardLater) {
            Handler handler = pictorialEngineContainerController.mHandler;
            if (handler != null) {
                handler.removeCallbacks(pictorialEngineContainerController.mReloadOplusKeyguardTask);
            }
            Handler handler2 = pictorialEngineContainerController.mHandler;
            if (handler2 != null) {
                handler2.postDelayed(pictorialEngineContainerController.mReloadOplusKeyguardTask, 100L);
            }
        }
    }

    public final void onScreenTurningOn() {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.this$0.mOplusKeyguard;
        if (oplusKeyguardCtrl != null) {
            oplusKeyguardCtrl.onScreenTurnedOn();
        }
    }
}
