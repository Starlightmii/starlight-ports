package com.oplus.systemui.scenario.host.impl.data.repository;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ScenarioAssistantRepositoryImpl$scenarioInfo$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ScenarioAssistantRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScenarioAssistantRepositoryImpl$scenarioInfo$1(ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scenarioAssistantRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        ScenarioAssistantRepositoryImpl$scenarioInfo$1 scenarioAssistantRepositoryImpl$scenarioInfo$1 = new ScenarioAssistantRepositoryImpl$scenarioInfo$1(this.this$0, continuation);
        scenarioAssistantRepositoryImpl$scenarioInfo$1.L$0 = obj;
        return scenarioAssistantRepositoryImpl$scenarioInfo$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            observer.1 r9 = new observer.1(producerScope);
            ScenarioAssistantRepositoryImpl.access$registerScenarioAssistantObserver(this.this$0, r9);
            Object obj2 = producerScope.trySend-JP2dKIU(Unit.INSTANCE);
            boolean z = obj2 instanceof ChannelResult.Failed;
            if (z) {
                ChannelResult.exceptionOrNull-impl(obj2);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->scenarioAssistant: register observer but trySend failure");
                }
            }
            if (!z) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "ScenarioAssistantRepositoryImpl-->scenarioAssistant: register observer and trySend success");
                }
            }
            ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl = this.this$0;
            ScenarioAssistantRepositoryImpl$scenarioInfo$1$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new ScenarioAssistantRepositoryImpl$scenarioInfo$1$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = scenarioAssistantRepositoryImpl;
            externalSyntheticLambda0.f$1 = r9;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(r9);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, externalSyntheticLambda0, this) == coroutine_suspended) {
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
