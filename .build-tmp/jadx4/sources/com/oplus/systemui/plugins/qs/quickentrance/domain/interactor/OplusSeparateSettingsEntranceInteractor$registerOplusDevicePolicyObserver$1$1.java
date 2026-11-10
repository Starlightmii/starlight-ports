package com.oplus.systemui.plugins.qs.quickentrance.domain.interactor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusSeparateSettingsEntranceInteractor this$0;

    public /* synthetic */ OplusSeparateSettingsEntranceInteractor$registerOplusDevicePolicyObserver$1$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusSeparateSettingsEntranceInteractor.access$updateHideSettingsEntranceState(this.this$0);
                break;
            default:
                OplusSeparateSettingsEntranceInteractor.access$updateHideSettingsEntranceState(this.this$0);
                break;
        }
    }
}
