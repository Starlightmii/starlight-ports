package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import com.android.keyguard.KeyguardUpdateMonitorCallback;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomPopupMenuControllerImpl$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusCustomPopupMenuControllerImpl this$0;

    public final void onKeyguardVisibilityChanged(boolean z) {
        OplusCustomPopupMenuControllerImpl oplusCustomPopupMenuControllerImpl = this.this$0;
        boolean z2 = oplusCustomPopupMenuControllerImpl.keyguardUpdateMonitor.mKeyguardShowing;
        boolean z3 = oplusCustomPopupMenuControllerImpl.keyguardUpdateMonitor.mKeyguardOccluded;
        if (z2 && z3) {
            OplusCustomPopupMenuController.dismissPopupMenu$default(oplusCustomPopupMenuControllerImpl, "LockScreenInOccluded", false, 4);
        }
    }
}
