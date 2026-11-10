package com.oplus.systemui.navigationbar.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: classes4.dex */
public final class LauncherCompatVersionUtil {
    public static final LauncherCompatVersionUtil INSTANCE = new LauncherCompatVersionUtil();
    private static final String KEY_LAUNCHER_TASKBAR_METADATA = "systemui_launcher_compat_version";
    private static final String PACKAGENAME_LAUNCHER = "com.android.launcher";
    private static final String TAG = "LauncherCompatVersionUtil";
    public static int launcherCompatVersion = -1;

    public static final int getLauncherCompatVersion(Context context) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$$ExternalSyntheticOutline0.m(launcherCompatVersion, " launcherCompatVersion: ", "LauncherCompatVersionUtil-->", "SystemUi--NavBar");
        }
        int i = launcherCompatVersion;
        if (i != -1) {
            return i;
        }
        if (context == null) {
            return -1;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo("com.android.launcher", 128).metaData;
            int i2 = bundle != null ? bundle.getInt(KEY_LAUNCHER_TASKBAR_METADATA, 0) : 0;
            launcherCompatVersion = i2;
            return i2;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.e$default(8, LogUtil.TAG_NAVBAR, TAG, "failed to get launcher metadata");
            return 0;
        }
    }
}
