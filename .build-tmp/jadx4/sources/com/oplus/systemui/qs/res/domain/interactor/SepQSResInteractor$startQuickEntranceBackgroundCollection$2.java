package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.base.res.drawable.GradientTileDrawable;
import com.oplus.systemui.qs.res.domain.util.ResourcesUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SepQSResInteractor$startQuickEntranceBackgroundCollection$2 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ SepQSResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSResInteractor$startQuickEntranceBackgroundCollection$2(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = sepQSResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        SepQSResInteractor$startQuickEntranceBackgroundCollection$2 sepQSResInteractor$startQuickEntranceBackgroundCollection$2 = new SepQSResInteractor$startQuickEntranceBackgroundCollection$2(this.this$0, (Continuation) obj4);
        sepQSResInteractor$startQuickEntranceBackgroundCollection$2.Z$0 = zBooleanValue;
        sepQSResInteractor$startQuickEntranceBackgroundCollection$2.Z$1 = zBooleanValue2;
        sepQSResInteractor$startQuickEntranceBackgroundCollection$2.Z$2 = zBooleanValue3;
        return sepQSResInteractor$startQuickEntranceBackgroundCollection$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (z) {
            SepQSResInteractor sepQSResInteractor = this.this$0;
            return FlowKt.mapLatest(sepQSResInteractor.themeChangeFlow, new SepQSResInteractor.startQuickEntranceBackgroundCollection.getGlobalThemeBackground.1(sepQSResInteractor, (Continuation) null));
        }
        if (z2 || z3) {
            return FlowKt.flowOf(new GradientTileDrawable.TileTypeConfig(ResourcesUtil.getQuickEntranceNormalBgColor(this.this$0.context), 0, 0, 0, 0, 62, 0));
        }
        SepQSResInteractor sepQSResInteractor2 = this.this$0;
        return FlowKt.combine(sepQSResInteractor2.isSupportStrokeCorner, sepQSResInteractor2.isNightMode, sepQSResInteractor2.mirrorScaleValue, new SepQSResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1(4, null));
    }
}
