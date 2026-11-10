package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
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
public final class OplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ MobileIconInteractor $mobileIconInteractorImpl$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusMobileIconInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1(Continuation continuation, MobileIconInteractor mobileIconInteractor, OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl) {
        super(3, continuation);
        this.$mobileIconInteractorImpl$inlined = mobileIconInteractor;
        this.this$0 = oplusMobileIconInteractorImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1 oplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1 = new OplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$mobileIconInteractorImpl$inlined, this.this$0);
        oplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusMobileIconInteractorImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StateFlow signalLevelIcon = ((Boolean) obj2).booleanValue() ? this.$mobileIconInteractorImpl$inlined.getSignalLevelIcon() : this.this$0.oplusLevelIcon;
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, signalLevelIcon, this) == coroutine_suspended) {
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
