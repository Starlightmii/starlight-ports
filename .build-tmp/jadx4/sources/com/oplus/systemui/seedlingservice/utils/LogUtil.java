package com.oplus.systemui.seedlingservice.utils;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.android.settingslib.bluetooth.BluetoothUtils$;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LogUtil {
    private static final String DAILY_OTA_VERSION_KEY = "ro.oplus.dailyota";
    private static final String LOG_SWITCH = "log_switch_type";
    private static final String OTA_VERSION_KEY = "ro.build.version.ota";
    private static final String SEPARATOR = "-->";
    private static final String TAG = "OplusSeedlingService";
    private static boolean isTrialVersion;
    private static final ContentObserver logObserver;
    private static boolean normalDebug;
    public static final LogUtil INSTANCE = new LogUtil();
    private static final String PANIC_KEY = "persist.sys.assert.panic";
    private static boolean panicEnabled = SystemProperties.getBoolean(PANIC_KEY, false);

    static {
        final Handler workHandler = ThreadUtil.INSTANCE.getWorkHandler();
        logObserver = new ContentObserver(workHandler) { // from class: com.oplus.systemui.seedlingservice.utils.LogUtil$logObserver$1
            private final Uri logSwitchUri = Settings.System.getUriFor("log_switch_type");

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (Intrinsics.areEqual(uri, this.logSwitchUri)) {
                    LogUtil.updateNormalDebug();
                }
            }
        };
        boolean z = StringsKt__StringsKt.contains(SystemProperties.get(OTA_VERSION_KEY, ""), "PRE", false) || SystemProperties.getBoolean(DAILY_OTA_VERSION_KEY, false);
        isTrialVersion = z;
        normalDebug = panicEnabled || z;
    }

    private LogUtil() {
    }

    public static final void d(String str, String str2) {
        Log.d(TAG, str + "-->" + str2);
    }

    public static /* synthetic */ void d$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        d(str, str2);
    }

    public static final void debug(String str, String str2) {
        if (normalDebug) {
            Log.d(TAG, str + "-->" + str2);
        }
    }

    public static /* synthetic */ void debug$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        debug(str, str2);
    }

    public static final void e(String str, String str2) {
        Log.e(TAG, str + "-->" + str2);
    }

    public static /* synthetic */ void e$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        e(str, str2);
    }

    private static /* synthetic */ void getNormalDebug$annotations() {
    }

    public static final void i(String str, String str2) {
        Log.i(TAG, str + "-->" + str2);
    }

    public static /* synthetic */ void i$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        i(str, str2);
    }

    private static /* synthetic */ void isTrialVersion$annotations() {
    }

    public static final void registerLogObserver(Context context) {
        updateNormalDebug();
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(LOG_SWITCH), false, logObserver);
    }

    public static final void unRegisterLogObserver(Context context) {
        context.getContentResolver().unregisterContentObserver(logObserver);
    }

    public static final void updateNormalDebug() {
        boolean z = SystemProperties.getBoolean(PANIC_KEY, false);
        panicEnabled = z;
        boolean z2 = z || isTrialVersion;
        normalDebug = z2;
        BluetoothUtils$.ExternalSyntheticOutline0.m("normalDebug = ", TAG, z2);
    }

    public static final void w(String str, String str2) {
        Log.w(TAG, str + "-->" + str2);
    }

    public static /* synthetic */ void w$default(String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        w(str, str2);
    }

    public final boolean getPanicEnabled() {
        return panicEnabled;
    }

    public final void setPanicEnabled(boolean z) {
        panicEnabled = z;
    }
}
