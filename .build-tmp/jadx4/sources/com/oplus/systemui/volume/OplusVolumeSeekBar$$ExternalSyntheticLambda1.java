package com.oplus.systemui.volume;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusVolumeSeekBar$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusVolumeSeekBar f$0;
    public /* synthetic */ int f$1;

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.mTransType = this.f$1;
                break;
            default:
                this.f$0.mThickType = this.f$1;
                break;
        }
    }
}
