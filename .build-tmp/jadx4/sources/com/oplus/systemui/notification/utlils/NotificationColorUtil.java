package com.oplus.systemui.notification.utlils;

import android.content.Context;
import android.content.res.ColorStateList;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplusos.systemui.common.util.ScrimUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationColorUtil {
    public static final NotificationColorUtil INSTANCE = new NotificationColorUtil();
    private static final String TAG = "NotificationColorUtil";

    public static final int getCouiColorLabelPrimary(Context context) {
        return isGlobalThemeApplied(context) ? context.getResources().getColor(2131102846) : context.getResources().getColor(2131100873);
    }

    public static final int getCouiColorLabelSecondary(Context context) {
        return isGlobalThemeApplied(context) ? context.getResources().getColor(2131102848) : context.getResources().getColor(2131100882);
    }

    public static final int getCouiColorPrimaryNeutral(Context context) {
        return isGlobalThemeApplied(context) ? context.getResources().getColor(2131102847) : context.getResources().getColor(2131100936);
    }

    public static final int getCouiColorSecondaryNeutral(Context context) {
        return isGlobalThemeApplied(context) ? context.getResources().getColor(2131102849) : context.getResources().getColor(2131101006);
    }

    public static final int getDateTimeDefaultColor(Context context) {
        return ScrimUtil.isWallpaperBlurDisable() ? context.getColor(2131103705) : context.getResources().getColor(2131102905);
    }

    public static final int getQSDateTimeColor(Context context) {
        if (!QsColorUtil.isTextNeedUseLightColorWhenLight(context, QsColorUtil.getQsColorState()) && isGlobalThemeApplied(context)) {
            return context.getColor(2131103705);
        }
        return getDateTimeDefaultColor(context);
    }

    public static final int getRemoteInputEditBgColor(Context context, int i) {
        return isGlobalThemeApplied(context) ? context.getResources().getColor(2131100716) : context.getResources().getColor(2131103335);
    }

    public static final ColorStateList getRemoteInputMessageTextColor(Context context, ColorStateList colorStateList) {
        if (!isGlobalThemeApplied(context)) {
            return colorStateList;
        }
        ColorStateList colorStateList2 = context.getResources().getColorStateList(2131103340);
        colorStateList2.getClass();
        return colorStateList2;
    }

    public static final int getRemoteInputTextHintColor(Context context, int i) {
        return isGlobalThemeApplied(context) ? context.getColor(2131103337) : i;
    }

    public static final boolean isGlobalThemeApplied(Context context) {
        return QsColorUtil.isGlobalThemeApplied(context) || context.getResources().getBoolean(2131034317);
    }

    public final int getCouiColorButtonSubDisable(Context context) {
        return context.getResources().getColor(2131100575, context.getTheme());
    }

    public final int getNotificationIconColor(Context context) {
        return context.getResources().getColor(2131102914, context.getTheme());
    }
}
