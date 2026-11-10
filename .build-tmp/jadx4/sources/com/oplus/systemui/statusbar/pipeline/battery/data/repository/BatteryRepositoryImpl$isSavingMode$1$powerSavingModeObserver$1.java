package com.oplus.systemui.statusbar.pipeline.battery.data.repository;

import com.oplus.systemui.statusbar.observer.PowerSavingModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryRepositoryImpl$isSavingMode$1$powerSavingModeObserver$1 implements IObserverListener {
    public /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        this.$$this$conflatedCallbackFlow.trySend-JP2dKIU(Boolean.valueOf(PowerSavingModeObserver.Companion.getInstance().batterySavingMode));
    }
}
