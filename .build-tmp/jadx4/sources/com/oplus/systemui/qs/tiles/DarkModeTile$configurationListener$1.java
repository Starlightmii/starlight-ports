package com.oplus.systemui.qs.tiles;

import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplus.systemui.separate.OplusSeparateQSManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DarkModeTile$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public /* synthetic */ DarkModeTile this$0;

    public final void onUiModeChanged() {
        DarkModeTile darkModeTile = this.this$0;
        QSTileImpl.H h = ((QSTileImpl) darkModeTile).mHandler;
        boolean zEnableSeparateNotificationAndQS = ((OplusSeparateNotificationAndQSEx) darkModeTile.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS();
        boolean z = darkModeTile.keyguardRepository.isKeyguardShowing() && !((Boolean) darkModeTile.keyguardRepository.isKeyguardOccluded().getValue()).booleanValue();
        LogUtil.i$default(LogUtil.TAG_QS, ((QSTileImpl) darkModeTile).TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("onUiModeChanged: isSeparate-", "; isOnlyKeyguardShowing-", zEnableSeparateNotificationAndQS, z), null, 8);
        if (zEnableSeparateNotificationAndQS) {
            OplusSeparateQSManager.collapseQSPanel$default(darkModeTile.separateQSManager, false, true, false, null, 12);
        } else if (z) {
            darkModeTile.notifiPanelController.hideQsWithoutAnimateInKeyguard();
        } else {
            darkModeTile.notifiPanelController.collapseNotifPanelWithoutAnimate();
        }
        h.removeCallbacks(darkModeTile.refreshRunnable);
        h.postDelayed(darkModeTile.refreshRunnable, 200L);
    }
}
