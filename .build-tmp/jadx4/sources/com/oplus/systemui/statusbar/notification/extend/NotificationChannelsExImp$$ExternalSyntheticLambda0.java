package com.oplus.systemui.statusbar.notification.extend;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.provider.Settings;
import com.oplus.systemui.statusbar.notification.power.supersave.OplusSuperSaveControllerImpl;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationChannelsExImp$$ExternalSyntheticLambda0 implements Runnable {
    public /* synthetic */ NotificationChannelsExImp f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ NotificationManager f$2;

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f$1;
        NotificationManager notificationManager = this.f$2;
        NotificationFeatureOption.INSTANCE.getClass();
        if (NotificationFeatureOption.isEnvelopSupport) {
            notificationManager.createNotificationChannel(new NotificationChannel("ENVELOPE", context.getString(2131953160), 4));
        }
        notificationManager.createNotificationChannel(new NotificationChannel("BLOCK_BANNER", context.getString(2131956564), 4));
        NotificationChannel notificationChannel = new NotificationChannel("channel_dnd_notice", context.getString(2131953070), 4);
        notificationChannel.setVibrationPattern(null);
        notificationChannel.setSound(null, null);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.createNotificationChannel(new NotificationChannel("after_sale_mode_notice", context.getString(2131954442), 4));
        NotificationChannel notificationChannel2 = new NotificationChannel("SYSTEM_AVERTOR_NOTICE_CHANNEL", context.getString(2131956752), 4);
        notificationChannel2.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, new AudioAttributes.Builder().setContentType(4).setUsage(5).build());
        notificationChannel2.enableVibration(true);
        notificationChannel2.setVibrationPattern(NotificationChannelsExImp.SYSTEM_AVERTOR_NOTICE_VIBRATION);
        notificationManager.createNotificationChannel(notificationChannel2);
        notificationManager.createNotificationChannel(new NotificationChannel(OplusSuperSaveControllerImpl.UN_STANDARD_CHARGE, context.getString(2131954805), 3));
    }
}
