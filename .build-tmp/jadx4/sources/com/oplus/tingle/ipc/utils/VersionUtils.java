package com.oplus.tingle.ipc.utils;

import android.util.Log;
import com.oplus.os.OplusBuild;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class VersionUtils {
    public static synchronized boolean isOsVersion11_3() {
        try {
        } catch (Throwable th) {
            String str = "Get OsVersion Exception : " + th.toString();
            if (Logger.sDEBUG) {
                Log.d("Tingle->".concat("VersionUtils"), str);
            }
            return false;
        }
        return OplusBuild.getOplusOSVERSION() >= 22;
    }
}
