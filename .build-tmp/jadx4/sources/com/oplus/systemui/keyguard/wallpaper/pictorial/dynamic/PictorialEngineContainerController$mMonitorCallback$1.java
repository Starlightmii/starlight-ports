package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import android.os.Handler;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.oplusos.keyguard.utils.KgdLogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController$mMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ PictorialEngineContainerController this$0;

    public final void onKeyguardBouncerStateChanged(boolean z) {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        pictorialEngineContainerController.mBouncerShow = z;
        if (z || pictorialEngineContainerController.mStatusBarState == 1) {
            pictorialEngineContainerController.setVisibility((pictorialEngineContainerController.mIsQsExpanded || z) ? 8 : 0);
            pictorialEngineContainerController.showOrHideOplusKeyguard();
            pictorialEngineContainerController.onVisibilityChange((pictorialEngineContainerController.mIsQsExpanded || pictorialEngineContainerController.mBouncerShow) ? false : true);
        }
    }

    public final void onKeyguardVisibilityChanged(boolean z) {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        boolean z2 = z && pictorialEngineContainerController.getMKeyguardStateControllerImpl().mShowing;
        if (pictorialEngineContainerController.mReloadOplusKeyguardLater) {
            KgdLogUtil.i("PictorialEngineContainerController", "onKeyguardVisibilityChanged mReloadOplusKeyguardLater");
            Handler handler = pictorialEngineContainerController.mHandler;
            if (handler != null) {
                handler.removeCallbacks(pictorialEngineContainerController.mReloadOplusKeyguardTask);
            }
            Handler handler2 = pictorialEngineContainerController.mHandler;
            if (handler2 != null) {
                handler2.postDelayed(pictorialEngineContainerController.mReloadOplusKeyguardTask, 100L);
            }
        }
        pictorialEngineContainerController.setVisibility(z2 ? 0 : 8);
        pictorialEngineContainerController.onVisibilityChange(z2);
        pictorialEngineContainerController.showOrHideOplusKeyguard();
    }

    public final void onOSThemeChanged() {
        KgdLogUtil.i("PictorialEngineContainerController", "onOSThemeChanged");
    }

    public final void onResolutionChanged() {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        OlockStateUtil$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged onResolutionChanged isKeyguardVisible:", ", mReloadOplusKeyguardLater:", "PictorialEngineContainerController", pictorialEngineContainerController.getMUpdateMonitor().isKeyguardVisible$1(), pictorialEngineContainerController.mReloadOplusKeyguardLater);
        if (!pictorialEngineContainerController.getMUpdateMonitor().isKeyguardVisible$1()) {
            pictorialEngineContainerController.mReloadOplusKeyguardLater = true;
            return;
        }
        Handler handler = pictorialEngineContainerController.mHandler;
        if (handler != null) {
            handler.removeCallbacks(pictorialEngineContainerController.mReloadOplusKeyguardTask);
        }
        Handler handler2 = pictorialEngineContainerController.mHandler;
        if (handler2 != null) {
            handler2.postDelayed(pictorialEngineContainerController.mReloadOplusKeyguardTask, 100L);
        }
    }

    public final void onSuperPowerSaveChanged(boolean z) {
        this.this$0.reloadOplusKeyguard();
    }

    public final void onUserSwitchComplete(int i) {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        pictorialEngineContainerController.mUserSwitching = false;
        pictorialEngineContainerController.showOrHideOplusKeyguard();
    }

    public final void onUserSwitching(int i) {
        this.this$0.mUserSwitching = true;
    }
}
