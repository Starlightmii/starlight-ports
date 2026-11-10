package com.oplus.systemui.qs;

import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shade.ShadeExpansionListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQuickStatusBarHeaderController$mShadeExpansionListener$1 implements ShadeExpansionListener {
    public /* synthetic */ OplusQuickStatusBarHeaderController this$0;

    public final void onPanelExpansionChanged(ShadeExpansionChangeEvent shadeExpansionChangeEvent) {
        OplusQuickStatusBarHeaderController oplusQuickStatusBarHeaderController = this.this$0;
        oplusQuickStatusBarHeaderController.mPanelTracking = shadeExpansionChangeEvent.getTracking();
        oplusQuickStatusBarHeaderController.mPanelFraction = shadeExpansionChangeEvent.getFraction();
        oplusQuickStatusBarHeaderController.mPanelExpanded = shadeExpansionChangeEvent.getExpanded();
        oplusQuickStatusBarHeaderController.updateCarrierMarqueeByPanelState();
    }
}
