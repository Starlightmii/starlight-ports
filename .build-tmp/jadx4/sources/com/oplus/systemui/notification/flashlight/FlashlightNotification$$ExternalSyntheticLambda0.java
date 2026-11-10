package com.oplus.systemui.notification.flashlight;

import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.observer.IObserverListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class FlashlightNotification$$ExternalSyntheticLambda0 implements IObserverListener {
    public /* synthetic */ FlashlightNotification f$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        FlashlightNotification flashlightNotification = this.f$0;
        flashlightNotification.mFlashlightControl.updateSuperPowerSaveMode(SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState);
    }
}
