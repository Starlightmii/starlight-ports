package com.oplus.systemui.notification.aod;

import android.os.Bundle;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1$onCall$1 implements Runnable {
    public /* synthetic */ Bundle $bundle;
    public /* synthetic */ String $method;
    public /* synthetic */ OplusPanoramicAodCoreStartable this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        if (!Intrinsics.areEqual(this.$method, "onAODUiStateChanged") || (bundle = this.$bundle) == null) {
            return;
        }
        OplusPanoramicAodCoreStartable oplusPanoramicAodCoreStartable = this.this$0;
        oplusPanoramicAodCoreStartable.setShouldUpdateUi(bundle.getBoolean("shouldUpdateUi", true));
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusPanoramicAodCoreStartable", KeyguardLogger$.ExternalSyntheticOutline0.m("onCall shouldUpdateUi  ", oplusPanoramicAodCoreStartable.getShouldUpdateUi()), null, 8);
        if (!oplusPanoramicAodCoreStartable.getSwitchAodEnable()) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusPanoramicAodCoreStartable", "onCall:switchAodEnable  false", null, 8);
            return;
        }
        if (!oplusPanoramicAodCoreStartable.getShouldUpdateUi() && !oplusPanoramicAodCoreStartable.isPanoramicAod) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusPanoramicAodCoreStartable", "onCall:isPanoramicAod and shouldUpdateUi all false", null, 8);
            return;
        }
        Runnable runnable = new OplusPanoramicAodCoreStartable.onFinishedGoingToSleep.1(1);
        ((OplusPanoramicAodCoreStartable.onFinishedGoingToSleep.1) runnable).this$0 = oplusPanoramicAodCoreStartable;
        VarHandle.storeStoreFence();
        if (oplusPanoramicAodCoreStartable.getShouldUpdateUi()) {
            runnable.run();
        } else {
            SystemUIThreadUtil.getMainHandler().postDelayed(runnable, 200L);
        }
    }
}
