package com.oplus.systemui.notification.avater;

import android.content.Context;
import android.os.Handler;
import android.os.UserHandle;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusAvaterNotificationManager implements CoreStartable, NotifCollectionListener {
    private final Context context;
    private final NotifPipeline pipeline;

    public OplusAvaterNotificationManager(NotifPipeline notifPipeline, Context context) {
        this.pipeline = notifPipeline;
        this.context = context;
    }

    public void onEntryAdded(NotificationEntry notificationEntry) {
        SystemAvaterNotificationHelper.getInstance(this.context).checkAndShowSystemAvatarNotification(this.context, notificationEntry.getSbn());
    }

    public void onEntryUpdated(NotificationEntry notificationEntry) {
        SystemAvaterNotificationHelper.getInstance(this.context).checkAndShowSystemAvatarNotification(this.context, notificationEntry.getSbn());
    }

    public final void start() {
        SystemAvaterShareStateObserver.getInstance().registerForUserSwitch(this.context, -1, false);
        SystemAvaterReceiver.getInstance().register(this.context, UserHandle.ALL, (Handler) null);
        this.pipeline.addCollectionListener(this);
    }
}
