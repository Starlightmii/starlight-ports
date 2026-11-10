package com.oplus.systemui.qs.policy;

import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSSecurityText$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSSecurityText f$0;

    public /* synthetic */ OplusQSSecurityText$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        OplusQSSecurityText oplusQSSecurityText = this.f$0;
        ((Boolean) obj).getClass();
        OplusQSSecurityText.Companion companion = OplusQSSecurityText.Companion;
        switch (i) {
            case 0:
                OplusQSSecurityText$initQSSecurityObserver$1$1$1 oplusQSSecurityText$initQSSecurityObserver$1$1$1 = new OplusQSSecurityText$initQSSecurityObserver$1$1$1(0);
                oplusQSSecurityText$initQSSecurityObserver$1$1$1.this$0 = oplusQSSecurityText;
                VarHandle.storeStoreFence();
                return Boolean.valueOf(SystemUIThreadUtil.runOnUiThread(oplusQSSecurityText$initQSSecurityObserver$1$1$1));
            case 1:
                OplusQSSecurityText$initQSSecurityObserver$1$1$1 oplusQSSecurityText$initQSSecurityObserver$1$1$2 = new OplusQSSecurityText$initQSSecurityObserver$1$1$1(1);
                oplusQSSecurityText$initQSSecurityObserver$1$1$2.this$0 = oplusQSSecurityText;
                VarHandle.storeStoreFence();
                return Boolean.valueOf(SystemUIThreadUtil.runOnUiThread(oplusQSSecurityText$initQSSecurityObserver$1$1$2));
            default:
                OplusQSSecurityText$initQSSecurityObserver$1$1$1 oplusQSSecurityText$initQSSecurityObserver$1$1$3 = new OplusQSSecurityText$initQSSecurityObserver$1$1$1(2);
                oplusQSSecurityText$initQSSecurityObserver$1$1$3.this$0 = oplusQSSecurityText;
                VarHandle.storeStoreFence();
                return Boolean.valueOf(SystemUIThreadUtil.runOnUiThread(oplusQSSecurityText$initQSSecurityObserver$1$1$3));
        }
    }
}
