package com.oplus.systemui.notification.icdf;

import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class IcdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ IcdfSynergyHeadsUpDecider f$0;

    public /* synthetic */ IcdfSynergyHeadsUpDecider$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        IcdfSynergyHeadsUpDecider icdfSynergyHeadsUpDecider = this.f$0;
        switch (i) {
            case 0:
                return IcdfSynergyHeadsUpDecider.m62$r8$lambda$Qeq3xf7_L5EfrwAYClKo3L9gGA(icdfSynergyHeadsUpDecider);
            default:
                return ((NotifPipeline) icdfSynergyHeadsUpDecider.notifPipeline.get()).getAllNotifs();
        }
    }
}
