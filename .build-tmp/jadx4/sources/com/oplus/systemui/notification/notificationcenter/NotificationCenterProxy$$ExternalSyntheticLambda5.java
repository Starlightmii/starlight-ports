package com.oplus.systemui.notification.notificationcenter;

import android.util.Log;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplus.systemui.receiver.RomUpdateReceiver;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationCenterProxy$$ExternalSyntheticLambda5 implements Function0 {
    public /* synthetic */ NotificationCenterProxy f$0;
    public /* synthetic */ NotificationCenterProxy$registerRusBroadcastForAllUser$receiver$1 f$1;

    public final Object invoke() {
        NotificationCenterProxy$registerRusBroadcastForAllUser$receiver$1 notificationCenterProxy$registerRusBroadcastForAllUser$receiver$1 = this.f$1;
        NotificationCenterProxy.Companion companion = NotificationCenterProxy.Companion;
        try {
            NotificationCenterProxy.Companion companion2 = NotificationCenterProxy.Companion;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationCenterProxy-->registerRusBroadcastForAllUser: unRegister");
            }
            RomUpdateReceiver companion3 = RomUpdateReceiver.Companion.getInstance();
            if (companion3 != null) {
                companion3.removeListener(notificationCenterProxy$registerRusBroadcastForAllUser$receiver$1);
            }
        } catch (IllegalArgumentException e) {
            OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "runCatchIllegalArgumentException: ", e.getMessage(), LogUtil.TAG_NOTIFICATION, "NotificationCenterProxy");
        }
        return Unit.INSTANCE;
    }
}
