package com.oplus.systemui.qs.pipeline.data.event;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Ref.BooleanRef $isAddVoWifi$inlined;
    final /* synthetic */ Ref.BooleanRef $isAddVoWifiOne$inlined;
    final /* synthetic */ Ref.BooleanRef $isAddVoWifiTwo$inlined;
    final /* synthetic */ int $userId$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    final /* synthetic */ VoWifiTileAutoAddEvent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1(Continuation continuation, VoWifiTileAutoAddEvent voWifiTileAutoAddEvent, int i, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, Ref.BooleanRef booleanRef3) {
        super(3, continuation);
        this.this$0 = voWifiTileAutoAddEvent;
        this.$userId$inlined = i;
        this.$isAddVoWifi$inlined = booleanRef;
        this.$isAddVoWifiTwo$inlined = booleanRef2;
        this.$isAddVoWifiOne$inlined = booleanRef3;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1 voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$userId$inlined, this.$isAddVoWifi$inlined, this.$isAddVoWifiTwo$inlined, this.$isAddVoWifiOne$inlined);
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1.L$1 = obj2;
        return voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0097, code lost:
    
        if (kotlinx.coroutines.flow.FlowKt.emitAll(r5, r9, r8) == r2) goto L19;
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
            r6 = 0
            if (r3 == 0) goto L2f
            if (r3 == r5) goto L21
            if (r3 != r4) goto L1a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L9a
        L1a:
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
            r8 = 0
            return r8
        L21:
            boolean r3 = r8.Z$0
            java.lang.Object r5 = r8.L$3
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            java.lang.Object r5 = r8.L$2
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            kotlin.ResultKt.throwOnFailure(r9)
            goto L61
        L2f:
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r1
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r3 = r9.booleanValue()
            com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent r9 = r8.this$0
            com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepository r9 = com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent.access$getInstalledTilesComponents$p(r9)
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r8.L$0 = r7
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.L$1 = r7
            r8.L$2 = r0
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r8.L$3 = r7
            r8.Z$0 = r3
            r8.I$0 = r6
            r8.label = r5
            java.lang.Object r9 = r9.reloadInstalledTilesComponent(r8)
            if (r9 != r2) goto L60
            goto L99
        L60:
            r5 = r0
        L61:
            if (r3 == 0) goto L70
            com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent r9 = r8.this$0
            com.android.systemui.qs.pipeline.data.repository.InstalledTilesComponentRepository r9 = com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent.access$getInstalledTilesComponents$p(r9)
            int r3 = r8.$userId$inlined
            kotlinx.coroutines.flow.Flow r9 = r9.getInstalledTilesComponents(r3)
            goto L80
        L70:
            kotlin.jvm.internal.Ref$BooleanRef r9 = r8.$isAddVoWifi$inlined
            r9.element = r6
            kotlin.jvm.internal.Ref$BooleanRef r9 = r8.$isAddVoWifiTwo$inlined
            r9.element = r6
            kotlin.jvm.internal.Ref$BooleanRef r9 = r8.$isAddVoWifiOne$inlined
            r9.element = r6
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt.emptyFlow()
        L80:
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r8.L$0 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r8.L$1 = r0
            r0 = 0
            r8.L$2 = r0
            r8.L$3 = r0
            r8.label = r4
            java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.emitAll(r5, r9, r8)
            if (r8 != r2) goto L9a
        L99:
            return r2
        L9a:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
