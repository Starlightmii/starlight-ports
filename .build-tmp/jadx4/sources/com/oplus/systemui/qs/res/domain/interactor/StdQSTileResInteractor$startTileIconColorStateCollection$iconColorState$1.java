package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.model.TileIconColorState;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1 extends SuspendLambda implements Function6 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ StdQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        super(6, continuation);
        this.this$0 = stdQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        ((Boolean) obj5).getClass();
        StdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1 stdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1 = new StdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1(this.this$0, (Continuation) obj6);
        stdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.Z$0 = zBooleanValue;
        stdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.Z$1 = zBooleanValue2;
        stdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.Z$2 = zBooleanValue3;
        return stdQSTileResInteractor$startTileIconColorStateCollection$iconColorState$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int stdInactiveIconColor;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        QSColorfulIconRepository.Companion companion = QSColorfulIconRepository.Companion;
        int stdActiveIconColor = companion.getStdActiveIconColor(this.this$0.context, z3);
        if (z2 || z) {
            stdInactiveIconColor = companion.getStdInactiveIconColor(this.this$0.context);
        } else {
            stdInactiveIconColor = z3 ? QsColorUtil.BRIGHTNESS_ICON_BG_LIGHT_COLOR : companion.getStdInactiveIconDarkColor(this.this$0.context);
        }
        return new TileIconColorState(stdActiveIconColor, stdInactiveIconColor, (z3 || z) ? companion.getStdUnavailableIconColor(this.this$0.context) : companion.getStdUnavailableIconDarkColor(this.this$0.context), false);
    }
}
