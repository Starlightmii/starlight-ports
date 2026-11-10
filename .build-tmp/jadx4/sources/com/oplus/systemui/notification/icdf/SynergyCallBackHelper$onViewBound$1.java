package com.oplus.systemui.notification.icdf;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SynergyCallBackHelper$onViewBound$1 implements Runnable {
    public /* synthetic */ NotificationEntry $entry;
    public /* synthetic */ boolean $onGoing;
    public /* synthetic */ boolean $pinned;
    public /* synthetic */ int $state;
    public /* synthetic */ SynergyCallBackHelper this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.getIcdfNotificationHelper().onAddNotification(this.$entry, this.$state, this.$pinned, this.$onGoing, null);
        SynergyCallBackHelper.access$updateRanking(this.this$0);
    }
}
