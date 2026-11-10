package com.oplus.systemui.qs.pipeline.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class AirplaneModeConfirmRepository$airplaneModeConfirmEvent$1 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ AirplaneModeConfirmRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AirplaneModeConfirmRepository$airplaneModeConfirmEvent$1(AirplaneModeConfirmRepository airplaneModeConfirmRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = airplaneModeConfirmRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        AirplaneModeConfirmRepository$airplaneModeConfirmEvent$1 airplaneModeConfirmRepository$airplaneModeConfirmEvent$1 = new AirplaneModeConfirmRepository$airplaneModeConfirmEvent$1(this.this$0, continuation);
        airplaneModeConfirmRepository$airplaneModeConfirmEvent$1.Z$0 = ((Boolean) obj).booleanValue();
        return airplaneModeConfirmRepository$airplaneModeConfirmEvent$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((AirplaneModeConfirmRepository$airplaneModeConfirmEvent$1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return z ? FlowKt.merge(new Flow[]{this.this$0.broadcastConfirmEvent, this.this$0.extraConfirmEvent}) : FlowKt.emptyFlow();
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
