package com.oplus.systemui.usb;

import com.oplus.systemui.usb.utils.OplusUsbData;
import java.util.function.ToIntFunction;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UsbService$$ExternalSyntheticLambda8 implements ToIntFunction {
    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object obj) {
        return ((OplusUsbData) obj).getIcon();
    }
}
