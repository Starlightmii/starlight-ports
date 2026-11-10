package com.oplus.zoom;

import android.app.ActivityManager;
import android.view.SurfaceControl;
import com.oplus.zoomwindow.OplusZoomTaskInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda7 implements Runnable {
    public /* synthetic */ ZoomController.OplusZoomTaskListenerImpl f$0;
    public /* synthetic */ OplusZoomTaskInfo f$1;
    public /* synthetic */ ActivityManager.RunningTaskInfo f$2;
    public /* synthetic */ SurfaceControl f$3;

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$onZoomEnter$0(this.f$1, this.f$2, this.f$3);
    }
}
