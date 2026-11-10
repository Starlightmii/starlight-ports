package com.oplus.systemui.notification.livealert.data.repository;

import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.util.time.SystemClock;
import com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertFilterByNotification extends OplusLiveAlertFilter implements Dumpable {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusLiveAlertFilterByNotification";
    private final SystemClock mSystemClock;
    private final List<NotifFilter> notificationFilters = new ArrayList();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusLiveAlertFilterByNotification(SystemClock systemClock) {
        this.mSystemClock = systemClock;
    }

    public final void addNotificationFilter(NotifFilter notifFilter) {
        this.notificationFilters.add(notifFilter);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("\t\tOplusLiveAlertFilterByNotification filter size: ", this.notificationFilters.size(), printWriter);
        for (NotifFilter notifFilter : this.notificationFilters) {
            printWriter.println("\t\t\t" + notifFilter.getName() + ": " + notifFilter);
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public int filterEntry(NotificationEntry notificationEntry) {
        this.mSystemClock.getClass();
        long jElapsedRealtime = android.os.SystemClock.elapsedRealtime();
        for (NotifFilter notifFilter : this.notificationFilters) {
            if (notifFilter.shouldFilterOut(notificationEntry, jElapsedRealtime)) {
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    return 0;
                }
                Log.d("SystemUi--Notification", "OplusLiveAlertFilterByNotification-->".concat(notificationEntry.getSbn().getKey() + " filter out by: " + notifFilter.getName()));
                return 0;
            }
        }
        return 7;
    }
}
