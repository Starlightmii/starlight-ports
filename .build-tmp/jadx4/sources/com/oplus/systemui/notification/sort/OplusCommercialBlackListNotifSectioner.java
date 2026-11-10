package com.oplus.systemui.notification.sort;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.oplusos.systemui.notification.commercialize.CommercialBlackListManager;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCommercialBlackListNotifSectioner extends NotifSectioner {
    private final CommercialBlackListManager blackListManager;

    public OplusCommercialBlackListNotifSectioner(Context context) {
        super("commercialBlackList", 20);
        this.blackListManager = CommercialBlackListManager.Companion.getInstance(context);
    }

    public boolean isInSection(PipelineEntry pipelineEntry) {
        ListEntry listEntryAsListEntry;
        NotificationEntry representativeEntry;
        StatusBarNotification sbn;
        if (pipelineEntry == null || (listEntryAsListEntry = pipelineEntry.asListEntry()) == null || (representativeEntry = listEntryAsListEntry.getRepresentativeEntry()) == null || (sbn = representativeEntry.getSbn()) == null) {
            return false;
        }
        return this.blackListManager.isInBlackList(sbn);
    }
}
