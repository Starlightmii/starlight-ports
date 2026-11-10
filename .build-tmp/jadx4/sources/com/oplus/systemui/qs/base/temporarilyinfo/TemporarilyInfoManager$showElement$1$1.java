package com.oplus.systemui.qs.base.temporarilyinfo;

import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoManager$showElement$1$1 implements Runnable {
    public /* synthetic */ TemporarilyInfoView $temporarilyView;
    public /* synthetic */ TemporarilyInfoManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        TemporarilyInfoManager temporarilyInfoManager = this.this$0;
        TemporarilyInfoView temporarilyInfoView = this.$temporarilyView;
        TemporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0 temporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0 = new TemporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0();
        temporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0.f$0 = temporarilyInfoManager;
        VarHandle.storeStoreFence();
        TemporarilyInfoManager.access$animateHide(temporarilyInfoManager, temporarilyInfoView, temporarilyInfoManager$showElement$1$1$$ExternalSyntheticLambda0);
    }
}
