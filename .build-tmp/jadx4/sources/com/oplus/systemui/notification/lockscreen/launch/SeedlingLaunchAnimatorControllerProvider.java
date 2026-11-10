package com.oplus.systemui.notification.lockscreen.launch;

import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingLaunchAnimatorControllerProvider {
    private final OplusImmersiveViewManager immersiveViewManager;
    private final InteractionJankMonitor jankMonitor;
    private final ILockScreenExtStateController lockScreenExtStateController;
    private final NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor;
    private final NotificationListContainer notificationListContainer;

    public SeedlingLaunchAnimatorControllerProvider(NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, NotificationListContainer notificationListContainer, InteractionJankMonitor interactionJankMonitor, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController) {
        this.notificationLaunchAnimationInteractor = notificationLaunchAnimationInteractor;
        this.notificationListContainer = notificationListContainer;
        this.jankMonitor = interactionJankMonitor;
        this.immersiveViewManager = oplusImmersiveViewManager;
        this.lockScreenExtStateController = iLockScreenExtStateController;
    }

    public final ActivityTransitionAnimator.Controller getAnimatorController(OplusCustomRow oplusCustomRow, Runnable runnable) {
        return new SeedlingTransitionAnimatorController(this.notificationLaunchAnimationInteractor, this.notificationListContainer, oplusCustomRow, this.jankMonitor, runnable, this.immersiveViewManager, this.lockScreenExtStateController);
    }

    public final ActivityTransitionAnimator.Controller getAnimatorController(OplusCustomRow oplusCustomRow) {
        return getAnimatorController(oplusCustomRow, null);
    }
}
