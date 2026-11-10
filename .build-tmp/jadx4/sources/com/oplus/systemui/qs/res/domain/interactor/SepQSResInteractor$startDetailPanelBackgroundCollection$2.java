package com.oplus.systemui.qs.res.domain.interactor;

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
final class SepQSResInteractor$startDetailPanelBackgroundCollection$2 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ SepQSResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SepQSResInteractor$startDetailPanelBackgroundCollection$2(SepQSResInteractor sepQSResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = sepQSResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        SepQSResInteractor$startDetailPanelBackgroundCollection$2 sepQSResInteractor$startDetailPanelBackgroundCollection$2 = new SepQSResInteractor$startDetailPanelBackgroundCollection$2(this.this$0, (Continuation) obj4);
        sepQSResInteractor$startDetailPanelBackgroundCollection$2.Z$0 = zBooleanValue;
        sepQSResInteractor$startDetailPanelBackgroundCollection$2.Z$1 = zBooleanValue2;
        sepQSResInteractor$startDetailPanelBackgroundCollection$2.Z$2 = zBooleanValue3;
        return sepQSResInteractor$startDetailPanelBackgroundCollection$2.invokeSuspend(Unit.INSTANCE);
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
            return FlowKt.mapLatest(sepQSResInteractor.themeChangeFlow, new SepQSResInteractor.startDetailPanelBackgroundCollection.getGlobalThemeBackground.1(sepQSResInteractor, (Continuation) null));
        }
        SepQSResInteractor sepQSResInteractor2 = this.this$0;
        if (z3) {
            return FlowKt.mapLatest(sepQSResInteractor2.isNightMode, new SepQSResInteractor.startDetailPanelBackgroundCollection.getPowerSaveBackground.1(sepQSResInteractor2, (Continuation) null));
        }
        return z2 ? FlowKt.mapLatest(sepQSResInteractor2.isNightMode, new SepQSResInteractor$startDetailPanelBackgroundCollection$getNormalBackground$1(2, null)) : FlowKt.combine(sepQSResInteractor2.isSupportStrokeCorner, sepQSResInteractor2.isNightMode, sepQSResInteractor2.mirrorScaleValue, new SepQSResInteractor$startDetailPanelBackgroundCollection$getBlurBackground$1(4, null));
    }
}
