package com.oplus.systemui.notification.collection.drivingmode;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusDrivingModeManager implements CoreStartable, NotifCollectionListener {
    private final Context context;
    private final NotifPipeline pipeline;

    public OplusDrivingModeManager(Context context, NotifPipeline notifPipeline) {
        this.context = context;
        this.pipeline = notifPipeline;
    }

    public void onEntryAdded(NotificationEntry notificationEntry) {
        DrivingModeHelper.Companion.getInstance().onNotificationPosted$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(notificationEntry.getSbn());
    }

    public void onEntryRemoved(NotificationEntry notificationEntry, int i) {
        DrivingModeHelper.Companion.getInstance().onNotificationRemoved$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(notificationEntry.getSbn());
    }

    public void onEntryUpdated(NotificationEntry notificationEntry) {
        DrivingModeHelper.Companion.getInstance().onNotificationPosted$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(notificationEntry.getSbn());
    }

    public final void start() {
        DrivingModeHelper.Companion.getInstance().init$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(this.context);
        this.pipeline.addCollectionListener(this);
    }
}
