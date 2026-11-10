package com.oplus.systemui.notification.interruption.wakeup;

import com.android.systemui.InitController;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProvider;
import com.oplus.systemui.notification.log.OplusNotificationInfoCollection;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import dagger.Lazy;
import dagger.MembersInjector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class WakeupScreenHelper_MembersInjector implements MembersInjector {
    public static void injectMDependOnKeyguard(WakeupScreenHelper wakeupScreenHelper, NotificationDependOnKeyguard notificationDependOnKeyguard) {
        wakeupScreenHelper.mDependOnKeyguard = notificationDependOnKeyguard;
    }

    public static void injectMInitController(WakeupScreenHelper wakeupScreenHelper, InitController initController) {
        wakeupScreenHelper.mInitController = initController;
    }

    public static void injectMKeyguardNotificationVisibilityProvider(WakeupScreenHelper wakeupScreenHelper, KeyguardNotificationVisibilityProvider keyguardNotificationVisibilityProvider) {
        wakeupScreenHelper.mKeyguardNotificationVisibilityProvider = keyguardNotificationVisibilityProvider;
    }

    public static void injectMNotifPipeline(WakeupScreenHelper wakeupScreenHelper, Lazy lazy) {
        wakeupScreenHelper.mNotifPipeline = lazy;
    }

    public static void injectMOplusInfoCollection(WakeupScreenHelper wakeupScreenHelper, OplusNotificationInfoCollection oplusNotificationInfoCollection) {
        wakeupScreenHelper.mOplusInfoCollection = oplusNotificationInfoCollection;
    }
}
