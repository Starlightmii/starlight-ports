package com.oplus.systemui.notification.lockscreen.seedling;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingImmersiveViewState$startImmersiveStateAnimation$startRunnable$1 implements Runnable {
    public /* synthetic */ ImmersiveViewEntry.AnimationInfo $info;

    @Override // java.lang.Runnable
    public final void run() {
        this.$info.getAnimator().start();
        this.$info.setDelayRunnable(null);
    }
}
