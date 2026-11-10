package com.oplus.systemui.notification.lockscreen;

import com.android.systemui.keyguard.shared.model.StatusBarState;
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
final class OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3 oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3 = new OplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3(3, (Continuation) obj3);
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3.L$0 = (StatusBarState) obj;
        oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3.Z$0 = zBooleanValue;
        return oplusNotificationCapsuleInteractorImpl$keyguardNotificationCapsule$3.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        StatusBarState statusBarState = (StatusBarState) this.L$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(statusBarState == StatusBarState.SHADE_LOCKED && z);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
