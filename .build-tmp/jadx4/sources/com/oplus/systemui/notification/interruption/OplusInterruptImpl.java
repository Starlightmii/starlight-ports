package com.oplus.systemui.notification.interruption;

import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.FullScreenIntentDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.oplus.systemui.notification.log.OplusNotificationInfoCollection;
import com.oplus.systemui.statusbar.notification.helper.HeadsUpHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import dagger.Lazy;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusInterruptImpl extends OplusInterrupt {
    private final Lazy headsUpHelper;
    private final OplusNotificationInfoCollection infoCollection;

    public OplusInterruptImpl(Lazy lazy, OplusNotificationInfoCollection oplusNotificationInfoCollection) {
        this.headsUpHelper = lazy;
        this.infoCollection = oplusNotificationInfoCollection;
    }

    @Override // com.oplus.systemui.notification.interruption.OplusInterrupt
    public FullScreenIntentDecisionProvider.DecisionImpl getExtendFullScreenIntentDecisionBeforeYes(NotificationEntry notificationEntry) {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isDragonfly() || !DeviceStateController.getInstance().isFolding()) {
            return null;
        }
        HeadsUpHelper headsUpHelper = (HeadsUpHelper) this.headsUpHelper.get();
        StatusBarNotification sbn = notificationEntry.getSbn();
        headsUpHelper.getClass();
        if (sbn != null && "com.tencent.mm".equals(sbn.getPackageName()) && headsUpHelper.isThirdPartInCallNotification(sbn)) {
            return FullScreenIntentDecisionProvider.DecisionImpl.NO_FSI_IS_WECHAT_CALL_FOLDING;
        }
        HeadsUpHelper headsUpHelper2 = (HeadsUpHelper) this.headsUpHelper.get();
        StatusBarNotification sbn2 = notificationEntry.getSbn();
        headsUpHelper2.getClass();
        if (sbn2 == null || sbn2.getNotification().fullScreenIntent == null || !"call".equals(sbn2.getNotification().category) || !"com.google.android.dialer".equals(sbn2.getPackageName())) {
            return null;
        }
        return FullScreenIntentDecisionProvider.DecisionImpl.NO_FSI_IS_GOOGLE_CALL_FOLDING;
    }

    @Override // com.oplus.systemui.notification.interruption.OplusInterrupt
    public FullScreenIntentDecisionProvider.DecisionImpl getExtendFullScreenIntentDecisionLast(NotificationEntry notificationEntry) {
        if (((HeadsUpHelper) this.headsUpHelper.get()).suppressForThirdPartInCall(notificationEntry.getSbn())) {
            return FullScreenIntentDecisionProvider.DecisionImpl.FSI_EXPECTED_NOT_TO_HUN_THIRD_CALL;
        }
        return null;
    }

    @Override // com.oplus.systemui.notification.interruption.OplusInterrupt
    public void logDecision(VisualInterruptionType visualInterruptionType, NotificationEntry notificationEntry, VisualInterruptionDecisionProvider.Decision decision) {
        if (visualInterruptionType == VisualInterruptionType.BUBBLE || visualInterruptionType == VisualInterruptionType.PULSE) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (decision.getShouldInterrupt()) {
            linkedHashMap.put(visualInterruptionType.name(), "on|" + decision.getLogReason());
        } else {
            linkedHashMap.put(visualInterruptionType.name(), "off|" + decision.getLogReason());
        }
        this.infoCollection.addInterruptInfo(notificationEntry, linkedHashMap);
    }
}
