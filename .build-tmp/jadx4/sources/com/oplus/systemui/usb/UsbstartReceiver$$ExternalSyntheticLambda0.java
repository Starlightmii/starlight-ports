package com.oplus.systemui.usb;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.oplus.systemui.statusbar.phone.OplusUsbDialogInfoProvider;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class UsbstartReceiver$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ boolean f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ Intent f$2;
    public /* synthetic */ boolean f$3;

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.f$0;
        Context context = this.f$1;
        Intent intent = this.f$2;
        boolean z2 = this.f$3;
        ComponentName componentName = UsbstartReceiver.SERVER_COMPONENT;
        if (!z) {
            if (z2) {
                return;
            }
            OplusUsbBackend.getInstance(context).mCachedServerIntent = null;
            OplusUsbDialogInfoProvider.resetCurrentBlockedValue();
            OplusUsbBackend.getInstance(context).setIsServiceRunning(false);
            context.stopService(intent);
            return;
        }
        if (OplusUsbBackend.getInstance(context).shouldBlockService()) {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", "configServiceInExecutor: caching service intent, should block");
            OplusUsbBackend.getInstance(context).mCachedServerIntent = intent;
        } else {
            LogUtil.d(LogUtil.TAG_COMMON, "UsbStartReceiver", "configServiceInExecutor: starting service directly");
            OplusUsbBackend.getInstance(context).setIsServiceRunning(true);
            context.startService(intent);
        }
    }
}
