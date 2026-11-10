package com.oplus.utils;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.SystemProperties;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Logger {
    public static boolean sDEBUG = getDebugMode();
    public static final AtomicBoolean sInitialized = new AtomicBoolean(false);
    public static volatile Logger sInstance;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LoggerObserver extends ContentObserver {
        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            Logger.sDEBUG = Logger.getDebugMode();
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (sDEBUG) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (sDEBUG) {
            if (objArr.length > 0) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean getDebugMode() {
        return SystemProperties.getBoolean("persist.sys.assert.panic", false);
    }

    public static Logger getInstance() {
        if (sInstance == null) {
            synchronized (Logger.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new Logger();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static void setDebug(boolean z) {
        sDEBUG = z;
    }
}
