package com.oplus.systemui.notification.aod;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import com.oplus.keyguard.IOplusKeyguardStyleServiceCallback;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleServiceCallbackImpl;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusPanoramicAodCoreStartable f$0;

    public final Object invoke() {
        int i = this.$r8$classId;
        final OplusPanoramicAodCoreStartable oplusPanoramicAodCoreStartable = this.f$0;
        switch (i) {
            case 0:
                return new IOplusKeyguardStyleServiceCallback() { // from class: com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1
                    public IBinder asBinder() {
                        return null;
                    }

                    public void onCall(String str, Bundle bundle) {
                        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
                        OplusPanoramicAodCoreStartable oplusPanoramicAodCoreStartable2 = oplusPanoramicAodCoreStartable;
                        OplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1 oplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1 = new OplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1();
                        oplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1.$method = str;
                        oplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1.$bundle = bundle;
                        oplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1.this$0 = oplusPanoramicAodCoreStartable2;
                        VarHandle.storeStoreFence();
                        mainHandler.post(oplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1);
                    }
                };
            default:
                return new KeyguardStyleServiceCallbackImpl() { // from class: com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1
                    public void onUiStateChanged(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState, boolean z) {
                        OplusPanoramicAodCoreStartable oplusPanoramicAodCoreStartable2 = oplusPanoramicAodCoreStartable;
                        OplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1 oplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1 = new OplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1();
                        oplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1.this$0 = oplusPanoramicAodCoreStartable2;
                        oplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1.$uiState = smoothTransitionUiState;
                        oplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1.$isAnim = z;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(oplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1$onUiStateChanged$1);
                    }
                };
        }
    }
}
