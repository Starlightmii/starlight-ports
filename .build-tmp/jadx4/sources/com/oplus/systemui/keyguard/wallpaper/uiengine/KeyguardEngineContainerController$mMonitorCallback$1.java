package com.oplus.systemui.keyguard.wallpaper.uiengine;

import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardEngineContainerController$mMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ KeyguardEngineContainerController this$0;

    public final void onKeyguardBouncerStateChanged(boolean z) {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        keyguardEngineContainerController.mBouncerShow = z;
        if (z || keyguardEngineContainerController.mStatusBarState == 1) {
            KeyguardEngineContainerController.access$updateVisibilityState(keyguardEngineContainerController, (keyguardEngineContainerController.mIsQsExpanded || z) ? false : true);
        }
    }

    public final void onKeyguardVisibilityChanged(boolean z) {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        boolean z2 = z && keyguardEngineContainerController.getMKeyguardStateControllerImpl().mShowing;
        if (keyguardEngineContainerController.mReloadOplusKeyguardLater) {
            KgdLogUtil.i("KeyguardEngineContainer", "onKeyguardVisibilityChanged mReloadOplusKeyguardLater");
            keyguardEngineContainerController.getMHandler().removeCallbacks(keyguardEngineContainerController.mReloadOplusKeyguardTask);
            keyguardEngineContainerController.getMHandler().postDelayed(keyguardEngineContainerController.mReloadOplusKeyguardTask, 100L);
        }
        KeyguardEngineContainerController.access$updateVisibilityState(keyguardEngineContainerController, z2);
    }

    public final void onOSThemeChanged() {
        KgdLogUtil.i("KeyguardEngineContainer", "onOSThemeChanged");
        this.this$0.needReloadOplusKeyguard();
    }

    public final void onResolutionChanged() {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        OlockStateUtil$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged onResolutionChanged isKeyguardVisible:", ", mReloadOplusKeyguardLater:", "KeyguardEngineContainer", keyguardEngineContainerController.getMUpdateMonitor().isKeyguardVisible$1(), keyguardEngineContainerController.mReloadOplusKeyguardLater);
        if (!keyguardEngineContainerController.getMUpdateMonitor().isKeyguardVisible$1()) {
            keyguardEngineContainerController.mReloadOplusKeyguardLater = true;
        } else {
            keyguardEngineContainerController.getMHandler().removeCallbacks(keyguardEngineContainerController.mReloadOplusKeyguardTask);
            keyguardEngineContainerController.getMHandler().postDelayed(keyguardEngineContainerController.mReloadOplusKeyguardTask, 100L);
        }
    }

    public final void onSuperPowerSaveChanged(boolean z) {
        this.this$0.reloadOplusKeyguard$1();
    }

    public final void onUserSwitchComplete(int i) {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        keyguardEngineContainerController.mUserSwitching = false;
        keyguardEngineContainerController.showOrHideOplusKeyguard$1();
    }

    public final void onUserSwitching(int i) {
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        keyguardEngineContainerController.mUserSwitching = true;
        keyguardEngineContainerController.needReloadOplusKeyguard();
    }
}
