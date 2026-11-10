package com.oplus.systemui.statusbar.phone;

import com.android.systemui.statusbar.OlockStateUtil;
import com.android.systemui.statusbar.phone.ScrimController;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimControllerExImp$olockStateCallback$1 implements OlockStateUtil.IOlockStateChangedCallback {
    public /* synthetic */ ScrimControllerExImp this$0;

    public final void onOlockStateChanged(boolean z) {
        ScrimControllerExImp scrimControllerExImp = this.this$0;
        ScrimControllerExImp.Companion companion = ScrimControllerExImp.Companion;
        ScrimController scrimController = scrimControllerExImp.getScrimController();
        if (scrimController != null) {
            ScrimControllerExImp$handleGaussBlur$runnable$1 scrimControllerExImp$handleGaussBlur$runnable$1 = new ScrimControllerExImp$handleGaussBlur$runnable$1(1);
            scrimControllerExImp$handleGaussBlur$runnable$1.this$0 = scrimController;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(scrimControllerExImp$handleGaussBlur$runnable$1);
        }
    }
}
