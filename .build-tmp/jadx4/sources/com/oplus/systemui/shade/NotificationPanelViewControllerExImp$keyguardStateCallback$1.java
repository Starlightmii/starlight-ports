package com.oplus.systemui.shade;

import com.android.systemui.keyguard.OplusKeyguardBottomAreaControllerEx;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.OplusKeyguardStateControllerEx;
import com.oplus.systemui.biometrics.OplusBiometricAuthController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationPanelViewControllerExImp$keyguardStateCallback$1 implements KeyguardStateController.Callback, OplusKeyguardStateControllerEx.Callback {
    public /* synthetic */ NotificationPanelViewControllerExImp this$0;

    public final void onKeyguardFpSuppressStateChanged(boolean z) {
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        if (((KeyguardStateController) notificationPanelViewControllerExImp.keyguardStateController.get()).isVisible()) {
            notificationPanelViewControllerExImp.sharedNotificationContainerViewModel.notificationStackChanged();
        }
    }

    public final void onKeyguardShowingChanged() {
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        if (!((KeyguardStateController) notificationPanelViewControllerExImp.keyguardStateController.get()).isVisible()) {
            ((OplusKeyguardBottomAreaControllerEx) notificationPanelViewControllerExImp.keyguardBottomAreaControllerEx.get()).removeBottomAreaHeightListener(notificationPanelViewControllerExImp.keyguardBottomAreaHeightListener);
            NotificationPanelViewControllerExImp.access$removeFingerprintVisibilityListener(notificationPanelViewControllerExImp);
        } else {
            notificationPanelViewControllerExImp.updateLockscreenNotificationBottomPadding();
            ((OplusKeyguardBottomAreaControllerEx) notificationPanelViewControllerExImp.keyguardBottomAreaControllerEx.get()).addBottomAreaHeightListener(notificationPanelViewControllerExImp.keyguardBottomAreaHeightListener);
            ((OplusBiometricAuthController) notificationPanelViewControllerExImp.oplusBiometricAuthControllerLazy.get()).addVisibilityListener(notificationPanelViewControllerExImp.fpVisibilityListener);
        }
    }

    public final void onUnlockedChanged() {
        NotificationPanelViewControllerExImp notificationPanelViewControllerExImp = this.this$0;
        if (((KeyguardStateController) notificationPanelViewControllerExImp.keyguardStateController.get()).isVisible()) {
            notificationPanelViewControllerExImp.sharedNotificationContainerViewModel.notificationStackChanged();
        }
    }
}
