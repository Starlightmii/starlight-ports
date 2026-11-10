package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutExtImpl$runAfterExpansionAnimationFinished$runOnEnd$1 implements Runnable {
    public /* synthetic */ ExpandableNotificationRow $changedRow;
    public /* synthetic */ NotificationStackScrollLayoutExtImpl this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.mPendingExpansionChangeRunnables.remove(this.$changedRow);
        this.$changedRow.onFinishedExpansionChange();
    }
}
