package com.oplus.systemui.statusbar.policy;

import android.os.Looper;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBrightnessMirrorController$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusBrightnessMirrorController this$0;

    public final void onKeyguardVisibilityChanged(boolean z) {
        OplusBrightnessMirrorController oplusBrightnessMirrorController = this.this$0;
        KeyguardUpdateMonitor keyguardUpdateMonitor = oplusBrightnessMirrorController.keyguardUpdateMonitor;
        boolean z2 = keyguardUpdateMonitor.mKeyguardShowing;
        boolean z3 = keyguardUpdateMonitor.mKeyguardOccluded;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusBrightnessMirrorController", PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged: isKeyguardShowing = ", ", isKeyguardOccluded = ", z2, z3), null, 8);
        if (z2 && z3) {
            if (Intrinsics.areEqual(Thread.currentThread(), Looper.getMainLooper().getThread())) {
                oplusBrightnessMirrorController.handleMirrorInvisible();
            }
            oplusBrightnessMirrorController.hideMirror();
        }
    }
}
