package com.oplus.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiIconSets;
import com.oplus.systemui.statusbar.pipeline.wifi.shared.OplusWifiModel;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
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
final class OplusWifiViewModel$wifiLeftResId$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ OplusWifiViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusWifiViewModel$wifiLeftResId$1(OplusWifiViewModel oplusWifiViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = oplusWifiViewModel;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusWifiViewModel$wifiLeftResId$1 oplusWifiViewModel$wifiLeftResId$1 = new OplusWifiViewModel$wifiLeftResId$1(this.this$0, (Continuation) obj4);
        oplusWifiViewModel$wifiLeftResId$1.L$0 = (OplusWifiModel) obj;
        oplusWifiViewModel$wifiLeftResId$1.Z$0 = zBooleanValue;
        oplusWifiViewModel$wifiLeftResId$1.Z$1 = zBooleanValue2;
        return oplusWifiViewModel$wifiLeftResId$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0025  */
    public final Object invokeSuspend(Object obj) {
        int passPointIcon;
        OplusWifiModel oplusWifiModel = (OplusWifiModel) this.L$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (oplusWifiModel.getWifiDouble()) {
            passPointIcon = OplusWifiIconSets.INSTANCE.mapWifiSmallIconRes(2131236916, z2);
        } else if (z2) {
            passPointIcon = 0;
        } else if (oplusWifiModel.isPassPointAp) {
            passPointIcon = this.this$0.getPassPointIcon();
        } else if (z && oplusWifiModel.getWifiStandard() == 6) {
            passPointIcon = CustomizeFeatureOption.sIsSupportShowWifi6E ? 2131236914 : 2131236913;
        } else if (z && oplusWifiModel.getWifiStandard() == 8) {
            passPointIcon = 2131236915;
        } else {
            passPointIcon = 0;
        }
        return Boxing.boxInt(passPointIcon);
    }
}
