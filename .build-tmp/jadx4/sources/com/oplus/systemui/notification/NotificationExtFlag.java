package com.oplus.systemui.notification;

import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import com.android.systemui.DependencyEx;
import com.android.systemui.doze.DozeLogger$;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationExtFlag {
    public static final NotificationExtFlag INSTANCE = new NotificationExtFlag();
    public static final boolean OPLUS_HEADSUP;
    private static final String TAG = "NotificationExtFlag";
    public static final boolean USE_OPLUS_PHYSICAL;

    static {
        boolean z;
        Boolean booleanStrictOrNull;
        boolean zBooleanValue = true;
        if (Build.isDebuggable()) {
            z = SystemProperties.getBoolean("oplus_physical", true);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, DozeLogger$.ExternalSyntheticOutline0.m("USE_OPLUS_PHYSICAL=", OplusPreventModeData.SEPARATOR, System.getProperty("oplus_physical"), z), null, 8);
        } else {
            z = true;
        }
        USE_OPLUS_PHYSICAL = z;
        if (Build.isDebuggable()) {
            Context context = (Context) DependencyEx.sDependency.getDependency(Context.class);
            String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), "oplus_headsup", context.getUserId());
            if (stringForUser != null && (booleanStrictOrNull = StringsKt__StringsKt.toBooleanStrictOrNull(stringForUser)) != null) {
                zBooleanValue = booleanStrictOrNull.booleanValue();
            }
        }
        OPLUS_HEADSUP = zBooleanValue;
    }
}
