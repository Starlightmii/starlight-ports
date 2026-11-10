package com.oplus.systemui.qs.detail.binder;

import com.coui.appcompat.couiswitch.COUISwitch;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSDetailHeaderBinder$collectSwitchState$4 extends SuspendLambda implements Function3 {
    final /* synthetic */ COUISwitch $switch;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSDetailHeaderBinder$collectSwitchState$4(COUISwitch cOUISwitch, Continuation continuation) {
        super(3, continuation);
        this.$switch = cOUISwitch;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return new QSDetailHeaderBinder$collectSwitchState$4(this.$switch, (Continuation) obj3).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        this.$switch.stopLoading$1();
        return Unit.INSTANCE;
    }
}
