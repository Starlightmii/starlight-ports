package com.oplus.systemui.statusbar.phone;

import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarRemoteInputCallbackExImpl$keyguardStateControllerCallback$1 implements KeyguardStateController.Callback {
    public /* synthetic */ StatusBarRemoteInputCallbackExImpl this$0;

    public final void onPrimaryBouncerShowingChanged() {
        Function0 function0;
        StatusBarRemoteInputCallbackExImpl statusBarRemoteInputCallbackExImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onPrimaryBouncerShowingChanged isShowing ", " isPrimaryBouncerShowing ", "StatusBarRemoteInputCallbackExImpl-->", statusBarRemoteInputCallbackExImpl.keyguardStateController.mShowing, statusBarRemoteInputCallbackExImpl.keyguardStateController.mPrimaryBouncerShowing));
        }
        if (statusBarRemoteInputCallbackExImpl.keyguardStateController.mPrimaryBouncerShowing) {
            return;
        }
        if (statusBarRemoteInputCallbackExImpl.keyguardStateController.mShowing && (function0 = statusBarRemoteInputCallbackExImpl.resetRemoteInputCallback) != null) {
            function0.invoke();
        }
        statusBarRemoteInputCallbackExImpl.resetRemoteInputCallback = null;
        statusBarRemoteInputCallbackExImpl.keyguardStateController.removeCallback(this);
    }
}
