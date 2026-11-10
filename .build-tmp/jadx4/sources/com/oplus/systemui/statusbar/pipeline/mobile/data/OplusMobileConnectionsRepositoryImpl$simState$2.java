package com.oplus.systemui.statusbar.pipeline.mobile.data;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusMobileConnectionsRepositoryImpl$simState$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusMobileConnectionsRepositoryImpl$simState$2 oplusMobileConnectionsRepositoryImpl$simState$2 = new OplusMobileConnectionsRepositoryImpl$simState$2(3, (Continuation) obj3);
        oplusMobileConnectionsRepositoryImpl$simState$2.L$0 = (Throwable) obj2;
        return oplusMobileConnectionsRepositoryImpl$simState$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Throwable th = (Throwable) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        Log.e("OplusMobileConnectionsRepository", "Error parsing broadcast", th);
        return Unit.INSTANCE;
    }
}
