package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$startHighlightTileOutlineCollection$2 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ StdQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StdQSTileResInteractor$startHighlightTileOutlineCollection$2(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = stdQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj3).booleanValue();
        StdQSTileResInteractor$startHighlightTileOutlineCollection$2 stdQSTileResInteractor$startHighlightTileOutlineCollection$2 = new StdQSTileResInteractor$startHighlightTileOutlineCollection$2(this.this$0, (Continuation) obj4);
        stdQSTileResInteractor$startHighlightTileOutlineCollection$2.L$0 = (ShapeConfig) obj2;
        stdQSTileResInteractor$startHighlightTileOutlineCollection$2.Z$0 = zBooleanValue;
        return stdQSTileResInteractor$startHighlightTileOutlineCollection$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ShapeConfig shapeConfig = (ShapeConfig) this.L$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        StdQSTileResInteractor stdQSTileResInteractor = this.this$0;
        return z ? QSConstant.getFullCornerProvider(stdQSTileResInteractor.context.getResources().getDimension(2131171939)) : QSConstant.getSmoothRoundRectOutlineProvider(stdQSTileResInteractor.context, shapeConfig.getRadius(this.this$0.context));
    }
}
