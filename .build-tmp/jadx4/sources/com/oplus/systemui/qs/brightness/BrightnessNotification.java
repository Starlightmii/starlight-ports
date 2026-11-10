package com.oplus.systemui.qs.brightness;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.Prefs;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BrightnessNotification {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BrightnessNotification";
    private final Context mContext;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BrightnessNotification(Context context) {
        this.mContext = context;
    }

    public static final void access$updateBrightness(BrightnessNotification brightnessNotification, int i, boolean z) {
        float f;
        brightnessNotification.getClass();
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "BrightnessNotification-->isFlavorOneDevice is false");
                return;
            }
            return;
        }
        if (!z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "BrightnessNotification-->automatic is false");
                return;
            }
            return;
        }
        if (!Prefs.getBoolean(brightnessNotification.mContext, "isFirstChangeBrightness", true)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "BrightnessNotification-->isFirstChangeBrightness is false");
                return;
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("android.os.OplusPowerManager");
            Class[] clsArr = new Class[0];
            float[] fArr = (float[]) ReflectionUtils.methodInvokeWithArgs(cls.getConstructor(null).newInstance(null), ReflectionUtils.getMethodWithParams(cls, Float.TYPE), Float.valueOf(3.0f));
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "BrightnessNotification-->".concat("nitToBrightness  floats[0] = " + fArr[0]));
            }
            f = fArr[0];
        } catch (Exception e) {
            Log.d(TAG, "nitToBrightness Exception = " + e);
            f = 0.0f;
        }
        if (f == 0.0f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "BrightnessNotification-->nitToBrightness == 0");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "BrightnessNotification-->".concat(" lastBrightness = " + i + " nitToBrightness = " + f));
        }
        if (i < f) {
            Intent intent = new Intent();
            intent.setAction("android.settings.DISPLAY_SETTINGS");
            Context context = brightnessNotification.mContext;
            UserHandle userHandle = UserHandle.CURRENT;
            ((NotificationManager) brightnessNotification.mContext.getSystemService("notification")).notifyAsUser(null, 10014, new Notification.Builder(brightnessNotification.mContext, "ALR").setContentText(brightnessNotification.mContext.getString(2131952193)).setStyle(new Notification.BigTextStyle()).setContentIntent(PendingIntent.getActivityAsUser(context, 0, intent, 67108864, null, userHandle)).setSmallIcon(2131232737).setAutoCancel(true).build(), userHandle);
            Prefs.putBoolean(brightnessNotification.mContext, "isFirstChangeBrightness", false);
        }
    }
}
