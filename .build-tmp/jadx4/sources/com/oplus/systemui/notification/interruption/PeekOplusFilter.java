package com.oplus.systemui.notification.interruption;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import kotlin.collections.SetsKt__SetsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PeekOplusFilter extends VisualInterruptionFilter {
    private final HeadsUpHelper headsUpHelper;
    private String reason;

    public PeekOplusFilter(HeadsUpHelper headsUpHelper) {
        super(SetsKt__SetsJVMKt.setOf(VisualInterruptionType.PEEK), "oplus reason");
        this.headsUpHelper = headsUpHelper;
        this.reason = "oplus reason";
    }

    public String getReason() {
        return this.reason;
    }

    public boolean shouldSuppress(NotificationEntry notificationEntry) {
        if (notificationEntry.getImportance() < 4) {
            this.reason = "suppress by not important enough";
            return true;
        }
        if (this.headsUpHelper.suppress(notificationEntry.getSbn())) {
            this.reason = "suppress by headsUp suppress";
            return true;
        }
        if (this.headsUpHelper.getImportance(notificationEntry) < 4) {
            this.reason = "suppress by small app not important enough";
            return true;
        }
        if (!this.headsUpHelper.suppressForThirdPartInCall(notificationEntry.getSbn())) {
            return false;
        }
        this.reason = "suppress by thirdPartInCall";
        return true;
    }
}
