package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import com.android.systemui.Dumpable;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.notification.livealert.data.repository.OplusLiveAlertFilterByNotification;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertFilters extends OplusLiveAlertFilter implements IConditionListener, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusLiveAlertFilters";
    private final List<OplusLiveAlertFilter> filters;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusLiveAlertFilters(OplusLiveAlertFilterByNotification oplusLiveAlertFilterByNotification) {
        ArrayList arrayList = new ArrayList();
        this.filters = arrayList;
        oplusLiveAlertFilterByNotification.setConditionListener(this);
        arrayList.add(oplusLiveAlertFilterByNotification);
    }

    public final void addFilter(OplusLiveAlertFilter oplusLiveAlertFilter) {
        oplusLiveAlertFilter.setConditionListener(this);
        this.filters.add(oplusLiveAlertFilter);
        notifyConditionChanged("addFilter");
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public final void clearCache$1() {
        Iterator<T> it = this.filters.iterator();
        while (it.hasNext()) {
            ((OplusLiveAlertFilter) it.next()).clearCache$1();
        }
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("OplusLiveAlertFilters:");
        Iterator<T> it = this.filters.iterator();
        while (it.hasNext()) {
            Dumpable dumpable = (OplusLiveAlertFilter) it.next();
            printWriter.println("\t" + dumpable);
            Dumpable dumpable2 = dumpable instanceof Dumpable ? dumpable : null;
            if (dumpable2 != null) {
                dumpable2.dump(printWriter, strArr);
            }
        }
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public int filterEntry(NotificationEntry notificationEntry) {
        Iterator<T> it = this.filters.iterator();
        int iFilterEntry = 7;
        while (it.hasNext()) {
            iFilterEntry &= ((OplusLiveAlertFilter) it.next()).filterEntry(notificationEntry);
        }
        return iFilterEntry;
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.IConditionListener
    public void onConditionChanged(String str) {
        notifyConditionChanged(str);
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public void removeCache(String str) {
        Iterator<T> it = this.filters.iterator();
        while (it.hasNext()) {
            ((OplusLiveAlertFilter) it.next()).removeCache(str);
        }
    }

    public final void removeFilter(OplusLiveAlertFilter oplusLiveAlertFilter) {
        oplusLiveAlertFilter.setConditionListener(null);
        this.filters.remove(oplusLiveAlertFilter);
        notifyConditionChanged("removeFilter");
    }
}
