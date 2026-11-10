package com.oplus.systemui.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import java.util.Arrays;
import java.util.Locale;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Util {
    public static final String ALEXA_ACTION = "amazon.intent.action.ALEXA_LISTEN_PUSHBUTTON";
    public static final String ALEXA_PKG = "com.amazon.dee.app";
    private static final String DISPLAY_BRIGHTNESS_RM_UX = "oplus_display_brightness_pms_ux";
    public static final Util INSTANCE = new Util();
    private static final String OPLUS_VENDOR_BRAND_NAME = "ro.product.vendor.brand";
    private static final String TAG = "Util";
    private static final String TARGET_BRAND_STRING_REPLACE_STOPWORD = "brandstringreplacestopword";
    private static String installedCameraPkgName;

    public static final String formatDuration(long j) {
        long j2 = j / 3600000;
        long j3 = (j / 60000) % 60;
        long j4 = (j / 1000) % 60;
        return j2 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4)}, 3)) : String.format(Locale.US, "%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j3), Long.valueOf(j4)}, 2));
    }

    public static final String getInstalledCameraPkgIntentUri(Context context) {
        String stringReplaceStopWord = getStringReplaceStopWord(context.getResources().getString(2131954098));
        String string = context.getResources().getString(2131954097);
        String stringReplaceStopWord2 = getStringReplaceStopWord(context.getResources().getString(2131954096));
        String string2 = context.getResources().getString(2131954095);
        if (isPkgInstalled(context, stringReplaceStopWord)) {
            return stringReplaceStopWord2;
        }
        if (isPkgInstalled(context, string)) {
            return string2;
        }
        Log.i(TAG, "camera is not installed");
        return string2;
    }

    public static final String getInstalledCameraPkgName(Context context) {
        String str = installedCameraPkgName;
        if (str != null) {
            return str;
        }
        String stringReplaceStopWord = getStringReplaceStopWord(context.getResources().getString(2131954098));
        String string = context.getResources().getString(2131954097);
        if (isPkgInstalled(context, stringReplaceStopWord)) {
            installedCameraPkgName = stringReplaceStopWord;
        } else if (isPkgInstalled(context, string)) {
            installedCameraPkgName = string;
        } else {
            Log.i(TAG, "camera is not installed");
            installedCameraPkgName = null;
        }
        return installedCameraPkgName;
    }

    public static final String getStringReplaceStopWord(String str) {
        return StringsKt__StringsKt.contains(str, TARGET_BRAND_STRING_REPLACE_STOPWORD, false) ? StringsKt__StringsJVMKt.replace(str, TARGET_BRAND_STRING_REPLACE_STOPWORD, SystemProperties.get(OPLUS_VENDOR_BRAND_NAME, "").toLowerCase(Locale.getDefault()), false) : str;
    }

    public static final boolean isPackageInstalledAsUser(Context context, String str, int i, int i2) {
        PackageInfo packageInfoAsUser;
        try {
            packageInfoAsUser = context.getPackageManager().getPackageInfoAsUser(str, 0, i2);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, str + " is not installed." + e);
            packageInfoAsUser = null;
        }
        return packageInfoAsUser != null;
    }

    public static final boolean isPkgInstalled(Context context, String str) {
        boolean z = false;
        if (str != null) {
            try {
                if (context.getPackageManager().getApplicationInfo(str, 0) != null) {
                    z = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            BluetoothUtils$.ExternalSyntheticOutline0.m("isPkgInstalled_isPkgInstalled = ", TAG, z);
        }
        return z;
    }

    public static final void setBrightnessUx(ContentResolver contentResolver, boolean z) {
        Settings.System.putIntForUser(contentResolver, DISPLAY_BRIGHTNESS_RM_UX, z ? 1 : 0, -2);
    }
}
