package com.oplus.systemui.viewdialog.ui.animation;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DetailAnimationManager$showDetailFromView$$inlined$doOnPreDraw$1 implements Runnable {
    public /* synthetic */ DetailAnimationManager this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.setLaunching(true);
        DetailAnimationManager detailAnimationManager = this.this$0;
        detailAnimationManager.startLaunchAnimation(detailAnimationManager.fromData, this.this$0.targetData);
    }
}
