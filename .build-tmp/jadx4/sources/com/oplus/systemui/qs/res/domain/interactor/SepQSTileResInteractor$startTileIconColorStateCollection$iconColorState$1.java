package com.oplus.systemui.qs.res.domain.interactor;

import android.content.Context;
import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ SepQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = sepQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        SepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1 sepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1 = new SepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1(this.this$0, (Continuation) obj4);
        sepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.Z$0 = zBooleanValue;
        sepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.Z$1 = zBooleanValue2;
        return sepQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.invokeSuspend(Unit.INSTANCE);
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
        QSColorfulIconRepository.Companion companion = QSColorfulIconRepository.Companion;
        Context context = this.this$0.context;
        QsColorUtil qsColorUtil = QsColorUtil.INSTANCE;
        int sepActiveIconColor = companion.getSepActiveIconColor(context, qsColorUtil.getWallpaperBlurDisable());
        int sepUnavailableIconColor = companion.getSepUnavailableIconColor(this.this$0.context, z2, qsColorUtil.getWallpaperBlurDisable());
        boolean wallpaperBlurDisable = qsColorUtil.getWallpaperBlurDisable();
        companion.getClass();
        return TuplesKt.to(new TileIconColorState(sepActiveIconColor, (!wallpaperBlurDisable || z2) ? QsColorUtil.QS_ICON_INACTIVE_DARK_COLOR : QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR, sepUnavailableIconColor, z), new TileIconColorState(sepActiveIconColor, companion.getSepThreeStageInactiveIconColor(this.this$0.context, z2, qsColorUtil.getWallpaperBlurDisable()), sepUnavailableIconColor, z));
    }
}
