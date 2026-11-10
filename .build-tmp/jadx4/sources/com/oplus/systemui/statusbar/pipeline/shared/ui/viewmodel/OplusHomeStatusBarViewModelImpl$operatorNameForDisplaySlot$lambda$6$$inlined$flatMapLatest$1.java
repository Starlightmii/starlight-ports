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
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $slot$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusHomeStatusBarViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1(Continuation continuation, OplusHomeStatusBarViewModelImpl oplusHomeStatusBarViewModelImpl, int i) {
        super(3, continuation);
        this.this$0 = oplusHomeStatusBarViewModelImpl;
        this.$slot$inlined = i;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1 oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1 = new OplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$slot$inlined);
        oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusHomeStatusBarViewModelImpl$operatorNameForDisplaySlot$lambda$6$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        OplusHomeStatusBarViewModelImpl$displaySlotSettings$lambda$1$.inlined.map.1 r4;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Integer num = (Integer) obj2;
            if (num != null) {
                Flow carrierName = this.this$0.mobileIconsInteractor.getMobileConnectionInteractorForSubId(num.intValue()).getCarrierName();
                int i2 = this.$slot$inlined;
                r4 = new OplusHomeStatusBarViewModelImpl$displaySlotSettings$lambda$1$.inlined.map.1(1);
                r4.$this_unsafeTransform$inlined = carrierName;
                r4.$user$inlined = i2;
                r4.this$0 = num;
                VarHandle.storeStoreFence();
            } else {
                r4 = FlowKt.flowOf((Object) null);
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, r4, this) == coroutine_suspended) {
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
