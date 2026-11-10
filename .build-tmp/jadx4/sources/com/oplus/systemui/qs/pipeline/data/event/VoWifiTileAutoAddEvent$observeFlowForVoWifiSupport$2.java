package com.oplus.systemui.qs.pipeline.data.event;

import androidx.compose.foundation.text.input.TextFieldBuffer$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $userId;
    /* synthetic */ int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2(int i, Continuation continuation) {
        super(2, continuation);
        this.$userId = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2 voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2(this.$userId, continuation);
        voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2.I$0 = ((Number) obj).intValue();
        return voWifiTileAutoAddEvent$observeFlowForVoWifiSupport$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        LogUtil.i$default(LogUtil.TAG_QS, "VoWifiTileAutoAddEvent", TextFieldBuffer$.ExternalSyntheticOutline0.m(i, this.$userId, "isVoWifiEnableSupport: ", " userId: "), null, 8);
        return Unit.INSTANCE;
    }
}
