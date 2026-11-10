package com.oplus.systemui.separate;

import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import java.util.Iterator;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$iSysUiStateCallback$2$1 implements SysUiStateController.ISysUiStateListener {
    public /* synthetic */ OplusSeparateQSManager this$0;

    public final void onFullscreenStateChanged(boolean z) {
        Iterator it = this.this$0.iSysUiStateCallbacks.iterator();
        while (it.hasNext()) {
            ((OplusSeparateQSPlugin.ISysUiStateListener) it.next()).onFullscreenStateChanged(z);
        }
    }
}
