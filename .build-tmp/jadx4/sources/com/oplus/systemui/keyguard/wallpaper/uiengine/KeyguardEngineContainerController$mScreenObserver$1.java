package com.oplus.systemui.keyguard.wallpaper.uiengine;

import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.base.OplusKeyguardCtrl;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.oplus.systemui.keyguard.ui.viewmodel.OplusKeyguardClockViewModel;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardEngineContainerController$mScreenObserver$1 implements ScreenLifecycle.Observer {
    public /* synthetic */ KeyguardEngineContainerController this$0;

    public final void onScreenTurnedOff() {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.this$0.mOplusKeyguard;
        if (oplusKeyguardCtrl != null) {
            oplusKeyguardCtrl.onScreenTurnedOff();
        }
    }

    public final void onScreenTurnedOn() {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        if (keyguardEngineContainerController.isNeedShow$2()) {
            KeyguardEngineContainerController.access$updateVisibilityState(keyguardEngineContainerController, true);
        } else {
            keyguardEngineContainerController.onVisibilityChange$1(false);
        }
    }

    public final void onScreenTurningOff() {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        OplusLockIconView$.ExternalSyntheticOutline0.m("onScreenTurningOff mReloadOplusKeyguardLater:", "KeyguardEngineContainer", keyguardEngineContainerController.mReloadOplusKeyguardLater);
        if (!keyguardEngineContainerController.mReloadOplusKeyguardLater) {
            keyguardEngineContainerController.needReloadOplusKeyguard();
        } else {
            keyguardEngineContainerController.getMHandler().removeCallbacks(keyguardEngineContainerController.mReloadOplusKeyguardTask);
            keyguardEngineContainerController.getMHandler().postDelayed(keyguardEngineContainerController.mReloadOplusKeyguardTask, 100L);
        }
    }

    public final void onScreenTurningOn() {
        OplusKeyguardCtrl oplusKeyguardCtrl;
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        if (((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) keyguardEngineContainerController.keyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getClockSize() == ClockSize.IMMERSED || (oplusKeyguardCtrl = keyguardEngineContainerController.mOplusKeyguard) == null) {
            return;
        }
        oplusKeyguardCtrl.onScreenTurnedOn();
    }
}
