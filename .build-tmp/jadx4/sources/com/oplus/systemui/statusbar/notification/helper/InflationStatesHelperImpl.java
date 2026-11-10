package com.oplus.systemui.statusbar.notification.helper;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.helper.InflationStatesHelper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InflationStatesHelperImpl extends InflationStatesHelper {
    private final Map<NotificationEntry, Integer> mInflationStates = new HashMap();

    public Integer getEntryInflationState(NotificationEntry notificationEntry) {
        return this.mInflationStates.get(notificationEntry);
    }

    public void putEntryInflationState(NotificationEntry notificationEntry, int i) {
        this.mInflationStates.put(notificationEntry, Integer.valueOf(i));
    }

    public void removeEntryInflationState(NotificationEntry notificationEntry) {
        this.mInflationStates.remove(notificationEntry);
    }
}
