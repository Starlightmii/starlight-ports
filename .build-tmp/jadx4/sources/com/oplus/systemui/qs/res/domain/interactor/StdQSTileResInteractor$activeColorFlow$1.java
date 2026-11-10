package com.oplus.systemui.qs.res.domain.interactor;

import com.oplus.systemui.qs.res.data.repository.QSColorfulIconRepository;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StdQSTileResInteractor$activeColorFlow$1 extends SuspendLambda implements Function4 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ StdQSTileResInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StdQSTileResInteractor$activeColorFlow$1(StdQSTileResInteractor stdQSTileResInteractor, Continuation continuation) {
        super(4, continuation);
        this.this$0 = stdQSTileResInteractor;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        ((Number) obj).intValue();
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        StdQSTileResInteractor$activeColorFlow$1 stdQSTileResInteractor$activeColorFlow$1 = new StdQSTileResInteractor$activeColorFlow$1(this.this$0, (Continuation) obj4);
        stdQSTileResInteractor$activeColorFlow$1.Z$0 = zBooleanValue;
        stdQSTileResInteractor$activeColorFlow$1.Z$1 = zBooleanValue2;
        return stdQSTileResInteractor$activeColorFlow$1.invokeSuspend(Unit.INSTANCE);
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
        StdQSTileResInteractor stdQSTileResInteractor = this.this$0;
        return z ? FlowKt.flowOf(Boxing.boxInt(QSColorfulIconRepository.Companion.getStdTwoToneAccentColor(stdQSTileResInteractor.context, z2))) : stdQSTileResInteractor.darkThemeAccentColor;
    }
}
