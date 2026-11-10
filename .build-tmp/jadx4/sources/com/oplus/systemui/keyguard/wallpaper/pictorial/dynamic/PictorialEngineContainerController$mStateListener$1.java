package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController$mStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ PictorialEngineContainerController this$0;

    public final void onStateChanged(int i) {
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        pictorialEngineContainerController.mStatusBarState = i;
        if (pictorialEngineContainerController.getMPictorialEngineContainer().isAttachedToWindow()) {
            pictorialEngineContainerController.setVisibility(pictorialEngineContainerController.mStatusBarState == 1 ? 0 : 8);
            pictorialEngineContainerController.showOrHideOplusKeyguard();
            pictorialEngineContainerController.onVisibilityChange(pictorialEngineContainerController.mStatusBarState == 1);
        }
    }
}
