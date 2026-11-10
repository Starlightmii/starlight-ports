package com.oplus.systemui.notification.row;

import android.content.Context;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.notification.row.material.MaterialColorMode;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.theme.OplusThirdPartUtil;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NotificationMenuRowItemResourceHelperKt {
    public static final String getDeleteItemLottieRes(Context context, MaterialColorMode materialColorMode) {
        if (!SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme) {
                if (ScrimUtil.isLowGaussianLevel(context)) {
                    return 32 == (context.getResources().getConfiguration().uiMode & 48) ? "notification_menu_delete_icon_lottie_white_final.json" : "notification_menu_delete_icon_lottie_night_final.json";
                }
                return materialColorMode != MaterialColorMode.NORMAL_LIGHT ? "notification_menu_delete_icon_lottie_white_final.json" : "notification_menu_delete_icon_lottie_night_final.json";
            }
        }
        return 32 == (context.getResources().getConfiguration().uiMode & 48) ? "notification_menu_delete_icon_lottie_white_final.json" : "notification_menu_delete_icon_lottie_night_final.json";
    }

    public static final boolean shouldShowDeleteAnimation(Context context) {
        if (ScrimUtil.isMiddleOrHighAnimLevel(context)) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme) {
                return true;
            }
        }
        return OplusThirdPartUtil.getDefaultTheme();
    }
}
