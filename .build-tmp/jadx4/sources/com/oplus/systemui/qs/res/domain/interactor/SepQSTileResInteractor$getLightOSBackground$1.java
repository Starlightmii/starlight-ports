package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSTileResInteractor$getLightOSBackground$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ boolean $isHighlight;
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileResInteractor$getLightOSBackground$1(boolean z, Continuation continuation) {
        super(3, continuation);
        this.$isHighlight = z;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        SepQSTileResInteractor$getLightOSBackground$1 sepQSTileResInteractor$getLightOSBackground$1 = new SepQSTileResInteractor$getLightOSBackground$1(this.$isHighlight, (Continuation) obj3);
        sepQSTileResInteractor$getLightOSBackground$1.I$0 = iIntValue;
        sepQSTileResInteractor$getLightOSBackground$1.Z$0 = zBooleanValue;
        return sepQSTileResInteractor$getLightOSBackground$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        int i2 = this.I$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (QsColorUtil.INSTANCE.getWallpaperBlurDisable()) {
            i = z ? QsColorUtil.QS_TILE_BG_INACTIVE_DARK_COLOR : QsColorUtil.QS_TILE_BG_INACTIVE_COLOR;
        } else {
            i = QsColorUtil.LIGHT_OS_QS_TILE_BG_INACTIVE_DARK_COLOR;
        }
        int i3 = i;
        return new GradientTileDrawable.TileTypeConfig(this.$isHighlight ? i3 : i2, i3, 0, 0, QsColorUtil.DRAG_MODE_BG_COLOR, 44, 0);
    }
}
