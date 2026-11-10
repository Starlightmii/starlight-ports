package com.oplus.zoom.common;

import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.common.DisplayChangeController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class ZoomDisplayController$$ExternalSyntheticLambda0 implements DisplayChangeController.OnDisplayChangingListener {
    public /* synthetic */ ZoomDisplayController f$0;

    public final void onDisplayChange(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        this.f$0.onRotateDisplay(i, i2, i3, displayAreaInfo, windowContainerTransaction);
    }
}
