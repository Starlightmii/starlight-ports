package com.oplus.systemui.navigationbar.gesture;

import android.os.oplusdevicepolicy.OplusDevicepolicyManager;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavBarOpenPlatformCustomize$isNavBarDisabledObserver$1 implements OplusDevicepolicyManager.OplusDevicePolicyObserver {
    public /* synthetic */ NavBarOpenPlatformCustomize this$0;

    public final void onOplusDevicePolicyUpdate(String str, String str2) {
        this.this$0.isNavigationBarDisabled = Boolean.parseBoolean(str2);
    }

    public final void onOplusDevicePolicyUpdate(String str, List list) {
    }
}
