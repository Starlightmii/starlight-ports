package com.oplus.systemui.separate;

import android.view.Display;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.settings.DisplayTracker;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSSpecialModeUpdateMonitorImpl$displayChangeListener$1 implements DisplayTracker.Callback {
    public /* synthetic */ QSSpecialModeUpdateMonitorImpl this$0;

    public final void onDisplayChanged(int i) {
        int rotation;
        QSSpecialModeUpdateMonitorImpl qSSpecialModeUpdateMonitorImpl = this.this$0;
        Display display = qSSpecialModeUpdateMonitorImpl.context.getDisplay();
        if (i != display.getDisplayId() || qSSpecialModeUpdateMonitorImpl.lastRotation == (rotation = display.getRotation())) {
            return;
        }
        qSSpecialModeUpdateMonitorImpl.lastRotation = rotation;
        Iterator it = qSSpecialModeUpdateMonitorImpl.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onDisplayRotationChanged(rotation);
        }
    }
}
