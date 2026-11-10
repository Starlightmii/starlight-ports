package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import android.util.Log;
import com.android.systemui.common.charge.OplusAddBatteryStatus;
import com.android.systemui.common.charge.OplusBatteryStatus;
import com.oplus.systemui.statusbar.pipeline.battery.data.model.ChargeStateModel;
import com.oplusos.systemui.common.battery.OplusBatteryController;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryRepositoryImpl$chargeState$1$listener$1 implements OplusBatteryController.IBatteryStateChanged {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ Ref.ObjectRef $model;
    public /* synthetic */ BatteryRepositoryImpl this$0;

    @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
    public final void onAdditionalBatteryStateChanged(OplusAddBatteryStatus oplusAddBatteryStatus) {
        boolean z;
        Ref.ObjectRef objectRef = this.$model;
        ChargeStateModel chargeStateModel = (ChargeStateModel) objectRef.element;
        boolean batteryCharging = oplusAddBatteryStatus.getBatteryCharging();
        boolean z2 = oplusAddBatteryStatus.isWirelessCharging;
        boolean z3 = false;
        if (oplusAddBatteryStatus.getBatteryCharging() && oplusAddBatteryStatus.isSuperVoocCharge()) {
            z3 = true;
            z = true;
        } else {
            z = true;
        }
        boolean zIsWirelessVoocCharge = oplusAddBatteryStatus.isWirelessVoocCharge();
        if (!oplusAddBatteryStatus.getBatteryCharging() || !oplusAddBatteryStatus.isHeatStateActive) {
            z = false;
        }
        boolean z4 = oplusAddBatteryStatus.isWirelessChargingReverse;
        int chargerWattageOrigin = oplusAddBatteryStatus.getChargerWattageOrigin();
        StatusBarFeatureOption.INSTANCE.getClass();
        objectRef.element = chargeStateModel.copy(batteryCharging, z2, z3, zIsWirelessVoocCharge, z, z4, chargerWattageOrigin, StatusBarFeatureOption.isChargeVoocSpecialColorShow());
        BatteryRepositoryImpl batteryRepositoryImpl = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "BatteryRepository-->".concat("model: " + objectRef.element + " -- isSavingMode:" + batteryRepositoryImpl.isSavingMode().getValue()));
        }
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(objectRef.element);
    }

    @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
    public /* bridge */ /* synthetic */ void onBatteryStateChanged(OplusBatteryStatus oplusBatteryStatus) {
        super.onBatteryStateChanged(oplusBatteryStatus);
    }
}
