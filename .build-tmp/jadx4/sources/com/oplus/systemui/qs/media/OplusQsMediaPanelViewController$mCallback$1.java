package com.oplus.systemui.qs.media;

import android.os.Handler;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaPanelViewController$mCallback$1 extends KeyguardUpdateMonitorCallback {
    public /* synthetic */ OplusQsMediaPanelViewController this$0;

    public final void onUserSwitchComplete(int i) {
        OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "onUserSwitchComplete userId is " + i);
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.this$0;
        ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewController).mView).setMediaOutputView();
        Handler handler = oplusQsMediaPanelViewController.bgHandler;
        OplusQsMediaPanelViewController.RunnableC02561 runnableC02561 = new OplusQsMediaPanelViewController.RunnableC02561(4);
        runnableC02561.this$0 = oplusQsMediaPanelViewController;
        VarHandle.storeStoreFence();
        handler.post(runnableC02561);
    }

    public final void onUserSwitching(int i) {
        OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "onUserSwitching userId is " + i);
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.this$0;
        OplusQsMediaPanelViewController.RunnableC02561 runnableC02561 = new OplusQsMediaPanelViewController.RunnableC02561(6);
        runnableC02561.this$0 = oplusQsMediaPanelViewController;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(runnableC02561);
    }
}
