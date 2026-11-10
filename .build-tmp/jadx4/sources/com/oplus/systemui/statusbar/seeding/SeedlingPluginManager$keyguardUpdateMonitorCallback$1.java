package com.oplus.systemui.statusbar.seeding;

import android.util.Log;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ SeedlingPluginManager this$0;

    public final void onKeyguardVisibilityChanged(boolean z) {
        SeedlingPluginManager seedlingPluginManager = this.this$0;
        boolean zIsKeyguardVisible$1 = seedlingPluginManager.keyguardUpdateMonitor.isKeyguardVisible$1();
        boolean z2 = seedlingPluginManager.keyguardUpdateMonitor.mKeyguardOccluded;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" isShowActivityOverLockscreen=", "SeedlingPluginManager-->", TextFieldImplKt$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged visible=", " isKeyguardVisible=", " isKeyguardOccluded=", z, zIsKeyguardVisible$1), z2, seedlingPluginManager.isShowActivityOverLockscreen));
        }
        SeedlingPluginManager.access$onKeyguardStateChanged(seedlingPluginManager, zIsKeyguardVisible$1, z2);
        if (seedlingPluginManager.isShowActivityOverLockscreen) {
            seedlingPluginManager.setLaunchActivityOverLockscreen(false);
        }
    }
}
