package com.oplus.systemui.statusbar.pipeline.battery.domain.interactor;

import android.util.Log;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class StatBatteryIconInteractorImpl$chargeState$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        StatBatteryIconInteractorImpl$chargeState$1 statBatteryIconInteractorImpl$chargeState$1 = new StatBatteryIconInteractorImpl$chargeState$1(3, (Continuation) obj3);
        statBatteryIconInteractorImpl$chargeState$1.L$0 = (ChargeStateModel) obj;
        statBatteryIconInteractorImpl$chargeState$1.Z$0 = zBooleanValue;
        return statBatteryIconInteractorImpl$chargeState$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ChargeStateModel chargeStateModel = (ChargeStateModel) this.L$0;
        boolean z = this.Z$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("wirelessChargingReverse:", " reverseChargeEnabled:", "StatBatteryIconInteractorImpl-->", chargeStateModel.getWirelessChargingReverse(), z));
        }
        return chargeStateModel.copy(chargeStateModel.charging, chargeStateModel.pluggedInWireless, chargeStateModel.superVoocCharging, chargeStateModel.wirelessVoocCharging, chargeStateModel.heatStateActive, chargeStateModel.getWirelessChargingReverse() && z, chargeStateModel.chargerWattage, chargeStateModel.voocSpecialColorShow);
    }
}
