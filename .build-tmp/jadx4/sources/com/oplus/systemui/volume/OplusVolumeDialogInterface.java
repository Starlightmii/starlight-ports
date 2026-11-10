package com.oplus.systemui.volume;

import com.android.systemui.plugins.VolumeDialogController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusVolumeDialogInterface {
    void onAccessibilityModeChanged(Boolean bool);

    void onStateChangedH(VolumeDialogController.State state);

    void onStateChangedH(VolumeDialogController.State state, int i);
}
