package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.MixColorTileDrawable;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1 extends SuspendLambda implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        StdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1 stdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1 = new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1(3, (Continuation) obj3);
        stdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1.Z$0 = zBooleanValue;
        stdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1.Z$1 = zBooleanValue2;
        return stdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        return new MixColorTileDrawable.TileTypeConfig(QSBlurConfigProvider.getStdInactiveBlurConfig(z2, z, 1.0f), null, 0, 0, null, GradientStrokeLineAdapter.getQsQuickEntranceBlurLightConfig(z2, z), 30);
    }
}
