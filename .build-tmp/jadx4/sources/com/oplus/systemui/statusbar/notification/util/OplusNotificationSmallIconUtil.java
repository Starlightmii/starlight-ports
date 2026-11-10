package com.oplus.systemui.statusbar.notification.util;

import android.app.Notification;
import android.os.Bundle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationSmallIconUtil {
    public static final OplusNotificationSmallIconUtil INSTANCE = new OplusNotificationSmallIconUtil();
    private static final String OPLUS_SMALLICON_USE_APP_ICON = "oplus_smallicon_use_app_icon";

    public static final boolean useAppIconForSmallIcon(Notification notification) {
        Bundle bundle;
        return (notification == null || (bundle = notification.extras) == null || !bundle.getBoolean(OPLUS_SMALLICON_USE_APP_ICON, false)) ? false : true;
    }
}
