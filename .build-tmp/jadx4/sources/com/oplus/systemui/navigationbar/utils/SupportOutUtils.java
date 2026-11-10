package com.oplus.systemui.navigationbar.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SupportOutUtils {
    public static final SupportOutUtils INSTANCE = new SupportOutUtils();
    private static final String KEY_LAUNCHER_BOTTOM_GESTURE_METADATA = "com.android.launcher.support.update_gesture_area";
    private static final String PACKAGENAME_LAUNCHER = "com.android.launcher";
    private static final String TAG = "SupportOutUtils";
    public static int isLauncherSupportBottomGestureArea = -1;

    public static final boolean isSystemUiSupportClearBottomGestureArea(Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(isLauncherSupportBottomGestureArea, " isLauncherSupportBottomGestureArea ", "SupportOutUtils-->", "SystemUi--NavBar");
        }
        int i = isLauncherSupportBottomGestureArea;
        if (i != -1) {
            return i == 1;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.android.launcher", 128);
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            if (bundle != null && bundle.getBoolean(KEY_LAUNCHER_BOTTOM_GESTURE_METADATA, false)) {
                isLauncherSupportBottomGestureArea = 1;
                return true;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e$default(8, LogUtil.TAG_NAVBAR, TAG, "failed to get bottom gesture area metadata");
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "SupportOutUtils-->launcher not support bottom gesture area");
        }
        isLauncherSupportBottomGestureArea = 0;
        return false;
    }
}
