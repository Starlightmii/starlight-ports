package com.oplus.systemui.statusbar.seeding;

import android.os.Bundle;
import android.service.notification.StatusBarNotification;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginCallUtils {
    public static final String PARAM_NOTIFICATION_IMPORTANCE = "notification_importance";
    public static final String PARAM_SHOW_HUN = "show_hun";
    public static final SeedlingPluginCallUtils INSTANCE = new SeedlingPluginCallUtils();
    private static final Bundle tmpBundle = new Bundle();

    public static final Bundle getBundle(String str, String str2, int i) {
        Bundle bundle = tmpBundle;
        bundle.clear();
        bundle.putString("method", str);
        bundle.putInt(str2, i);
        return bundle;
    }

    public static final Bundle getBundle(String str) {
        Bundle bundle = tmpBundle;
        bundle.clear();
        bundle.putString("method", str);
        return bundle;
    }

    public static final Bundle getBundle() {
        Bundle bundle = tmpBundle;
        bundle.clear();
        return bundle;
    }

    public static final Bundle getBundle(String str, String str2, StatusBarNotification statusBarNotification) {
        Bundle bundle = tmpBundle;
        bundle.clear();
        bundle.putString("method", str);
        bundle.putParcelable(str2, statusBarNotification);
        return bundle;
    }
}
