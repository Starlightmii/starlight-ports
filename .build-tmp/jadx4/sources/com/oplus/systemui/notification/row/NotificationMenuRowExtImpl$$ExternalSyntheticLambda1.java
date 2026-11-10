package com.oplus.systemui.notification.row;

import android.content.Context;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationMenuRowExtImpl$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ NotificationMenuRowExtImpl$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                return NotificationMenuRowExtImpl.$r8$lambda$lNWbyGxQqUgPPvcuHCPEElu3Y3I((NotificationMenuRowExtImpl) obj);
            case 1:
                return NotificationMenuRowExtImpl.$r8$lambda$w8L7FI5m8wA1KbM6MJKmx_RIQ6I((NotificationMenuRowExtImpl) obj);
            default:
                return NotificationMenuRowItemResourceHelperKt.getDeleteItemLottieRes((Context) obj, MaterialColorMode.MATERIAL);
        }
    }
}
