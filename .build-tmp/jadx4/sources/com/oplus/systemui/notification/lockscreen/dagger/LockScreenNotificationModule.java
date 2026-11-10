package com.oplus.systemui.notification.lockscreen.dagger;

import android.content.Context;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.stack.NotificationListContainer;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleListContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.launch.CapsuleLaunchAnimatorControllerProvider;
import com.oplus.systemui.notification.lockscreen.launch.CommonLaunchAnimatorControllerProvider;
import com.oplus.systemui.notification.lockscreen.launch.SeedlingLaunchAnimatorControllerProvider;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSManager;
import com.oplus.systemui.notification.lockscreen.lightos.EmptyLightOSControllerImpl;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import dagger.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LockScreenNotificationModule {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final CapsuleLightOSController provideCapsuleLightOSManagerProvider(Context context, Lazy lazy, LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager, Lazy lazy2, Lazy lazy3) {
            return CapsuleConfig.INSTANCE.checkIsLightOS(context) ? new CapsuleLightOSManager(lazy, lockScreenNotificationExtStateManager, lazy2, lazy3) : new EmptyLightOSControllerImpl();
        }

        public final CapsuleLaunchAnimatorControllerProvider provideCapsuleTransitionAnimatorControllerProvider(CapsuleCardControl capsuleCardControl, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController) {
            return new CapsuleLaunchAnimatorControllerProvider(capsuleCardControl, oplusImmersiveViewManager, iLockScreenExtStateController);
        }

        public final CommonLaunchAnimatorControllerProvider provideCommonTransitionAnimatorControllerProvider(CapsuleLaunchAnimatorControllerProvider capsuleLaunchAnimatorControllerProvider, SeedlingLaunchAnimatorControllerProvider seedlingLaunchAnimatorControllerProvider) {
            return new CommonLaunchAnimatorControllerProvider(capsuleLaunchAnimatorControllerProvider, seedlingLaunchAnimatorControllerProvider);
        }

        public final SeedlingLaunchAnimatorControllerProvider provideSeedlingTransitionAnimatorControllerProvider(NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, NotificationListContainer notificationListContainer, InteractionJankMonitor interactionJankMonitor, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController) {
            return new SeedlingLaunchAnimatorControllerProvider(notificationLaunchAnimationInteractor, notificationListContainer, interactionJankMonitor, oplusImmersiveViewManager, iLockScreenExtStateController);
        }

        public final CapsuleListContainer providerCapsuleListContainer(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl) {
            return notificationCapsuleContainerControllerImpl.getCapsuleListContainer();
        }

        public final NotificationCapsuleContainer providerKeyguardNotificationCapsuleView(NotificationPanelView notificationPanelView) {
            return (NotificationCapsuleContainer) notificationPanelView.findViewById(2131364382);
        }
    }
}
