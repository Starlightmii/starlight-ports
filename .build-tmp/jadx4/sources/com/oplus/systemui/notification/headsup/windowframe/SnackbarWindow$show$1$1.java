package com.oplus.systemui.notification.headsup.windowframe;

import android.widget.FrameLayout;
import com.coui.appcompat.snackbar.COUISnackBar;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SnackbarWindow$show$1$1 implements COUISnackBar.OnStatusChangeListener {
    public /* synthetic */ FrameLayout $windowView;
    public /* synthetic */ SnackbarWindow this$0;

    public final void onDismissed(COUISnackBar cOUISnackBar) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, "SnackbarWindow", "onDismissed.", null, 8);
        this.this$0.getWindowManager().removeView(this.$windowView);
    }

    public final void onShown(COUISnackBar cOUISnackBar) {
    }
}
