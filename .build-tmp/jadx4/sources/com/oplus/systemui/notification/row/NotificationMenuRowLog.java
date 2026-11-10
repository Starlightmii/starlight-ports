package com.oplus.systemui.notification.row;

import android.util.Log;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMenuRowLog {
    public static final NotificationMenuRowLog INSTANCE = new NotificationMenuRowLog();
    public static final String TAG = "NotificationMenuRowMetaBall";

    public static final void d(Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String str = (String) function0.invoke();
            if (str == null) {
                str = "";
            }
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat(str));
        }
    }

    public static final void e(String str) {
        LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, str);
    }

    public static final void i(String str) {
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, str, null, 8);
    }

    public static final String simpleNameAndHash(Object obj) {
        return PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(obj != null ? obj.hashCode() : 0, obj != null ? obj.getClass().getSimpleName() : null, "@");
    }

    public static final void v(Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String str = (String) function0.invoke();
            if (str == null) {
                str = "";
            }
            Log.d("SystemUi--Notification", "NotificationMenuRowMetaBall-->".concat(str));
        }
    }
}
