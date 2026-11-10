package com.oplus.systemui.plugins.qs.customize.data.repo;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $userId$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ SepTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1(int i, SepTileConfigRepository sepTileConfigRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepTileConfigRepository;
        this.$userId$inlined = i;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepTileConfigRepository sepTileConfigRepository = this.this$0;
        SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1 sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1 = new SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1(this.$userId$inlined, sepTileConfigRepository, (Continuation) obj3);
        sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1.L$1 = obj2;
        return sepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x011c, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r3, r11, r10) == r2) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository$innerTileSpecRepository$1$tilesSpecs$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
