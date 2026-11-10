package com.oplus.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationStackScrollLayoutExtImpl$updatePivot$1$1 implements Runnable {
    public /* synthetic */ NotificationStackScrollLayout $it;

    @Override // java.lang.Runnable
    public final void run() {
        NotificationStackScrollLayout notificationStackScrollLayout = this.$it;
        notificationStackScrollLayout.setPivotX(notificationStackScrollLayout.getWidth() / 2.0f);
        NotificationStackScrollLayout notificationStackScrollLayout2 = this.$it;
        notificationStackScrollLayout2.setPivotY(notificationStackScrollLayout2.getHeight() / 2.0f);
    }
}
