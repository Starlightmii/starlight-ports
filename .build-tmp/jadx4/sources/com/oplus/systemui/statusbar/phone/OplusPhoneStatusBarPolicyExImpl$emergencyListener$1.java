package com.oplus.systemui.statusbar.phone;

import com.android.systemui.statusbar.connectivity.NetworkController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPhoneStatusBarPolicyExImpl$emergencyListener$1 implements NetworkController.EmergencyListener {
    public /* synthetic */ OplusPhoneStatusBarPolicyExImpl this$0;

    public final void setEmergencyCallsOnly(boolean z) {
        OplusPhoneStatusBarPolicyExImpl oplusPhoneStatusBarPolicyExImpl = this.this$0;
        oplusPhoneStatusBarPolicyExImpl.emergencyOnly = z;
        oplusPhoneStatusBarPolicyExImpl.updateSOS(z);
    }
}
