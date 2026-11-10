package com.oplus.systemui.viewdialog.ui.binder;

import com.oplusos.systemui.common.util.LogUtil;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder$stubDisposableHandle$1 implements DisposableHandle {
    public static final ViewDialogContainerBinder$stubDisposableHandle$1 INSTANCE = new ViewDialogContainerBinder$stubDisposableHandle$1();

    public final void dispose() {
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "the view dialogContainer is null!", null, 8);
    }
}
