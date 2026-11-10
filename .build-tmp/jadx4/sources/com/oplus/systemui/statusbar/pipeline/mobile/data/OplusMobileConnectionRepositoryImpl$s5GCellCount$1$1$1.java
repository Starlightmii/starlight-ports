package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.provider.Settings;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $phoneId;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OplusMobileConnectionRepositoryImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Function0 {
        public /* synthetic */ Object $cellCountObserver;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        public final Object invoke() {
            switch (this.$r8$classId) {
                case 0:
                    ObserverDispatcher.getInstance().unregisterObserver((OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$cellCountObserver$1) this.$cellCountObserver);
                    break;
                default:
                    ObserverDispatcher.getInstance().unregisterObserver((OplusMobileConnectionRepositoryImpl$voNrSettings$1$1$1$voNrCallObserver$1) this.$cellCountObserver);
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1(int i, OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$phoneId = i;
        this.this$0 = oplusMobileConnectionRepositoryImpl;
    }

    public static final int invokeSuspend$get5GCellCount(OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl, Ref.ObjectRef objectRef, int i) {
        int i2 = Settings.System.getInt(oplusMobileConnectionRepositoryImpl.context.getContentResolver(), (String) objectRef.element, 0);
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, OplusMobileConnectionRepositoryImpl.TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(i, i2, "phoneId:", " get5GCellCount: "), null, 8);
        return i2;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1 oplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1 = new OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1(this.$phoneId, this.this$0, continuation);
        oplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1.L$0 = obj;
        return oplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0059, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r8, r7) == r1) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b8, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r5, r7) == r1) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L2a
            if (r2 == r4) goto L26
            if (r2 != r3) goto L1f
            java.lang.Object r0 = r7.L$2
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$cellCountObserver$1 r0 = (com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$cellCountObserver$1) r0
            java.lang.Object r7 = r7.L$1
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lbb
        L1f:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            r7 = 0
            return r7
        L26:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L5c
        L2a:
            kotlin.ResultKt.throwOnFailure(r8)
            int r8 = r7.$phoneId
            boolean r8 = android.telephony.SubscriptionManager.isValidPhoneId(r8)
            r2 = 0
            if (r8 != 0) goto L5f
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
            r0.trySend-JP2dKIU(r8)
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$1 r8 = new com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$1
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl r3 = r7.this$0
            int r5 = r7.$phoneId
            r8.<init>(r2)
            r8.this$0 = r3
            r8.$phoneId = r5
            java.lang.invoke.VarHandle.storeStoreFence()
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$0 = r2
            r7.label = r4
            java.lang.Object r7 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r8, r7)
            if (r7 != r1) goto L5c
            goto Lba
        L5c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L5f:
            kotlin.jvm.internal.Ref$ObjectRef r8 = new kotlin.jvm.internal.Ref$ObjectRef
            r8.<init>()
            int r4 = r7.$phoneId
            java.lang.String r5 = "settings_oplus_5ga_carrier_nums_slot_"
            java.lang.String r4 = androidx.appcompat.widget.ListPopupWindow$.ExternalSyntheticOutline0.m(r4, r5)
            r8.element = r4
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$cellCountObserver$1 r4 = new com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$cellCountObserver$1
            int r5 = r7.$phoneId
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl r6 = r7.this$0
            r4.<init>()
            com.oplusos.systemui.common.observer.ObserverDispatcher r5 = com.oplusos.systemui.common.observer.ObserverDispatcher.getInstance()
            java.lang.Object r6 = r8.element
            java.lang.String r6 = (java.lang.String) r6
            android.net.Uri r6 = android.provider.Settings.System.getUriFor(r6)
            r5.registerObserver(r6, r4, r2)
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl r5 = r7.this$0
            int r6 = r7.$phoneId
            int r5 = invokeSuspend$get5GCellCount(r5, r8, r6)
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            r0.trySend-JP2dKIU(r5)
            com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$2 r5 = new com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1$2
            r5.<init>(r2)
            r5.$cellCountObserver = r4
            java.lang.invoke.VarHandle.storeStoreFence()
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
            r7.L$0 = r2
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r7.L$1 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
            r7.L$2 = r8
            r7.label = r3
            java.lang.Object r7 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r5, r7)
            if (r7 != r1) goto Lbb
        Lba:
            return r1
        Lbb:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$s5GCellCount$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
