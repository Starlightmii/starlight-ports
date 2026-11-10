package com.oplus.systemui.statusbar.pipeline.ui.viewmodel;

import androidx.compose.runtime.SnapshotStateKt;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModelCommon;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusStackedMobileIconViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1(OplusStackedMobileIconViewModelImpl oplusStackedMobileIconViewModelImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusStackedMobileIconViewModelImpl;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1 oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1 = new OplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusStackedMobileIconViewModelImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Flow flowCombine;
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            List list = (List) obj2;
            if (list.isEmpty()) {
                flowCombine = FlowKt.flowOf(Boxing.boxBoolean(false));
            } else {
                List list2 = list;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(((OplusMobileIconViewModelCommon) it.next()).isVisible());
                }
                Flow[] flowArr = (Flow[]) CollectionsKt___CollectionsKt.toList(arrayList).toArray(new Flow[0]);
                OplusStackedMobileIconViewModelImpl$_isIconVisible$lambda$3$.inlined.combine.1 r3 = new OplusStackedMobileIconViewModelImpl$_isIconVisible$lambda$3$.inlined.combine.1(0);
                r3.$flowArray$inlined = flowArr;
                VarHandle.storeStoreFence();
                OplusStackedMobileIconViewModelImpl oplusStackedMobileIconViewModelImpl = this.this$0;
                OplusStackedMobileIconViewModelImpl._isIconVisible.1.3 r9 = new OplusStackedMobileIconViewModelImpl._isIconVisible.1.3(0);
                r9.this$0 = oplusStackedMobileIconViewModelImpl;
                VarHandle.storeStoreFence();
                flowCombine = FlowKt.combine(r3, SnapshotStateKt.snapshotFlow(r9), new OplusStackedMobileIconViewModelImpl._isIconVisible.1.4(3, (Continuation) null));
            }
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowCombine, this) == coroutine_suspended) {
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
