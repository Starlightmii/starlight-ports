package com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor;

import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileIconInteractorImpl$special$$inlined$combine$3 implements Flow {
    public /* synthetic */ Flow[] $flows$inlined;
    public /* synthetic */ OplusMobileIconInteractorImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl$special$$inlined$combine$3$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        boolean Z$1;
        int label;
        final /* synthetic */ OplusMobileIconInteractorImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Continuation continuation, OplusMobileIconInteractorImpl oplusMobileIconInteractorImpl) {
            super(3, continuation);
            this.this$0 = oplusMobileIconInteractorImpl;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3((Continuation) obj3, this.this$0);
            anonymousClass3.L$0 = (FlowCollector) obj;
            anonymousClass3.L$1 = (Object[]) obj2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0140, code lost:
        
            if (r1.emit(r0, r16) == r10) goto L31;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 326
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.pipeline.mobile.domain.interactor.OplusMobileIconInteractorImpl$special$$inlined$combine$3.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow[] flowArr = this.$flows$inlined;
        OplusMobileIconInteractorImpl$special$$inlined$combine$1.AnonymousClass2 anonymousClass2 = new OplusMobileIconInteractorImpl$special$$inlined$combine$1.AnonymousClass2(2);
        anonymousClass2.$flows = flowArr;
        VarHandle.storeStoreFence();
        Object objCombineInternal = CombineKt.combineInternal(flowCollector, flowArr, anonymousClass2, new AnonymousClass3(null, this.this$0), continuation);
        return objCombineInternal == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCombineInternal : Unit.INSTANCE;
    }
}
