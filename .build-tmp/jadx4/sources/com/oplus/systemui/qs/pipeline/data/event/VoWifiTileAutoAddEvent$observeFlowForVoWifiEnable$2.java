package com.oplus.systemui.qs.pipeline.data.event;

import com.android.keyguard.logging.KeyguardLogger$;
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
final class VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $userId;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2(int i, Continuation continuation) {
        super(2, continuation);
        this.$userId = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2 voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2 = new VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2(this.$userId, continuation);
        voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2.Z$0 = ((Boolean) obj).booleanValue();
        return voWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((VoWifiTileAutoAddEvent$observeFlowForVoWifiEnable$2) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        LogUtil.i$default(LogUtil.TAG_QS, "VoWifiTileAutoAddEvent", KeyguardLogger$.ExternalSyntheticOutline0.m("isVoWifiEnabled ", z, " userId: ", this.$userId), null, 8);
        return Unit.INSTANCE;
    }
}
