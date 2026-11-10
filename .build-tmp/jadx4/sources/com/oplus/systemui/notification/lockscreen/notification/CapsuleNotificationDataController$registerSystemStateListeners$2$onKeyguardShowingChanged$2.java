package com.oplus.systemui.notification.lockscreen.notification;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationDataController$registerSystemStateListeners$2$onKeyguardShowingChanged$2 implements Runnable {
    public /* synthetic */ CapsuleNotificationDataController this$0;

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.hasEnterKeyguard = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->after 3s, hasEnterKeyguard = false");
        }
    }
}
