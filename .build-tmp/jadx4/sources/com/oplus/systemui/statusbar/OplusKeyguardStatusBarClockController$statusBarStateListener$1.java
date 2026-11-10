package com.oplus.systemui.statusbar;

import android.util.Log;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStatusBarClockController$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusKeyguardStatusBarClockController this$0;

    public final void onStateChanged(int i) {
        super.onStateChanged(i);
        OplusKeyguardStatusBarClockController oplusKeyguardStatusBarClockController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onStateChanged ", oplusKeyguardStatusBarClockController.statusBarState, i, " -> ", "OplusKeyguardStatClockController-->"));
        }
        oplusKeyguardStatusBarClockController.statusBarState = i;
        if (i == 2) {
            OplusKeyguardStatusBarClockController.access$animateAppear(oplusKeyguardStatusBarClockController);
        } else {
            OplusKeyguardStatusBarClockController.access$animateDisappear(oplusKeyguardStatusBarClockController);
        }
    }
}
