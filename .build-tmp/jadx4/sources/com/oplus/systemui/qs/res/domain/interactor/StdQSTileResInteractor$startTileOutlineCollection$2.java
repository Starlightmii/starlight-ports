package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.model.ShapeConfig;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplusos.systemui.common.outline.OvalOutlineProvider;
import com.oplusos.systemui.common.outline.PathOutlineProvider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$startTileOutlineCollection$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ StdQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StdQSTileResInteractor$startTileOutlineCollection$2(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = stdQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        StdQSTileResInteractor$startTileOutlineCollection$2 stdQSTileResInteractor$startTileOutlineCollection$2 = new StdQSTileResInteractor$startTileOutlineCollection$2(this.this$0, (Continuation) obj3);
        stdQSTileResInteractor$startTileOutlineCollection$2.L$0 = (ShapeConfig) obj2;
        return stdQSTileResInteractor$startTileOutlineCollection$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ShapeConfig shapeConfig = (ShapeConfig) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (shapeConfig.getCircleShape()) {
            return OvalOutlineProvider.INSTANCE;
        }
        if (!shapeConfig.getSupportSmoothRound()) {
            return new PathOutlineProvider(shapeConfig.getPath(), shapeConfig.getStdWidth(), shapeConfig.getStdHeight());
        }
        return QSConstant.getSmoothRoundRectOutlineProvider(this.this$0.context, shapeConfig.getRadius(this.this$0.context));
    }
}
