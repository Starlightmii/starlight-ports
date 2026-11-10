package com.oplus.systemui.qs.res.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSPersonalityRepository$stdTileShapeConfig$1 extends SuspendLambda implements Function2 {
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ QSPersonalityRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSPersonalityRepository$stdTileShapeConfig$1(QSPersonalityRepository qSPersonalityRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = qSPersonalityRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        QSPersonalityRepository$stdTileShapeConfig$1 qSPersonalityRepository$stdTileShapeConfig$1 = new QSPersonalityRepository$stdTileShapeConfig$1(this.this$0, continuation);
        qSPersonalityRepository$stdTileShapeConfig$1.I$0 = ((Number) obj).intValue();
        return qSPersonalityRepository$stdTileShapeConfig$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.this$0.shapeConfigs.get(i, this.this$0.getDefaultConfig());
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
