package com.oplus.zoom;

import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomRootTaskController$$ExternalSyntheticLambda16 implements Consumer {
    public /* synthetic */ int f$0;
    public /* synthetic */ int f$1;
    public /* synthetic */ int f$2;
    public /* synthetic */ DisplayAreaInfo f$3;
    public /* synthetic */ WindowContainerTransaction f$4;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((ZoomRootTaskManager) obj).onRotateDisplay(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
