package com.oplus.systemui.plugins.qs.customize.component;

import com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPersonalComponent$resizeCallBack$2$1 implements IDeformStateChanged {
    public /* synthetic */ OplusQSPersonalComponent this$0;

    @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged
    public final void onDeformEnd() {
        this.this$0.getDragCallbackProxy().exitDragMode();
    }

    @Override // com.oplus.systemui.plugins.qs.customize.view.deformation.IDeformStateChanged
    public final void onDeformStarted() {
        this.this$0.getDragCallbackProxy().enterDragMode();
    }
}
