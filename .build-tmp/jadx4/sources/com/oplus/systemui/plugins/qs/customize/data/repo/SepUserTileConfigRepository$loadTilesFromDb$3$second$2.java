package com.oplus.systemui.plugins.qs.customize.data.repo;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepUserTileConfigRepository$loadTilesFromDb$3$second$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SepUserTileConfigRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepUserTileConfigRepository$loadTilesFromDb$3$second$2(SepUserTileConfigRepository sepUserTileConfigRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sepUserTileConfigRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        SepUserTileConfigRepository$loadTilesFromDb$3$second$2 sepUserTileConfigRepository$loadTilesFromDb$3$second$2 = new SepUserTileConfigRepository$loadTilesFromDb$3$second$2(this.this$0, continuation);
        sepUserTileConfigRepository$loadTilesFromDb$3$second$2.L$0 = obj;
        return sepUserTileConfigRepository$loadTilesFromDb$3$second$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((String) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) throws Throwable {
        String str = (String) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        SepStaticTileConfigRepository sepStaticTileConfigRepository = this.this$0.staticTileConfigRepo;
        this.L$0 = SpillingKt.nullOutSpilledVariable(str);
        this.label = 1;
        Object tileConfig = sepStaticTileConfigRepository.getTileConfig(str, this);
        return tileConfig == coroutine_suspended ? coroutine_suspended : tileConfig;
    }
}
