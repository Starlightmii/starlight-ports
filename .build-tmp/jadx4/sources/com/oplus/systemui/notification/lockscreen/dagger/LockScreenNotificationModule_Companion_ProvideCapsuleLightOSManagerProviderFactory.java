package com.oplus.systemui.notification.lockscreen.dagger;

import android.content.Context;
import com.oplus.systemui.notification.lockscreen.lightos.CapsuleLightOSController;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import dagger.Lazy;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LockScreenNotificationModule_Companion_ProvideCapsuleLightOSManagerProviderFactory implements Factory {
    public static CapsuleLightOSController provideCapsuleLightOSManagerProvider(Context context, Lazy lazy, LockScreenNotificationExtStateManager lockScreenNotificationExtStateManager, Lazy lazy2, Lazy lazy3) {
        CapsuleLightOSController capsuleLightOSControllerProvideCapsuleLightOSManagerProvider = LockScreenNotificationModule.Companion.provideCapsuleLightOSManagerProvider(context, lazy, lockScreenNotificationExtStateManager, lazy2, lazy3);
        capsuleLightOSControllerProvideCapsuleLightOSManagerProvider.getClass();
        return capsuleLightOSControllerProvideCapsuleLightOSManagerProvider;
    }
}
