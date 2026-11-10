package com.oplus.systemui.notification.lockscreen.statistic;

import android.content.Context;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import oplus.util.OplusStatistics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class LockScreenStatisticUtil$$ExternalSyntheticLambda7 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ LockScreenStatisticUtil$$ExternalSyntheticLambda7(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                return LockScreenStatisticUtil.m92$r8$lambda$j0ltI_ey8gQv96ODBziHOZGQ3A((String) obj);
            case 1:
                ((Function0) obj).invoke();
                return Unit.INSTANCE;
            default:
                Context context = (Context) obj;
                LockScreenStatisticUtil lockScreenStatisticUtil = LockScreenStatisticUtil.INSTANCE;
                context.getClass();
                Map sinkCommonMap = lockScreenStatisticUtil.getSinkCommonMap(context);
                sinkCommonMap.put("type", "4");
                OplusStatistics.onCommon(context, "20082", "20082003", "capsule_expose", sinkCommonMap, true);
                return Unit.INSTANCE;
        }
    }
}
