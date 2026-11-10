package com.oplus.systemui.qs.res.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSPersonalityRepository$isSupportStrokeCorner$1$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        QSPersonalityRepository$isSupportStrokeCorner$1$1 qSPersonalityRepository$isSupportStrokeCorner$1$1 = new QSPersonalityRepository$isSupportStrokeCorner$1$1(2, continuation);
        qSPersonalityRepository$isSupportStrokeCorner$1$1.L$0 = obj;
        return qSPersonalityRepository$isSupportStrokeCorner$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((FlowCollector) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Unit unit = Unit.INSTANCE;
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.label = 1;
            if (flowCollector.emit(unit, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
