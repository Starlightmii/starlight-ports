package com.oplus.zoom;

import android.content.res.Configuration;
import com.oplus.zoomwindow.OplusZoomTaskInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomController$ZoomImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;
    public /* synthetic */ Object f$1;

    public /* synthetic */ ZoomController$ZoomImpl$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((ZoomController.ZoomImpl) this.f$0).lambda$onConfigChanged$2((Configuration) this.f$1);
                break;
            default:
                ((ZoomController.OplusZoomTaskListenerImpl) this.f$0).lambda$onZoomTaskChanged$1((OplusZoomTaskInfo) this.f$1);
                break;
        }
    }
}
