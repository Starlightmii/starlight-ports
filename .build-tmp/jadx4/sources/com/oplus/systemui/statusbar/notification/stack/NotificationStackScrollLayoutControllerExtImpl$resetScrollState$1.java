package com.oplus.systemui.statusbar.notification.stack;

import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutControllerExtImpl$resetScrollState$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationStackScrollLayoutControllerExtImpl this$0;

    public /* synthetic */ NotificationStackScrollLayoutControllerExtImpl$resetScrollState$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                if (this.this$0.isScrolling) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "NotificationStackScrollLayoutControllerExtImpl", "scroll timeout, reset isScrolling false", null, 8);
                    this.this$0.isScrolling = false;
                }
                break;
            default:
                NotificationStackScrollLayoutControllerExtImpl notificationStackScrollLayoutControllerExtImpl = this.this$0;
                if (!notificationStackScrollLayoutControllerExtImpl.isScrolling) {
                    notificationStackScrollLayoutControllerExtImpl.getStrategy().stopWaitAction(5);
                }
                break;
        }
    }
}
