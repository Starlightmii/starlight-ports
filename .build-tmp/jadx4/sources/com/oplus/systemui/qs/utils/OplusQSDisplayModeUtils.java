package com.oplus.systemui.qs.utils;

import android.content.Context;
import com.oplus.keyguard.utils.KeyguardUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSDisplayModeUtils {
    public static final OplusQSDisplayModeUtils INSTANCE = new OplusQSDisplayModeUtils();

    public static final float getScaleAmount(Context context) {
        int initialDisplayDensity = KeyguardUtils.Companion.getInitialDisplayDensity();
        if (initialDisplayDensity == 0) {
            return 1.0f;
        }
        return (context.getResources().getDisplayMetrics().densityDpi * 1.0f) / initialDisplayDensity;
    }

    public static final boolean isBigDisplayMode(Context context) {
        return KeyguardUtils.Companion.getInitialDisplayDensity() < context.getResources().getDisplayMetrics().densityDpi;
    }

    public static final boolean isSmallDisplayMode(Context context) {
        return KeyguardUtils.Companion.getInitialDisplayDensity() > context.getResources().getDisplayMetrics().densityDpi;
    }

    public static final boolean isStandardDisplayMode(Context context) {
        return KeyguardUtils.Companion.getInitialDisplayDensity() == context.getResources().getDisplayMetrics().densityDpi;
    }
}
