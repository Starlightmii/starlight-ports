package com.oplus.zoom.ui.common;

import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import com.android.launcher3.icons.IconProvider$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class UiLogUtils {
    private static final String LOG_TAG = "ZoomUiController";
    public static final String TAG_CONFIGURATION = "ZoomUiConfiguration";
    public static final String TAG_FLOAT_HANDLE = "FloatHandle";
    public static final String TAG_OBSERVER = "ZoomUiObserver";
    public static final String TAG_TIPS = "Tips";
    public static final String TAG_UI_MANAGER = "ZoomUIManager";

    public static void d(String str, String str2) {
        Log.d(LOG_TAG, str + "-" + str2);
    }

    public static void e(String str, String str2, Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("-");
        sb.append(str2);
        sb.append(", Exception: ");
        IconProvider$.ExternalSyntheticOutline0.m(exc, sb, LOG_TAG);
    }

    public static void i(String str, String str2) {
        Log.i(LOG_TAG, str + "-" + str2);
    }

    public static void v(String str) {
    }

    public static void v(String str, String str2) {
    }

    public static void w(String str, String str2) {
        Log.w(LOG_TAG, str + "-" + str2);
    }

    public static void d(String str) {
        Log.d(LOG_TAG, str);
    }

    public static void i(String str) {
        Log.i(LOG_TAG, str);
    }

    public static void w(String str) {
        Log.w(LOG_TAG, str);
    }

    public static void e(String str, Exception exc) {
        IconProvider$.ExternalSyntheticOutline0.m(exc, Bundler$.ExternalSyntheticOutline0.m(str, ", Exception: "), LOG_TAG);
    }
}
