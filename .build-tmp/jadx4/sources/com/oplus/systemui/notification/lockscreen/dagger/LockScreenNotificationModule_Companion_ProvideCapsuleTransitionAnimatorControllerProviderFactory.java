package com.oplus.systemui.notification.lockscreen.dagger;

import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleCardControl;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.launch.CapsuleLaunchAnimatorControllerProvider;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LockScreenNotificationModule_Companion_ProvideCapsuleTransitionAnimatorControllerProviderFactory implements Factory {
    public static CapsuleLaunchAnimatorControllerProvider provideCapsuleTransitionAnimatorControllerProvider(CapsuleCardControl capsuleCardControl, OplusImmersiveViewManager oplusImmersiveViewManager, ILockScreenExtStateController iLockScreenExtStateController) {
        CapsuleLaunchAnimatorControllerProvider capsuleLaunchAnimatorControllerProviderProvideCapsuleTransitionAnimatorControllerProvider = LockScreenNotificationModule.Companion.provideCapsuleTransitionAnimatorControllerProvider(capsuleCardControl, oplusImmersiveViewManager, iLockScreenExtStateController);
        capsuleLaunchAnimatorControllerProviderProvideCapsuleTransitionAnimatorControllerProvider.getClass();
        return capsuleLaunchAnimatorControllerProviderProvideCapsuleTransitionAnimatorControllerProvider;
    }
}
