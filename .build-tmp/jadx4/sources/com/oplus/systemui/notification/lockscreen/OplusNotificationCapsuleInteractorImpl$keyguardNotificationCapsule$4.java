package com.oplus.systemui.notification.lockscreen;

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
final class OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int iIntValue = ((Number) obj2).intValue();
        OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4 oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4 = new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4(3, (Continuation) obj3);
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4.Z$0 = zBooleanValue;
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4.I$0 = iIntValue;
        return oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$4.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(z || i == 0);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
