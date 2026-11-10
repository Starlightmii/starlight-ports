package com.oplus.systemui.notification.extend;

import com.android.systemui.notification.NotificationEx;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusNotificationEx extends NotificationEx {
    default boolean isOplusLogic() {
        return true;
    }
}
