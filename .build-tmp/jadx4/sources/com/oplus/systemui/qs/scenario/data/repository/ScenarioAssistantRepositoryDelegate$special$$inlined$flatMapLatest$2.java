package com.oplus.systemui.qs.scenario.data.repository;

import com.oplus.systemui.scenario.host.impl.data.repository.ScenarioAssistantRepositoryImpl;
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
public final class ScenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ ScenarioAssistantRepositoryImpl $impl$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2(ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl, Continuation continuation) {
        super(3, continuation);
        this.$impl$inlined = scenarioAssistantRepositoryImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ScenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2 scenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2 = new ScenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2(this.$impl$inlined, (Continuation) obj3);
        scenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        scenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return scenarioAssistantRepositoryDelegate$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow scenarioInfo = ((Boolean) obj2).booleanValue() ? this.$impl$inlined.getScenarioInfo() : FlowKt.flowOf((Object) null);
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, scenarioInfo, this) == coroutine_suspended) {
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
