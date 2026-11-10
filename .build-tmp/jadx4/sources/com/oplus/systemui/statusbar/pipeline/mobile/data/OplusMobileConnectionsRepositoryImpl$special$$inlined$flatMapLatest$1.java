package com.oplus.systemui.statusbar.pipeline.mobile.data;

import com.android.systemui.util.settings.SettingsProxyExt;
import com.android.systemui.util.settings.SystemSettings;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ SystemSettings $systemSettings$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1(Continuation continuation, SystemSettings systemSettings) {
        super(3, continuation);
        this.$systemSettings$inlined = systemSettings;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1 oplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1 = new OplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$systemSettings$inlined);
        oplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        oplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return oplusMobileConnectionsRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object obj2 = this.L$1;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            int iIntValue = ((Number) obj2).intValue();
            Flow flowOnStart = FlowKt.onStart(SettingsProxyExt.INSTANCE.observerFlow(this.$systemSettings$inlined, iIntValue, new String[]{"OPLUS_SIM_VSIM_TYPE"}), new OplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$1$1(2, null));
            SystemSettings systemSettings = this.$systemSettings$inlined;
            OplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1 oplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1 = new OplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1();
            oplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1.$this_unsafeTransform$inlined = flowOnStart;
            oplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1.$systemSettings$inlined = systemSettings;
            oplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1.$user$inlined = iIntValue;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(flowCollector);
            this.L$1 = SpillingKt.nullOutSpilledVariable(obj2);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, oplusMobileConnectionsRepositoryImpl$isCMCCDataPlan$lambda$1$$inlined$map$1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
