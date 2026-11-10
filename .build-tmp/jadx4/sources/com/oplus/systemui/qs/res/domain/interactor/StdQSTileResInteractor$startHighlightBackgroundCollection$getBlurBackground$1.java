package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.MixColorTileDrawable;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1 extends SuspendLambda implements Function5 {
    /* synthetic */ float F$0;
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int iIntValue = ((Number) obj2).intValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        float fFloatValue = ((Number) obj4).floatValue();
        StdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1 stdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1 = new StdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1(5, (Continuation) obj5);
        stdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1.Z$0 = zBooleanValue;
        stdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1.I$0 = iIntValue;
        stdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1.Z$1 = zBooleanValue2;
        stdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1.F$0 = fFloatValue;
        return stdQSTileResInteractor$startHighlightBackgroundCollection$getBlurBackground$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        int i = this.I$0;
        boolean z2 = this.Z$1;
        float f = this.F$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        BlurMixConfig stdInactiveBlurConfig = QSBlurConfigProvider.getStdInactiveBlurConfig(z2, z, f);
        return new MixColorTileDrawable.TileTypeConfig(stdInactiveBlurConfig, stdInactiveBlurConfig, i, 0, GradientStrokeLineAdapter.getQSTileActiveBlurLightConfig(z2, z), GradientStrokeLineAdapter.getQSHighlightInActiveBlurLightConfig(z2, z)).getStdHighLightTypeBuilder();
    }
}
