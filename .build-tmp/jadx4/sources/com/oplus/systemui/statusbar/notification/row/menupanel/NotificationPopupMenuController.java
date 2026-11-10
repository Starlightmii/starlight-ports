package com.oplus.systemui.statusbar.notification.row.menupanel;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationPopupMenuController {
    public static /* synthetic */ void dismissPopupMenu$default(NotificationPopupMenuController notificationPopupMenuController, String str, boolean z, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        notificationPopupMenuController.dismissPopupMenu(str, z, null);
    }

    public void dismissPopupMenu(String str, boolean z, Function0 function0) {
    }

    public boolean isPopupMenuShowing() {
        return false;
    }

    public void showPopupMenu(boolean z, boolean z2, ExpandableNotificationRow expandableNotificationRow, boolean z3, Function0 function0) {
    }
}
