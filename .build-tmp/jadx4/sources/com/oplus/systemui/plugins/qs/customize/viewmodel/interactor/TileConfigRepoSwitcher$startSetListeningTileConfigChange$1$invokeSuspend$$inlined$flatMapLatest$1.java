package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ TileConfigRepoSwitcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1(TileConfigRepoSwitcher tileConfigRepoSwitcher, Continuation continuation) {
        super(3, continuation);
        this.this$0 = tileConfigRepoSwitcher;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1 tileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1 = new TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        tileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        tileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1.L$1 = obj2;
        return tileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x007b, code lost:
    
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
            if (r3 == 0) goto L2b
            if (r3 == r5) goto L1f
            if (r3 != r4) goto L18
            kotlin.ResultKt.throwOnFailure(r8)
            goto L7e
        L18:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            r7 = 0
            return r7
        L1f:
            java.lang.Object r3 = r7.L$3
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            java.lang.Object r3 = r7.L$2
            kotlinx.coroutines.flow.FlowCollector r3 = (kotlinx.coroutines.flow.FlowCollector) r3
            kotlin.ResultKt.throwOnFailure(r8)
            goto L62
        L2b:
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r1
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher r3 = r7.this$0
            com.oplus.systemui.plugins.qs.customize.data.repo.SepTileConfigRepository r3 = com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher.access$getTileConfigRepository$p(r3)
            com.oplus.systemui.plugins.qs.customize.data.repo.SepUserTileConfigRepository r3 = r3.ensureUserTileConfigRepository(r8)
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r7.L$1 = r6
            r7.L$2 = r0
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r7.L$3 = r6
            r7.I$0 = r8
            r8 = 0
            r7.I$1 = r8
            r7.label = r5
            java.lang.Object r8 = r3.tileConfigs(r7)
            if (r8 != r2) goto L61
            goto L7d
        L61:
            r3 = r0
        L62:
            kotlinx.coroutines.flow.Flow r8 = (kotlinx.coroutines.flow.Flow) r8
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r7.L$1 = r0
            r0 = 0
            r7.L$2 = r0
            r7.L$3 = r0
            r7.label = r4
            java.lang.Object r7 = kotlinx.coroutines.flow.FlowKt.emitAll(r3, r8, r7)
            if (r7 != r2) goto L7e
        L7d:
            return r2
        L7e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher$startSetListeningTileConfigChange$1$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
