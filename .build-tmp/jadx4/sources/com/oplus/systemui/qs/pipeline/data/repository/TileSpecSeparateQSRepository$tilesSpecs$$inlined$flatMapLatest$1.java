package com.oplus.systemui.qs.pipeline.data.repository;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $userId$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ TileSpecSeparateQSRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1(Continuation continuation, TileSpecSeparateQSRepository tileSpecSeparateQSRepository, int i) {
        super(3, continuation);
        this.this$0 = tileSpecSeparateQSRepository;
        this.$userId$inlined = i;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1 tileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1 = new TileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$userId$inlined);
        tileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        tileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1.L$1 = obj2;
        return tileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x007b, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r3, (kotlinx.coroutines.flow.Flow) r9, r8) == r2) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            java.lang.Object r1 = r8.L$1
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r8.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L2f
            if (r3 == r5) goto L1f
            if (r3 != r4) goto L18
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7e
        L18:
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
            r8 = 0
            return r8
        L1f:
            java.lang.Object r3 = r8.L$4
            com.android.systemui.plugins.qs.SepTileSpecRepository r3 = (com.android.systemui.plugins.qs.SepTileSpecRepository) r3
            java.lang.Object r3 = r8.L$3
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            java.lang.Object r3 = r8.L$2
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r9)
            goto L60
        L2f:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r1
            com.android.systemui.plugins.qs.SepTileSpecRepository r9 = (com.android.systemui.plugins.qs.SepTileSpecRepository) r9
            com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository r3 = r8.this$0
            int r6 = r8.$userId$inlined
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r8.L$0 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.L$1 = r7
            r8.L$2 = r0
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r8.L$3 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r8.L$4 = r7
            r7 = 0
            r8.I$0 = r7
            r8.label = r5
            java.lang.Object r9 = com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository.access$processSepTileSpecsFlow(r3, r6, r9, r8)
            if (r9 != r2) goto L5f
            goto L7d
        L5f:
            r3 = r0
        L60:
            kotlinx.coroutines.flow.Flow r9 = (kotlinx.coroutines.flow.Flow) r9
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r8.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.L$1 = r0
            r0 = 0
            r8.L$2 = r0
            r8.L$3 = r0
            r8.L$4 = r0
            r8.label = r4
            java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.emitAll(r3, r9, r8)
            if (r8 != r2) goto L7e
        L7d:
            return r2
        L7e:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.repository.TileSpecSeparateQSRepository$tilesSpecs$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
