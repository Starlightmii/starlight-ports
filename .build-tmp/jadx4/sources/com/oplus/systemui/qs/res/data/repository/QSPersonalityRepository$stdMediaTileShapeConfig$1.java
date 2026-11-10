package com.oplus.systemui.qs.res.data.repository;

import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSPersonalityRepository$stdMediaTileShapeConfig$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ QSPersonalityRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSPersonalityRepository$stdMediaTileShapeConfig$1(QSPersonalityRepository qSPersonalityRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = qSPersonalityRepository;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        QSPersonalityRepository$stdMediaTileShapeConfig$1 qSPersonalityRepository$stdMediaTileShapeConfig$1 = new QSPersonalityRepository$stdMediaTileShapeConfig$1(this.this$0, (Continuation) obj3);
        qSPersonalityRepository$stdMediaTileShapeConfig$1.I$0 = iIntValue;
        qSPersonalityRepository$stdMediaTileShapeConfig$1.Z$0 = zBooleanValue;
        return qSPersonalityRepository$stdMediaTileShapeConfig$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        QSPersonalityRepository qSPersonalityRepository = this.this$0;
        return z ? qSPersonalityRepository.getDefaultMediaConfig() : (ShapeConfig) qSPersonalityRepository.shapeConfigs.get(i, this.this$0.getDefaultMediaConfig());
    }
}
