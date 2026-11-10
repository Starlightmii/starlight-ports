package com.oplus.systemui.separate;

import com.android.systemui.panelanimation.PanelAnimationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSSimpleHeader$panelCollapseListener$1 implements PanelAnimationEx.ExpandedFractionChangeListener {
    public /* synthetic */ OplusQSSimpleHeader this$0;

    public final void onFractionChanged(float f) {
        if (f == 0.0f) {
            this.this$0.setStatusBarAnimateChangeDisabled(false);
        }
    }

    public final void onFractionEnd(float f) {
        if (f == 0.0f) {
            this.this$0.setStatusBarAnimateChangeDisabled(false);
        }
    }
}
