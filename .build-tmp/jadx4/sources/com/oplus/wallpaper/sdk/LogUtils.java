package com.oplus.wallpaper.sdk;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LogUtils {
    protected static final boolean DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static final String TAG = "WallpaperSetter.";

    public static void debugD(String str, String str2, Throwable th) {
        if (DEBUG) {
            Log.d(TAG + str, str2, th);
        }
    }

    public static void debugE(String str, String str2) {
        Log.e(TAG + str, str2);
    }

    public static void debugI(String str, String str2) {
        Log.i(TAG + str, str2);
    }

    public static void debugV(String str, String str2) {
    }

    public static void debugW(String str, String str2) {
        Log.w(str, str2);
    }

    public static void debugD(String str, String str2) {
        if (DEBUG) {
            Log.d(TAG + str, str2);
        }
    }
}
