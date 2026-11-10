package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSTileResInteractor$startTileIconThemeColorStateCollection$iconColorState$1 extends SuspendLambda implements Function3 {
    int label;
    final /* synthetic */ SepQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSTileResInteractor$startTileIconThemeColorStateCollection$iconColorState$1(SepQSTileResInteractor sepQSTileResInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sepQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ((Boolean) obj).getClass();
        return new SepQSTileResInteractor$startTileIconThemeColorStateCollection$iconColorState$1(this.this$0, (Continuation) obj3).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return new TileIconColorState(QSColorfulIconRepository.Companion.getSepThemeActiveIconColor(this.this$0.context, QsColorUtil.INSTANCE.getWallpaperBlurDisable()), this.this$0.context.getColor(2131103746), this.this$0.context.getColor(2131103749), false);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
