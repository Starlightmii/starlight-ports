package com.oplusos.systemui.common.util;

import android.text.TextUtils;
import com.oplus.wrapper.os.SystemProperties;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BrandUtils {
    public static final BrandUtils INSTANCE = new BrandUtils();
    public static boolean isOneplus;
    public static boolean isOppo;
    public static boolean isRealme;

    static {
        String lowerCase = SystemProperties.get("ro.oplus.image.system_ext.brand", "oppo").toLowerCase(Locale.getDefault());
        isOppo = TextUtils.equals(lowerCase, "oppo");
        isOneplus = TextUtils.equals(lowerCase, "oneplus");
        isRealme = TextUtils.equals(lowerCase, "realme");
    }

    public static final int getBrandProductCode() {
        if (isRealme) {
            return 1;
        }
        return isOneplus ? 2 : 0;
    }

    public final void setOneplus(boolean z) {
        isOneplus = z;
    }

    public final void setOppo(boolean z) {
        isOppo = z;
    }

    public final void setRealme(boolean z) {
        isRealme = z;
    }
}
