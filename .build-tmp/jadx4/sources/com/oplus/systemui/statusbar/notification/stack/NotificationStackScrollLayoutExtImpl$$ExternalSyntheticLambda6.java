package com.oplus.systemui.statusbar.notification.stack;

import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationStackScrollLayoutExtImpl f$0;

    public /* synthetic */ NotificationStackScrollLayoutExtImpl$$ExternalSyntheticLambda6(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        NotificationStackScrollLayoutExtImpl notificationStackScrollLayoutExtImpl = this.f$0;
        switch (i) {
            case 0:
                return NotificationStackScrollLayoutExtImpl.m461$r8$lambda$T3GaZm8IoTxmjcCJL_0g6bRqBM(notificationStackScrollLayoutExtImpl);
            default:
                return Boolean.valueOf(notificationStackScrollLayoutExtImpl.getPanelAnimationEx().isShadeShowing());
        }
    }
}
