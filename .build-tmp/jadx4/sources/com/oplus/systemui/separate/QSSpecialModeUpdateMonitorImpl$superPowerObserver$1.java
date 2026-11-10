package com.oplus.systemui.separate;

import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.oplusos.systemui.common.observer.IObserverListener;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSSpecialModeUpdateMonitorImpl$superPowerObserver$1 implements IObserverListener {
    public /* synthetic */ QSSpecialModeUpdateMonitorImpl this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        Iterator it = this.this$0.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onSuperPowerSaveModeChanged();
        }
    }
}
