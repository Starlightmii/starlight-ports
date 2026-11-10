package com.oplus.systemui.notification.lockscreen.capsule;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class NotificationCapsuleContainerControllerImpl$collectAlphaChange$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ NotificationCapsuleContainerControllerImpl this$0;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl$collectAlphaChange$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function4 {
        /* synthetic */ float F$0;
        /* synthetic */ float F$1;
        /* synthetic */ float F$2;
        int label;

        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            float fFloatValue = ((Number) obj).floatValue();
            float fFloatValue2 = ((Number) obj2).floatValue();
            float fFloatValue3 = ((Number) obj3).floatValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(4, (Continuation) obj4);
            anonymousClass1.F$0 = fFloatValue;
            anonymousClass1.F$1 = fFloatValue2;
            anonymousClass1.F$2 = fFloatValue3;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            float f = this.F$0;
            float f2 = this.F$1;
            float f3 = this.F$2;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxFloat(Math.min(f, Math.min(f2, f3)));
            }
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationCapsuleContainerControllerImpl$collectAlphaChange$1(NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = notificationCapsuleContainerControllerImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationCapsuleContainerControllerImpl$collectAlphaChange$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flowCombine = FlowKt.combine(this.this$0.keyguardAlpha, this.this$0.orientationAlpha, this.this$0.lightOSAnimaAlpha, new AnonymousClass1(4, null));
            NotificationCapsuleContainerControllerImpl notificationCapsuleContainerControllerImpl = this.this$0;
            NotificationCapsuleContainerControllerImpl.AnonymousClass1.C00211.C00221.C00231 c00231 = new NotificationCapsuleContainerControllerImpl.AnonymousClass1.C00211.C00221.C00231(1);
            c00231.this$0 = notificationCapsuleContainerControllerImpl;
            VarHandle.storeStoreFence();
            this.label = 1;
            if (flowCombine.collect(c00231, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
