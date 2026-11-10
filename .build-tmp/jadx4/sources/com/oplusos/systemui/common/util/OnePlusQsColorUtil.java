package com.oplusos.systemui.common.util;

import android.content.Context;
import com.oplus.util.OplusDarkModeUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OnePlusQsColorUtil {
    public static final OnePlusQsColorUtil INSTANCE = new OnePlusQsColorUtil();
    private static final String TAG = "OnePlusQsColorUtil";
    public static boolean enableSeparateNotificationAndQS;
    public static boolean enableTwoToneTheme;

    public static final boolean getEnableTwoToneThemeState() {
        return enableTwoToneTheme;
    }

    public static final boolean getSeparateNotificationAndQSState() {
        return enableSeparateNotificationAndQS;
    }

    public static final boolean isNeedSeparateDarkThemeColor(Context context) {
        return !OplusDarkModeUtil.isNightMode(context) && enableSeparateNotificationAndQS;
    }

    public static final int obtainOnePlusColorForQsPanelBackground(Context context) {
        return context.getColor(2131102961);
    }

    public static final int obtainOnePlusColorForTileUnavailable(Context context) {
        return context.getColor(2131102962);
    }

    public static final void setEnableTwoToneThemeState(boolean z) {
        enableTwoToneTheme = z;
    }

    public static final void setSeparateNotificationAndQSState(boolean z) {
        enableSeparateNotificationAndQS = z;
    }

    public final boolean getEnableSeparateNotificationAndQS() {
        return enableSeparateNotificationAndQS;
    }

    public final boolean getEnableTwoToneTheme() {
        return enableTwoToneTheme;
    }

    public final void setEnableSeparateNotificationAndQS(boolean z) {
        enableSeparateNotificationAndQS = z;
    }

    public final void setEnableTwoToneTheme(boolean z) {
        enableTwoToneTheme = z;
    }
}
