package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class TileConfigRepoSwitcher$flowWrapped$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ int I$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj).intValue();
        int iIntValue2 = ((Number) obj2).intValue();
        TileConfigRepoSwitcher$flowWrapped$1 tileConfigRepoSwitcher$flowWrapped$1 = new TileConfigRepoSwitcher$flowWrapped$1(3, (Continuation) obj3);
        tileConfigRepoSwitcher$flowWrapped$1.I$0 = iIntValue;
        tileConfigRepoSwitcher$flowWrapped$1.I$1 = iIntValue2;
        return tileConfigRepoSwitcher$flowWrapped$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i = this.I$0;
        int i2 = this.I$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return new TileConfigRepoSwitcher.UserAndScene(i, i2);
        }
        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
