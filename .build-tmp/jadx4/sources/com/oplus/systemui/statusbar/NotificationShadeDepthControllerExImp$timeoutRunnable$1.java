package com.oplus.systemui.statusbar;

import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationShadeDepthControllerExImp$timeoutRunnable$1 implements Runnable {
    public /* synthetic */ NotificationShadeDepthControllerExImp this$0;

    @Override // java.lang.Runnable
    public final void run() {
        if (this.this$0.sceneFlags.get() != 0) {
            LogUtil.i$default(LogUtil.TAG_COMMON, "NotificationShadeDepthC", "Timeout 5s, reset sceneFlags 0x000", null, 8);
            this.this$0.sceneFlags.set(0);
            this.this$0.getController().restrainNavigationBarInvertColor(false);
        }
    }
}
