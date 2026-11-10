package com.oplus.systemui.shade;

import com.android.systemui.keyguard.OplusKeyguardBottomAreaControllerEx;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$keyguardBottomAreaHeightListener$1 implements OplusKeyguardBottomAreaControllerEx.BottomAreaHeightListener {
    public /* synthetic */ NotificationPanelViewControllerExImp this$0;

    public final void onKeyguardBottomAreaHeightChange() {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationPanelViewControllerExImp", "onKeyguardBottomAreaHeightChange", null, 8);
        ThreadManager companion = ThreadManager.Companion.getInstance();
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        NotificationPanelViewControllerExImp$onTrackingStarted$2$1 notificationPanelViewControllerExImp$onTrackingStarted$2$1 = new NotificationPanelViewControllerExImp$onTrackingStarted$2$1(2);
        notificationPanelViewControllerExImp$onTrackingStarted$2$1.$this_run = notificationPanelViewControllerExImp;
        VarHandle.storeStoreFence();
        companion.postInMainThread(notificationPanelViewControllerExImp$onTrackingStarted$2$1);
    }
}
