package com.oplus.systemui.statusbar.pipeline.battery.domain.interactor;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ForceShowStyleInteractor$filteredForceStyle$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ForceShowStyleInteractor$filteredForceStyle$2 forceShowStyleInteractor$filteredForceStyle$2 = new ForceShowStyleInteractor$filteredForceStyle$2(3, (Continuation) obj3);
        forceShowStyleInteractor$filteredForceStyle$2.L$0 = (Pair) obj;
        forceShowStyleInteractor$filteredForceStyle$2.L$1 = (Pair) obj2;
        return forceShowStyleInteractor$filteredForceStyle$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        Pair pair2 = (Pair) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        int iIntValue = ((Number) pair.component1()).intValue();
        int iIntValue2 = ((Number) pair2.component1()).intValue();
        boolean zBooleanValue = ((Boolean) pair2.component2()).booleanValue();
        if (!zBooleanValue) {
            return Boxing.boxInt(iIntValue2);
        }
        if (zBooleanValue && iIntValue == 2) {
            return Boxing.boxInt(0);
        }
        return null;
    }
}
