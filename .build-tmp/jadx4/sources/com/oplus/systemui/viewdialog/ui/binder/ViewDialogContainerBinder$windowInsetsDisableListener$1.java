package com.oplus.systemui.viewdialog.ui.binder;

import android.view.View;
import android.view.WindowInsets;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewDialogContainerBinder$windowInsetsDisableListener$1 implements View.OnApplyWindowInsetsListener {
    public static final ViewDialogContainerBinder$windowInsetsDisableListener$1 INSTANCE = new ViewDialogContainerBinder$windowInsetsDisableListener$1();

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        LogUtil.i$default(LogUtil.TAG_QS, "ViewDialogContainerBinder", "windowInsetsListener: edge-to-edge, padding cleared", null, 8);
        view.setPadding(0, 0, 0, 0);
        return WindowInsets.CONSUMED;
    }
}
