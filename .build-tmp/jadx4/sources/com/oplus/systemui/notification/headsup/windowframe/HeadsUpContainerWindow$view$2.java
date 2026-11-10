package com.oplus.systemui.notification.headsup.windowframe;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class HeadsUpContainerWindow$view$2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ HeadsUpContainerWindow this$0;

    public /* synthetic */ HeadsUpContainerWindow$view$2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                HeadsUpContainerWindow.access$initExcludeRegion(this.this$0);
                break;
            case 1:
                this.this$0.notifySizeOrLocationChanged(null);
                break;
            default:
                HeadsUpCOUItouchMotionAnimator touchMotion = this.this$0.getTouchMotion();
                if (touchMotion != null) {
                    touchMotion.onConfigChanged();
                }
                this.this$0.notifySizeOrLocationChanged(null);
                break;
        }
    }
}
