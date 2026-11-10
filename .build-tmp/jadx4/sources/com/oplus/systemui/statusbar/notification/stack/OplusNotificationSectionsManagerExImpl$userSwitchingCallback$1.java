package com.oplus.systemui.statusbar.notification.stack;

import android.os.Bundle;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationSectionsManagerExImpl$userSwitchingCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusNotificationSectionsManagerExImpl this$0;

    public final void onUserSwitching(int i) {
        Bundle bundle = new Bundle();
        bundle.putString("state", "onUserIdChanged");
        bundle.putInt("onUserIdChanged", i);
        SeedlingPlugin seedlingPlugin = this.this$0.plugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle);
        }
    }
}
