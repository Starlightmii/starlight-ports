package com.oplus.systemui.statusbar.notification.row.menupanel.notificationtop;

import android.util.Log;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.List;
import kotlin.Pair;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTopUtils {
    public static final NotificationTopUtils INSTANCE = new NotificationTopUtils();
    private static final String JOINT = "_";
    private static final String TAG = "NotificationTopUtils";

    public static final Pair<String, String> getNotificationTopInfo(String str) {
        if (str != null) {
            List listSplit$default = StringsKt__StringsKt.split$default(str, new String[]{JOINT}, 2, 2);
            return listSplit$default.size() == 2 ? new Pair<>(listSplit$default.get(0), listSplit$default.get(1)) : new Pair<>("", "");
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationTopUtils-->key == null");
        }
        return new Pair<>("", "");
    }

    public static final String getNotificationTopKey(String str, Integer num) {
        if (str != null && num != null) {
            return num + JOINT + str;
        }
        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("NotificationTopUtils-->");
        sb.append("pkg: " + (str == null) + ", uid: " + (num == null));
        Log.d("SystemUi--Notification", sb.toString());
        return "";
    }
}
