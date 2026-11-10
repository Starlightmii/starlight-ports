package com.oplus.systemui.notification.data;

import android.content.Context;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.oplus.systemui.notification.flashlight.FlashlightSeedlingChecker;
import dagger.Lazy;
import dagger.internal.Factory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationListenerDelayImpl_Factory implements Factory {
    public static NotificationListenerDelayImpl newInstance(Context context, Lazy lazy, FlashlightSeedlingChecker flashlightSeedlingChecker, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9) {
        return new NotificationListenerDelayImpl(context, lazy, flashlightSeedlingChecker, visualInterruptionDecisionProvider, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9);
    }
}
