package com.oplus.systemui.notification.headsup.nssl;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusHeadsUpRowChanger {
    void setHeadsUpDismissed(NotificationEntry notificationEntry);

    void setHeadsUpShowing(NotificationEntry notificationEntry, Function0 function0);

    void setTempHeadsUpViewVisibility(int i);
}
