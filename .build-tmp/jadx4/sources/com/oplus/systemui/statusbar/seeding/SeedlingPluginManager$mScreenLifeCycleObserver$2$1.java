package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.systemui.seedlingservice.utils.SeedlingMediaDataHandleUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$mScreenLifeCycleObserver$2$1 implements ScreenLifecycle.Observer {
    public /* synthetic */ SeedlingPluginManager this$0;

    public final void onScreenTurnedOff() {
        Bundle bundle = new Bundle();
        bundle.putString("method", "screenChanged");
        bundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_SCREEN_ON, false);
        SeedlingPlugin seedlingPlugin = this.this$0.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            seedlingPlugin.call(bundle);
        }
    }

    public final void onScreenTurnedOn() {
        Bundle bundle = new Bundle();
        bundle.putString("method", "screenChanged");
        bundle.putBoolean(SeedlingMediaDataHandleUtils.KEY_IS_SCREEN_ON, true);
        SeedlingPlugin seedlingPlugin = this.this$0.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            seedlingPlugin.call(bundle);
        }
    }
}
