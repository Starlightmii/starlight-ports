package com.oplus.systemui.qs.res.domain.util;

import android.content.Context;
import com.oplus.keyguard.utils.KeyguardUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ResourcesUtil {
    public static final ResourcesUtil INSTANCE = new ResourcesUtil();

    public static final int getCustomizerNormalBgColor(Context context) {
        return context.getColor(2131103086);
    }

    public static final int getQuickEntranceNormalBgColor(Context context) {
        return context.getColor(2131103089);
    }

    public static final int lockDpi(Context context, int i) {
        return (int) ((context.getResources().getDimensionPixelOffset(i) * (KeyguardUtils.Companion.getInitialDisplayDensity() / 160.0f)) / context.getResources().getDisplayMetrics().density);
    }
}
