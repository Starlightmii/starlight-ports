package com.oplus.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiIconSets;
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
final class OplusWifiViewModel$wifiActivityResId$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusWifiViewModel$wifiActivityResId$1 oplusWifiViewModel$wifiActivityResId$1 = new OplusWifiViewModel$wifiActivityResId$1(4, (Continuation) obj4);
        oplusWifiViewModel$wifiActivityResId$1.Z$0 = zBooleanValue;
        oplusWifiViewModel$wifiActivityResId$1.L$0 = (DataActivityModel) obj2;
        oplusWifiViewModel$wifiActivityResId$1.Z$1 = zBooleanValue2;
        return oplusWifiViewModel$wifiActivityResId$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        int iMapWifiSmallIconRes;
        boolean z = this.Z$0;
        DataActivityModel dataActivityModel = (DataActivityModel) this.L$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (!z) {
            iMapWifiSmallIconRes = 0;
        } else if (dataActivityModel.getHasActivityIn() && dataActivityModel.getHasActivityOut()) {
            iMapWifiSmallIconRes = OplusWifiIconSets.INSTANCE.mapWifiSmallIconRes(2131236762, z2);
        } else if (dataActivityModel.getHasActivityIn()) {
            iMapWifiSmallIconRes = OplusWifiIconSets.INSTANCE.mapWifiSmallIconRes(2131236760, z2);
        } else {
            iMapWifiSmallIconRes = dataActivityModel.getHasActivityOut() ? OplusWifiIconSets.INSTANCE.mapWifiSmallIconRes(2131236766, z2) : OplusWifiIconSets.INSTANCE.mapWifiSmallIconRes(2131236764, z2);
        }
        return Boxing.boxInt(iMapWifiSmallIconRes);
    }
}
