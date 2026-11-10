package com.oplus.systemui.qs.scenario.domain.viewmodel;

import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class PrivacyScenarioCapsuleViewModelImpl$scenarioLightAction$1 extends SuspendLambda implements Function5 {
    /* synthetic */ int I$0;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ PrivacyScenarioCapsuleViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrivacyScenarioCapsuleViewModelImpl$scenarioLightAction$1(PrivacyScenarioCapsuleViewModelImpl privacyScenarioCapsuleViewModelImpl, Continuation continuation) {
        super(5, continuation);
        this.this$0 = privacyScenarioCapsuleViewModelImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int iIntValue = ((Number) obj4).intValue();
        PrivacyScenarioCapsuleViewModelImpl$scenarioLightAction$1 privacyScenarioCapsuleViewModelImpl$scenarioLightAction$1 = new PrivacyScenarioCapsuleViewModelImpl$scenarioLightAction$1(this.this$0, (Continuation) obj5);
        privacyScenarioCapsuleViewModelImpl$scenarioLightAction$1.Z$0 = zBooleanValue;
        privacyScenarioCapsuleViewModelImpl$scenarioLightAction$1.L$0 = (String) obj2;
        privacyScenarioCapsuleViewModelImpl$scenarioLightAction$1.L$1 = (ScenarioInfo) obj3;
        privacyScenarioCapsuleViewModelImpl$scenarioLightAction$1.I$0 = iIntValue;
        return privacyScenarioCapsuleViewModelImpl$scenarioLightAction$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        String str = (String) this.L$0;
        ScenarioInfo scenarioInfo = (ScenarioInfo) this.L$1;
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return PrivacyScenarioCapsuleViewModelImpl.access$buildScenarioLightAction(this.this$0, z, str, scenarioInfo, i);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
