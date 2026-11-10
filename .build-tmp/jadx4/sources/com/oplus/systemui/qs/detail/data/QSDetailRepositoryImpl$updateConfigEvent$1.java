package com.oplus.systemui.qs.detail.data;

import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSDetailRepositoryImpl$updateConfigEvent$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ QSDetailRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSDetailRepositoryImpl$updateConfigEvent$1(QSDetailRepositoryImpl qSDetailRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = qSDetailRepositoryImpl;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        QSDetailRepositoryImpl$updateConfigEvent$1 qSDetailRepositoryImpl$updateConfigEvent$1 = new QSDetailRepositoryImpl$updateConfigEvent$1(this.this$0, continuation);
        qSDetailRepositoryImpl$updateConfigEvent$1.L$0 = obj;
        return qSDetailRepositoryImpl$updateConfigEvent$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((ProducerScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope = (ProducerScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            QSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1 qSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1 = new QSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1();
            qSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            this.this$0.configurationController.addCallback(qSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1);
            QSDetailRepositoryImpl qSDetailRepositoryImpl = this.this$0;
            QSDetailRepositoryImpl$updateConfigEvent$1$$ExternalSyntheticLambda0 qSDetailRepositoryImpl$updateConfigEvent$1$$ExternalSyntheticLambda0 = new QSDetailRepositoryImpl$updateConfigEvent$1$$ExternalSyntheticLambda0();
            qSDetailRepositoryImpl$updateConfigEvent$1$$ExternalSyntheticLambda0.f$0 = qSDetailRepositoryImpl;
            qSDetailRepositoryImpl$updateConfigEvent$1$$ExternalSyntheticLambda0.f$1 = qSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(qSDetailRepositoryImpl$updateConfigEvent$1$configurationListener$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, qSDetailRepositoryImpl$updateConfigEvent$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
