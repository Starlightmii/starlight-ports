package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileConfigRepoSwitcher$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ TileConfigRepoSwitcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileConfigRepoSwitcher$special$$inlined$flatMapLatest$1(TileConfigRepoSwitcher tileConfigRepoSwitcher, Continuation continuation) {
        super(3, continuation);
        this.this$0 = tileConfigRepoSwitcher;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TileConfigRepoSwitcher$special$$inlined$flatMapLatest$1 tileConfigRepoSwitcher$special$$inlined$flatMapLatest$1 = new TileConfigRepoSwitcher$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        tileConfigRepoSwitcher$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        tileConfigRepoSwitcher$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return tileConfigRepoSwitcher$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0182  */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01f3, code lost:
    
        if (r13 == r2) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0219, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r3, r13, r12) == r2) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher$special$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
