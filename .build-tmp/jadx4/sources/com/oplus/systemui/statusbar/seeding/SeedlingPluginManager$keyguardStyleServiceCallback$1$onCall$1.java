package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1 implements Runnable {
    public /* synthetic */ Bundle $bundle;
    public /* synthetic */ String $method;
    public /* synthetic */ SeedlingPluginManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        if (!Intrinsics.areEqual(this.$method, "onUiStateChanged")) {
            if (!Intrinsics.areEqual(this.$method, "onAODUiStateChanged") || (bundle = this.$bundle) == null) {
                return;
            }
            SeedlingPluginManager seedlingPluginManager = this.this$0;
            boolean z = bundle.getBoolean("shouldUpdateUi", true);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "SeedlingPluginManager", KeyguardLogger$.ExternalSyntheticOutline0.m("shouldUpdateUi: ", z), null, 8);
            Bundle bundle2 = new Bundle();
            bundle2.putString("state", "onAODUiStateChanged");
            bundle2.putBoolean("aodUiState", z);
            SeedlingPlugin seedlingPlugin = seedlingPluginManager.getSeedlingPlugin();
            if (seedlingPlugin != null) {
                seedlingPlugin.onStateChange(bundle2);
                return;
            }
            return;
        }
        Bundle bundle3 = this.$bundle;
        if (bundle3 != null) {
            SeedlingPluginManager seedlingPluginManager2 = this.this$0;
            SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState = SmoothTransitionController.SmoothTransitionUiState.UNKNOW_STATE;
            int i = bundle3.getInt("uiState", 0);
            int i2 = i != 5 ? 0 : 1;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, "SeedlingPluginManager", TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "onUiStateChanged, uiState=", " panoramicAod="), null, 8);
            Bundle bundle4 = new Bundle();
            bundle4.putString("state", "panoramicAodStateChange");
            bundle4.putInt("panoramicAodState", i2);
            SeedlingPlugin seedlingPlugin2 = seedlingPluginManager2.getSeedlingPlugin();
            if (seedlingPlugin2 != null) {
                seedlingPlugin2.onStateChange(bundle4);
            }
        }
    }
}
