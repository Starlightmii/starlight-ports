package com.oplus.systemui.notification.aonaccess;

import com.oplus.systemui.notification.aon.AonAuthManager;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthStarter$openCamera$invokeResult$1 implements Runnable {
    public /* synthetic */ Function1 $onGetAonAuthResult;
    public /* synthetic */ AonAuthStarter this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Function1 function1 = this.$onGetAonAuthResult;
        Boolean aonAuthResult = ((AonAuthManager) this.this$0.aonAuthManager.get()).getAonAuthResult();
        function1.invoke(Boolean.valueOf(aonAuthResult != null ? aonAuthResult.booleanValue() : false));
        this.this$0.onGetAonAuthCallbacks.remove(this);
    }
}
