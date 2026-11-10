package com.oplus.systemui.qs;

import android.util.Log;
import com.android.systemui.panelanimation.PanelAnimationEx;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerImplController$elementShowStateChangeListener$1 implements PanelAnimationEx.ElementShowingChangeListener {
    public /* synthetic */ OplusQSContainerImplController this$0;

    public final void onElementShowingChanged() {
        OplusQSContainerImplController oplusQSContainerImplController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSContainerImplController-->onElementShowingChanged");
        }
        if (oplusQSContainerImplController.mPanelAnimationEx.isElementShowing()) {
            oplusQSContainerImplController.updatePanelExpansion(oplusQSContainerImplController.mPanelExpandedFraction, true);
        }
    }
}
