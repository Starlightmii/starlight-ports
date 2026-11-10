package com.oplus.systemui.qs.res.domain.interactor;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StdQSResInteractor$special$$inlined$map$1 implements Flow {
    public /* synthetic */ SharedFlow $this_unsafeTransform$inlined;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.res.domain.interactor.StdQSResInteractor$special$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ FlowCollector $this_unsafeFlow;

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        public final Object emit(Object obj, Continuation continuation) {
            1 r0;
            if (continuation instanceof 1) {
                r0 = (1) continuation;
                int i = r0.label;
                if ((i & Integer.MIN_VALUE) != 0) {
                    r0.label = i - Integer.MIN_VALUE;
                } else {
                    r0 = new 1(this, continuation);
                }
            } else {
                r0 = new 1(this, continuation);
            }
            Object obj2 = r0.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = r0.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                FlowCollector flowCollector = this.$this_unsafeFlow;
                Unit unit = Unit.INSTANCE;
                r0.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                r0.L$1 = SpillingKt.nullOutSpilledVariable(r0);
                r0.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                r0.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                r0.I$0 = 0;
                r0.label = 1;
                if (flowCollector.emit(unit, r0) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        SharedFlow sharedFlow = this.$this_unsafeTransform$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        VarHandle.storeStoreFence();
        Object objCollect = sharedFlow.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
