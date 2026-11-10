package com.oplus.systemui.qs.tileex;

import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSTileExImpl$isDisableVolumeMute$result$1 extends SuspendLambda implements Function2 {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ QSTileExImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSTileExImpl$isDisableVolumeMute$result$1(QSTileExImpl qSTileExImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = qSTileExImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        QSTileExImpl$isDisableVolumeMute$result$1 qSTileExImpl$isDisableVolumeMute$result$1 = new QSTileExImpl$isDisableVolumeMute$result$1(this.this$0, continuation);
        qSTileExImpl$isDisableVolumeMute$result$1.L$0 = obj;
        return qSTileExImpl$isDisableVolumeMute$result$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                QSTileExImpl$isDisableVolumeMute$result$1$1$1 qSTileExImpl$isDisableVolumeMute$result$1$1$1 = new QSTileExImpl$isDisableVolumeMute$result$1$1$1(this.this$0, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 1;
                obj = TimeoutKt.withTimeout(5000L, qSTileExImpl$isDisableVolumeMute$result$1$1$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            obj2 = Result.constructor-impl((String) obj);
        } catch (Throwable th) {
            obj2 = Result.constructor-impl(ResultKt.createFailure(th));
        }
        QSTileExImpl qSTileExImpl = this.this$0;
        Throwable th2 = Result.exceptionOrNull-impl(obj2);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_QS, qSTileExImpl.getTAG(), "isDisableVolumeMute execute error: " + th2);
        }
        if (obj2 instanceof Result.Failure) {
            return null;
        }
        return obj2;
    }
}
