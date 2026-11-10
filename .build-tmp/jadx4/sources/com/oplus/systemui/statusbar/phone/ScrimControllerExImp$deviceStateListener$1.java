package com.oplus.systemui.statusbar.phone;

import com.oplusos.systemui.common.blurability.screenshot.OplusScreenshotHelper;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimControllerExImp$deviceStateListener$1 implements DeviceStateController.StateChangedListener {
    @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
    public final void onStateChanged(int i) {
        OplusScreenshotHelper.INSTANCE.needCheckPureBlack(2000L);
    }
}
