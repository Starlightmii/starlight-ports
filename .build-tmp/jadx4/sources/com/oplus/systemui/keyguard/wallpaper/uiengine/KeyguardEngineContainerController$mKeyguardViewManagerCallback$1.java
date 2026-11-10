package com.oplus.systemui.keyguard.wallpaper.uiengine;

import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardEngineContainerController$mKeyguardViewManagerCallback$1 implements StatusBarKeyguardViewManager.KeyguardViewManagerCallback {
    public /* synthetic */ KeyguardEngineContainerController this$0;

    public final void onQSExpansionChanged(float f) {
        boolean z = false;
        boolean z2 = f > 0.0f;
        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
        boolean z3 = keyguardEngineContainerController.mIsQsExpanded != z2;
        keyguardEngineContainerController.mIsQsExpanded = z2;
        if (z3 && ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() == 2 && !this.this$0.getMUpdateMonitor().mGoingToSleep) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onQsExpansionChanged: expand = ", "KeyguardEngineContainer", z2);
            KeyguardEngineContainerController keyguardEngineContainerController2 = this.this$0;
            boolean z4 = keyguardEngineContainerController2.mIsQsExpanded;
            if (z4 || keyguardEngineContainerController2.mStatusBarState == 1) {
                if (!z4 && !keyguardEngineContainerController2.mBouncerShow) {
                    z = true;
                }
                KeyguardEngineContainerController.access$updateVisibilityState(keyguardEngineContainerController2, z);
            }
        }
    }
}
