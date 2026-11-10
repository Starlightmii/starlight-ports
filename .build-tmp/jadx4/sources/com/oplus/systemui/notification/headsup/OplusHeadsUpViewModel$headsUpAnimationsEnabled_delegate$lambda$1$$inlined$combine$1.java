package com.oplus.systemui.notification.headsup;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1 implements Flow {
    public /* synthetic */ Flow[] $flows$inlined;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Function0 {
        public /* synthetic */ Flow[] $flows;

        public final Object invoke() {
            return new Boolean[this.$flows.length];
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.headsup.OplusHeadsUpViewModel$headsUpAnimationsEnabled_delegate$lambda$1$$inlined$combine$1$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(3, (Continuation) obj3);
            anonymousClass3.L$0 = (FlowCollector) obj;
            anonymousClass3.L$1 = (Object[]) obj2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object[] objArr = (Object[]) this.L$1;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Boolean[] boolArr = (Boolean[]) objArr;
                boolean z = false;
                boolean zBooleanValue = boolArr[0].booleanValue();
                boolean zBooleanValue2 = boolArr[1].booleanValue();
                if (!zBooleanValue && zBooleanValue2) {
                    z = true;
                }
                Boolean boolBoxBoolean = Boxing.boxBoolean(z);
                this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
                this.L$1 = SpillingKt.nullOutSpilledVariable(objArr);
                this.label = 1;
                if (flowCollector.emit(boolBoxBoolean, this) == coroutine_suspended) {
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

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow[] flowArr = this.$flows$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$flows = flowArr;
        VarHandle.storeStoreFence();
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, anonymousClass2, new AnonymousClass3(3, null), continuation);
        return objCombineInternal == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
    }
}
