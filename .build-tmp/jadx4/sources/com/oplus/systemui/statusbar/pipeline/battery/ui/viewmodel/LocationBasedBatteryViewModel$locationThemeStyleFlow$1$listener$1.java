package com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel;

import com.android.systemui.statusbar.policy.ConfigurationController;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LocationBasedBatteryViewModel$locationThemeStyleFlow$1$listener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ LocationBasedBatteryViewModel this$0;

    public final void onThemeChanged() {
        LocationBasedBatteryViewModel$locationThemeStyleFlow$1.invokeSuspend$sendThemeStyle(this.this$0, this.$$this$conflatedCallbackFlow, "onThemeChanged");
    }

    public final void onUiModeChanged() {
        LocationBasedBatteryViewModel$locationThemeStyleFlow$1.invokeSuspend$sendThemeStyle(this.this$0, this.$$this$conflatedCallbackFlow, "onUiModeChanged");
    }
}
