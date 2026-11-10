package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.model.QSDrawableConfig;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSResInteractor$startQuickEntranceBackgroundCollection$4 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        SepQSResInteractor$startQuickEntranceBackgroundCollection$4 sepQSResInteractor$startQuickEntranceBackgroundCollection$4 = new SepQSResInteractor$startQuickEntranceBackgroundCollection$4(2, continuation);
        sepQSResInteractor$startQuickEntranceBackgroundCollection$4.L$0 = obj;
        return sepQSResInteractor$startQuickEntranceBackgroundCollection$4;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((QSDrawableConfig) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        QSDrawableConfig qSDrawableConfig = (QSDrawableConfig) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        SepQSResPool.updateQuickEntranceDrawable(qSDrawableConfig.getDefaultTypeBuilder());
        return Unit.INSTANCE;
    }
}
