package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusLiveAlertFilter {
    private IConditionListener listener;

    public void clearCache$1() {
    }

    public abstract int filterEntry(NotificationEntry notificationEntry);

    public final void notifyConditionChanged(String str) {
        IConditionListener iConditionListener = this.listener;
        if (iConditionListener != null) {
            iConditionListener.onConditionChanged(str);
        }
    }

    public void removeCache(String str) {
    }

    public final void setConditionListener(IConditionListener iConditionListener) {
        this.listener = iConditionListener;
    }
}
