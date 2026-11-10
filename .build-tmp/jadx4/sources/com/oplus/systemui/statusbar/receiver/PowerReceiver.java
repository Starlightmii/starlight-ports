package com.oplus.systemui.statusbar.receiver;

import com.oplus.systemui.statusbar.notification.helper.LowBatteryWarningDelayHelper;
import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PowerReceiver extends AbstractReceiver {
    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"android.intent.action.BATTERY_CHANGED", "android.intent.action.ACTION_POWER_CONNECTED", "android.intent.action.ACTION_POWER_DISCONNECTED", "android.intent.action.CONFIGURATION_CHANGED", "android.intent.action.BOOT_COMPLETED", "android.intent.action.USER_UNLOCKED", "android.intent.action.ADDITIONAL_BATTERY_CHANGED", "android.intent.action.PHONE_STATE", "oplus.intent.action.CANCEL_NOTIFICAITON", LowBatteryWarningDelayHelper.ACTION_LOW_BATTERY_WARNING_HOLD, LowBatteryWarningDelayHelper.ACTION_SUPER_POWER_READY_NOTIFY, "android.intent.action.ACTION_SHUTDOWN_IPO", "android.intent.action.ACTION_BOOT_IPO", "android.intent.action.ACTION_CLOSE_CHANGED_NOTIFY", "android.intent.action.ACTION_DETAIL_CHANGED_NOTIFY"};
    }
}
