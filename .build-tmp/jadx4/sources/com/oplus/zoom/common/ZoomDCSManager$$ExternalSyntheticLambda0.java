package com.oplus.zoom.common;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomDCSManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ ZoomDCSManager$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((ZoomDCSManager) obj).lambda$initZoomTrack$0();
                break;
            case 1:
                ((ZoomDCSManager.ZoomStateRecorder) obj).lambda$onExitZoomWindow$1();
                break;
            case 2:
                ((ZoomDCSManager.ZoomStateRecorder) obj).lambda$onExitZoomFloatHandle$5();
                break;
            case 3:
                ((ZoomDCSManager.ZoomStateRecorder) obj).lambda$onExitMiniZoomWindow$3();
                break;
            case 4:
                ((ZoomDCSManager.ZoomStateRecorder) obj).lambda$onStartZoomWindow$0();
                break;
            case 5:
                ((ZoomDCSManager.ZoomStateRecorder) obj).lambda$onStartMiniZoomWindow$2();
                break;
            default:
                ((ZoomDCSManager.ZoomStateRecorder) obj).lambda$onStartZoomFloatHandle$4();
                break;
        }
    }
}
