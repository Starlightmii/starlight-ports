package com.oplus.systemui.plugins.qs.animator;

import com.android.systemui.util.JankUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelAnimatorManager$qsElementShowingChangeListener$1 implements QSPanelExpandFraction.ElementVisibleChangeListener {
    public /* synthetic */ QSPanelAnimatorManager this$0;

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ElementVisibleChangeListener
    public final void onFirstElementVisibleChanged(boolean z, boolean z2) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ElementVisibleChangeListener
    public final void onFixedElementVisibleChanged(boolean z, boolean z2) {
    }

    @Override // com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction.ElementVisibleChangeListener
    public final void onSecondElementVisibleChanged(boolean z, boolean z2) {
        QSPanelAnimatorManager qSPanelAnimatorManager = this.this$0;
        if (z && !z2) {
            JankUtils.gfxSceneBegin$default(qSPanelAnimatorManager.qsRootView.getContext(), 7, "QS PULL", 4L);
        }
        boolean z3 = qSPanelAnimatorManager.isLowGaussianLevel;
        if (!z3 || z) {
            boolean z4 = (z3 || z2) ? false : true;
            qSPanelAnimatorManager.setFirstElementVisible(z, z4);
            qSPanelAnimatorManager.updatePivotXY(false);
            qSPanelAnimatorManager.setSecondElementVisible(z, z4);
        }
    }
}
