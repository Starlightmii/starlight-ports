package com.oplus.systemui.statusbar.notification.helper;

import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.helper.NotificationApplyDelayHelper;
import com.oplus.systemui.statusbar.seeding.FluidReplaceNotificationManager;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationApplyDelayHelperImpl extends NotificationApplyDelayHelper {
    public static final Companion Companion = new Companion();
    private static final String FLUID_SERVICE_ID = "op_fluid_serviceId";
    private static final String TAG = "NotificationApplyDelayHelperImpl";
    public boolean animating;
    private final Map<String, Runnable> delayApplyNotification = new HashMap();
    private final FluidReplaceNotificationManager fluidReplaceNotificationManager;
    private final Lazy notifPipeline;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationApplyDelayHelperImpl(FluidReplaceNotificationManager fluidReplaceNotificationManager, Lazy lazy) {
        this.fluidReplaceNotificationManager = fluidReplaceNotificationManager;
        this.notifPipeline = lazy;
    }

    public Runnable getNotificationApplyTask(String str) {
        return this.delayApplyNotification.get(str);
    }

    public boolean isNotificationApplyTaskContains(String str) {
        return this.delayApplyNotification.containsKey(str);
    }

    public boolean isNotificationCardShow(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        Bundle bundle = statusBarNotification.getNotification().extras;
        String string = bundle != null ? bundle.getString(FLUID_SERVICE_ID) : null;
        if (string == null) {
            return false;
        }
        FluidReplaceNotificationManager fluidReplaceNotificationManager = this.fluidReplaceNotificationManager;
        int id = statusBarNotification.getId();
        packageName.getClass();
        if (!fluidReplaceNotificationManager.isNotificationCardShow(id, packageName, string, statusBarNotification.getUserId())) {
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("notificationCard is show, packageName:", packageName, ", notificationId: ", statusBarNotification.getId()), null, 8);
        return true;
    }

    public void putNotificationApplyTask(String str, Runnable runnable) {
        this.delayApplyNotification.put(str, runnable);
    }

    public void removeNotificationApplyTask(String str) {
        this.delayApplyNotification.remove(str);
    }

    public void setSeedlingAnimatingState(boolean z) {
        this.animating = z;
    }

    public boolean shouldBeReplacedByFluid(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        try {
            Bundle bundle = statusBarNotification.getNotification().extras;
            String string = bundle != null ? bundle.getString(FLUID_SERVICE_ID) : null;
            if (string != null) {
                FluidReplaceNotificationManager fluidReplaceNotificationManager = this.fluidReplaceNotificationManager;
                packageName.getClass();
                fluidReplaceNotificationManager.updateFluidMap(packageName, statusBarNotification.getId(), string, statusBarNotification.getUserId());
            }
            FluidReplaceNotificationManager fluidReplaceNotificationManager2 = this.fluidReplaceNotificationManager;
            packageName.getClass();
            if (fluidReplaceNotificationManager2.isNotificationInFluidMap(packageName, statusBarNotification.getId(), statusBarNotification.getUserId(), string)) {
                NotifPipeline notifPipeline = (NotifPipeline) this.notifPipeline.get();
                if ((notifPipeline != null ? notifPipeline.getEntry(statusBarNotification.getKey()) : null) == null) {
                    LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "shouldBeReplacedByFluid, packageName:" + packageName + ", notificationId: " + statusBarNotification.getId(), null, 8);
                    return true;
                }
            }
        } catch (IllegalArgumentException unused) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "Duplicate key in ArrayMap: null");
        }
        if (!isNotificationCardShow(statusBarNotification)) {
            return false;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("shouldBeReplacedByFluid, packageName:", packageName, ", notificationId: ", statusBarNotification.getId()), null, 8);
        return true;
    }
}
