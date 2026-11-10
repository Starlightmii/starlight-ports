package com.oplus.systemui.qs.widget;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFgsManagerController$updateNumberOfVisibleRunningPackagesLocked$1$1 implements Runnable {
    public /* synthetic */ OplusFgsManagerController.OnNumberOfPackagesChangedListener $it;
    public /* synthetic */ int $numberPackages;

    @Override // java.lang.Runnable
    public final void run() {
        this.$it.onNumberOfPackagesChanged(this.$numberPackages);
    }
}
