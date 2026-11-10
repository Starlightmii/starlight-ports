package com.oplus.systemui.qs.pipeline.data.event;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Flow $this_unsafeTransform$inlined;
    public /* synthetic */ int $userId$inlined;
    public /* synthetic */ VoWifiTileAutoAddEvent this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ int $userId$inlined;
        public /* synthetic */ VoWifiTileAutoAddEvent this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.pipeline.data.event.VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1$2$1, reason: invalid class name */
        public final class AnonymousClass1 extends ContinuationImpl {
            int I$0;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
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
                Boolean boolBoxBoolean = Boxing.boxBoolean(VoWifiTileAutoAddEvent.access$isVoWifiEnabled(this.this$0, this.$userId$inlined));
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (flowCollector.emit(boolBoxBoolean, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
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

    public /* synthetic */ VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$$inlined$map$1(int i) {
        this.$r8$classId = i;
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Flow flow = this.$this_unsafeTransform$inlined;
                VoWifiTileAutoAddEvent voWifiTileAutoAddEvent = this.this$0;
                int i = this.$userId$inlined;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2();
                anonymousClass2.$this_unsafeFlow = flowCollector;
                anonymousClass2.this$0 = voWifiTileAutoAddEvent;
                anonymousClass2.$userId$inlined = i;
                VarHandle.storeStoreFence();
                Object objCollect = flow.collect(anonymousClass2, continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            default:
                Flow flow2 = this.$this_unsafeTransform$inlined;
                VoWifiTileAutoAddEvent voWifiTileAutoAddEvent2 = this.this$0;
                int i2 = this.$userId$inlined;
                VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2 voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2();
                voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2.$this_unsafeFlow = flowCollector;
                voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2.this$0 = voWifiTileAutoAddEvent2;
                voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2.$userId$inlined = i2;
                VarHandle.storeStoreFence();
                Object objCollect2 = flow2.collect(voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$$inlined$map$1$2, continuation);
                return objCollect2 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect2 : Unit.INSTANCE;
        }
    }
}
