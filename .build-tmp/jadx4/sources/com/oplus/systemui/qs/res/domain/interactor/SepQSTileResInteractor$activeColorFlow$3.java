package com.oplus.systemui.qs.res.domain.interactor;

import android.support.v4.content.FileProvider$;
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
final class SepQSTileResInteractor$activeColorFlow$3 extends SuspendLambda implements Function2 {
    /* synthetic */ int I$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        SepQSTileResInteractor$activeColorFlow$3 sepQSTileResInteractor$activeColorFlow$3 = new SepQSTileResInteractor$activeColorFlow$3(2, continuation);
        sepQSTileResInteractor$activeColorFlow$3.I$0 = ((Number) obj).intValue();
        return sepQSTileResInteractor$activeColorFlow$3;
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
        LogUtil.i$default(LogUtil.TAG_QS, "SepQSTileResInteractor", FileProvider$.ExternalSyntheticOutline0.m("activeColorFlow: color=", Integer.toHexString(i)), null, 8);
        return Unit.INSTANCE;
    }
}
