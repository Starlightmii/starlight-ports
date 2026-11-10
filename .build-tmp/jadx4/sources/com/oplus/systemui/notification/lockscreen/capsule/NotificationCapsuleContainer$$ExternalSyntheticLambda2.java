package com.oplus.systemui.notification.lockscreen.capsule;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationCapsuleContainer$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationCapsuleContainer f$0;

    public /* synthetic */ NotificationCapsuleContainer$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        NotificationCapsuleContainer notificationCapsuleContainer = this.f$0;
        switch (i) {
            case 0:
                return NotificationCapsuleContainer.m75$r8$lambda$45_gOOb31NfaY9oZEaBxIAZ3b8(notificationCapsuleContainer);
            case 1:
                NotificationCapsuleContainer.Companion companion = NotificationCapsuleContainer.Companion;
                return notificationCapsuleContainer.getAnimatorHelper().getChildCountFlow();
            default:
                NotificationCapsuleContainer.Companion companion2 = NotificationCapsuleContainer.Companion;
                return notificationCapsuleContainer.getAnimatorHelper().getPreChildCountFlow();
        }
    }
}
