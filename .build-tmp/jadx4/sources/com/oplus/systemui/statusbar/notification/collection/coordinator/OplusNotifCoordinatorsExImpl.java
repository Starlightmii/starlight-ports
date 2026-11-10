package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.OplusNotifCoordinatorsEx;
import com.android.systemui.statusbar.notification.collection.coordinator.Coordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import dagger.Lazy;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotifCoordinatorsExImpl extends OplusNotifCoordinatorsEx {
    private final Lazy groupSummaryCoordinator;
    private final Lazy liveAlertNotificationCoordinator;
    private final Lazy lockScreenNotificationCoordinator;
    private final Lazy notificationTopCoordinator;
    private final Lazy oplusMediaCoordinator;
    private final Lazy privacyCoordinator;
    private final Lazy superPowerSaveCoordinator;
    private final Lazy visualStabilityCoordinator;

    public OplusNotifCoordinatorsExImpl(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8) {
        this.privacyCoordinator = lazy;
        this.superPowerSaveCoordinator = lazy2;
        this.visualStabilityCoordinator = lazy3;
        this.oplusMediaCoordinator = lazy4;
        this.lockScreenNotificationCoordinator = lazy5;
        this.notificationTopCoordinator = lazy6;
        this.groupSummaryCoordinator = lazy7;
        this.liveAlertNotificationCoordinator = lazy8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void init(List<Coordinator> list, List<NotifSectioner> list2, RankingCoordinator rankingCoordinator) {
        list.add(this.privacyCoordinator.get());
        list.add(this.superPowerSaveCoordinator.get());
        list.add(this.visualStabilityCoordinator.get());
        list.add(this.oplusMediaCoordinator.get());
        list.add(this.lockScreenNotificationCoordinator.get());
        list.add(this.notificationTopCoordinator.get());
        list.add(this.groupSummaryCoordinator.get());
        list.add(0, this.liveAlertNotificationCoordinator.get());
    }
}
