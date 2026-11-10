package com.oplus.systemui.qs.scenario.domain.viewmodel;

import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class PrivacyScenarioCapsuleViewModelImpl$showScenario$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        PrivacyScenarioCapsuleViewModelImpl$showScenario$1 privacyScenarioCapsuleViewModelImpl$showScenario$1 = new PrivacyScenarioCapsuleViewModelImpl$showScenario$1(2, continuation);
        privacyScenarioCapsuleViewModelImpl$showScenario$1.L$0 = obj;
        return privacyScenarioCapsuleViewModelImpl$showScenario$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ScenarioInfo) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ScenarioInfo scenarioInfo = (ScenarioInfo) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(scenarioInfo != null);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
