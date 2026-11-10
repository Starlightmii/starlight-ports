package com.oplus.systemui.notification.interruption.wakeup;

import android.util.Pair;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WakeupScreenHelper$$ExternalSyntheticLambda2 implements WakeupScreenHelper.Filter {
    @Override // com.oplus.systemui.notification.interruption.wakeup.WakeupScreenHelper.Filter
    public final Pair shouldWakeup(NotificationEntry notificationEntry) {
        List list = WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST;
        int identifier = notificationEntry.getSbn().getUser().getIdentifier();
        if (identifier == -1 || OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(identifier)) || identifier == KeyguardUpdateMonitor.getCurrentUser()) {
            return (KeyguardUpdateMonitor.getCurrentUser() == 0 || !OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(notificationEntry.getSbn().getUserId()))) ? Pair.create(Boolean.TRUE, null) : Pair.create(Boolean.FALSE, "not avatar");
        }
        return Pair.create(Boolean.FALSE, "isNoCurrentUser");
    }
}
