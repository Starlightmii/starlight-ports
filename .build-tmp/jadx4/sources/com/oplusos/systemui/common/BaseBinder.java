package com.oplusos.systemui.common;

import com.android.systemui.shade.NotificationPanelViewController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface BaseBinder {
    default void bind$1(NotificationPanelViewController notificationPanelViewController) {
        setBase(notificationPanelViewController);
    }

    Object getBase();

    void setBase(Object obj);
}
