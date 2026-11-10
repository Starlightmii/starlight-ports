package com.oplus.systemui.qs.res.data.repository;

import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSPersonalityRepository$stdSupportSpotLight$1$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        QSPersonalityRepository$stdSupportSpotLight$1$1 qSPersonalityRepository$stdSupportSpotLight$1$1 = new QSPersonalityRepository$stdSupportSpotLight$1$1(4, (Continuation) obj4);
        qSPersonalityRepository$stdSupportSpotLight$1$1.Z$0 = zBooleanValue;
        qSPersonalityRepository$stdSupportSpotLight$1$1.Z$1 = zBooleanValue2;
        qSPersonalityRepository$stdSupportSpotLight$1$1.L$0 = (ShapeConfig) obj3;
        return qSPersonalityRepository$stdSupportSpotLight$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        ShapeConfig shapeConfig = (ShapeConfig) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(z && z2 && shapeConfig.getCircleShape());
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
