package com.oplus.systemui.statusbar.notification;

import androidx.appcompat.widget.AppCompatButton;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCloseAllController$statusBarStateListener$1 implements StatusBarStateController.StateListener {
    public /* synthetic */ OplusCloseAllController this$0;

    public final void onStateChanged(int i) {
        OplusCloseAllController oplusCloseAllController = this.this$0;
        if (i == 2) {
            AppCompatButton appCompatButton = oplusCloseAllController.oplusCloseAllButton;
            if (appCompatButton != null) {
                appCompatButton.setAlpha(1.0f);
            }
            AppCompatButton appCompatButton2 = oplusCloseAllController.oplusCloseAllButton;
            if (appCompatButton2 != null) {
                appCompatButton2.setVisibility(0);
            }
        } else {
            AppCompatButton appCompatButton3 = oplusCloseAllController.oplusCloseAllButton;
            if (appCompatButton3 != null) {
                appCompatButton3.setVisibility(4);
            }
        }
        if (ScrimUtil.isBPAnimLevel() && i == 2) {
            OplusCloseAllController.updatePlatformBlurDrawable$default(oplusCloseAllController);
        }
    }
}
