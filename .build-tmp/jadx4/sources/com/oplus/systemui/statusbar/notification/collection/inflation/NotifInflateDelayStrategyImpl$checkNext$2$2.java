package com.oplus.systemui.statusbar.notification.collection.inflation;

import android.view.Choreographer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotifInflateDelayStrategyImpl$checkNext$2$2 implements Choreographer.FrameCallback {
    public /* synthetic */ Runnable $task;
    public /* synthetic */ NotifInflateDelayStrategyImpl this$0;

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        this.$task.run();
        this.this$0.checkNext();
    }
}
