package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1 extends SuspendLambda implements Function2 {
    /* synthetic */ boolean Z$0;
    int label;

    public final Continuation create(Object obj, Continuation continuation) {
        SepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1 sepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1 = new SepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1(2, continuation);
        sepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1.Z$0 = ((Boolean) obj).booleanValue();
        return sepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((SepQSResInteractor$startMediaPanelBackgroundCollection$getLightOSBackground$1) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
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
        return new GradientTileDrawable.TileTypeConfig(i, 0, 0, QsColorUtil.DRAG_MODE_BG_COLOR, 0, 54, 0);
    }
}
