package com.oplus.systemui.statusbar;

import android.util.Log;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusKeyguardStatusBarColorController$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusKeyguardStatusBarColorController this$0;

    public final void onStateChanged(int i) {
        super.onStateChanged(i);
        OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onStateChanged ", oplusKeyguardStatusBarColorController.statusBarState, i, " -> ", "OplusKeyguardStatusBarColorController-->"));
        }
        OplusKeyguardStatusBarColorController oplusKeyguardStatusBarColorController2 = this.this$0;
        int i2 = oplusKeyguardStatusBarColorController2.statusBarState;
        if (i != i2) {
            long j = (i == 2 && i2 == 1) ? 100L : 0L;
            oplusKeyguardStatusBarColorController2.statusBarState = i;
            oplusKeyguardStatusBarColorController2.usingLightBgInShadeLock = i == 2 && KeyguardUtils.Companion.getKeyguardUtils(oplusKeyguardStatusBarColorController2.context).isUseReverseColor;
            this.this$0.updateColor(j, "onStateChanged");
        }
    }
}
