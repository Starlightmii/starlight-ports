package com.oplus.systemui.qs.media;

import android.view.View;
import com.android.systemui.util.JankUtils;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.util.QsFastClickUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import com.oplusos.systemui.qs.util.QsStatisticUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaPanelViewController$initClickListener$1 implements View.OnClickListener {
    public /* synthetic */ OplusQsMediaPanelViewController this$0;

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (QsFastClickUtil.isFastClick(QsFastClickUtil.TYPE_QS_SHOW_DIALOG)) {
            OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "bindMediaCarouselController  fastclick is return");
            return;
        }
        if (((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).isHistoryListEmpty()) {
            OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "bindMediaCarouselController  historyList is empty");
            return;
        }
        JankUtils.latencySceneBegin(this.this$0.getContext(), 335, "MUSIC_PANEL_L1", 500L);
        JankUtils.latencySceneBegin(this.this$0.getContext(), 436, "MUSIC_PANEL_L2", 1000L);
        this.this$0.qsMediaDialogController.showPrompt(this.this$0.getView());
        QsStatisticUtil.collectMediaPanelActionClick(this.this$0.getContext(), "1");
    }
}
