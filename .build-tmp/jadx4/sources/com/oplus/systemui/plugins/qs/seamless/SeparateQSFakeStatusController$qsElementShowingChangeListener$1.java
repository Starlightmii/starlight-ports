package com.oplus.systemui.plugins.qs.seamless;

import com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSFakeStatusController$qsElementShowingChangeListener$1 implements QSPanelExpandFraction.ElementVisibleChangeListener {
    public /* synthetic */ SeparateQSFakeStatusController this$0;

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ElementVisibleChangeListener
    public final void onFirstElementVisibleChanged(boolean z, boolean z2) {
        SeparateQSFakeStatusController.access$showOrHideView(this.this$0, z, !z2);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ElementVisibleChangeListener
    public final void onFixedElementVisibleChanged(boolean z, boolean z2) {
        SeparateQSFakeStatusController.access$showOrHideFixedView(this.this$0, z, !z2);
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ElementVisibleChangeListener
    public final void onSecondElementVisibleChanged(boolean z, boolean z2) {
    }
}
