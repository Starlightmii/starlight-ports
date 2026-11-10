package com.oplus.systemui.qs;

import com.android.keyguard.KeyguardUpdateMonitor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSCarrierTextController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQSCarrierTextController f$0;

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        OplusQSCarrierTextController oplusQSCarrierTextController = this.f$0;
        switch (i) {
            case 0:
                KeyguardUpdateMonitor keyguardUpdateMonitor = oplusQSCarrierTextController.mKeyguardUpdateMonitor;
                if (keyguardUpdateMonitor != null) {
                    keyguardUpdateMonitor.registerCallback(oplusQSCarrierTextController.mCallback);
                }
                break;
            default:
                KeyguardUpdateMonitor keyguardUpdateMonitor2 = oplusQSCarrierTextController.mKeyguardUpdateMonitor;
                if (keyguardUpdateMonitor2 != null) {
                    keyguardUpdateMonitor2.removeCallback(oplusQSCarrierTextController.mCallback);
                }
                break;
        }
    }
}
