package com.oplus.systemui.statusbar.pipeline.ui.viewmodel;

import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2 oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2 = new OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2(3, (Continuation) obj3);
        oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        StateFlow stateFlowFlowOf;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            OplusMobileIconViewModelCommon oplusMobileIconViewModelCommon = (OplusMobileIconViewModelCommon) CollectionsKt___CollectionsKt.firstOrNull((List) obj2);
            if (oplusMobileIconViewModelCommon == null || (stateFlowFlowOf = oplusMobileIconViewModelCommon.getActivityVisible()) == null) {
                stateFlowFlowOf = FlowKt.flowOf(Boxing.boxBoolean(false));
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, stateFlowFlowOf, this) == coroutine_suspended) {
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
