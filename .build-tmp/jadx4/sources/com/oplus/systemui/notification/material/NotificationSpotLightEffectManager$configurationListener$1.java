package com.oplus.systemui.notification.material;

import com.android.systemui.statusbar.policy.ConfigurationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightEffectManager$configurationListener$1 implements ConfigurationController.ConfigurationListener {
    public final void onUiModeChanged() {
        NotificationSpotLightEffectManager.access$notifyUiModeChanged(NotificationSpotLightEffectManager.INSTANCE);
    }
}
