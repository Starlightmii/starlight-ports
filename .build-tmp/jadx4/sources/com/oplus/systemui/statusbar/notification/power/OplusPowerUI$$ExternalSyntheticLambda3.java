package com.oplus.systemui.statusbar.notification.power;

import android.telecom.TelecomManager;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPowerUI$$ExternalSyntheticLambda3 implements Function0 {
    public /* synthetic */ OplusPowerUI f$0;

    public final Object invoke() {
        int callState;
        OplusPowerUI oplusPowerUI = this.f$0;
        oplusPowerUI.getClass();
        LogUtil.d(LogUtil.TAG_COMMON, "Oplus_PowerUI", "ACTION_PHONE_STATE_CHANGED");
        TelecomManager telecomManager = (TelecomManager) oplusPowerUI.mContext.getSystemService("telecom");
        if (telecomManager != null) {
            callState = telecomManager.getCallState();
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(callState, "ACTION_PHONE_STATE_CHANGED: state = ", LogUtil.TAG_COMMON, "Oplus_PowerUI");
        } else {
            callState = 0;
        }
        if (callState == 0) {
            return Unit.INSTANCE;
        }
        ThreadManager threadManager = ThreadManager.getInstance();
        OplusPowerUI$$ExternalSyntheticLambda0 oplusPowerUI$$ExternalSyntheticLambda0 = new OplusPowerUI$$ExternalSyntheticLambda0(2);
        oplusPowerUI$$ExternalSyntheticLambda0.f$0 = oplusPowerUI;
        VarHandle.storeStoreFence();
        threadManager.postInMainThread(oplusPowerUI$$ExternalSyntheticLambda0);
        return Unit.INSTANCE;
    }
}
