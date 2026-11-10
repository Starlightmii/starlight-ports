package com.oplus.systemui.privacy;

import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusPrivacyDotViewControllerImplKt {
    public static boolean DEBUG = false;
    private static final String TAG = "OplusPrivacyDotViewController";

    public static final void access$dlog(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }
}
