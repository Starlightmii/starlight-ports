package com.oplus.systemui.separate;

import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewPagerController$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusPanelViewPagerController this$0;

    public final void onStateChanged(int i) {
        OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
        super.onStateChanged(i);
        if (i == 0) {
            OplusPanelViewPagerController.access$resetStateForShade(oplusPanelViewPagerController);
        } else {
            if (i != 1) {
                return;
            }
            OplusPanelViewPagerController.access$resetStateForKg(oplusPanelViewPagerController);
        }
    }
}
