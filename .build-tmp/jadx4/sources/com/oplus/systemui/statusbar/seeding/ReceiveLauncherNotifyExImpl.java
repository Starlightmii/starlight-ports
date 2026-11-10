package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import com.android.systemui.statusbar.seedling.ReceiveLauncherNotifyEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ReceiveLauncherNotifyExImpl extends ReceiveLauncherNotifyEx {
    private final SeedlingPluginManager seedlingPluginManager;

    public ReceiveLauncherNotifyExImpl(SeedlingPluginManager seedlingPluginManager) {
        this.seedlingPluginManager = seedlingPluginManager;
    }

    public void notifyAppTransition(Bundle bundle) {
        this.seedlingPluginManager.notifyAppTransition(bundle);
    }

    public final void onOverviewProxyServiceInit() {
        this.seedlingPluginManager.onOverviewProxyServiceInit();
    }
}
