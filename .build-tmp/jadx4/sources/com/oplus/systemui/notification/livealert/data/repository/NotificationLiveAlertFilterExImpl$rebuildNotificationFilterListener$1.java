package com.oplus.systemui.notification.livealert.data.repository;

import com.oplus.systemui.notification.livealertfilter.NotificationLiveAlertFilterEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationLiveAlertFilterExImpl$rebuildNotificationFilterListener$1 implements NotificationLiveAlertFilterEx.RebuildNotificationFilterListener {
    public /* synthetic */ NotificationLiveAlertFilterExImpl this$0;

    @Override // com.oplus.systemui.notification.livealertfilter.NotificationLiveAlertFilterEx.RebuildNotificationFilterListener
    public final void onRebuildNotificationFilter(String str) {
        this.this$0.oplusLiveAlertFilterByNotification.notifyConditionChanged(str);
    }
}
