package com.oplus.systemui.statusbar.notification.customcard.popupmenu;

import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomPopupMenuControllerImpl$sysStateControllerListener$1 implements SysUiStateController.ISysUiStateListener {
    public /* synthetic */ OplusCustomPopupMenuControllerImpl this$0;

    public final void onPanelExpandedChanged(boolean z, View view) {
        if (z) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusCustomPopupMenuController", KeyguardLogger$.ExternalSyntheticOutline0.m("onQSExpandedChanged: isExpanded=", z), null, 8);
            OplusCustomPopupMenuController.dismissPopupMenu$default(this.this$0, "QSExpanded", false, 4);
        }
    }
}
