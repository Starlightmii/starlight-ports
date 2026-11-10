package com.oplus.systemui.notification.lockscreen.dagger;

import com.android.systemui.shade.NotificationPanelView;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainer;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LockScreenNotificationModule_Companion_ProviderKeyguardNotificationCapsuleViewFactory implements Factory {
    public static NotificationCapsuleContainer providerKeyguardNotificationCapsuleView(NotificationPanelView notificationPanelView) {
        NotificationCapsuleContainer notificationCapsuleContainerProviderKeyguardNotificationCapsuleView = LockScreenNotificationModule.Companion.providerKeyguardNotificationCapsuleView(notificationPanelView);
        notificationCapsuleContainerProviderKeyguardNotificationCapsuleView.getClass();
        return notificationCapsuleContainerProviderKeyguardNotificationCapsuleView;
    }
}
