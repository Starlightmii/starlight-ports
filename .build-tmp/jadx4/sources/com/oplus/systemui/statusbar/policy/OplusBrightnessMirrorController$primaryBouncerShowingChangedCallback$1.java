package com.oplus.systemui.statusbar.policy;

import android.os.Looper;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusBrightnessMirrorController$primaryBouncerShowingChangedCallback$1 implements KeyguardStateController.Callback {
    public /* synthetic */ OplusBrightnessMirrorController this$0;

    public final void onPrimaryBouncerShowingChanged() {
        OplusBrightnessMirrorController oplusBrightnessMirrorController = this.this$0;
        KeyguardStateControllerImpl keyguardStateControllerImpl = oplusBrightnessMirrorController.keyguardStateController;
        boolean z = keyguardStateControllerImpl.mPrimaryBouncerShowing;
        oplusBrightnessMirrorController.isPrimaryBouncerShowing = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusBrightnessMirrorController", KeyguardLogger$.ExternalSyntheticOutline0.m("onPrimaryBouncerShowingChanged: isPrimaryBouncerShowing = ", z), null, 8);
        if (!oplusBrightnessMirrorController.isPrimaryBouncerShowing) {
            keyguardStateControllerImpl.removeCallback(this);
        } else if (Intrinsics.areEqual(Thread.currentThread(), Looper.getMainLooper().getThread())) {
            oplusBrightnessMirrorController.handleMirrorInvisible();
        }
        oplusBrightnessMirrorController.hideMirrorInner(!oplusBrightnessMirrorController.isPrimaryBouncerShowing, false);
    }
}
