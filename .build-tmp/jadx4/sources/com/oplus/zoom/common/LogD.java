package com.oplus.zoom.common;

import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class LogD {
    public static final String TAG_ANIM = "ZoomAnimation";
    public static final String TAG_CONTROLLER = "ZoomController";
    public static final String TAG_DECOR = "ZoomDecor";
    public static final String TAG_OBSERVER = "ZoomSettingObserver";
    public static final String TAG_POINT_HANDLER = "ZoomPointerHandler";
    public static final String TAG_STATE = "ZoomStateManager";

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
    }

    public static void v(String str, String str2) {
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }
}
