package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.StateListTileDrawable;
import com.oplus.systemui.qs.base.util.QsGlobalThemeResourceUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSResInteractor$startMediaPanelThemeBackgroundCollection$getGlobalThemeBackground$1 extends SuspendLambda implements Function2 {
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        return new SepQSResInteractor$startMediaPanelThemeBackgroundCollection$getGlobalThemeBackground$1(2, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((Unit) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return new StateListTileDrawable.TileTypeConfig(QsGlobalThemeResourceUtil.getMediaPanelDrawable(), 0, 10, true);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
