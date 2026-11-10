package com.oplus.systemui.statusbar.notification.row;

import android.content.Context;
import com.oplus.systemui.notification.base.radius.SmoothRoundExKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationBackgroundViewExtImp$$ExternalSyntheticLambda2 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ NotificationBackgroundViewExtImp$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                return NotificationBackgroundViewExtImp.m450$r8$lambda$BlTDWmetZMMJD5VgreLhtr3q4((NotificationBackgroundViewExtImp) obj);
            default:
                NotificationBackgroundViewExtImp.Companion companion = NotificationBackgroundViewExtImp.Companion;
                return SmoothRoundExKt.getSmoothDrawable((Context) obj, 2131102886);
        }
    }
}
