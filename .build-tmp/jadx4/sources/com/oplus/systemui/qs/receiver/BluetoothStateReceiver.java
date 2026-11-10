package com.oplus.systemui.qs.receiver;

import android.content.IntentFilter;
import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BluetoothStateReceiver extends AbstractReceiver {
    public static final BluetoothStateReceiver INSTANCE = new BluetoothStateReceiver();

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public void beforeRegister(IntentFilter intentFilter) {
        intentFilter.setPriority(1000);
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"android.bluetooth.adapter.action.STATE_CHANGED", "android.bluetooth.device.action.PAIRING_REQUEST"};
    }
}
