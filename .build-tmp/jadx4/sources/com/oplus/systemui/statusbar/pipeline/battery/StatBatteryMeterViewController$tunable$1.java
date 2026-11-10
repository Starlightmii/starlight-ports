package com.oplus.systemui.statusbar.pipeline.battery;

import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatBatteryMeterViewController$tunable$1 implements TunerService.Tunable {
    public /* synthetic */ StatBatteryMeterViewController this$0;

    public final void onTuningChanged(String str, String str2) {
        StatBatteryMeterViewController statBatteryMeterViewController = this.this$0;
        if ("icon_blacklist".equals(str)) {
            ((StatBatteryMeterView) ((ViewController) statBatteryMeterViewController).mView).disable(StatusBarIconController.getIconHideList(statBatteryMeterViewController.getContext(), str2).contains(statBatteryMeterViewController.slotBattery));
        }
    }
}
