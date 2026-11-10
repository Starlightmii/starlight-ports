package com.oplus.systemui.notification.material;

import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightEffectManager$panoramicAodObserver$1 implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener {
    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
        NotificationSpotLightEffectManager.isPanoramicAod = z;
        NotificationSpotLightEffectManager.INSTANCE.getClass();
        NotificationSpotLightEffectManager.syncCouiSpotLightFromSystem();
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onProgressChanged(float f) {
    }
}
