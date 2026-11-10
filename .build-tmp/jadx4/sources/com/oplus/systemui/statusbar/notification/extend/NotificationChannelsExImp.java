package com.oplus.systemui.statusbar.notification.extend;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import com.android.systemui.statusbar.notification.collection.NotificationChannelsEx;
import com.android.systemui.util.NotificationChannels;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NotificationChannelsExImp implements NotificationChannelsEx {
    public static final long[] SYSTEM_AVERTOR_NOTICE_VIBRATION = {100, 200, 300};

    public final NotificationChannel buildNewBatteryChannel(Context context, NotificationManager notificationManager) {
        notificationManager.deleteNotificationChannel(NotificationChannels.BATTERY);
        NotificationChannels.BATTERY = "BAT_NEW";
        NotificationChannel notificationChannel = new NotificationChannel("BAT_NEW", context.getString(2131954831), 5);
        notificationChannel.setSound(null, null);
        notificationChannel.setBlockable(true);
        return notificationChannel;
    }

    public final void createAll(Context context, NotificationManager notificationManager) {
        OplusUiOffloadThread oplusUiOffloadThread = OplusUiOffloadThread.getInstance();
        NotificationChannelsExImp$$ExternalSyntheticLambda0 notificationChannelsExImp$$ExternalSyntheticLambda0 = new NotificationChannelsExImp$$ExternalSyntheticLambda0();
        notificationChannelsExImp$$ExternalSyntheticLambda0.f$0 = this;
        notificationChannelsExImp$$ExternalSyntheticLambda0.f$1 = context;
        notificationChannelsExImp$$ExternalSyntheticLambda0.f$2 = notificationManager;
        VarHandle.storeStoreFence();
        oplusUiOffloadThread.runOnSubThread(notificationChannelsExImp$$ExternalSyntheticLambda0);
    }
}
