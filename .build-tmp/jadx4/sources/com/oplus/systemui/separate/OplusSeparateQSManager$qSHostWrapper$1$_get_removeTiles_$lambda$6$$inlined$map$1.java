package com.oplus.systemui.separate;

import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import com.oplus.systemui.qs.pipeline.data.repository.RemovedTilesEvent;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1 implements Flow {
    public /* synthetic */ OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1 $this_unsafeTransform$inlined;
    public /* synthetic */ OplusSeparateQSManager this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ OplusSeparateQSManager this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.separate.OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0013  */
        public final Object emit(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                int i = anonymousClass1.label;
                if ((i & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label = i - Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
            Object obj2 = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = anonymousClass1.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj2);
                FlowCollector flowCollector = this.$this_unsafeFlow;
                RemovedTilesEvent removedTilesEvent = (RemovedTilesEvent) obj;
                Iterable iterable = (Iterable) this.this$0.currentTilesInteractor.getCurrentTiles().getValue();
                int iMapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
                if (iMapCapacity < 16) {
                    iMapCapacity = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iMapCapacity);
                for (Object obj3 : iterable) {
                    linkedHashMap.put(((TileModel) obj3).getTile().getTileSpec(), obj3);
                }
                List<String> tileSpecs = removedTilesEvent.getTileSpecs();
                ArrayList arrayList = new ArrayList();
                Iterator<T> it = tileSpecs.iterator();
                while (it.hasNext()) {
                    TileModel tileModel = (TileModel) linkedHashMap.get((String) it.next());
                    QSTile tile = tileModel != null ? tileModel.getTile() : null;
                    if (tile != null) {
                        arrayList.add(tile);
                    }
                }
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flowCollector.emit(arrayList, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        OplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1 oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1 = this.$this_unsafeTransform$inlined;
        OplusSeparateQSManager oplusSeparateQSManager = this.this$0;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        anonymousClass2.this$0 = oplusSeparateQSManager;
        VarHandle.storeStoreFence();
        Object objCollect = oplusSeparateQSManager$qSHostWrapper$1$_get_removeTiles_$lambda$6$$inlined$filter$1.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
