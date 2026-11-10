package com.oplus.systemui.qs.pipeline.data.adapter;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileSpecRepositoryAdapter$tileSpecRepository$1 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ TileSpecRepositoryAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileSpecRepositoryAdapter$tileSpecRepository$1(TileSpecRepositoryAdapter tileSpecRepositoryAdapter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = tileSpecRepositoryAdapter;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        TileSpecRepositoryAdapter$tileSpecRepository$1 tileSpecRepositoryAdapter$tileSpecRepository$1 = new TileSpecRepositoryAdapter$tileSpecRepository$1(this.this$0, continuation);
        tileSpecRepositoryAdapter$tileSpecRepository$1.Z$0 = ((Boolean) obj).booleanValue();
        return tileSpecRepositoryAdapter$tileSpecRepository$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((TileSpecRepositoryAdapter$tileSpecRepository$1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        TileSpecRepositoryAdapter tileSpecRepositoryAdapter = this.this$0;
        int i = TileSpecRepositoryAdapter.$r8$clinit;
        return tileSpecRepositoryAdapter.getTargetTileSpecRepository(z);
    }
}
