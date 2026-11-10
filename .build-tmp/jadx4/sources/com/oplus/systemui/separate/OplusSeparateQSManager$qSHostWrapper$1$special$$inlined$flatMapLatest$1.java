package com.oplus.systemui.separate;

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
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusSeparateQSManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1(Continuation continuation, OplusSeparateQSManager oplusSeparateQSManager) {
        super(3, continuation);
        this.this$0 = oplusSeparateQSManager;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1 oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1 = new OplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusSeparateQSManager$qSHostWrapper$1$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int iIntValue = ((Number) obj2).intValue();
            SharedFlow removeTilesFlow = this.this$0.sepTileSpecRepository.getRemoveTilesFlow();
            OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1 oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1 = new OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1();
            oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1.$this_unsafeTransform$inlined = removeTilesFlow;
            oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1.$userId$inlined = iIntValue;
            VarHandle.storeStoreFence();
            OplusSeparateQSManager oplusSeparateQSManager = this.this$0;
            OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1 oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1 = new OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1();
            oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1.$this_unsafeTransform$inlined = oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1;
            oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1.this$0 = oplusSeparateQSManager;
            VarHandle.storeStoreFence();
            Flow flowOnStart = FlowKt.onStart(oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1, new OplusSeparateQSManager$qSHostWrapper$1$removeTiles$1$3(2, null));
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowOnStart, this) == coroutine_suspended) {
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
