package com.oplus.systemui.notification.aonaccess;

import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.oplus.systemui.notification.aon.AonAuthManager;
import com.oplus.systemui.notification.data.NotificationListenerDelayImpl;
import com.oplus.systemui.notification.interruption.fullscreenbanner.FullScreenBannerHelper;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AonAuthStarter$closeRunnable$1 implements Runnable {
    public /* synthetic */ AonAuthStarter this$0;

    @Override // java.lang.Runnable
    public final void run() {
        if (((NotificationListenerDelayImpl) this.this$0.notificationListenerDelayImpl.get()).hasAnyWaitingForAonResult() || !((Boolean) this.this$0.getHeadsUpCoordinatorIsEmpty().invoke()).booleanValue() || ((HeadsUpManager) this.this$0.headsUpManager.get()).hasNotifications() || ((HeadsUpManager) this.this$0.headsUpManager.get()).hasPinnedHeadsUp() || ((FullScreenBannerHelper) this.this$0.fullScreenBannerHelper.get()).mIsBannerShowing) {
            return;
        }
        ((AonAuthManager) this.this$0.aonAuthManager.get()).closeCamera(false);
    }
}
