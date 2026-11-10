package com.oplus.systemui.volume;

import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeDialogImpl$$ExternalSyntheticLambda4 implements DeviceStateController.StateChangedListener {
    public /* synthetic */ OplusVolumeDialogImpl f$0;

    @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
    public final void onStateChanged(int i) {
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.f$0;
        if (DeviceStateController.getInstance().isOpenOrHalfOpen()) {
            return;
        }
        oplusVolumeDialogImpl.dismiss(4);
    }
}
