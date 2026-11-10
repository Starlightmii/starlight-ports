package com.oplus.systemui.media.seedling;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeedlingMediaDataCombineLatest$cancelSuppressSort$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusSeedlingMediaDataCombineLatest this$0;

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.setSuppressMediaListSort(false);
                break;
            default:
                this.this$0.setSuppressSeekBarUpdate(false);
                break;
        }
    }
}
