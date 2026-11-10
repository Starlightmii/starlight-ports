package com.oplus.systemui.shade;

import android.view.ViewGroup;
import com.android.systemui.shade.NotificationPanelViewController;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleClockControllerImpl;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$onTrackingStarted$2$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object $this_run;

    public /* synthetic */ NotificationPanelViewControllerExImp$onTrackingStarted$2$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewGroup view;
        switch (this.$r8$classId) {
            case 0:
                ((ViewGroup) this.$this_run).postOnAnimation(1.INSTANCE);
                break;
            case 1:
                ((NotificationPanelViewControllerExImp) this.$this_run).updateLockscreenNotificationBottomPadding();
                NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = (NotificationPanelViewControllerExImp) this.$this_run;
                notificationPanelViewControllerExImp.onNotificationExpectedHeightChanged(((KeyguardStyleClockControllerImpl) notificationPanelViewControllerExImp.keyguardStyleClockController.get()).getLatestNotificationHeight());
                ((NotificationPanelViewControllerExImp) this.$this_run).notificationStackScrollLayoutController.getView().mAnimateNextTopPaddingChange = true;
                break;
            case 2:
                ((NotificationPanelViewControllerExImp) this.$this_run).updateLockscreenNotificationBottomPadding();
                break;
            case 3:
                NotificationPanelViewController base = ((NotificationPanelViewControllerExImp) this.$this_run).getBase();
                if (base != null) {
                    base.updateExpandedHeightToMaxHeight();
                }
                break;
            default:
                NotificationPanelViewController base2 = ((NotificationPanelViewControllerExImp) this.$this_run).getBase();
                if (base2 != null && (view = base2.getView()) != null) {
                    view.requestLayout();
                    break;
                }
                break;
        }
    }
}
