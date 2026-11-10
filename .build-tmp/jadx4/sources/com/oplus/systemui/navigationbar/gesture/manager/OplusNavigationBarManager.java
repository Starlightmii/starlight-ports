package com.oplus.systemui.navigationbar.gesture.manager;

import com.android.systemui.navigationbar.OplusNavigationBarManagerEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNavigationBarManager extends OplusNavigationBarManagerEx {
    public static final OplusNavigationBarManager S_OPLUS_NAVIGATION_BAR_MANAGER;
    public boolean mDetectToView;
    public KeyguardStateController mKeyguardStateController;

    static {
        OplusNavigationBarManager oplusNavigationBarManager = new OplusNavigationBarManager();
        oplusNavigationBarManager.mDetectToView = false;
        S_OPLUS_NAVIGATION_BAR_MANAGER = oplusNavigationBarManager;
    }

    public static OplusNavigationBarManager getInstance() {
        return S_OPLUS_NAVIGATION_BAR_MANAGER;
    }

    public boolean getSideGestureDetectToView() {
        return this.mDetectToView;
    }

    public void setKeyguardStateController(KeyguardStateController keyguardStateController) {
        this.mKeyguardStateController = keyguardStateController;
    }

    public void setSideGestureDetectToView(boolean z) {
        this.mDetectToView = z;
    }
}
