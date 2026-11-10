package com.oplus.systemui.usb;

import android.window.OnBackInvokedCallback;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UsbService$$ExternalSyntheticLambda12 implements OnBackInvokedCallback {
    public /* synthetic */ UsbService f$0;

    @Override // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        this.f$0.mUsbSelectDialog.dismiss();
        LogUtil.i(LogUtil.TAG_COMMON, "UsbService", "onBackInvoked from OnBackInvokedDispatcher");
    }
}
