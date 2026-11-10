package com.oplus.systemui.qs.scenario.domain.viewmodel;

import com.oplus.systemui.scenario.host.api.data.model.ScenarioInfo;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PrivacyScenarioCapsuleViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2(PrivacyScenarioCapsuleViewModelImpl privacyScenarioCapsuleViewModelImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = privacyScenarioCapsuleViewModelImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2 privacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2 = new PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2(this.this$0, continuation);
        privacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2.L$0 = obj;
        return privacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ScenarioInfo) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String scenarioName;
        ScenarioInfo scenarioInfo = (ScenarioInfo) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (scenarioInfo != null && (scenarioName = scenarioInfo.getScenarioName()) != null) {
            this.this$0._scenarioName.setValue(scenarioName);
        }
        return Unit.INSTANCE;
    }
}
