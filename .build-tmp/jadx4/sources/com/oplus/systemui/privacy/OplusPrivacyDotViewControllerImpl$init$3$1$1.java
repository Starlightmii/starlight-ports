package com.oplus.systemui.privacy;

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
final class OplusPrivacyDotViewControllerImpl$init$3$1$1 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        OplusPrivacyDotViewControllerImpl$init$3$1$1 oplusPrivacyDotViewControllerImpl$init$3$1$1 = new OplusPrivacyDotViewControllerImpl$init$3$1$1(4, (Continuation) obj4);
        oplusPrivacyDotViewControllerImpl$init$3$1$1.Z$0 = zBooleanValue;
        oplusPrivacyDotViewControllerImpl$init$3$1$1.Z$1 = zBooleanValue2;
        oplusPrivacyDotViewControllerImpl$init$3$1$1.Z$2 = zBooleanValue3;
        return oplusPrivacyDotViewControllerImpl$init$3$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean((z || z2 || !z3) ? false : true);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
