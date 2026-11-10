package com.oplus.zoom;

import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomRootTaskController$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ int f$0;
    public /* synthetic */ boolean f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ boolean f$3;

    public /* synthetic */ ZoomRootTaskController$$ExternalSyntheticLambda5(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((ZoomRootTaskManager) obj).onImePositionChanged(this.f$0, this.f$1, this.f$2, this.f$3);
                break;
            case 1:
                ZoomRootTaskController.lambda$requestChangeZoomState$15(this.f$0, this.f$2, this.f$1, this.f$3, (ZoomRootTaskManager) obj);
                break;
            default:
                ZoomRootTaskController.lambda$requestChangeZoomState$14(this.f$0, this.f$2, this.f$1, this.f$3, (ZoomRootTaskManager) obj);
                break;
        }
    }
}
