package com.oplus.systemui.plugins.qs.quickentrance;

import com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSQuickEntranceComponent$qsPanelExpandFractionListener$1 implements QSPanelExpandFraction.ExpandedFractionChangeListener {
    public /* synthetic */ OplusQSQuickEntranceComponent this$0;

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onBlurFractionChange() {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onBlurFractionEnd(boolean z) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onFractionChanged(float f) {
        OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
        if (oplusQSQuickEntranceComponent.mIsExpanded && f < 1.0f) {
            oplusQSQuickEntranceComponent.mIsExpanded = false;
            if (oplusQSQuickEntranceComponent.canCancelShowPullDownTextView) {
                OplusQSQuickEntranceComponent.access$cancelShowOrHideScenarioPreview(oplusQSQuickEntranceComponent);
            }
        }
        if (f == 1.0f) {
            oplusQSQuickEntranceComponent.mIsExpanded = true;
        }
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ExpandedFractionChangeListener
    public final void onFractionEnd(boolean z) {
        OplusQSQuickEntranceComponent oplusQSQuickEntranceComponent = this.this$0;
        oplusQSQuickEntranceComponent.mIsExpanded = z;
        if (z) {
            OplusQSQuickEntranceComponent.access$handleScenarioPreviewFirstShow(oplusQSQuickEntranceComponent);
        }
    }
}
