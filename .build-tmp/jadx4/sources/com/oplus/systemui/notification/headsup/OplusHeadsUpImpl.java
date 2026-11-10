package com.oplus.systemui.notification.headsup;

import android.view.ViewGroup;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.shade.ShadeHeadsUpTracker;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.HeadsUpTouchHelper;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.oplus.systemui.notification.NotificationExtFlag;
import com.oplus.systemui.notification.headsup.nssl.OplusNotificationListContainerWraper;
import com.oplus.systemui.notification.headsup.paneview.OplusPaneViewHeadsUpManager;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayoutController;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpImpl extends OplusHeadsUp {
    private final HeadsUpContainerWindow control;
    private final OplusHeadsUpCoreStartable headsUpCoreStartable;
    private final HeadsUpLayoutController headsUpLayoutController;
    private final OplusNotificationListContainerWraper oplusNotificationListContainer;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpImpl$wrapOnFinishAnimationCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Runnable $origin;
        public /* synthetic */ OplusHeadsUpImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Runnable runnable = this.$origin;
            if (runnable != null) {
                runnable.run();
            }
            this.this$0.headsUpCoreStartable.onFinishAnimationCallback();
        }
    }

    public OplusHeadsUpImpl(OplusNotificationListContainerWraper oplusNotificationListContainerWraper, OplusHeadsUpCoreStartable oplusHeadsUpCoreStartable, HeadsUpLayoutController headsUpLayoutController, HeadsUpContainerWindow headsUpContainerWindow) {
        this.oplusNotificationListContainer = oplusNotificationListContainerWraper;
        this.headsUpCoreStartable = oplusHeadsUpCoreStartable;
        this.headsUpLayoutController = headsUpLayoutController;
        this.control = headsUpContainerWindow;
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public HeadsUpTouchHelper createHeadsUpTouchHelper(HeadsUpManager headsUpManager, IStatusBarService iStatusBarService, HeadsUpTouchHelper.Callback callback, HeadsUpTouchHelper.HeadsUpNotificationViewController headsUpNotificationViewController) {
        if (!NotificationExtFlag.OPLUS_HEADSUP) {
            return new HeadsUpTouchHelper(headsUpManager, iStatusBarService, callback, headsUpNotificationViewController);
        }
        this.headsUpCoreStartable.initHeadsUpHelper(headsUpManager, iStatusBarService, callback, headsUpNotificationViewController);
        return new OplusEmptyHeadsUpTouchHelper(headsUpManager, iStatusBarService, callback, headsUpNotificationViewController);
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public NotificationListContainer createNsslNotificationListContainer(NotificationListContainer notificationListContainer) {
        if (!NotificationExtFlag.OPLUS_HEADSUP || (notificationListContainer instanceof OplusNotificationListContainerWraper)) {
            return notificationListContainer;
        }
        this.oplusNotificationListContainer.setOrgin(notificationListContainer);
        return this.oplusNotificationListContainer;
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public HeadsUpManager getPaneViewHeadsUpManager(HeadsUpManager headsUpManager) {
        return NotificationExtFlag.OPLUS_HEADSUP ? new OplusPaneViewHeadsUpManager(headsUpManager) : headsUpManager;
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public ShadeHeadsUpTracker getShadeHeadsUpTracker(ShadeHeadsUpTracker shadeHeadsUpTracker) {
        return this.headsUpLayoutController;
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public ViewGroup getWindowRootView() {
        return this.control.getRootView();
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public void reuseSwipeHelper(NotificationSwipeHelper.Builder builder, NotificationSwipeHelper.NotificationCallback notificationCallback, NotificationMenuRowPlugin.OnMenuEventListener onMenuEventListener) {
        this.headsUpCoreStartable.reuseSwipeHelper(builder, notificationCallback, onMenuEventListener);
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public final void updateSensitiveness(boolean z, boolean z2) {
        this.headsUpLayoutController.updateSensitiveness();
    }

    @Override // com.oplus.systemui.notification.headsup.OplusHeadsUp
    public Runnable wrapOnFinishAnimationCallback(Runnable runnable) {
        if (!NotificationExtFlag.OPLUS_HEADSUP) {
            return runnable;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$origin = runnable;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        return anonymousClass1;
    }
}
