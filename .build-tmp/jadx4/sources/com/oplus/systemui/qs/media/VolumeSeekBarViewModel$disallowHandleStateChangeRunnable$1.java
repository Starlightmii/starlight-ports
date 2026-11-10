package com.oplus.systemui.qs.media;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeSeekBarViewModel$disallowHandleStateChangeRunnable$1 implements Runnable {
    public /* synthetic */ VolumeSeekBarViewModel this$0;

    @Override // java.lang.Runnable
    public final void run() {
        VolumeSeekBarViewModel volumeSeekBarViewModel = this.this$0;
        volumeSeekBarViewModel.disallowHandleStateChange = false;
        volumeSeekBarViewModel.setVolumeBarProgress(volumeSeekBarViewModel.curVolumeLevel);
    }
}
