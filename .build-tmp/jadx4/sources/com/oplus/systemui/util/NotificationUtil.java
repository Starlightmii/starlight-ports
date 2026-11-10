package com.oplus.systemui.util;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.OplusBatteryManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.activity.result.ActivityResultRegistry$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.oplus.systemui.notification.mcs.OplusNotificationCommercializeControl;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationUtil {
    public static final String[] FIRST_CONTENT_IN_TEXTLINES_PKGS = {"org.telegram.messenger"};
    public static float sKgdAnimationFraction = 1.0f;
    public static boolean sAtoVersion = false;

    public static String getForceExpand(StatusBarNotification statusBarNotification) {
        return (statusBarNotification == null || statusBarNotification.getNotification() == null || statusBarNotification.getNotification().extras == null) ? String.valueOf(-1) : String.valueOf(statusBarNotification.getNotification().extras.getInt(OplusNotificationCommercializeControl.EXTRA_OPLUS_FORCE_EXPAND, -1));
    }

    public static String getForceTop(StatusBarNotification statusBarNotification) {
        return (statusBarNotification == null || statusBarNotification.getNotification() == null || statusBarNotification.getNotification().extras == null) ? String.valueOf(-1) : String.valueOf(statusBarNotification.getNotification().extras.getInt(OplusNotificationCommercializeControl.EXTRA_OPLUS_FORCE_TOP, -1));
    }

    public static float getFraction() {
        return sKgdAnimationFraction;
    }

    public static boolean isATOVersion() {
        boolean zEquals = SystemProperties.get("ro.oplus.factory_mode", "0").equals("1");
        if (sAtoVersion != zEquals) {
            sAtoVersion = zEquals;
            MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("is ATO version: ", LogUtil.TAG_NOTIFICATION, "Notification--Util", zEquals);
        }
        return zEquals;
    }

    public static boolean isBatteryOriginal() {
        int psyBatteryHmac = new OplusBatteryManager().getPsyBatteryHmac();
        boolean z = psyBatteryHmac != 0;
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(psyBatteryHmac, "hmacStatus = ", LogUtil.TAG_NOTIFICATION, "Notification--Util");
        return z;
    }

    public static boolean isCurrentAppForcingFullScreenBanner(Context context) {
        ComponentName componentName;
        if (context == null) {
            return false;
        }
        try {
            String[] stringArray = context.getResources().getStringArray(2130903237);
            ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
            if (runningTask != null && (componentName = runningTask.topActivity) != null) {
                return Arrays.asList(stringArray).contains(componentName.getPackageName());
            }
            LogUtil.w(LogUtil.TAG_COMMON, "Notification--Util", "isCurrentAppForcingFullScreenBanner getRunningTask returns null");
            return false;
        } catch (Exception e) {
            LogUtil.w(LogUtil.TAG_COMMON, "Notification--Util", "isCurrentAppForcingFullScreenBanner getRunningTask e " + e.getMessage());
        }
    }

    public static boolean isMscTopNotification(String str, StatusBarNotification statusBarNotification) {
        return MCSUtil.isMCSPkg(statusBarNotification) && !TextUtils.isEmpty(str) && str.equals(String.valueOf(0));
    }

    public static String loadHeaderAppName(StatusBarNotification statusBarNotification, PackageManager packageManager, ApplicationInfo applicationInfo) {
        if (OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(UserHandle.getUserId(statusBarNotification.getUid())))) {
            String multiAppName = OplusMultiAppUtils.getMultiAppName(Integer.valueOf(UserHandle.getUserId(statusBarNotification.getUid())), statusBarNotification.getPackageName());
            if (!TextUtils.isEmpty(multiAppName)) {
                return multiAppName;
            }
        }
        String mcsCustomAppName = MCSUtil.getMcsCustomAppName(packageManager, statusBarNotification);
        if (!TextUtils.isEmpty(mcsCustomAppName)) {
            return mcsCustomAppName;
        }
        Notification notification = statusBarNotification.getNotification();
        boolean z = false;
        if (statusBarNotification.getNotification().extras.containsKey("android.substName")) {
            String packageName = statusBarNotification.getPackageName();
            z = packageManager.checkPermission("android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME", packageName) == 0;
            if (!z) {
                StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("warning: pkg ", packageName, " attempting to substitute app name from '");
                sbM.append(statusBarNotification.getPackageName());
                sbM.append("' without holding perm android.permission.SUBSTITUTE_NOTIFICATION_APP_NAME");
                Log.w("Notification--Util", sbM.toString());
            }
        }
        String string = z ? notification.extras.getString("android.substName") : null;
        if (TextUtils.isEmpty(string)) {
            string = String.valueOf(packageManager.getApplicationLabel(applicationInfo));
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return String.valueOf(string);
    }

    public static void sendCancelMotionEvent(ViewGroup viewGroup, String str) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        motionEventObtain.setSource(4098);
        if (viewGroup != null) {
            viewGroup.dispatchTouchEvent(motionEventObtain);
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--Util", "sendCancelMotionEvent reason: " + str + ", handle view: " + viewGroup.getClass().getSimpleName());
        }
        motionEventObtain.recycle();
    }

    public static void sendPendingIntent(int i, PendingIntent pendingIntent, boolean z) throws PendingIntent.CanceledException {
        if (!pendingIntent.isActivity()) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--Util", "sendPendingIntent:is not activity");
            pendingIntent.send();
            return;
        }
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Notification--Util", "sendPendingIntent:Activity on " + i);
        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
        if (z) {
            activityOptionsMakeBasic.setPendingIntentLaunchFlags(268435456);
        }
        activityOptionsMakeBasic.setLaunchDisplayId(i);
        activityOptionsMakeBasic.setPendingIntentBackgroundActivityStartMode(1);
        pendingIntent.send(activityOptionsMakeBasic.toBundle());
    }

    public static void setFraction(float f) {
        if (sKgdAnimationFraction != f) {
            sKgdAnimationFraction = f;
        }
    }
}
