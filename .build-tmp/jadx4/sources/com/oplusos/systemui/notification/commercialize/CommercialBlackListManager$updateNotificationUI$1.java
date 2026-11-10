package com.oplusos.systemui.notification.commercialize;

import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CommercialBlackListManager$updateNotificationUI$1 implements Runnable {
    public static final CommercialBlackListManager$updateNotificationUI$1 INSTANCE = new CommercialBlackListManager$updateNotificationUI$1();

    @Override // java.lang.Runnable
    public final void run() {
        ((ShadeListBuilder) DependencyEx.sDependency.getDependency(ShadeListBuilder.class)).rebuildListIfBefore(1);
    }
}
