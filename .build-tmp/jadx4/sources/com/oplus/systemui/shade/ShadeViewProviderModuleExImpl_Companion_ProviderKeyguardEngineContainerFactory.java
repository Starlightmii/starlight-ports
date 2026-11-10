package com.oplus.systemui.shade;

import com.android.systemui.shade.NotificationPanelView;
import com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainer;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class ShadeViewProviderModuleExImpl_Companion_ProviderKeyguardEngineContainerFactory implements Factory {
    public static KeyguardEngineContainer providerKeyguardEngineContainer(NotificationPanelView notificationPanelView) {
        KeyguardEngineContainer keyguardEngineContainerProviderKeyguardEngineContainer = ShadeViewProviderModuleExImpl.Companion.providerKeyguardEngineContainer(notificationPanelView);
        keyguardEngineContainerProviderKeyguardEngineContainer.getClass();
        return keyguardEngineContainerProviderKeyguardEngineContainer;
    }
}
