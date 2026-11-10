package com.oplus.systemui.volume.repository;

import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.oplus.systemui.volume.OplusVolumeDialogInterface;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusVolumeRepositoryEx {
    void setController(VolumeDialogController volumeDialogController, OplusVolumeControllerEx oplusVolumeControllerEx);

    void setViewImpl(OplusVolumeDialogInterface oplusVolumeDialogInterface);
}
