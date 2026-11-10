package com.oplus.systemui.plugins.qs.quickentrance;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSQuickEntranceComponent$$ExternalSyntheticLambda1 implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ OplusQSQuickEntranceComponent$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke(Object obj, Object obj2) {
        int i = this.$r8$classId;
        Object obj3 = this.f$0;
        switch (i) {
            case 0:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                OplusQSQuickEntranceComponent.Companion companion = OplusQSQuickEntranceComponent.Companion;
                Boolean bool2 = (Boolean) ((Function1) obj3).invoke(bool);
                bool2.booleanValue();
                return bool2;
            default:
                float fFloatValue = ((Float) obj).floatValue();
                ((Float) obj2).getClass();
                return OplusQSQuickEntranceComponent.m230$r8$lambda$OJ9G9bvcfFjibdhKSq6GhuWOrI((OplusQSQuickEntranceComponent) obj3, fFloatValue);
        }
    }
}
