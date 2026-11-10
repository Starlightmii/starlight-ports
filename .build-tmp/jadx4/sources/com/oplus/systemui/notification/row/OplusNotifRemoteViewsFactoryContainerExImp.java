package com.oplus.systemui.notification.row;

import com.android.systemui.statusbar.notification.row.NotifRemoteViewsFactory;
import com.android.systemui.statusbar.notification.row.OplusNotifRemoteViewsFactoryContainerEx;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotifRemoteViewsFactoryContainerExImp implements OplusNotifRemoteViewsFactoryContainerEx {
    private final OplusNotificationDateTimeViewFactory factoryContainer;

    public OplusNotifRemoteViewsFactoryContainerExImp(OplusNotificationDateTimeViewFactory oplusNotificationDateTimeViewFactory) {
        this.factoryContainer = oplusNotificationDateTimeViewFactory;
    }

    public void addExtraFactory(Set<NotifRemoteViewsFactory> set) {
        set.add(this.factoryContainer);
    }
}
