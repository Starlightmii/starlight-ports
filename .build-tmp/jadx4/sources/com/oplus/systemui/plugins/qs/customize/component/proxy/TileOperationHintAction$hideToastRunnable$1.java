package com.oplus.systemui.plugins.qs.customize.component.proxy;

import android.animation.Animator;
import com.android.systemui.plugins.ToastPlugin;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileOperationHintAction$hideToastRunnable$1 implements Runnable {
    public /* synthetic */ TileOperationHintAction this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Animator outAnimation;
        ToastPlugin.Toast toast = this.this$0.systemUIToast;
        if (toast == null || (outAnimation = toast.getOutAnimation()) == null) {
            return;
        }
        TileOperationHintAction tileOperationHintAction = this.this$0;
        outAnimation.start();
        outAnimation.addListener(new 1.1(tileOperationHintAction));
    }
}
