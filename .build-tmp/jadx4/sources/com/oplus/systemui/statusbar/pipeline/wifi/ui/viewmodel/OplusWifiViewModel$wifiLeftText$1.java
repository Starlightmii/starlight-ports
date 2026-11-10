package com.oplus.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import com.oplus.systemui.statusbar.pipeline.wifi.shared.OplusWifiModel;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusWifiViewModel$wifiLeftText$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ OplusWifiViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusWifiViewModel$wifiLeftText$1(OplusWifiViewModel oplusWifiViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = oplusWifiViewModel;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        boolean zBooleanValue2 = ((Boolean) obj3).booleanValue();
        OplusWifiViewModel$wifiLeftText$1 oplusWifiViewModel$wifiLeftText$1 = new OplusWifiViewModel$wifiLeftText$1(this.this$0, (Continuation) obj4);
        oplusWifiViewModel$wifiLeftText$1.L$0 = (OplusWifiModel) obj;
        oplusWifiViewModel$wifiLeftText$1.Z$0 = zBooleanValue;
        oplusWifiViewModel$wifiLeftText$1.Z$1 = zBooleanValue2;
        return oplusWifiViewModel$wifiLeftText$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        OplusWifiModel oplusWifiModel = (OplusWifiModel) this.L$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (!z2 || oplusWifiModel.getWifiDouble()) {
            return null;
        }
        if (oplusWifiModel.isPassPointAp) {
            return this.this$0.context.getString(2131955285);
        }
        if (z && oplusWifiModel.getWifiStandard() == 6) {
            boolean z3 = CustomizeFeatureOption.sIsSupportShowWifi6E;
            OplusWifiViewModel oplusWifiViewModel = this.this$0;
            return z3 ? oplusWifiViewModel.context.getString(2131955283) : oplusWifiViewModel.context.getString(2131955282);
        }
        if (z && oplusWifiModel.getWifiStandard() == 8) {
            return this.this$0.context.getString(2131955284);
        }
        return null;
    }
}
