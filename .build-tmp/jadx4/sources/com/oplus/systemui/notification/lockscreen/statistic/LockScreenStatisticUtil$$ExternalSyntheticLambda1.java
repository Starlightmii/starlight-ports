package com.oplus.systemui.notification.lockscreen.statistic;

import android.content.Context;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import oplus.util.OplusStatistics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockScreenStatisticUtil$$ExternalSyntheticLambda1 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Context f$0;
    public /* synthetic */ Map f$1;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                OplusStatistics.onCommon(this.f$0, "20082", "20082003", "fluid_immerse_state", this.f$1, true);
                break;
            default:
                OplusStatistics.onCommon(this.f$0, "20082", "20082003", "capsule_left_right_slide", this.f$1, true);
                break;
        }
        return Unit.INSTANCE;
    }
}
