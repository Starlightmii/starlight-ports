package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.util.QSConstant;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSResInteractor$startMediaPanelOutlineCollection$2 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ SepQSResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSResInteractor$startMediaPanelOutlineCollection$2(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepQSResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj2).intValue();
        SepQSResInteractor$startMediaPanelOutlineCollection$2 sepQSResInteractor$startMediaPanelOutlineCollection$2 = new SepQSResInteractor$startMediaPanelOutlineCollection$2(this.this$0, (Continuation) obj3);
        sepQSResInteractor$startMediaPanelOutlineCollection$2.I$0 = iIntValue;
        return sepQSResInteractor$startMediaPanelOutlineCollection$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return QSConstant.getSmoothRoundRectOutlineProvider(this.this$0.context, this.this$0.context.getResources().getDimension(i));
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
