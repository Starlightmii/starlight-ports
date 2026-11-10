package com.oplus.systemui.qs.res.data.repository;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSPersonalityRepository$couiSpotLightEnabled$1 extends SuspendLambda implements Function2 {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ QSPersonalityRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSPersonalityRepository$couiSpotLightEnabled$1(QSPersonalityRepository qSPersonalityRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = qSPersonalityRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        QSPersonalityRepository$couiSpotLightEnabled$1 qSPersonalityRepository$couiSpotLightEnabled$1 = new QSPersonalityRepository$couiSpotLightEnabled$1(this.this$0, continuation);
        qSPersonalityRepository$couiSpotLightEnabled$1.L$0 = obj;
        return qSPersonalityRepository$couiSpotLightEnabled$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x006a, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r7, r6) == r1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007e, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r7, r6) == r1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0080, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.L$0
            kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r6.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L22
            if (r2 == r4) goto L1a
            if (r2 != r3) goto L13
            goto L1e
        L13:
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r6)
            r6 = 0
            return r6
        L1a:
            java.lang.Object r6 = r6.L$1
            com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$listener$1 r6 = (com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$listener$1) r6
        L1e:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L81
        L22:
            kotlin.ResultKt.throwOnFailure(r7)
            com.coui.appcompat.COUIMaterialStateManager r7 = com.coui.appcompat.COUIMaterialStateManager.INSTANCE
            r7.getClass()
            boolean r2 = com.coui.appcompat.COUIMaterialStateManager.materialVersionCheck()
            if (r2 == 0) goto L6d
            com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository r2 = r6.this$0
            android.content.Context r2 = com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository.access$getContext$p(r2)
            r3 = 7
            r7.registerMaterialStateObserver(r2, r3)
            com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$listener$1 r2 = new com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$listener$1
            r2.<init>()
            r2.$$this$conflatedCallbackFlow = r0
            java.lang.invoke.VarHandle.storeStoreFence()
            r7.addOnMaterialStateChangedListener(r2)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            r0.trySend-JP2dKIU(r7)
            com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$$ExternalSyntheticLambda0 r7 = new com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$$ExternalSyntheticLambda0
            r7.<init>()
            r7.f$0 = r2
            java.lang.invoke.VarHandle.storeStoreFence()
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r6.L$0 = r5
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r6.L$1 = r2
            r6.I$0 = r3
            r6.label = r4
            java.lang.Object r6 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r7, r6)
            if (r6 != r1) goto L81
            goto L80
        L6d:
            com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$$ExternalSyntheticLambda1 r7 = new com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1$$ExternalSyntheticLambda1
            r7.<init>()
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r6.L$0 = r2
            r6.label = r3
            java.lang.Object r6 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r7, r6)
            if (r6 != r1) goto L81
        L80:
            return r1
        L81:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.qs.res.data.repository.QSPersonalityRepository$couiSpotLightEnabled$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
