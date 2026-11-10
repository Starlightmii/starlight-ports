package com.oplus.systemui.statusbar;

import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.statusbar.PulseExpansionHandlerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExt;
import com.oplus.systemui.separate.OplusPanelViewPagerController;
import com.oplus.systemui.statusbar.notification.helper.PanelPullHelper;
import com.oplus.systemui.statusbar.notification.stack.NotificationStackScrollLayoutExtImpl;
import dagger.Lazy;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PulseExpansionHandlerExtImpl extends PulseExpansionHandlerExt {
    private final Lazy panelViewPagerController;
    private final PanelPullHelper pullHelper;
    private final Lazy separateNotificationAndQSEx;

    public PulseExpansionHandlerExtImpl(PanelPullHelper panelPullHelper, Lazy lazy, Lazy lazy2) {
        this.pullHelper = panelPullHelper;
        this.panelViewPagerController = lazy;
        this.separateNotificationAndQSEx = lazy2;
    }

    public boolean goToLockedShade(NotificationStackScrollLayoutController notificationStackScrollLayoutController, Function0 function0) {
        NotificationStackScrollLayoutExt notificationStackScrollLayoutExt = notificationStackScrollLayoutController.getView().mExt;
        notificationStackScrollLayoutExt.updateHasVisibleKeyguardElement(notificationStackScrollLayoutController.getView());
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = (NotificationStackScrollLayoutExtImpl) notificationStackScrollLayoutExt;
        if (!notificationStackScrollLayoutExtImpl.hasVisibleUbiquitousRow && !notificationStackScrollLayoutExtImpl.hasMediaPanelOnKg && !notificationStackScrollLayoutExtImpl.hasVisibleNotificationRow) {
            return false;
        }
        function0.invoke();
        return true;
    }

    public final boolean isDisableDragSbWhenInKg() {
        if (((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS()) {
            return !((OplusPanelViewPagerController) this.panelViewPagerController.get()).isDownNotificationArea() || this.pullHelper.getDisablePullStatusBar(Boolean.TRUE);
        }
        return this.pullHelper.getDisablePullStatusBar(Boolean.FALSE);
    }
}
