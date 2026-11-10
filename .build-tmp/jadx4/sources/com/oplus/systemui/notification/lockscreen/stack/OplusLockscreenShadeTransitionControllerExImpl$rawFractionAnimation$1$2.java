package com.oplus.systemui.notification.lockscreen.stack;

import com.android.systemui.keyguard.IKeyguardTipsViewController;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLockscreenShadeTransitionControllerExImpl$rawFractionAnimation$1$2 implements COUIDynamicAnimation.OnAnimationEndListener {
    public /* synthetic */ OplusLockscreenShadeTransitionControllerExImpl this$0;

    public final void onAnimationEnd(COUIDynamicAnimation cOUIDynamicAnimation, boolean z, float f, float f2) {
        NotificationStackScrollLayout view;
        NotificationListContainer notificationListContainer;
        OplusLockscreenShadeTransitionControllerExImpl oplusLockscreenShadeTransitionControllerExImpl = this.this$0;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "OplusLockscreenShadeTransitionControllerExImpl", "fractionAnimation onAnimationEnd, value=" + f + ", currentState=" + oplusLockscreenShadeTransitionControllerExImpl.getCurrentState(), null, 8);
        boolean z2 = oplusLockscreenShadeTransitionControllerExImpl.getCurrentState() == 2 && f == 0.0f;
        oplusLockscreenShadeTransitionControllerExImpl.setNotificationDragAmount(0.0f);
        oplusLockscreenShadeTransitionControllerExImpl.setRawFraction(f);
        if (oplusLockscreenShadeTransitionControllerExImpl.getCurrentState() == 1 && f == 1.0f) {
            oplusLockscreenShadeTransitionControllerExImpl.statusBarStateController.setState(2);
        } else if (oplusLockscreenShadeTransitionControllerExImpl.getCurrentState() == 2 && f == 0.0f) {
            oplusLockscreenShadeTransitionControllerExImpl.statusBarStateController.setState(1);
        }
        ((IKeyguardTipsViewController) oplusLockscreenShadeTransitionControllerExImpl.oplusKeyguardTipsViewController.get()).onStatusBarStateAnimtionStart(f == 1.0f);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = oplusLockscreenShadeTransitionControllerExImpl.nsslController;
        NotificationSwipeActionHelper swipeActionHelper = (notificationStackScrollLayoutController == null || (notificationListContainer = notificationStackScrollLayoutController.getNotificationListContainer()) == null) ? null : notificationListContainer.getSwipeActionHelper();
        NotificationSwipeHelper notificationSwipeHelper = swipeActionHelper instanceof NotificationSwipeHelper ? (NotificationSwipeHelper) swipeActionHelper : null;
        if (notificationSwipeHelper != null) {
            notificationSwipeHelper.resetExposedMenuView(false, true);
        }
        if (z2) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = oplusLockscreenShadeTransitionControllerExImpl.nsslController;
            if (notificationStackScrollLayoutController2 != null && (view = notificationStackScrollLayoutController2.getView()) != null) {
                view.requestChildrenUpdate$1();
            }
        } else {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController3 = oplusLockscreenShadeTransitionControllerExImpl.nsslController;
            if (notificationStackScrollLayoutController3 != null) {
                NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController3.mView;
                if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled) {
                    notificationStackScrollLayout.mEverythingNeedsAnimation = true;
                    notificationStackScrollLayout.mNeedsAnimation = true;
                    notificationStackScrollLayout.requestChildrenUpdate$1();
                }
            }
        }
        NotificationPanelViewController notificationPanelViewController = oplusLockscreenShadeTransitionControllerExImpl.notificationPanelViewController;
        if (notificationPanelViewController != null) {
            notificationPanelViewController.requestScrollerTopPaddingUpdate();
        }
        OplusLockscreenShadeTransitionControllerExImpl.access$runAnimationFinishedRunnables(oplusLockscreenShadeTransitionControllerExImpl);
    }
}
