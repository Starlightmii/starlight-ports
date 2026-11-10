package com.oplus.systemui.volume.view;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView$showOrHideAnimation$1$3$1$2 implements Runnable {
    public /* synthetic */ int $i;
    public /* synthetic */ int $lastAnimatedRowIndex;
    public /* synthetic */ OplusVolumeRow $row;
    public /* synthetic */ Long $sideEnterGeneration;
    public /* synthetic */ boolean $toShow;
    public /* synthetic */ OplusVolumeDialogView this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.$row.setTouchable(true);
        if (this.$toShow && this.$i == this.$lastAnimatedRowIndex) {
            this.this$0.notifySideEnterAnimationEnded(this.$sideEnterGeneration);
        }
    }
}
