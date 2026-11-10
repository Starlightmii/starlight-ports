package com.oplus.systemui.qs.media;

import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsBaseMediaPanelView$refreshActionButtonRunnable$1 implements Runnable {
    public /* synthetic */ OplusQsBaseMediaPanelView this$0;

    @Override // java.lang.Runnable
    public final void run() {
        OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = this.this$0;
        boolean zIsMediaDataExpected = oplusQsBaseMediaPanelView.isMediaDataExpected(oplusQsBaseMediaPanelView.pendingRefreshMediaData);
        OplusNotificationLogger.logMediaPanel("OplusQsBaseMediaPanelView", "refreshActionButtonRunnable: expectPlayState = " + this.this$0.expectPlayState + ", isMediaDataExpected = " + zIsMediaDataExpected);
        OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView2 = this.this$0;
        if (oplusQsBaseMediaPanelView2.expectPlayState == 0 || zIsMediaDataExpected) {
            oplusQsBaseMediaPanelView2.refreshActionButton();
            return;
        }
        oplusQsBaseMediaPanelView2.handler.postDelayed(this.this$0.loadingRunnable, 1000L);
        this.this$0.handler.postDelayed(this.this$0.stopLoadingRunnable, 3000L);
        OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView3 = this.this$0;
        oplusQsBaseMediaPanelView3.expectPlayState = 0;
        oplusQsBaseMediaPanelView3.handler.removeCallbacks(this);
        this.this$0.handler.postDelayed(this, 1000L);
    }
}
