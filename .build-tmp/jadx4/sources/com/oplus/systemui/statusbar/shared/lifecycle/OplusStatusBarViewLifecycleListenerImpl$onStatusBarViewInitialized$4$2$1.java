package com.oplus.systemui.statusbar.shared.lifecycle;

import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.oplus.systemui.statusbar.seeding.ScaleChangedListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusBarViewLifecycleListenerImpl$onStatusBarViewInitialized$4$2$1 implements ScaleChangedListener {
    public /* synthetic */ OplusStatusBarViewLifecycleListenerImpl this$0;

    @Override // com.oplus.systemui.statusbar.seeding.ScaleChangedListener
    public final void onScaleChanged(float f) {
        OplusStatusBarViewLifecycleListenerImpl oplusStatusBarViewLifecycleListenerImpl = this.this$0;
        if (((OplusSeparateNotificationAndQSEx) oplusStatusBarViewLifecycleListenerImpl.separateNotificationAndQSEx.get()).enableSeparateNotificationAndQS()) {
            ((OplusSeparateNotificationAndQSEx) oplusStatusBarViewLifecycleListenerImpl.separateNotificationAndQSEx.get()).onSystemIconScaleChanged(f);
        }
    }
}
