package com.oplus.systemui.notification.sort;

import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifComparator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PinTopCoordinator$fixedPinTopNotifSectioner$1 extends NotifSectioner {
    public final /* synthetic */ PinTopCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PinTopCoordinator$fixedPinTopNotifSectioner$1(PinTopCoordinator pinTopCoordinator) {
        super("Pintop(Fixed)", 21);
        this.this$0 = pinTopCoordinator;
    }

    public final int findTopIndex(PipelineEntry pipelineEntry) {
        ListEntry listEntryAsListEntry;
        NotificationEntry representativeEntry;
        StatusBarNotification sbn;
        if (pipelineEntry == null || (listEntryAsListEntry = pipelineEntry.asListEntry()) == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null || (sbn = representativeEntry.getSbn()) == null) {
            return -1;
        }
        return this.this$0.fixedPinTopWhiteList.indexOf(new Pair(sbn.getPackageName(), String.valueOf(sbn.getId())));
    }

    public NotifComparator getComparator() {
        return new NotifComparator() { // from class: com.oplus.systemui.notification.sort.PinTopCoordinator$fixedPinTopNotifSectioner$1$getComparator$1
            {
                super("PinTopCoordinator");
            }

            public final int compare(PipelineEntry pipelineEntry, PipelineEntry pipelineEntry2) {
                return this.this$0.findTopIndex(pipelineEntry) - this.this$0.findTopIndex(pipelineEntry2);
            }
        };
    }

    public final boolean isInSection(PipelineEntry pipelineEntry) {
        return (pipelineEntry == null || findTopIndex(pipelineEntry) == -1) ? false : true;
    }
}
