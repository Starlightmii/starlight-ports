package com.oplus.systemui.statusbar.pipeline.shared.ui.viewmodel;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusHomeStatusBarViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2(OplusHomeStatusBarViewModelImpl oplusHomeStatusBarViewModelImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusHomeStatusBarViewModelImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2 oplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2 = new OplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2(this.this$0, (Continuation) obj3);
        oplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        oplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return oplusHomeStatusBarViewModelImpl$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Flow flowTransformLatest;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int iIntValue = ((Number) obj2).intValue();
            if (iIntValue < 0) {
                flowTransformLatest = FlowKt.flowOf((Object) null);
            } else {
                StateFlow subscriptions = this.this$0.mobileConnectionsRepository.getSubscriptions();
                OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$map$1 oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$map$1 = new OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$map$1();
                oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$map$1.$this_unsafeTransform$inlined = subscriptions;
                oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$map$1.$slot$inlined = iIntValue;
                VarHandle.storeStoreFence();
                flowTransformLatest = FlowKt.transformLatest(oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$map$1, new OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1(null, this.this$0, iIntValue));
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowTransformLatest, this) == coroutine_suspended) {
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
