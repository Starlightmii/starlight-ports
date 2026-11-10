package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.provider.Settings;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $phoneId;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusMobileConnectionRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1(int i, OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$phoneId = i;
        this.this$0 = oplusMobileConnectionRepositoryImpl;
    }

    public static final boolean invokeSuspend$getVoNrSettings(OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl, Ref.ObjectRef objectRef, int i) {
        boolean z = Settings.System.getInt(oplusMobileConnectionRepositoryImpl.context.getContentResolver(), (String) objectRef.element, 0) == 1;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionRepositoryImpl.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isVoNrEnabled getVoNrSettings: ", z, " phoneid:", i), null, 8);
        return z;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1 oplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1 = new OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1(this.$phoneId, this.this$0, continuation);
        oplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1.L$0 = obj;
        return oplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0059, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r9, r8) == r1) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b8, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r2, r8) == r1) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L2a
            if (r2 == r4) goto L26
            if (r2 != r3) goto L1f
            java.lang.Object r0 = r8.L$2
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1$voNrCallObserver$1 r0 = (com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1$voNrCallObserver$1) r0
            java.lang.Object r8 = r8.L$1
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lbb
        L1f:
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r8)
            r8 = 0
            return r8
        L26:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5c
        L2a:
            kotlin.ResultKt.throwOnFailure(r9)
            int r9 = r8.$phoneId
            boolean r9 = android.telephony.SubscriptionManager.isValidPhoneId(r9)
            r2 = 0
            if (r9 != 0) goto L5f
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r0.trySend-JP2dKIU(r9)
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$1 r9 = new com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$1
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl r2 = r8.this$0
            int r5 = r8.$phoneId
            r9.<init>(r3)
            r9.this$0 = r2
            r9.$phoneId = r5
            java.lang.invoke.VarHandle.storeStoreFence()
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r8.L$0 = r2
            r8.label = r4
            java.lang.Object r8 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r9, r8)
            if (r8 != r1) goto L5c
            goto Lba
        L5c:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L5f:
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            int r5 = r8.$phoneId
            java.lang.String r6 = "vonr_switch_change_slot_"
            java.lang.String r5 = androidx.appcompat.widget.ListPopupWindow$.ExternalSyntheticOutline0.m(r5, r6)
            r9.element = r5
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1$voNrCallObserver$1 r5 = new com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1$voNrCallObserver$1
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl r6 = r8.this$0
            int r7 = r8.$phoneId
            r5.<init>()
            com.oplusos.systemui.common.observer.ObserverDispatcher r6 = com.oplusos.systemui.common.observer.ObserverDispatcher.getInstance()
            java.lang.Object r7 = r9.element
            java.lang.String r7 = (java.lang.String) r7
            android.net.Uri r7 = android.provider.Settings.System.getUriFor(r7)
            r6.registerObserver(r7, r5, r2)
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl r2 = r8.this$0
            int r6 = r8.$phoneId
            boolean r2 = invokeSuspend$getVoNrSettings(r2, r9, r6)
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r0.trySend-JP2dKIU(r2)
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$2 r2 = new com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$2
            r2.<init>(r4)
            r2.$cellCountObserver = r5
            java.lang.invoke.VarHandle.storeStoreFence()
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r8.L$0 = r4
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r8.L$1 = r9
            java.lang.Object r9 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r8.L$2 = r9
            r8.label = r3
            java.lang.Object r8 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r2, r8)
            if (r8 != r1) goto Lbb
        Lba:
            return r1
        Lbb:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
