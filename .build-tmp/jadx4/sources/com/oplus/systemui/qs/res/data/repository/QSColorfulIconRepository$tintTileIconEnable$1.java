package com.oplus.systemui.qs.res.data.repository;

import android.content.res.Configuration;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class QSColorfulIconRepository$tintTileIconEnable$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ QSColorfulIconRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSColorfulIconRepository$tintTileIconEnable$1(QSColorfulIconRepository qSColorfulIconRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = qSColorfulIconRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new QSColorfulIconRepository$tintTileIconEnable$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((Configuration) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(QSColorfulIconRepository.Companion.isTintIconAllowed(this.this$0.context));
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
