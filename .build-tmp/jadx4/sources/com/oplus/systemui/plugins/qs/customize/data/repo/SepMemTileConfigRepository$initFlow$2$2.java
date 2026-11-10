package com.oplus.systemui.plugins.qs.customize.data.repo;

import android.util.Log;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepMemTileConfigRepository$initFlow$2$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        SepMemTileConfigRepository$initFlow$2$2 sepMemTileConfigRepository$initFlow$2$2 = new SepMemTileConfigRepository$initFlow$2$2(2, continuation);
        sepMemTileConfigRepository$initFlow$2$2.L$0 = obj;
        return sepMemTileConfigRepository$initFlow$2$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((Pair) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Pair pair = (Pair) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("[onEach] first.size=", ((List) pair.getFirst()).size(), ((List) pair.getSecond()).size(), ", second.size=", "SepMemTileConfigRepository-->"));
        }
        return Unit.INSTANCE;
    }
}
