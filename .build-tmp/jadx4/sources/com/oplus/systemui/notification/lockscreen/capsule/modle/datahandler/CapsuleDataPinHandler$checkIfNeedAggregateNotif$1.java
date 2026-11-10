package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class CapsuleDataPinHandler$checkIfNeedAggregateNotif$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ CapsuleItemView $otherNotifCard;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CapsuleDataPinHandler$checkIfNeedAggregateNotif$1(CapsuleItemView capsuleItemView, Continuation continuation) {
        super(2, continuation);
        this.$otherNotifCard = capsuleItemView;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new CapsuleDataPinHandler$checkIfNeedAggregateNotif$1(this.$otherNotifCard, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(80L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        CapsuleDataPinHandler capsuleDataPinHandler = CapsuleDataPinHandler.INSTANCE;
        if (CapsuleViewExpandKtKt.existCard(capsuleDataPinHandler.getCapsuleContainer(), this.$otherNotifCard)) {
            CapsuleDataPinHandler.CapsuleTimer.AGGREGATE.cancelTimeout("onBaseCardChange");
            capsuleDataPinHandler.startNotifAggregate(false, "onBaseCardChange");
        }
        return Unit.INSTANCE;
    }
}
