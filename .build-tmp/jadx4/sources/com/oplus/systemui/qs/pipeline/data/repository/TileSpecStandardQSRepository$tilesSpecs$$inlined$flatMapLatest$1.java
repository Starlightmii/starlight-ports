package com.oplus.systemui.qs.pipeline.data.repository;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $userId$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1(Continuation continuation, int i) {
        super(3, continuation);
        this.$userId$inlined = i;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1 tileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1 = new TileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1((Continuation) obj3, this.$userId$inlined);
        tileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        tileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1.L$1 = obj2;
        return tileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0079, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r3, (kotlinx.coroutines.flow.Flow) r8, r7) == r2) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            java.lang.Object r1 = r7.L$1
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r7.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L2f
            if (r3 == r5) goto L1f
            if (r3 != r4) goto L18
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7c
        L18:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            r7 = 0
            return r7
        L1f:
            java.lang.Object r3 = r7.L$4
            com.android.systemui.qs.pipeline.data.repository.TileSpecRepository r3 = (com.android.systemui.qs.pipeline.data.repository.TileSpecRepository) r3
            java.lang.Object r3 = r7.L$3
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            java.lang.Object r3 = r7.L$2
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5e
        L2f:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r1
            com.android.systemui.qs.pipeline.data.repository.TileSpecRepository r8 = (com.android.systemui.qs.pipeline.data.repository.TileSpecRepository) r8
            int r3 = r7.$userId$inlined
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r7.L$1 = r6
            r7.L$2 = r0
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r7.L$3 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r7.L$4 = r6
            r6 = 0
            r7.I$0 = r6
            r7.label = r5
            java.lang.Object r8 = r8.tilesSpecs(r3, r7)
            if (r8 != r2) goto L5d
            goto L7b
        L5d:
            r3 = r0
        L5e:
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r7.L$1 = r0
            r0 = 0
            r7.L$2 = r0
            r7.L$3 = r0
            r7.L$4 = r0
            r7.label = r4
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.emitAll(r3, r8, r7)
            if (r7 != r2) goto L7c
        L7b:
            return r2
        L7c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.repository.TileSpecStandardQSRepository$tilesSpecs$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
