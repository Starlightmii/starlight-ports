package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.util.Log;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallbackEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatusBarRemoteInputCallbackExImpl extends StatusBarRemoteInputCallbackEx {
    private static final Companion Companion = new Companion();
    private static final String TAG = "StatusBarRemoteInputCallbackExImpl";
    private final KeyguardStateController keyguardStateController;
    private final KeyguardStateController.Callback keyguardStateControllerCallback;
    private Function0 resetRemoteInputCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StatusBarRemoteInputCallbackExImpl(KeyguardStateController keyguardStateController) {
        this.keyguardStateController = keyguardStateController;
        StatusBarRemoteInputCallbackExImpl$keyguardStateControllerCallback$1 statusBarRemoteInputCallbackExImpl$keyguardStateControllerCallback$1 = new StatusBarRemoteInputCallbackExImpl$keyguardStateControllerCallback$1();
        statusBarRemoteInputCallbackExImpl$keyguardStateControllerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardStateControllerCallback = statusBarRemoteInputCallbackExImpl$keyguardStateControllerCallback$1;
    }

    public void showBouncer(ActivityStarter activityStarter, Context context, StatusBarKeyguardViewManager statusBarKeyguardViewManager, Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "StatusBarRemoteInputCallbackExImpl-->showBouncer");
        }
        this.resetRemoteInputCallback = function0;
        this.keyguardStateController.removeCallback(this.keyguardStateControllerCallback);
        this.keyguardStateController.addCallback(this.keyguardStateControllerCallback);
        activityStarter.dismissKeyguardThenExecute(showBouncer.2.INSTANCE, (Runnable) null, !FingerSwitchHelper.Companion.getInstance(context).isFingerprintUnlockEnable());
    }
}
