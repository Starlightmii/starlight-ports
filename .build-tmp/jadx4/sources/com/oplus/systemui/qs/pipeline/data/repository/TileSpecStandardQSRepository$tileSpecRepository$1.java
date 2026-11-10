package com.oplus.systemui.qs.pipeline.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileSpecStandardQSRepository$tileSpecRepository$1 extends SuspendLambda implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ TileSpecStandardQSRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileSpecStandardQSRepository$tileSpecRepository$1(TileSpecStandardQSRepository tileSpecStandardQSRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = tileSpecStandardQSRepository;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        TileSpecStandardQSRepository$tileSpecRepository$1 tileSpecStandardQSRepository$tileSpecRepository$1 = new TileSpecStandardQSRepository$tileSpecRepository$1(this.this$0, (Continuation) obj3);
        tileSpecStandardQSRepository$tileSpecRepository$1.Z$0 = zBooleanValue;
        tileSpecStandardQSRepository$tileSpecRepository$1.Z$1 = zBooleanValue2;
        return tileSpecStandardQSRepository$tileSpecRepository$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        TileSpecStandardQSRepository tileSpecStandardQSRepository = this.this$0;
        int i = TileSpecStandardQSRepository.$r8$clinit;
        return tileSpecStandardQSRepository.getTargetTileSpecRepository(z, z2);
    }
}
