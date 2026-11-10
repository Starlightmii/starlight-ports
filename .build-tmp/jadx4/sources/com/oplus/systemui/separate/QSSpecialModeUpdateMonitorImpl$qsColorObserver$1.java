package com.oplus.systemui.separate;

import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.qs.OplusQsColorManagerEx;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSSpecialModeUpdateMonitorImpl$qsColorObserver$1 implements OplusQsColorManagerEx.QsColorStateObserver {
    public /* synthetic */ QSSpecialModeUpdateMonitorImpl this$0;

    public final void onQsColorStateChanged(int i) {
        Iterator it = this.this$0.listeners.iterator();
        while (it.hasNext()) {
            ((QSSpecialModeUpdateMonitor.IQSSpecialModeChangeListener) it.next()).onQSColorStateChanged();
        }
    }
}
