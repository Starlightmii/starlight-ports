package com.oplus.systemui.notification.notificationcenter;

import android.content.Context;
import android.util.Log;
import com.oplus.systemui.aod.display.OplusAODRefreshRateController$RequestRefreshRateRunnable$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class NotificationCenterProxy$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ NotificationCenterProxy f$0;
    public /* synthetic */ Context f$1;
    public /* synthetic */ NotificationCenterProxy.ProxyBroadcastReceiver f$2;

    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                Context context = this.f$1;
                NotificationCenterProxy.ProxyBroadcastReceiver proxyBroadcastReceiver = this.f$2;
                NotificationCenterProxy.Companion companion = NotificationCenterProxy.Companion;
                try {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationCenterProxy-->registerMediaProviderReceiver: unRegister");
                    }
                    context.unregisterReceiver(proxyBroadcastReceiver);
                } catch (IllegalArgumentException e) {
                    OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "runCatchIllegalArgumentException: ", e.getMessage(), LogUtil.TAG_NOTIFICATION, "NotificationCenterProxy");
                }
                break;
            case 1:
                Context context2 = this.f$1;
                NotificationCenterProxy.ProxyBroadcastReceiver proxyBroadcastReceiver2 = this.f$2;
                NotificationCenterProxy.Companion companion2 = NotificationCenterProxy.Companion;
                try {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationCenterProxy-->registerPkgBroadcastReceiver: unRegister");
                    }
                    context2.unregisterReceiver(proxyBroadcastReceiver2);
                } catch (IllegalArgumentException e2) {
                    OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "runCatchIllegalArgumentException: ", e2.getMessage(), LogUtil.TAG_NOTIFICATION, "NotificationCenterProxy");
                }
                break;
            case 2:
                Context context3 = this.f$1;
                NotificationCenterProxy.ProxyBroadcastReceiver proxyBroadcastReceiver3 = this.f$2;
                NotificationCenterProxy.Companion companion3 = NotificationCenterProxy.Companion;
                try {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationCenterProxy-->registerNormal: unRegister");
                    }
                    context3.unregisterReceiver(proxyBroadcastReceiver3);
                } catch (IllegalArgumentException e3) {
                    OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "runCatchIllegalArgumentException: ", e3.getMessage(), LogUtil.TAG_NOTIFICATION, "NotificationCenterProxy");
                }
                break;
            default:
                Context context4 = this.f$1;
                NotificationCenterProxy.ProxyBroadcastReceiver proxyBroadcastReceiver4 = this.f$2;
                NotificationCenterProxy.Companion companion4 = NotificationCenterProxy.Companion;
                try {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "NotificationCenterProxy-->registerNormalForAllUser: unRegister");
                    }
                    context4.unregisterReceiver(proxyBroadcastReceiver4);
                } catch (IllegalArgumentException e4) {
                    OplusAODRefreshRateController$RequestRefreshRateRunnable$.ExternalSyntheticOutline0.m(8, "runCatchIllegalArgumentException: ", e4.getMessage(), LogUtil.TAG_NOTIFICATION, "NotificationCenterProxy");
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
