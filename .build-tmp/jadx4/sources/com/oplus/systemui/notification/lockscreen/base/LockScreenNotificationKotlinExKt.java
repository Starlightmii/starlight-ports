package com.oplus.systemui.notification.lockscreen.base;

import androidx.car.app.serialization.Bundler$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.notification.lockscreen.config.CapsuleDebug;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class LockScreenNotificationKotlinExKt {
    public static final String getLogKey(ExpandableView expandableView) {
        if (expandableView instanceof ExpandableNotificationRow) {
            return ((ExpandableNotificationRow) expandableView).getKey();
        }
        if (!(expandableView instanceof OplusCustomRow)) {
            return expandableView.toString();
        }
        CustomRowEntry entry = ((OplusCustomRow) expandableView).getEntry();
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    public static final void logAnimProgress(String str, Function0 function0) {
        if (CapsuleDebug.INSTANCE.getLogSwitchItemViewUpdate() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            String str2 = (String) function0.invoke();
            if (str2 == null) {
                str2 = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str2, "SystemUi--Notification");
        }
    }

    public static final void logCapsule(String str, Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            String str2 = (String) function0.invoke();
            if (str2 == null) {
                str2 = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str2, "SystemUi--Notification");
        }
    }

    public static final void logCapsule(String str, String str2, Throwable th) {
        LogUtil.d(LogUtil.TAG_NOTIFICATION, str, str2, th);
    }
}
