package com.oplus.systemui.plugins.qs.util;

import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewLifecycleOwner$windowVisibleListener$1 implements ViewTreeObserver.OnWindowVisibilityChangeListener {
    public /* synthetic */ ViewLifecycleOwner this$0;

    @Override // android.view.ViewTreeObserver.OnWindowVisibilityChangeListener
    public final void onWindowVisibilityChanged(int i) {
        this.this$0.updateState$4$1();
    }
}
