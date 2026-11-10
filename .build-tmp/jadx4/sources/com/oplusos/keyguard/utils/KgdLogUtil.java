package com.oplusos.keyguard.utils;

import androidx.car.app.serialization.Bundler$;
import androidx.exifinterface.media.ExifInterface$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KgdLogUtil {
    public static final KgdLogUtil INSTANCE = new KgdLogUtil();
    public static final String LIMIT_TYPE_FINGERPRINT_UI_MECH = "OnScreenFingerprintUiMechLimit";
    private static final Map<String, Integer> limitTypeCntMap;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        limitTypeCntMap = linkedHashMap;
        linkedHashMap.put(LIMIT_TYPE_FINGERPRINT_UI_MECH, 0);
    }

    public static final void d(String str, String str2, int i, boolean z, Function0 function0) {
        if (LogUtil.getNormalDebug() && isAllowLimitPrint(i, str2, z) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            String str3 = (String) function0.invoke();
            if (str3 == null) {
                str3 = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str3, "SystemUi--Keyguard");
        }
    }

    public static /* synthetic */ void d$default(String str, String str2, int i, boolean z, Function0 function0, int i2) {
        if ((i2 & 4) != 0) {
            i = 8;
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        d(str, str2, i, z, function0);
    }

    public static final void e(String str, String str2) {
        LogUtil.e$default(8, LogUtil.TAG_KEYGUARD, str, str2);
    }

    public static final void i(String str, String str2, int i, boolean z, Function0 function0) {
        if (isAllowLimitPrint(i, str2, z)) {
            LogUtil.i$default(LogUtil.TAG_KEYGUARD, str, (String) function0.invoke(), null, 8);
        }
    }

    public static /* synthetic */ void i$default(String str, String str2, int i, boolean z, Function0 function0, int i2) {
        if ((i2 & 4) != 0) {
            i = 8;
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        i(str, str2, i, z, function0);
    }

    public static final boolean isAllowLimitPrint(int i, String str, boolean z) {
        if (z) {
            return true;
        }
        Map<String, Integer> map = limitTypeCntMap;
        Integer num = map.get(str);
        int iIntValue = num != null ? num.intValue() : 0;
        if (iIntValue == 0) {
            map.put(str, 1);
            return true;
        }
        int i2 = iIntValue + 1;
        if (i2 > i) {
            map.put(str, 0);
        } else {
            map.put(str, Integer.valueOf(i2));
        }
        return false;
    }

    public static final void w(String str, String str2) {
        LogUtil.w$default(8, LogUtil.TAG_KEYGUARD, str, str2);
    }

    public static final void e(String str, String str2, Throwable th) {
        LogUtil.e(LogUtil.TAG_KEYGUARD, str, str2, th);
    }

    public static final void i(String str, String str2, Function0 function0) {
        i$default(str, str2, 0, false, function0, 12);
    }

    public static final void i(String str, String str2) {
        LogUtil.i$default(LogUtil.TAG_KEYGUARD, str, str2, null, 8);
    }

    public static final void i(String str, String str2, int i, Function0 function0) {
        i$default(str, str2, i, false, function0, 8);
    }

    public static final void d(String str, String str2, Function0 function0) {
        d$default(str, str2, 0, false, function0, 12);
    }

    public static final void d(String str, String str2) {
        LogUtil.d$default(8, LogUtil.TAG_KEYGUARD, str, str2);
    }

    public static final void d(String str, String str2, int i, Function0 function0) {
        d$default(str, str2, i, false, function0, 8);
    }

    public static final void d(String str, Function0 function0) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            String str2 = (String) function0.invoke();
            if (str2 == null) {
                str2 = "";
            }
            ExifInterface$.ExternalSyntheticOutline1.m(sbM, str2, "SystemUi--Keyguard");
        }
    }
}
