package com.oplus.systemui.separate;

import android.content.Context;
import android.view.View;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanelViewPagerController$separateQSPluginListener$1 implements OplusSeparateQSManager.Listener {
    public /* synthetic */ OplusPanelViewPager $panelViewPager;
    public /* synthetic */ OplusPanelViewPagerController this$0;

    @Override // com.oplus.systemui.separate.OplusSeparateQSManager.Listener
    public final void onPluginAdded(OplusSeparateQSPlugin oplusSeparateQSPlugin, Context context) {
        OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
        oplusPanelViewPagerController.loadQSPanelView(((Boolean) oplusPanelViewPagerController.panelModeRepository.getEnableSeparateNotificationAndQS().getValue()).booleanValue());
    }

    @Override // com.oplus.systemui.separate.OplusSeparateQSManager.Listener
    public final void onPluginRemove(OplusSeparateQSPlugin oplusSeparateQSPlugin) {
        OplusPanelViewPagerController oplusPanelViewPagerController = this.this$0;
        View view = oplusPanelViewPagerController.qsPanelView;
        if (view != null) {
            OplusPanelViewPager oplusPanelViewPager = this.$panelViewPager;
            OplusSeparateQSManager.collapseQSPanel$default(oplusPanelViewPagerController.separateQSManager, false, true, false, null, 12);
            oplusPanelViewPager.removeView(view);
        }
    }
}
