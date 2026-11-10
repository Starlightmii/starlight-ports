package com.oplus.systemui.qs;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSCarrierTextController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ boolean f$1;

    public /* synthetic */ OplusQSCarrierTextController$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OplusQSCarrierTextController.$r8$lambda$TsvvTFF76CaCVfAEBQ3hplPVLK8((OplusQSCarrierTextController) this.f$0, this.f$1);
                break;
            default:
                ((OplusQSCarrierTextController.CarrierTextCallback) this.f$0).setVisible(this.f$1);
                break;
        }
    }
}
