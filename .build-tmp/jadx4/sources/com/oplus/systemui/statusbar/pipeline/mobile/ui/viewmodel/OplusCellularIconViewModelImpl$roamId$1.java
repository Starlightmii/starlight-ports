package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.oplus.systemui.statusbar.policy.MobileIconSets;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusCellularIconViewModelImpl$roamId$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
        OplusCellularIconViewModelImpl$roamId$1 oplusCellularIconViewModelImpl$roamId$1 = new OplusCellularIconViewModelImpl$roamId$1(4, (Continuation) obj4);
        oplusCellularIconViewModelImpl$roamId$1.Z$0 = zBooleanValue;
        oplusCellularIconViewModelImpl$roamId$1.Z$1 = zBooleanValue2;
        oplusCellularIconViewModelImpl$roamId$1.L$0 = (MobileIconSets) obj3;
        return oplusCellularIconViewModelImpl$roamId$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int i;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        MobileIconSets mobileIconSets = (MobileIconSets) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (z) {
            mobileIconSets.getClass();
            i = z2 ? 2131236857 : 2131236823;
        } else {
            i = 0;
        }
        return Boxing.boxInt(i);
    }
}
