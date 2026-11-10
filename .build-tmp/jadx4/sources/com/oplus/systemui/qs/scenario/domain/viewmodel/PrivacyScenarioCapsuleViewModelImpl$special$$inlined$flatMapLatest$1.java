package com.oplus.systemui.qs.scenario.domain.viewmodel;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ PrivacyScenarioCapsuleViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1(PrivacyScenarioCapsuleViewModelImpl privacyScenarioCapsuleViewModelImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = privacyScenarioCapsuleViewModelImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1 privacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1 = new PrivacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        privacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        privacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return privacyScenarioCapsuleViewModelImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            boolean zBooleanValue = ((Boolean) obj2).booleanValue();
            StateFlow privacyElementList = this.this$0.privacyInteractor.getPrivacyElementList();
            PrivacyScenarioCapsuleViewModelImpl$privacyElementList$lambda$1$.inlined.filter.1 r5 = new PrivacyScenarioCapsuleViewModelImpl$privacyElementList$lambda$1$.inlined.filter.1(0);
            r5.$this_unsafeTransform$inlined = privacyElementList;
            r5.$listening$inlined = zBooleanValue;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, r5, this) == coroutine_suspended) {
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
