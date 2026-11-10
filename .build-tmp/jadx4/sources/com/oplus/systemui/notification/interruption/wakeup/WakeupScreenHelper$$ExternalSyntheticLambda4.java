package com.oplus.systemui.notification.interruption.wakeup;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class WakeupScreenHelper$$ExternalSyntheticLambda4 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        NotifFilter excludingFilter;
        NotificationEntry notificationEntry = (NotificationEntry) obj;
        List list = WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST;
        return (notificationEntry == null || (excludingFilter = notificationEntry.getExcludingFilter()) == null || !"KeyguardCoordinator".equals(excludingFilter.getName())) ? false : true;
    }
}
