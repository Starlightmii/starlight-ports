package com.oplus.systemui.qs.res.data.repository;

import com.oplusos.systemui.common.receiver.ThemeReceiver;
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
final class QSThemeConfigRepository$themeChangeEvent$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        QSThemeConfigRepository$themeChangeEvent$1 qSThemeConfigRepository$themeChangeEvent$1 = new QSThemeConfigRepository$themeChangeEvent$1(2, continuation);
        qSThemeConfigRepository$themeChangeEvent$1.L$0 = obj;
        return qSThemeConfigRepository$themeChangeEvent$1;
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
            QSThemeConfigRepository$themeChangeEvent$1$listener$1 qSThemeConfigRepository$themeChangeEvent$1$listener$1 = new QSThemeConfigRepository$themeChangeEvent$1$listener$1();
            qSThemeConfigRepository$themeChangeEvent$1$listener$1.$$this$conflatedCallbackFlow = producerScope;
            VarHandle.storeStoreFence();
            ThemeReceiver.Companion.getInstance().addListener(qSThemeConfigRepository$themeChangeEvent$1$listener$1);
            QSThemeConfigRepository$themeChangeEvent$1$$ExternalSyntheticLambda0 qSThemeConfigRepository$themeChangeEvent$1$$ExternalSyntheticLambda0 = new QSThemeConfigRepository$themeChangeEvent$1$$ExternalSyntheticLambda0();
            qSThemeConfigRepository$themeChangeEvent$1$$ExternalSyntheticLambda0.f$0 = qSThemeConfigRepository$themeChangeEvent$1$listener$1;
            VarHandle.storeStoreFence();
            this.L$0 = SpillingKt.nullOutSpilledVariable(producerScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(qSThemeConfigRepository$themeChangeEvent$1$listener$1);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, qSThemeConfigRepository$themeChangeEvent$1$$ExternalSyntheticLambda0, this) == coroutine_suspended) {
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
