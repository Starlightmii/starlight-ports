package com.oplus.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusMediaCoordinator$$ExternalSyntheticLambda0 implements Function0 {
    public /* synthetic */ OplusMediaCoordinator f$0;

    public final Object invoke() {
        final OplusMediaCoordinator oplusMediaCoordinator = this.f$0;
        return new NotifSectioner() { // from class: com.oplus.systemui.statusbar.notification.collection.coordinator.OplusMediaCoordinator$mediaNotifSectioner$2$1
            {
                super("OplusMedia", 1);
            }

            public NotifComparator getComparator() {
                return new OplusMediaCoordinator$mediaNotifSectioner$2$1$getComparator$1("OplusMedia");
            }

            public final boolean isInSection(PipelineEntry pipelineEntry) {
                ListEntry listEntryAsListEntry;
                return oplusMediaCoordinator.notificationMediaManager.getOplusNotificationMediaManagerExt().isMediaNotification((pipelineEntry == null || (listEntryAsListEntry = pipelineEntry.asListEntry()) == null) ? null : listEntryAsListEntry.getRepresentativeEntry());
            }
        };
    }
}
