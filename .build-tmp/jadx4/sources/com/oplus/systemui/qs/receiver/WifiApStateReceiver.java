package com.oplus.systemui.qs.receiver;

import android.content.IntentFilter;
import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WifiApStateReceiver extends AbstractReceiver {
    public static final WifiApStateReceiver INSTANCE = new WifiApStateReceiver();

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public void beforeRegister(IntentFilter intentFilter) {
        super.beforeRegister(intentFilter);
        intentFilter.setPriority(1000);
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"android.net.wifi.WIFI_AP_STATE_CHANGED"};
    }
}
