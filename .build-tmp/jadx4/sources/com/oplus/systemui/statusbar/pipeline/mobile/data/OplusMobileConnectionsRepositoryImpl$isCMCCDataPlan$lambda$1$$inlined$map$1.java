package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.android.systemui.util.settings.SystemSettings;
import com.oplusos.systemui.common.util.LogUtil;
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
public final class OplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1 implements Flow {
    public /* synthetic */ SystemSettings $systemSettings$inlined;
    public /* synthetic */ Flow $this_unsafeTransform$inlined;
    public /* synthetic */ int $user$inlined;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements FlowCollector {
        public /* synthetic */ SystemSettings $systemSettings$inlined;
        public /* synthetic */ FlowCollector $this_unsafeFlow;
        public /* synthetic */ int $user$inlined;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.mobile.data.OplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1$2$1, reason: invalid class name */
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

        /* JADX WARN: Code duplicated, block: B:7:0x0015  */
        public final Object emit(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1;
            int i = this.$user$inlined;
            if (continuation instanceof AnonymousClass1) {
                anonymousClass1 = (AnonymousClass1) continuation;
                int i2 = anonymousClass1.label;
                if ((i2 & Integer.MIN_VALUE) != 0) {
                    anonymousClass1.label = i2 - Integer.MIN_VALUE;
                } else {
                    anonymousClass1 = new AnonymousClass1(continuation);
                }
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
            Object obj2 = anonymousClass1.result;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = anonymousClass1.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj2);
                FlowCollector flowCollector = this.$this_unsafeFlow;
                boolean z = this.$systemSettings$inlined.getIntForUser("OPLUS_SIM_VSIM_TYPE", -1, i) == 2;
                LogUtil.i$default(LogUtil.TAG_STATUSBAR, "OplusMobileConnectionsRepository", "isCMCCDataPlan flow emit: " + z + " (user=" + i + ")", null, 8);
                Boolean boolBoxBoolean = Boxing.boxBoolean(z);
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
                if (i3 != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj2);
            }
            return Unit.INSTANCE;
        }
    }

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        SystemSettings systemSettings = this.$systemSettings$inlined;
        int i = this.$user$inlined;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$this_unsafeFlow = flowCollector;
        anonymousClass2.$systemSettings$inlined = systemSettings;
        anonymousClass2.$user$inlined = i;
        VarHandle.storeStoreFence();
        Object objCollect = flow.collect(anonymousClass2, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
