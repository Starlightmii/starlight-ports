package com.oplus.systemui.separate;

import android.hardware.biometrics.IBiometricContextListener;
import android.hardware.fingerprint.IUdfpsRefreshRateRequestCallback;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.statusbar.CommandQueue;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$commandQueueCallback$1 implements CommandQueue.Callbacks {
    public /* synthetic */ OplusSeparateQSManager this$0;

    public final void disable(int i, int i2, int i3, boolean z) {
        OplusSeparateQSPlugin separateQSPlugin = this.this$0.getSeparateQSPlugin();
        if (separateQSPlugin != null) {
            separateQSPlugin.disable(i, i2, i3, z);
        }
    }

    public /* bridge */ /* synthetic */ void setBiometricContextListener(IBiometricContextListener iBiometricContextListener) {
        super.setBiometricContextListener(iBiometricContextListener);
    }

    public /* bridge */ /* synthetic */ void setQsTiles(String[] strArr) {
        super.setQsTiles(strArr);
    }

    public /* bridge */ /* synthetic */ void setSplitscreenFocus(boolean z) {
        super.setSplitscreenFocus(z);
    }

    public /* bridge */ /* synthetic */ void setTopAppHidesStatusBar(boolean z) {
        super.setTopAppHidesStatusBar(z);
    }

    public /* bridge */ /* synthetic */ void setUdfpsRefreshRateCallback(IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback) {
        super.setUdfpsRefreshRateCallback(iUdfpsRefreshRateRequestCallback);
    }
}
