package com.oplus.systemui.notification.log;

import android.service.notification.StatusBarNotification;
import androidx.room.util.CursorUtil$;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.util.EventLog;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotifiEventLog {
    public static final Companion Companion = new Companion();
    public static final String TYPE_ATTACH_INFO = "attachInfo";
    public static final String TYPE_GROUP = "group";
    public static final String TYPE_INTERRUPT = "interrupt";
    public static final String TYPE_NOTIFICATION_HASH = "hash";
    private final EventLog eventLog;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusNotifiEventLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }

    public final void log(String str, Object... objArr) {
        this.eventLog.writeEvent(36009, new Object[]{str, objArr});
    }

    public final void logInitNotification(ListEntry listEntry) {
        StatusBarNotification sbn;
        EventLog eventLog = this.eventLog;
        String strLogKey = NotificationUtils.logKey(listEntry);
        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
        eventLog.writeEvent(36009, new Object[]{CursorUtil$.ExternalSyntheticOutline0.m("hash:", strLogKey, ":", (representativeEntry == null || (sbn = representativeEntry.getSbn()) == null) ? null : sbn.getOpPkg()), Integer.valueOf(NotificationUtils.logKey(listEntry).hashCode())});
    }

    public final void logNotification(String str, ListEntry listEntry, String str2) {
        this.eventLog.writeEvent(36009, new Object[]{PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(NotificationUtils.logKey(listEntry).hashCode(), str, ":"), str2});
    }
}
