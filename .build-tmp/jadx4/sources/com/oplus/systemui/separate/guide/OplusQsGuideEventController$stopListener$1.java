package com.oplus.systemui.separate.guide;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideEventController$stopListener$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusQsGuideEventController this$0;

    public /* synthetic */ OplusQsGuideEventController$stopListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.keyguardUpdateMonitor.removeCallback(this.this$0.getKeyguardUpdateMonitorCallback());
                break;
            default:
                this.this$0.keyguardUpdateMonitor.registerCallback(this.this$0.getKeyguardUpdateMonitorCallback());
                break;
        }
    }
}
