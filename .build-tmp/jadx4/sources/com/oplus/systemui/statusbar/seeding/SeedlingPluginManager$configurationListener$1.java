package com.oplus.systemui.statusbar.seeding;

import android.app.Service;
import android.content.res.Configuration;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ SeedlingPluginManager this$0;

    public final void onConfigChanged(Configuration configuration) {
        Service seedlingPlugin = this.this$0.getSeedlingPlugin();
        Service service = seedlingPlugin instanceof Service ? seedlingPlugin : null;
        if (configuration == null || service == null) {
            return;
        }
        service.onConfigurationChanged(configuration);
    }
}
