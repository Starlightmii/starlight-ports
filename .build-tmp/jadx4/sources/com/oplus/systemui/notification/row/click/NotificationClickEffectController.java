package com.oplus.systemui.notification.row.click;

import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationClickEffectController {
    private ExpandableNotificationRow effectingRow;

    public final ExpandableNotificationRow getEffectingRow() {
        return this.effectingRow;
    }

    public final void setEffectingRow(ExpandableNotificationRow expandableNotificationRow) {
        this.effectingRow = expandableNotificationRow;
    }

    public void startClickAnimation(ExpandableNotificationRow expandableNotificationRow, boolean z, boolean z2, String str, Runnable runnable) {
    }
}
