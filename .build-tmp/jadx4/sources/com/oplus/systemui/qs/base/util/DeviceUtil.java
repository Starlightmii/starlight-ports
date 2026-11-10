package com.oplus.systemui.qs.base.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.oplus.transition.OplusAnimationController;
import java.util.Locale;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DeviceUtil {
    public static final DeviceUtil INSTANCE = new DeviceUtil();

    public static final int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static final int getNavigationBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", OplusAnimationController.PACKAGE_SYSTEM);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static final boolean isLandscape(Context context) {
        return context != null && context.getResources().getConfiguration().orientation == 2;
    }

    public static final boolean isRtl() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    public static final boolean shouldChangePrivacyPlace(Resources resources) {
        return resources.getBoolean(2131034207);
    }

    public static final boolean shouldUseLargeFoldConfig(Resources resources) {
        return resources.getBoolean(2131034172);
    }
}
