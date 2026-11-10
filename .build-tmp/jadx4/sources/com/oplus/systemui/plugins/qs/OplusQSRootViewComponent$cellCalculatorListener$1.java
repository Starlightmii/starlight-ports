package com.oplus.systemui.plugins.qs;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootViewComponent$cellCalculatorListener$1 implements CellCalculatorManager.CellCalculatorListener {
    public /* synthetic */ OplusQSRootViewComponent this$0;

    @Override // com.oplus.systemui.plugins.qs.CellCalculatorManager.CellCalculatorListener
    public final void onCellSizeChange(boolean z) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
        oplusQSRootViewComponent.updateResource("onCellSizeChange", false);
    }

    @Override // com.oplus.systemui.plugins.qs.CellCalculatorManager.CellCalculatorListener
    public final void onGridInfoChange(boolean z, boolean z2, boolean z3) {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
        oplusQSRootViewComponent.updateResource("onGridInfoChange", false);
    }
}
