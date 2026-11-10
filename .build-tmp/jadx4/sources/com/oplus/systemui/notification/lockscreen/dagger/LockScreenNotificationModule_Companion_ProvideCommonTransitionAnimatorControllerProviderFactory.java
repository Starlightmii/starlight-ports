package com.oplus.systemui.notification.lockscreen.dagger;

import com.oplus.systemui.notification.lockscreen.launch.CapsuleLaunchAnimatorControllerProvider;
import com.oplus.systemui.notification.lockscreen.launch.CommonLaunchAnimatorControllerProvider;
import com.oplus.systemui.notification.lockscreen.launch.SeedlingLaunchAnimatorControllerProvider;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LockScreenNotificationModule_Companion_ProvideCommonTransitionAnimatorControllerProviderFactory implements Factory {
    public static CommonLaunchAnimatorControllerProvider provideCommonTransitionAnimatorControllerProvider(CapsuleLaunchAnimatorControllerProvider capsuleLaunchAnimatorControllerProvider, SeedlingLaunchAnimatorControllerProvider seedlingLaunchAnimatorControllerProvider) {
        CommonLaunchAnimatorControllerProvider commonLaunchAnimatorControllerProviderProvideCommonTransitionAnimatorControllerProvider = LockScreenNotificationModule.Companion.provideCommonTransitionAnimatorControllerProvider(capsuleLaunchAnimatorControllerProvider, seedlingLaunchAnimatorControllerProvider);
        commonLaunchAnimatorControllerProviderProvideCommonTransitionAnimatorControllerProvider.getClass();
        return commonLaunchAnimatorControllerProviderProvideCommonTransitionAnimatorControllerProvider;
    }
}
