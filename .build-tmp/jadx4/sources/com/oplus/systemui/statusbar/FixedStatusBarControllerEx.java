package com.oplus.systemui.statusbar;

import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface FixedStatusBarControllerEx {
    void updateFixedKeyguardStatusBarView(boolean z, KeyguardStatusBarView keyguardStatusBarView, String str);

    void updateFixedStatusBarView(boolean z, PhoneStatusBarView phoneStatusBarView, String str);
}
