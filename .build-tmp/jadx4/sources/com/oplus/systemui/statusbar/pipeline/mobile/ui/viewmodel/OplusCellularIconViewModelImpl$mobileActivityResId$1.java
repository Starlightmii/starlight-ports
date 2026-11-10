package com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.oplus.systemui.statusbar.policy.MobileIconSets;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusCellularIconViewModelImpl$mobileActivityResId$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusCellularIconViewModelImpl$mobileActivityResId$1 oplusCellularIconViewModelImpl$mobileActivityResId$1 = new OplusCellularIconViewModelImpl$mobileActivityResId$1(5, (Continuation) obj5);
        oplusCellularIconViewModelImpl$mobileActivityResId$1.L$0 = (DataActivityModel) obj;
        oplusCellularIconViewModelImpl$mobileActivityResId$1.Z$0 = zBooleanValue;
        oplusCellularIconViewModelImpl$mobileActivityResId$1.Z$1 = zBooleanValue2;
        oplusCellularIconViewModelImpl$mobileActivityResId$1.L$1 = (MobileIconSets) obj4;
        return oplusCellularIconViewModelImpl$mobileActivityResId$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        DataActivityModel dataActivityModel = (DataActivityModel) this.L$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        MobileIconSets mobileIconSets = (MobileIconSets) this.L$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        int dataActivityDirection = 0;
        if (z) {
            dataActivityDirection = mobileIconSets.getDataActivityDirection(dataActivityModel != null ? dataActivityModel.getHasActivityIn() : false, dataActivityModel != null ? dataActivityModel.getHasActivityOut() : false, z2);
        }
        return Boxing.boxInt(dataActivityDirection);
    }
}
