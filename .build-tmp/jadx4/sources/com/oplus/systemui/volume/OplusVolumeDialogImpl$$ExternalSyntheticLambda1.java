package com.oplus.systemui.volume;

import com.oplus.systemui.volume.view.OplusVolumeRow;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeDialogImpl$$ExternalSyntheticLambda1 implements Runnable {
    public /* synthetic */ OplusVolumeDialogImpl f$0;
    public /* synthetic */ boolean f$1;

    @Override // java.lang.Runnable
    public final void run() {
        OplusVolumeDialogImpl oplusVolumeDialogImpl = this.f$0;
        if (!this.f$1) {
            VolumeFeatureOption.INSTANCE.getClass();
            if (VolumeFeatureOption.isOplusSuperVolume()) {
                OplusVolumeRow activeRow = oplusVolumeDialogImpl.getActiveRow();
                oplusVolumeDialogImpl.mSuperVolumeRepository.showSuperVolume(activeRow.isSuperVolume, activeRow);
            }
        }
        oplusVolumeDialogImpl.mOplusVolumeDialogView.tryFlushPendingSideStructuralUpdate();
    }
}
