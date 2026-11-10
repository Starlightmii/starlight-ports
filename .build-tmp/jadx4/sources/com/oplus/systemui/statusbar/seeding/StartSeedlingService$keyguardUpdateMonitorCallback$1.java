package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StartSeedlingService$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ StartSeedlingService this$0;

    public final void onKeyguardVisibilityChanged(boolean z) {
        StartSeedlingService startSeedlingService = this.this$0;
        boolean z2 = startSeedlingService.keyguardUpdateMonitor.mKeyguardShowing;
        boolean z3 = startSeedlingService.keyguardUpdateMonitor.mKeyguardOccluded;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("StartSeedlingService-->", "SystemUi--Common", TextFieldImplKt$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged visible=", ", isKeyguardShowing=", ", isKeyguardOccluded=", z, z2), z3);
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("isKeyguardShowing", z2);
        bundle.putBoolean("isKeyguardOccluded", z3);
        startSeedlingService.sendMessage(bundle, true);
    }
}
