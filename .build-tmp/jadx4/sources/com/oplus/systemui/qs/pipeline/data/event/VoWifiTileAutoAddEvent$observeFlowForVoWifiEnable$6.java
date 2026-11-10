package com.oplus.systemui.qs.pipeline.data.event;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6 voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6(2, continuation);
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6.L$0 = obj;
        return voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$6;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((List) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        List list = (List) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return FlowKt.asFlow(list);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
