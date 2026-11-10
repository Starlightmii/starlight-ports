package com.oplus.systemui.volume.repository;

import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AudioDataRepository$special$$inlined$map$1 implements Flow {
    public /* synthetic */ StateFlow $this_unsafeTransform$inlined;

    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        StateFlow stateFlow = this.$this_unsafeTransform$inlined;
        2 r0 = new 2();
        r0.$this_unsafeFlow = flowCollector;
        VarHandle.storeStoreFence();
        Object objCollect = stateFlow.collect(r0, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
