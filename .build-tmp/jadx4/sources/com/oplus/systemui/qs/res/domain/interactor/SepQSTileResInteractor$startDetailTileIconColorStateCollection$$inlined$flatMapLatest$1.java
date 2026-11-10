package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.SepQSResPool;
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
public final class SepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ SepQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1 sepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1 = new SepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        sepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        sepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1.L$1 = obj2;
        return sepQSTileResInteractor$startDetailTileIconColorStateCollection$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Flow flowM246getTileIconColorState;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((Boolean) obj2).booleanValue()) {
                SepQSTileResInteractor sepQSTileResInteractor = this.this$0;
                flowM246getTileIconColorState = FlowKt.mapLatest(sepQSTileResInteractor.themeChangeFlow, new SepQSTileResInteractor.startDetailTileIconColorStateCollection.getDetailTileIconThemeColorState.1(sepQSTileResInteractor, (Continuation) null));
            } else {
                flowM246getTileIconColorState = SepQSResPool.INSTANCE.m246getTileIconColorState();
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowM246getTileIconColorState, this) == coroutine_suspended) {
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
