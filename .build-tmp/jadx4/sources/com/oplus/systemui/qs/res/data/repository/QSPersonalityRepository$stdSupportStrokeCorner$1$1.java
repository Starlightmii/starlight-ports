package com.oplus.systemui.qs.res.data.repository;

import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSPersonalityRepository$stdSupportStrokeCorner$1$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        QSPersonalityRepository$stdSupportStrokeCorner$1$1 qSPersonalityRepository$stdSupportStrokeCorner$1$1 = new QSPersonalityRepository$stdSupportStrokeCorner$1$1(3, (Continuation) obj3);
        qSPersonalityRepository$stdSupportStrokeCorner$1$1.Z$0 = zBooleanValue;
        qSPersonalityRepository$stdSupportStrokeCorner$1$1.L$0 = (ShapeConfig) obj2;
        return qSPersonalityRepository$stdSupportStrokeCorner$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        ShapeConfig shapeConfig = (ShapeConfig) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(z && shapeConfig.getCircleShape());
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
