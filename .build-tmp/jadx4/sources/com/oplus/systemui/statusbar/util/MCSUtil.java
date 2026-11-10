package com.oplus.systemui.statusbar.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MCSUtil {
    public static String getMcsCustomAppName(PackageManager packageManager, StatusBarNotification statusBarNotification) {
        Bundle bundle;
        if (statusBarNotification == null || packageManager == null) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "MCSUtil", "[getMcsCustomAppName]: sbn or pm is null");
            return null;
        }
        try {
            if (!isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg())) {
                return null;
            }
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.android.systemui", 128);
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                boolean z = bundle.getBoolean("isSupportMcsCustomAppName", false);
                Bundle bundle2 = statusBarNotification.getNotification().extras;
                if (z && bundle2 != null && bundle2.containsKey("oplus_mcs_custom_name")) {
                    String string = bundle2.getString("oplus_mcs_custom_name");
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "MCSUtil", "[getMcsCustomAppName]: customAppName: " + string);
                    return string;
                }
                return null;
            }
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "MCSUtil", "[getMcsCustomAppName]: appInfo is null or appInfo.metaData is null");
            return null;
        } catch (Exception e) {
            CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("[getMcsCustomAppName]: "), LogUtil.TAG_NOTIFICATION, "MCSUtil");
        }
    }

    public static boolean isMCSPkg(String str, String str2) {
        return OplusAnimationController.PACKAGE_SYSTEM.equals(str2) && !OplusAnimationController.PACKAGE_SYSTEM.equals(str);
    }

    public static void sendNotificationExposeDataMsc(Context context, List list) {
        Uri uri = Uri.parse("content://com.heytap.mcs.provider/track_event");
        ContentValues contentValues = new ContentValues();
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Map map = (Map) it.next();
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry entry : map.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e) {
            LogUtil.e(LogUtil.TAG_NOTIFICATION, "MCSUtil", e.toString());
        }
        contentValues.put("notification_expose", jSONArray.toString());
        Uri uriInsert = context.getContentResolver().insert(uri, contentValues);
        if (uriInsert == null) {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "MCSUtil", "Failed to insert: " + jSONArray.toString());
        } else {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "MCSUtil", "Insert successful! New record ID is:" + uriInsert.getLastPathSegment() + " info:" + jSONArray.toString());
        }
    }

    public static boolean isMCSPkg(StatusBarNotification statusBarNotification) {
        return isMCSPkg(statusBarNotification.getPackageName(), statusBarNotification.getOpPkg());
    }
}
