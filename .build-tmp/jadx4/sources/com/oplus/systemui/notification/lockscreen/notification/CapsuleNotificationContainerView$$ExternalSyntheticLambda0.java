package com.oplus.systemui.notification.lockscreen.notification;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class CapsuleNotificationContainerView$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ CapsuleNotificationContainerView f$0;
    public /* synthetic */ CapsuleNotificationDataController.CapsuleNotificationInnerData f$1;
    public /* synthetic */ boolean f$2;

    public final Object invoke() {
        CapsuleNotificationContainerView capsuleNotificationContainerView = this.f$0;
        CapsuleNotificationDataController.CapsuleNotificationInnerData capsuleNotificationInnerData = this.f$1;
        boolean z = this.f$2;
        CapsuleNotificationContainerView.Companion companion = CapsuleNotificationContainerView.Companion;
        capsuleNotificationContainerView.showCapsule(capsuleNotificationInnerData, z);
        return Unit.INSTANCE;
    }
}
