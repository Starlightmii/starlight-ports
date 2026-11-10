package com.oplus.systemui.volume.subDisplay;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogComponentSubDisplay {
    private final VolumeDialogImplSubDisplay dialog;

    public OplusVolumeDialogComponentSubDisplay(VolumeDialogImplSubDisplay volumeDialogImplSubDisplay) {
        this.dialog = volumeDialogImplSubDisplay;
        if (volumeDialogImplSubDisplay != null) {
            volumeDialogImplSubDisplay.init();
        }
    }

    public final VolumeDialogImplSubDisplay getDialog() {
        return this.dialog;
    }
}
