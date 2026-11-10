package com.oplus.zoom;

import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomRootTaskController$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ int f$0;

    public /* synthetic */ ZoomRootTaskController$$ExternalSyntheticLambda4(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        int i2 = this.f$0;
        ZoomRootTaskManager zoomRootTaskManager = (ZoomRootTaskManager) obj;
        switch (i) {
            case 0:
                ZoomRootTaskController.lambda$onZoomTaskEvent$13(i2, zoomRootTaskManager);
                break;
            default:
                ZoomRootTaskController.lambda$clearFixRotationFlag$8(i2, zoomRootTaskManager);
                break;
        }
    }
}
