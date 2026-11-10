package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController$mKeyguardViewManagerCallback$1 implements StatusBarKeyguardViewManager.KeyguardViewManagerCallback {
    public /* synthetic */ PictorialEngineContainerController this$0;

    public final void onQSExpansionChanged(float f) {
        boolean z = false;
        boolean z2 = f > 0.0f;
        if (this.this$0.mIsQsExpanded == z2 || ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() != 2 || this.this$0.getMUpdateMonitor().mGoingToSleep) {
            return;
        }
        this.this$0.mIsQsExpanded = z2;
        OplusLockIconView$.ExternalSyntheticOutline0.m("onQsExpansionChanged: expand = ", "PictorialEngineContainerController", z2);
        PictorialEngineContainerController pictorialEngineContainerController = this.this$0;
        boolean z3 = pictorialEngineContainerController.mIsQsExpanded;
        if (z3 || pictorialEngineContainerController.mStatusBarState == 1) {
            pictorialEngineContainerController.setVisibility((z3 || pictorialEngineContainerController.mBouncerShow) ? 8 : 0);
            this.this$0.showOrHideOplusKeyguard();
            PictorialEngineContainerController pictorialEngineContainerController2 = this.this$0;
            if (!pictorialEngineContainerController2.mIsQsExpanded && !pictorialEngineContainerController2.mBouncerShow) {
                z = true;
            }
            pictorialEngineContainerController2.onVisibilityChange(z);
        }
    }
}
