package com.oplus.systemui.shade;

import com.android.systemui.biometrics.OplusBiometricAuthControllerEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$fpVisibilityListener$1 implements OplusBiometricAuthControllerEx.OnFingerprintVisibilityListener {
    public /* synthetic */ NotificationPanelViewControllerExImp this$0;

    public final void onFingerprintVisibilityChange(boolean z) {
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        notificationPanelViewControllerExImp.mainHandler.removeCallbacks(notificationPanelViewControllerExImp.fpVisibilityRefreshRunnable);
        notificationPanelViewControllerExImp.mainHandler.post(notificationPanelViewControllerExImp.fpVisibilityRefreshRunnable);
    }
}
