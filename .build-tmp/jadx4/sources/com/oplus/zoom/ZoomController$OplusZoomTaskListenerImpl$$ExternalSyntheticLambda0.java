package com.oplus.zoom;

import android.view.IRemoteAnimationFinishedCallback;
import android.view.RemoteAnimationTarget;
import com.oplus.zoomwindow.IOplusZoomTaskInfoCallback;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ ZoomController.OplusZoomTaskListenerImpl f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ Object f$2;

    public /* synthetic */ ZoomController$OplusZoomTaskListenerImpl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.f$0.lambda$getCurrentZoomInfo$7(this.f$1, (IOplusZoomTaskInfoCallback) this.f$2);
                break;
            case 1:
                this.f$0.lambda$onRemoteTransitionStart$6(this.f$1, (RemoteAnimationTarget[]) this.f$2);
                break;
            default:
                this.f$0.lambda$onRemoteTransitionCancel$8(this.f$1, (IRemoteAnimationFinishedCallback) this.f$2);
                break;
        }
    }
}
