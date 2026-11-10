package com.oplus.systemui.notification.interruption;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.FullScreenIntentDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusInterrupt {
    public FullScreenIntentDecisionProvider.DecisionImpl getExtendFullScreenIntentDecisionBeforeYes(NotificationEntry notificationEntry) {
        return null;
    }

    public FullScreenIntentDecisionProvider.DecisionImpl getExtendFullScreenIntentDecisionLast(NotificationEntry notificationEntry) {
        return null;
    }

    public void logDecision(VisualInterruptionType visualInterruptionType, NotificationEntry notificationEntry, VisualInterruptionDecisionProvider.Decision decision) {
    }
}
