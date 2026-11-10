package com.oplus.systemui.statusbar.notification.helper;

import dagger.Lazy;
import dagger.MembersInjector;
import java.util.Optional;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationKeyguardHelper_MembersInjector implements MembersInjector {
    public static void injectMAfterSaleModeAlertHelper(NotificationKeyguardHelper notificationKeyguardHelper, Lazy lazy) {
        notificationKeyguardHelper.mAfterSaleModeAlertHelper = lazy;
    }

    public static void injectMBubblesOptional(NotificationKeyguardHelper notificationKeyguardHelper, Optional optional) {
        notificationKeyguardHelper.mBubblesOptional = optional;
    }

    public static void injectMDndAlertHelper(NotificationKeyguardHelper notificationKeyguardHelper, Lazy lazy) {
        notificationKeyguardHelper.mDndAlertHelper = lazy;
    }

    public static void injectMEnvelopeHelper(NotificationKeyguardHelper notificationKeyguardHelper, Lazy lazy) {
        notificationKeyguardHelper.mEnvelopeHelper = lazy;
    }

    public static void injectMNotifPipeline(NotificationKeyguardHelper notificationKeyguardHelper, Lazy lazy) {
        notificationKeyguardHelper.mNotifPipeline = lazy;
    }

    public static void injectMNotificationCenterHelper(NotificationKeyguardHelper notificationKeyguardHelper, Lazy lazy) {
        notificationKeyguardHelper.mNotificationCenterHelper = lazy;
    }
}
