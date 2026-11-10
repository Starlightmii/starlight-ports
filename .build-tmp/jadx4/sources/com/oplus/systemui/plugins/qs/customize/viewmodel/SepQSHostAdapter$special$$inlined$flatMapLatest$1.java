package com.oplus.systemui.plugins.qs.customize.viewmodel;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SepQSHostAdapter$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ SepQSHostAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSHostAdapter$special$$inlined$flatMapLatest$1(SepQSHostAdapter sepQSHostAdapter, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepQSHostAdapter;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SepQSHostAdapter$special$$inlined$flatMapLatest$1 sepQSHostAdapter$special$$inlined$flatMapLatest$1 = new SepQSHostAdapter$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        sepQSHostAdapter$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        sepQSHostAdapter$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return sepQSHostAdapter$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00bf, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r5, r10, r9) == r2) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            java.lang.Object r1 = r9.L$1
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r9.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L2e
            if (r3 == r5) goto L20
            if (r3 != r4) goto L19
            kotlin.ResultKt.throwOnFailure(r10)
            goto Lc2
        L19:
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r9)
            r9 = 0
            return r9
        L20:
            int r3 = r9.I$0
            java.lang.Object r5 = r9.L$3
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            java.lang.Object r5 = r9.L$2
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            kotlin.ResultKt.throwOnFailure(r10)
            goto L87
        L2e:
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r1
            java.lang.Number r10 = (java.lang.Number) r10
            int r3 = r10.intValue()
            boolean r10 = com.oplusos.systemui.common.util.LogUtil.getNormalDebug()
            if (r10 == 0) goto L5a
            java.lang.String r10 = "QuickSettings"
            boolean r10 = com.oplusos.systemui.common.util.LogUtil.isTagValid(r10)
            if (r10 == 0) goto L5a
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r10 = r9.this$0
            int r10 = r10.currentUserId
            java.lang.String r6 = " new="
            java.lang.String r7 = "SepQSHostAdapter-->"
            java.lang.String r8 = "userChanged: old="
            java.lang.String r10 = com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m(r8, r10, r3, r6, r7)
            java.lang.String r6 = "SystemUi--QuickSettings"
            android.util.Log.d(r6, r10)
        L5a:
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r10 = r9.this$0
            r10.currentUserId = r3
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r10 = r9.this$0
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher r10 = com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter.access$getRepoSwitcherInteractor$p(r10)
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r9.L$0 = r6
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r9.L$1 = r6
            r9.L$2 = r0
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r9.L$3 = r6
            r9.I$0 = r3
            r6 = 0
            r9.I$1 = r6
            r9.label = r5
            java.lang.Object r10 = r10.setUserId(r3, r9)
            if (r10 != r2) goto L86
            goto Lc1
        L86:
            r5 = r0
        L87:
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r10 = r9.this$0
            com.oplus.systemui.plugins.qs.customize.viewmodel.interactor.TileConfigRepoSwitcher r10 = com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter.access$getRepoSwitcherInteractor$p(r10)
            kotlinx.coroutines.flow.Flow r10 = r10.getFlowWrapped()
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter r6 = r9.this$0
            com.android.systemui.plugins.qs.QSHostWrapper r6 = com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter.access$getHostWrapper$p(r6)
            kotlinx.coroutines.flow.Flow r6 = r6.getCurrentTiles()
            com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$currentTileAndUiConfig$1$2 r7 = new com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$currentTileAndUiConfig$1$2
            r8 = 0
            r7.<init>(r3, r8)
            kotlinx.coroutines.flow.Flow r10 = kotlinx.coroutines.flow.FlowKt.flowCombine(r10, r6, r7)
            kotlinx.coroutines.flow.Flow r10 = kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(r10)
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r9.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r9.L$1 = r0
            r9.L$2 = r8
            r9.L$3 = r8
            r9.label = r4
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.emitAll(r5, r10, r9)
            if (r9 != r2) goto Lc2
        Lc1:
            return r2
        Lc2:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.plugins.qs.customize.viewmodel.SepQSHostAdapter$special$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
