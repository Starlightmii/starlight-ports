package com.oplus.systemui.statusbar;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.seamless.FakeBinder;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiStateControllerImpl$keyguardStateCallback$1 implements KeyguardStateController.Callback {
    public /* synthetic */ SysUiStateControllerImpl this$0;

    public final void onKeyguardShowingChanged() {
        SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
        sysUiStateControllerImpl._isKeyguardShowing.setValue(Boolean.valueOf(sysUiStateControllerImpl.m383isKeyguardShowing()));
        if (!sysUiStateControllerImpl.getQsExpanded() || sysUiStateControllerImpl.m383isKeyguardShowing()) {
            return;
        }
        ((FakeBinder) sysUiStateControllerImpl.fakeBinder.get()).unbindQsFakeView(true);
        ((FakeBinder) sysUiStateControllerImpl.fakeBinder.get()).bindQsFakeView(false);
    }
}
