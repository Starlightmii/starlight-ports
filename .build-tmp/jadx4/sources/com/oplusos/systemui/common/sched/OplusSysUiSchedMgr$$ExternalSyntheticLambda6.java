package com.oplusos.systemui.common.sched;

import android.os.Handler;
import com.oplusos.systemui.common.concurrency.OplusThreadFactoryImpl;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusSysUiSchedMgr$$ExternalSyntheticLambda6 implements Function0 {
    public /* synthetic */ OplusSysUiSchedMgr f$0;

    public final Object invoke() {
        OplusSysUiSchedMgr oplusSysUiSchedMgr = this.f$0;
        Handler handler = new Handler(OplusThreadFactoryImpl.INSTANCE.buildNewThread("GestureBackground", -2).getLooper());
        OplusSysUiSchedMgr.AnonymousClass2 anonymousClass2 = new OplusSysUiSchedMgr.AnonymousClass2(1);
        anonymousClass2.this$0 = oplusSysUiSchedMgr;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass2);
        return handler;
    }
}
