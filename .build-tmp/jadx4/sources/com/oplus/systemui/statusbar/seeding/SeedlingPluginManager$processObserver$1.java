package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.CommonProcessObserverEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$processObserver$1 implements CommonProcessObserverEx.ICommonProcessObserver {
    public /* synthetic */ SeedlingPluginManager this$0;

    public final void onProcessDied(int i, int i2, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("state", "onProcessDied");
        bundle.putInt("pid", i);
        bundle.putInt("uid", i2);
        bundle.putString("pkgName", str);
        SeedlingPlugin seedlingPlugin = this.this$0.getSeedlingPlugin();
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle);
        }
    }
}
