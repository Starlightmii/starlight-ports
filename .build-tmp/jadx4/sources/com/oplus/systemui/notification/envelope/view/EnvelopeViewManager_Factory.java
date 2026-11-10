package com.oplus.systemui.notification.envelope.view;

import android.content.Context;
import android.os.Handler;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class EnvelopeViewManager_Factory implements Factory {
    public static EnvelopeViewManager newInstance(Context context, Handler handler, NotificationDependOnKeyguard notificationDependOnKeyguard) {
        return new EnvelopeViewManager(context, handler, notificationDependOnKeyguard);
    }
}
