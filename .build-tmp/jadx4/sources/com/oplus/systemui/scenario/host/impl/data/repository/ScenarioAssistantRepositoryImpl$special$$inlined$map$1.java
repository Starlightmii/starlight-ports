package com.oplus.systemui.scenario.host.impl.data.repository;

import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioAssistantRepositoryImpl$special$$inlined$map$1 implements Flow {
    public /* synthetic */ Flow $this_unsafeTransform$inlined;
    public /* synthetic */ ScenarioAssistantRepositoryImpl this$0;

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        ScenarioAssistantRepositoryImpl scenarioAssistantRepositoryImpl = this.this$0;
        2 r1 = new 2();
        r1.$this_unsafeFlow = flowCollector;
        r1.this$0 = scenarioAssistantRepositoryImpl;
        VarHandle.storeStoreFence();
        Object objCollect = flow.collect(r1, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
