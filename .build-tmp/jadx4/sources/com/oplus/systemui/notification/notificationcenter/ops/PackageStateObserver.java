package com.oplus.systemui.notification.notificationcenter.ops;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.oplus.systemui.statusbar.notification.helper.NotificationCenterConnect;
import com.oplusos.notification.INotificationCenter;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PackageStateObserver implements AppOpsManager.OnOpChangedListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OpsConfigManager";
    private static Boolean supportNotify;
    private final NotificationCenterConnect centerConnect;
    private final Context context;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Unit $r8$lambda$KSCTgiEEJf11Ux37qunrogWlxkI(PackageStateObserver packageStateObserver, String str, int i, INotificationCenter iNotificationCenter) {
        if (supportNotify == null) {
            Boolean boolValueOf = Boolean.valueOf(iNotificationCenter.getVersion() > 2);
            supportNotify = boolValueOf;
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "first time,result = " + boolValueOf, null, 8);
        }
        if (Intrinsics.areEqual(supportNotify, Boolean.TRUE)) {
            try {
                int packageUidAsUser = packageStateObserver.context.getPackageManager().getPackageUidAsUser(str, i);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "OpsConfigManager-->".concat("onOpChanged: " + str + ":" + packageUidAsUser));
                }
                iNotificationCenter.notifyPackageNotificationStateChanged(str, packageUidAsUser);
            } catch (PackageManager.NameNotFoundException unused) {
                LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "onOpChanged: NameNotFoundException");
            }
        } else {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "onOpChanged: unSupport2");
        }
        return Unit.INSTANCE;
    }

    public PackageStateObserver(Context context, NotificationCenterConnect notificationCenterConnect) {
        this.context = context;
        this.centerConnect = notificationCenterConnect;
    }

    public void onOpChanged(String str, String str2, int i) {
        super.onOpChanged(str, str2, i);
        if (Intrinsics.areEqual(supportNotify, Boolean.FALSE)) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "onOpChanged: unSupport");
            return;
        }
        NotificationCenterConnect notificationCenterConnect = this.centerConnect;
        PackageStateObserver$$ExternalSyntheticLambda0 packageStateObserver$$ExternalSyntheticLambda0 = new PackageStateObserver$$ExternalSyntheticLambda0();
        packageStateObserver$$ExternalSyntheticLambda0.f$0 = this;
        packageStateObserver$$ExternalSyntheticLambda0.f$1 = str2;
        packageStateObserver$$ExternalSyntheticLambda0.f$2 = i;
        VarHandle.storeStoreFence();
        notificationCenterConnect.runAction(packageStateObserver$$ExternalSyntheticLambda0);
    }

    public final void startRegister() {
        AppOpsManager appOpsManager = (AppOpsManager) this.context.getSystemService(AppOpsManager.class);
        if (appOpsManager != null) {
            appOpsManager.startWatchingMode(11, (String) null, this);
        }
    }

    public final void stopRegister() {
        AppOpsManager appOpsManager = (AppOpsManager) this.context.getSystemService(AppOpsManager.class);
        if (appOpsManager != null) {
            appOpsManager.stopWatchingMode(this);
        }
    }

    @Override // android.app.AppOpsManager.OnOpChangedListener
    public void onOpChanged(String str, String str2) {
    }
}
