package com.oplus.systemui.usb.receiver;

import com.oplusos.systemui.common.receiver.AbstractReceiver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class UsbStateReceiver extends AbstractReceiver {
    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"com.oplus.test.show.usbselector.dialog", "com.oplus.test.file.transmit", "com.oplus.test.image.transmit", "com.oplus.test.only.charge", "com.oplus.test.usb.tethering", "com.oplus.test.midi", "android.intent.action.TIME_SET", "android.intent.action.CLOSE_SYSTEM_DIALOGS"};
    }
}
