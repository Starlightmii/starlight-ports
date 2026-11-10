package com.oplus.systemui.qs.scenario.domain.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$lambda$3$$inlined$filter$1$2 implements FlowCollector {
    public /* synthetic */ boolean $listening$inlined;
    public /* synthetic */ FlowCollector $this_unsafeFlow;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.scenario.domain.viewmodel.PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$lambda$3$$inlined$filter$1$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PrivacyScenarioCapsuleViewModelImpl$scenarioPreviewInfo$lambda$3$$inlined$filter$1$2.this.emit(null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj2);
            FlowCollector flowCollector = this.$this_unsafeFlow;
            if (this.$listening$inlined) {
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flowCollector.emit(obj, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj2);
        }
        return Unit.INSTANCE;
    }
}
