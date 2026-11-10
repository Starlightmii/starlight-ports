package com.oplus.tingle.ipc.utils;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.SystemProperties;
import android.support.v4.content.FileProvider$;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class Logger {
    public static boolean sDEBUG;
    public static final AtomicBoolean sInitialized = new AtomicBoolean(false);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class LoggerObserver extends ContentObserver {
        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            Logger.sDEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        String strM = FileProvider$.ExternalSyntheticOutline0.m("Tingle->", str);
        if (str2 != null && objArr.length > 0) {
            str2 = String.format(str2, objArr);
        }
        Log.e(strM, str2);
    }
}
