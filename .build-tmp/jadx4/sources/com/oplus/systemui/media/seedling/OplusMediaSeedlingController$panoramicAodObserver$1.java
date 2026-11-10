package com.oplus.systemui.media.seedling;

import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingController$panoramicAodObserver$1 implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener {
    public /* synthetic */ OplusMediaSeedlingController this$0;

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "panoramicAodObserver isPanoramicAod=" + z + " shouldUpdateUi=" + z2);
        ((MediaSeedling) this.this$0.mediaSeedling.get()).setPanoramicAod(z && z2);
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onProgressChanged(float f) {
    }
}
