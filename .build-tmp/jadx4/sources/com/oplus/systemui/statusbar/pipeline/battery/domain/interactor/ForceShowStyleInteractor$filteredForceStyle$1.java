package com.oplus.systemui.statusbar.pipeline.battery.domain.interactor;

import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ForceShowStyleInteractor$filteredForceStyle$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        ForceShowStyleInteractor$filteredForceStyle$1 forceShowStyleInteractor$filteredForceStyle$1 = new ForceShowStyleInteractor$filteredForceStyle$1(3, (Continuation) obj3);
        forceShowStyleInteractor$filteredForceStyle$1.I$0 = iIntValue;
        forceShowStyleInteractor$filteredForceStyle$1.Z$0 = zBooleanValue;
        return forceShowStyleInteractor$filteredForceStyle$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return TuplesKt.to(Boxing.boxInt(i), Boxing.boxBoolean(z));
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
