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
final class StdQSTileResInteractor$startQuickEntranceBackgroundCollection$2 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ StdQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StdQSTileResInteractor$startQuickEntranceBackgroundCollection$2(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = stdQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue3 = ((Boolean) obj3).booleanValue();
        StdQSTileResInteractor$startQuickEntranceBackgroundCollection$2 stdQSTileResInteractor$startQuickEntranceBackgroundCollection$2 = new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$2(this.this$0, (Continuation) obj4);
        stdQSTileResInteractor$startQuickEntranceBackgroundCollection$2.Z$0 = zBooleanValue;
        stdQSTileResInteractor$startQuickEntranceBackgroundCollection$2.Z$1 = zBooleanValue2;
        stdQSTileResInteractor$startQuickEntranceBackgroundCollection$2.Z$2 = zBooleanValue3;
        return stdQSTileResInteractor$startQuickEntranceBackgroundCollection$2.invokeSuspend(Unit.INSTANCE);
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
            StdQSTileResInteractor stdQSTileResInteractor = this.this$0;
            return FlowKt.mapLatest(stdQSTileResInteractor.themeChangeFlow, new StdQSTileResInteractor.startQuickEntranceBackgroundCollection.getGlobalThemeBackground.1(stdQSTileResInteractor, (Continuation) null));
        }
        if (z2 || z3) {
            return FlowKt.mapLatest(this.this$0.isNightMode, new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$getNormalBackground$1(2, null));
        }
        StdQSTileResInteractor stdQSTileResInteractor2 = this.this$0;
        return FlowKt.combine(stdQSTileResInteractor2.isSupportStrokeCorner, stdQSTileResInteractor2.isNightMode, new StdQSTileResInteractor$startQuickEntranceBackgroundCollection$getBlurBackground$1(3, null));
    }
}
