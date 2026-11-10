package com.oplus.systemui.notification.smallreply;

import android.util.Log;
import android.widget.Toast;
import com.oplusos.systemui.common.receiver.AbstractReceiver;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationActionReceiver extends AbstractReceiver {
    public static volatile NotificationActionReceiver sInstance;
    public Toast mToast;

    public static NotificationActionReceiver getInstance() {
        if (sInstance == null) {
            synchronized (NotificationActionReceiver.class) {
                try {
                    if (sInstance == null) {
                        NotificationActionReceiver notificationActionReceiver = new NotificationActionReceiver();
                        Log.i("NotificationActionReceiver", "NotificationActionReceiver: addListener");
                        NotificationActionReceiver$$ExternalSyntheticLambda0 notificationActionReceiver$$ExternalSyntheticLambda0 = new NotificationActionReceiver$$ExternalSyntheticLambda0();
                        notificationActionReceiver$$ExternalSyntheticLambda0.f$0 = notificationActionReceiver;
                        VarHandle.storeStoreFence();
                        notificationActionReceiver.addListener(notificationActionReceiver$$ExternalSyntheticLambda0);
                        sInstance = notificationActionReceiver;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    @Override // com.oplusos.systemui.common.receiver.AbstractReceiver
    public String[] getActions() {
        return new String[]{"oppo.intent.action.NOTIFICATION_ACTION_CLICK"};
    }
}
