package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import com.android.systemui.common.charge.OplusAddBatteryStatus;
import com.android.systemui.common.charge.OplusBatteryStatus;
import com.oplusos.systemui.common.battery.OplusBatteryController;
import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryRepositoryImpl$batteryLevel$1$listener$1 implements OplusBatteryController.IBatteryStateChanged {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;
    public /* synthetic */ BatteryRepositoryImpl this$0;

    @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
    public /* bridge */ /* synthetic */ void onAdditionalBatteryStateChanged(OplusAddBatteryStatus oplusAddBatteryStatus) {
        super.onAdditionalBatteryStateChanged(oplusAddBatteryStatus);
    }

    @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
    public final void onBatteryStateChanged(int i) {
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, "BatteryRepository", "level: " + i + " -- isSavingMode: " + this.this$0.isSavingMode().getValue(), null, 8);
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Integer.valueOf(i));
    }

    @Override // com.oplusos.systemui.common.battery.OplusBatteryController.IBatteryStateChanged
    public /* bridge */ /* synthetic */ void onBatteryStateChanged(OplusBatteryStatus oplusBatteryStatus) {
        super.onBatteryStateChanged(oplusBatteryStatus);
    }
}
