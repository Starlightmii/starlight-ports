package com.oplus.systemui.panelanimation;

import androidx.appcompat.widget.ListPopupWindow$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelAnimationExImpl$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusPanelAnimationExImpl this$0;

    public final void onStateChanged(int i) {
        super.onStateChanged(i);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "PanelAnimationEx", ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onStateChanged newState "), null, 8);
        OplusPanelAnimationExImpl oplusPanelAnimationExImpl = this.this$0;
        oplusPanelAnimationExImpl.updateElementShowing(oplusPanelAnimationExImpl.getPanelExpandFraction().getRawFraction(), 0.0f);
    }
}
