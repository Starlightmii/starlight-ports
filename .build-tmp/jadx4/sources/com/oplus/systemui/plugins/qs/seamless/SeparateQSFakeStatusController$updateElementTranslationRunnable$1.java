package com.oplus.systemui.plugins.qs.seamless;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateQSFakeStatusController$updateElementTranslationRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ SeparateQSFakeStatusController this$0;

    public /* synthetic */ SeparateQSFakeStatusController$updateElementTranslationRunnable$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                SeparateQSFakeStatusController separateQSFakeStatusController = this.this$0;
                SeparateQSFakeStatusController.Companion companion = SeparateQSFakeStatusController.Companion;
                separateQSFakeStatusController.updateElementTranslation$1();
                break;
            default:
                SeparateQSFakeStatusController separateQSFakeStatusController2 = this.this$0;
                SeparateQSFakeStatusController.Companion companion2 = SeparateQSFakeStatusController.Companion;
                separateQSFakeStatusController2.updateElementTranslation$1();
                break;
        }
    }
}
