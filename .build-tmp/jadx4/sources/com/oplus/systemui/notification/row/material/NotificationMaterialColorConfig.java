package com.oplus.systemui.notification.row.material;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import androidx.room.util.CursorUtil$;
import com.coui.appcompat.contextutil.COUIContextUtil;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialColorConfig {
    public static final NotificationMaterialColorConfig INSTANCE;
    private static final String TAG = "NotificationMaterialColorConfig";
    public static int buttonBgMaterialColor;
    public static int buttonTextMaterialColor;
    private static PorterDuffColorFilter buttonTextMaterialColorFilter;
    public static int commonTextMaterialColor;
    private static final Lazy context$delegate;
    public static int couiColorButtonSubDisableDark;
    public static int couiColorButtonSubDisableLight;
    public static int couiColorContainer16Dark;
    public static int couiColorContainer16Light;
    public static int couiColorLabelPrimaryDark;
    public static int couiColorLabelPrimaryLight;
    public static int couiColorLabelSecondaryDark;
    public static int couiColorLabelSecondaryLight;
    public static int couiColorPrimaryNeutralDark;
    private static PorterDuffColorFilter couiColorPrimaryNeutralFilterDark;
    private static PorterDuffColorFilter couiColorPrimaryNeutralFilterLight;
    public static int couiColorPrimaryNeutralLight;
    public static int couiColorSecondaryNeutralDark;
    public static int couiColorSecondaryNeutralLight;
    public static int defaultNotificationIconFilterColorDark;
    public static int defaultNotificationIconFilterColorLight;
    public static int expandBtnAreaMaterialColor;
    public static int expandHeaderArrowColor;
    public static int expandHeaderArrowReverseColor;
    public static int expandHeaderTextColor;
    public static int expandHeaderTextReverseColor;
    public static int inputHintMaterialColor;
    public static int notificationIconBgColor;
    public static int notificationIconColorDark;
    public static int notificationIconColorLight;
    private static final OplusContrastColorUtil oplusContrastColorUtil;
    public static int progressBgMaterialColor;
    public static int progressFgMaterialColor;
    public static int settingIconColorDark;
    public static int settingIconColorLight;
    public static int settingIconMaterialColor;
    public static int smallIconColorDark;
    public static int smallIconColorLight;
    public static int smallIconMaterialColor;

    static {
        NotificationMaterialColorConfig notificationMaterialColorConfig = new NotificationMaterialColorConfig();
        INSTANCE = notificationMaterialColorConfig;
        context$delegate = LazyKt__LazyJVMKt.lazy(new NotificationMaterialColorConfig$$ExternalSyntheticLambda0());
        couiColorPrimaryNeutralLight = NotificationColorUtil.getCouiColorPrimaryNeutral(notificationMaterialColorConfig.getContext());
        couiColorSecondaryNeutralLight = NotificationColorUtil.getCouiColorSecondaryNeutral(notificationMaterialColorConfig.getContext());
        couiColorLabelPrimaryLight = NotificationColorUtil.getCouiColorLabelPrimary(notificationMaterialColorConfig.getContext());
        couiColorLabelSecondaryLight = NotificationColorUtil.getCouiColorLabelSecondary(notificationMaterialColorConfig.getContext());
        NotificationColorUtil notificationColorUtil = NotificationColorUtil.INSTANCE;
        couiColorButtonSubDisableLight = notificationColorUtil.getCouiColorButtonSubDisable(notificationMaterialColorConfig.getContext());
        notificationIconColorLight = notificationColorUtil.getNotificationIconColor(notificationMaterialColorConfig.getContext());
        couiColorPrimaryNeutralDark = NotificationColorUtil.getCouiColorPrimaryNeutral(notificationMaterialColorConfig.getContext());
        couiColorSecondaryNeutralDark = NotificationColorUtil.getCouiColorSecondaryNeutral(notificationMaterialColorConfig.getContext());
        couiColorLabelPrimaryDark = NotificationColorUtil.getCouiColorLabelPrimary(notificationMaterialColorConfig.getContext());
        couiColorLabelSecondaryDark = NotificationColorUtil.getCouiColorLabelSecondary(notificationMaterialColorConfig.getContext());
        couiColorButtonSubDisableDark = notificationColorUtil.getCouiColorButtonSubDisable(notificationMaterialColorConfig.getContext());
        notificationIconColorDark = notificationColorUtil.getNotificationIconColor(notificationMaterialColorConfig.getContext());
        settingIconMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102899, notificationMaterialColorConfig.getContext().getTheme());
        commonTextMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102900, notificationMaterialColorConfig.getContext().getTheme());
        expandBtnAreaMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102895, notificationMaterialColorConfig.getContext().getTheme());
        buttonBgMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102892, notificationMaterialColorConfig.getContext().getTheme());
        buttonTextMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102894, notificationMaterialColorConfig.getContext().getTheme());
        inputHintMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102896, notificationMaterialColorConfig.getContext().getTheme());
        progressBgMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102897, notificationMaterialColorConfig.getContext().getTheme());
        progressFgMaterialColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102898, notificationMaterialColorConfig.getContext().getTheme());
        expandHeaderTextReverseColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102865, notificationMaterialColorConfig.getContext().getTheme());
        expandHeaderTextColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102864, notificationMaterialColorConfig.getContext().getTheme());
        expandHeaderArrowReverseColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102863, notificationMaterialColorConfig.getContext().getTheme());
        expandHeaderArrowColor = notificationMaterialColorConfig.getContext().getResources().getColor(2131102862, notificationMaterialColorConfig.getContext().getTheme());
        oplusContrastColorUtil = OplusContrastColorUtil.getInstance(notificationMaterialColorConfig.getContext());
    }

    private final Context getContext() {
        return (Context) context$delegate.getValue();
    }

    public final int getButtonBgMaterialColor() {
        return buttonBgMaterialColor;
    }

    public final int getButtonTextMaterialColor() {
        return buttonTextMaterialColor;
    }

    public final PorterDuffColorFilter getButtonTextMaterialColorFilter() {
        return buttonTextMaterialColorFilter;
    }

    public final int getCommonTextMaterialColor() {
        return commonTextMaterialColor;
    }

    public final int getCouiColorButtonSubDisableDark() {
        return couiColorButtonSubDisableDark;
    }

    public final int getCouiColorButtonSubDisableLight() {
        return couiColorButtonSubDisableLight;
    }

    public final int getCouiColorContainer16Dark() {
        return couiColorContainer16Dark;
    }

    public final int getCouiColorContainer16Light() {
        return couiColorContainer16Light;
    }

    public final int getCouiColorLabelPrimaryDark() {
        return couiColorLabelPrimaryDark;
    }

    public final int getCouiColorLabelPrimaryLight() {
        return couiColorLabelPrimaryLight;
    }

    public final int getCouiColorLabelSecondaryDark() {
        return couiColorLabelSecondaryDark;
    }

    public final int getCouiColorLabelSecondaryLight() {
        return couiColorLabelSecondaryLight;
    }

    public final int getCouiColorPrimaryNeutralDark() {
        return couiColorPrimaryNeutralDark;
    }

    public final PorterDuffColorFilter getCouiColorPrimaryNeutralFilterDark() {
        return couiColorPrimaryNeutralFilterDark;
    }

    public final PorterDuffColorFilter getCouiColorPrimaryNeutralFilterLight() {
        return couiColorPrimaryNeutralFilterLight;
    }

    public final int getCouiColorPrimaryNeutralLight() {
        return couiColorPrimaryNeutralLight;
    }

    public final int getCouiColorSecondaryNeutralDark() {
        return couiColorSecondaryNeutralDark;
    }

    public final int getCouiColorSecondaryNeutralLight() {
        return couiColorSecondaryNeutralLight;
    }

    public final int getDefaultNotificationIconFilterColorDark() {
        return defaultNotificationIconFilterColorDark;
    }

    public final int getDefaultNotificationIconFilterColorLight() {
        return defaultNotificationIconFilterColorLight;
    }

    public final int getExpandBtnAreaMaterialColor() {
        return expandBtnAreaMaterialColor;
    }

    public final int getExpandHeaderArrowColor() {
        return expandHeaderArrowColor;
    }

    public final int getExpandHeaderArrowReverseColor() {
        return expandHeaderArrowReverseColor;
    }

    public final int getExpandHeaderTextColor() {
        return expandHeaderTextColor;
    }

    public final int getExpandHeaderTextReverseColor() {
        return expandHeaderTextReverseColor;
    }

    public final int getInputHintMaterialColor() {
        return inputHintMaterialColor;
    }

    public final int getNotificationIconBgColor() {
        return notificationIconBgColor;
    }

    public final int getNotificationIconColorDark() {
        return notificationIconColorDark;
    }

    public final int getNotificationIconColorLight() {
        return notificationIconColorLight;
    }

    public final OplusContrastColorUtil getOplusContrastColorUtil() {
        return oplusContrastColorUtil;
    }

    public final Context getOrCreateContext(boolean z) {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.uiMode & 48;
        int i2 = z ? 32 : 16;
        if (i == i2) {
            return getContext();
        }
        Configuration configuration2 = new Configuration(configuration);
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        return getContext().createConfigurationContext(configuration2);
    }

    public final int getProgressBgMaterialColor() {
        return progressBgMaterialColor;
    }

    public final int getProgressFgMaterialColor() {
        return progressFgMaterialColor;
    }

    public final int getQSDateTimeColor() {
        return 0;
    }

    public final int getSettingIconColorDark() {
        return settingIconColorDark;
    }

    public final int getSettingIconColorLight() {
        return settingIconColorLight;
    }

    public final int getSettingIconMaterialColor() {
        return settingIconMaterialColor;
    }

    public final int getSmallIconColorDark() {
        return smallIconColorDark;
    }

    public final int getSmallIconColorLight() {
        return smallIconColorLight;
    }

    public final int getSmallIconMaterialColor() {
        return smallIconMaterialColor;
    }

    public final void loadConfig(String str) {
        if (ScrimUtil.isMiddleOrHighAnimLevel(getContext())) {
            settingIconMaterialColor = getContext().getResources().getColor(2131102899, getContext().getTheme());
            commonTextMaterialColor = getContext().getResources().getColor(2131102900, getContext().getTheme());
            expandBtnAreaMaterialColor = getContext().getResources().getColor(2131102895, getContext().getTheme());
            buttonBgMaterialColor = getContext().getResources().getColor(2131102892, getContext().getTheme());
            buttonTextMaterialColor = getContext().getResources().getColor(2131102894, getContext().getTheme());
            inputHintMaterialColor = getContext().getResources().getColor(2131102896, getContext().getTheme());
            progressBgMaterialColor = getContext().getResources().getColor(2131102897, getContext().getTheme());
            progressFgMaterialColor = getContext().getResources().getColor(2131102898, getContext().getTheme());
            smallIconMaterialColor = commonTextMaterialColor;
            buttonTextMaterialColorFilter = new PorterDuffColorFilter(expandBtnAreaMaterialColor, PorterDuff.Mode.SRC_ATOP);
            notificationIconBgColor = getContext().getResources().getColor(2131102879, getContext().getTheme());
        }
        expandHeaderTextReverseColor = getContext().getResources().getColor(2131102865, getContext().getTheme());
        expandHeaderTextColor = getContext().getResources().getColor(2131102864, getContext().getTheme());
        expandHeaderArrowReverseColor = getContext().getResources().getColor(2131102863, getContext().getTheme());
        expandHeaderArrowColor = getContext().getResources().getColor(2131102862, getContext().getTheme());
        Context orCreateContext = getOrCreateContext(false);
        Context orCreateContext2 = getOrCreateContext(true);
        couiColorPrimaryNeutralLight = NotificationColorUtil.getCouiColorPrimaryNeutral(orCreateContext);
        couiColorSecondaryNeutralLight = NotificationColorUtil.getCouiColorSecondaryNeutral(orCreateContext);
        couiColorLabelPrimaryLight = NotificationColorUtil.getCouiColorLabelPrimary(orCreateContext);
        couiColorLabelSecondaryLight = NotificationColorUtil.getCouiColorLabelSecondary(orCreateContext);
        NotificationColorUtil notificationColorUtil = NotificationColorUtil.INSTANCE;
        couiColorButtonSubDisableLight = notificationColorUtil.getCouiColorButtonSubDisable(orCreateContext);
        notificationIconColorLight = notificationColorUtil.getNotificationIconColor(orCreateContext);
        OplusContrastColorUtil oplusContrastColorUtil2 = oplusContrastColorUtil;
        smallIconColorLight = oplusContrastColorUtil2 != null ? OplusContrastColorUtil.calculateContrastColor(orCreateContext, null) : 0;
        settingIconColorLight = orCreateContext.getResources().getColor(2131102937, orCreateContext.getTheme());
        int i = couiColorSecondaryNeutralLight;
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        couiColorPrimaryNeutralFilterLight = new PorterDuffColorFilter(i, mode);
        defaultNotificationIconFilterColorLight = orCreateContext.getResources().getColor(2131102881, orCreateContext.getTheme());
        couiColorContainer16Light = COUIContextUtil.getAttrColor(orCreateContext, 2130969262);
        couiColorPrimaryNeutralDark = NotificationColorUtil.getCouiColorPrimaryNeutral(orCreateContext2);
        couiColorSecondaryNeutralDark = NotificationColorUtil.getCouiColorSecondaryNeutral(orCreateContext2);
        couiColorLabelPrimaryDark = NotificationColorUtil.getCouiColorLabelPrimary(orCreateContext2);
        couiColorLabelSecondaryDark = NotificationColorUtil.getCouiColorLabelSecondary(orCreateContext2);
        couiColorButtonSubDisableDark = notificationColorUtil.getCouiColorButtonSubDisable(orCreateContext2);
        notificationIconColorDark = notificationColorUtil.getNotificationIconColor(orCreateContext2);
        smallIconColorDark = oplusContrastColorUtil2 != null ? OplusContrastColorUtil.calculateContrastColor(orCreateContext2, null) : 0;
        settingIconColorDark = orCreateContext2.getResources().getColor(2131102937, orCreateContext2.getTheme());
        couiColorPrimaryNeutralFilterDark = new PorterDuffColorFilter(couiColorSecondaryNeutralDark, mode);
        defaultNotificationIconFilterColorDark = orCreateContext2.getResources().getColor(2131102881, orCreateContext2.getTheme());
        couiColorContainer16Dark = COUIContextUtil.getAttrColor(orCreateContext2, 2130969262);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, CursorUtil$.ExternalSyntheticOutline0.m("loadConfig: color:", Integer.toHexString(couiColorPrimaryNeutralLight), ", reason:", str), null, 8);
    }

    public final void onConfigurationChanged(String str) {
        loadConfig(str);
    }

    public final void setButtonTextMaterialColorFilter(PorterDuffColorFilter porterDuffColorFilter) {
        buttonTextMaterialColorFilter = porterDuffColorFilter;
    }
}
