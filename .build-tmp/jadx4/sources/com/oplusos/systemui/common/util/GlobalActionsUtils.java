package com.oplusos.systemui.common.util;

import android.content.Context;
import android.content.res.Resources;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GlobalActionsUtils {
    public static final GlobalActionsUtils INSTANCE = new GlobalActionsUtils();

    public static final int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final boolean isShowManuallyLock(Context context) {
        return false;
    }

    public static final int px2dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final void setManuallyLockedOn(boolean z) {
    }

    public static final int dp2px(Resources resources, float f) {
        return (int) ((f * resources.getDisplayMetrics().density) + 0.5f);
    }
}
