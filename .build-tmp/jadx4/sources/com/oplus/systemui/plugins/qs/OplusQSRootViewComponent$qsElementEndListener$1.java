package com.oplus.systemui.plugins.qs;

import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.plugins.qs.animator.QSPanelExpandFraction;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSRootViewComponent$qsElementEndListener$1 implements QSPanelExpandFraction.ElementEndListener {
    public /* synthetic */ OplusQSRootViewComponent this$0;

    public final void elementAnimatorEnd() {
        OplusQSRootViewComponent oplusQSRootViewComponent = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusQSRootViewComponent.Companion companion = OplusQSRootViewComponent.Companion;
            float translationX = ((OplusQSRootView) oplusQSRootViewComponent.mView).getTranslationX();
            boolean z = oplusQSRootViewComponent.isExpanded;
            float rawFraction = oplusQSRootViewComponent.getQsPanelAnimatorManager().getRawFraction();
            StringBuilder sb = new StringBuilder("elementAnimatorEnd translationX=");
            sb.append(translationX);
            sb.append(" isExpanded=");
            sb.append(z);
            sb.append(" rawFraction=");
            VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, rawFraction, "OplusQSRootViewComponent-->", "SystemUi--QuickSettings");
        }
        OplusQSRootViewComponent.Companion companion2 = OplusQSRootViewComponent.Companion;
        oplusQSRootViewComponent.tryStopGfx();
    }
}
