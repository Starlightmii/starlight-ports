package com.oplus.systemui.plugins.qs.customize.data.repo;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ StateFlow $realTileConfigs$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ SepTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1(Continuation continuation, SepTileConfigRepository sepTileConfigRepository, StateFlow stateFlow) {
        super(3, continuation);
        this.this$0 = sepTileConfigRepository;
        this.$realTileConfigs$inlined = stateFlow;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1 sepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1 = new SepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$realTileConfigs$inlined);
        sepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        sepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1.L$1 = obj2;
        return sepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00dc, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r3, r10, r9) == r2) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository$tileConfig$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
