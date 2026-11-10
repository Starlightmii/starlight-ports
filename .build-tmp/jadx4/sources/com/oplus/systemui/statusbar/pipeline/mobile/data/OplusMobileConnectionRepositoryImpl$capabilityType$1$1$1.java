package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $phoneId;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    final /* synthetic */ OplusMobileConnectionRepositoryImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Function0 {
        public /* synthetic */ OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1$imsListenerCb$1 $imsListenerCb;
        public /* synthetic */ int $phoneId;
        public /* synthetic */ OplusMobileConnectionRepositoryImpl this$0;

        public final Object invoke() {
            OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl = this.this$0;
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, oplusMobileConnectionRepositoryImpl.logTag, "stopListenImsStatusChanged", null, 8);
            oplusMobileConnectionRepositoryImpl.imsListenerProxy.stopListenImsStatusChanged(this.$phoneId, this.$imsListenerCb);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1(int i, OplusMobileConnectionRepositoryImpl oplusMobileConnectionRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = oplusMobileConnectionRepositoryImpl;
        this.$phoneId = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1 oplusMobileConnectionRepositoryImpl$capabilityType$1$1$1 = new OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1(this.$phoneId, this.this$0, continuation);
        oplusMobileConnectionRepositoryImpl$capabilityType$1$1$1.L$0 = obj;
        return oplusMobileConnectionRepositoryImpl$capabilityType$1$1$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x00b0, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r3, r12) == r1) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x010b, code lost:
    
        if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r3, r12) == r1) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionRepositoryImpl$capabilityType$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
