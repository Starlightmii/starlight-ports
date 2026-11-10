package com.oplus.systemui.qs.base.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.ContextThemeWrapper;
import com.coui.appcompat.theme.COUIThemeOverlay;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsColorUtil {
    public static final int DRAG_MODE_BG_LIGHT_COLOR;
    public static final int QS_ICON_VIEW_INACTIVE_DARK_COLOR;
    public static boolean isSuperPowerSaveState;
    public static int mQsColorState;
    private static final Lazy wallpaperBlurDisable$delegate;
    public static final QsColorUtil INSTANCE = new QsColorUtil();
    public static final int CARRIER_TEXT_LIGHT_COLOR = Color.parseColor("#CCFFFFFF");
    public static final int TOOLBAR_SECONDARY_TEXT_COLOR = Color.parseColor("#8AFFFFFF");
    public static final int QS_TILE_BG_INACTIVE_COLOR = Color.parseColor("#CCFFFFFF");
    public static final int QS_TILE_BG_INACTIVE_DARK_COLOR = Color.parseColor("#33FFFFFF");
    public static final int LIGHT_OS_QS_TILE_BG_INACTIVE_COLOR = Color.parseColor("#CCFFFFFF");
    public static final int LIGHT_OS_QS_TILE_BG_INACTIVE_DARK_COLOR = Color.parseColor("#66000000");
    public static final int QS_TILE_BG_UNAVAILABLE_DARK_COLOR = Color.parseColor("#13FFFFFF");
    public static final int QS_TILE_LABEL_UNAVAILABLE_COLOR = Color.parseColor("#4DFFFFFF");
    public static final int BRIGHTNESS_SLIDER_BG_COLOR = Color.parseColor("#66FFFFFF");
    public static final int LIGHT_OS_BRIGHTNESS_SLIDER_DARK_BG_COLOR = Color.parseColor("#66000000");
    public static final int BRIGHTNESS_ICON_BG_LIGHT_COLOR = Color.parseColor("#B3000000");
    public static final int QS_PAGE_INDICATOR_UNSELECTED_COLOR = Color.parseColor("#66FFFFFF");
    public static final int QS_ICON_INACTIVE_DARK_COLOR = Color.parseColor("#E6FFFFFF");
    public static final int QS_ICON_ACTIVE_DARK_LOW_COLOR = Color.parseColor("#B3000000");
    public static final int QS_VIEW_INACTIVE_BACKGROUND_COLOR = Color.parseColor("#33FFFFFF");
    public static final int QS_ICON_VIEW_ACTIVE_DARK_COLOR = Color.parseColor("#E6FFFFFF");
    public static final int ICON_VOLUME_MEDIA_MUTE_WHITE_COLOR = Color.parseColor("#4DFFFFFF");
    public static final int DRAG_MODE_BG_DARK_COLOR = Color.parseColor("#57595C");
    public static final int DRAG_MODE_BG_COLOR = Color.parseColor("#B3000000");
    public static final int QS_TILE_PREVIEW_BG_COLOR = Color.parseColor("#33FFFFFF");
    public static final int BRIGHTNESS_ICON_COLOR = Color.parseColor("#F2A900");
    public static final int QS_ICON_SEP_ADD_DIALOG_LIGHT_LOW_COLOR = Color.parseColor("#474747");

    static {
        Color.parseColor("#E5000000");
        QS_ICON_VIEW_INACTIVE_DARK_COLOR = Color.parseColor("#13000000");
        DRAG_MODE_BG_LIGHT_COLOR = Color.parseColor("#F4F4F4");
        mQsColorState = 1;
        wallpaperBlurDisable$delegate = LazyKt__LazyJVMKt.lazy(new QsColorUtil$$ExternalSyntheticLambda0());
    }

    public static final void adaptiveColor(TextView textView, int i) {
        if (isTextNeedUseLightColorWhenLight(textView.getContext(), getQsColorState())) {
            i = -1;
        }
        compareAndUpdateTextColor(textView, i);
    }

    public static final void compareAndUpdateBgTintList(View view, ColorStateList colorStateList) {
        ColorStateList backgroundTintList;
        int[] colors;
        if (colorStateList == null || (backgroundTintList = view.getBackgroundTintList()) == null || (colors = backgroundTintList.getColors()) == null || Arrays.equals(colors, colorStateList.getColors())) {
            return;
        }
        view.setBackgroundTintList(colorStateList);
    }

    public static final void compareAndUpdateImageTintList(ImageView imageView, ColorStateList colorStateList) {
        ColorStateList imageTintList;
        int[] colors;
        if (colorStateList == null || (imageTintList = imageView.getImageTintList()) == null || (colors = imageTintList.getColors()) == null || Arrays.equals(colors, colorStateList.getColors())) {
            return;
        }
        imageView.setImageTintList(colorStateList);
    }

    public static final void compareAndUpdateTextColor(TextView textView, ColorStateList colorStateList) {
        if (colorStateList == null || Arrays.equals(textView.getTextColors().getColors(), colorStateList.getColors())) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public static final Context createDarkColorContext(Context context, boolean z) {
        if (z) {
            context = new ContextThemeWrapper(context, 2132019096);
        }
        COUIThemeOverlay.getInstance().applyThemeOverlays(context);
        return context;
    }

    public static final int getFooterTextColor(Context context) {
        if (isTextNeedUseLightColorWhenLight(context, getQsColorState())) {
            return -1;
        }
        return context.getColor(2131103705);
    }

    public static final int getQsColorState() {
        return mQsColorState;
    }

    public static final boolean getSeparateNotificationAndQSState() {
        return INSTANCE.getEnableSeparateNotificationAndQS();
    }

    public static final boolean isAddDialogLowGaussianColor(Context context) {
        QsColorUtil qsColorUtil = INSTANCE;
        if (!qsColorUtil.getWallpaperBlurDisable()) {
            return false;
        }
        if (ScrimUtil.isLowGaussianLevel(context) && !ScrimUtil.isLightOsGaussianLevel()) {
            return true;
        }
        qsColorUtil.getClass();
        NotificationFeatureOption.INSTANCE.getClass();
        return NotificationFeatureOption.isGaussBlurDisabled;
    }

    @Deprecated
    public static final boolean isGlobalThemeApplied(Context context) {
        return context.getResources().getBoolean(2131034330);
    }

    public static final boolean isIconNeedUseLightColor(Context context, boolean z) {
        return (OplusDarkModeUtil.isNightMode(context) || (!z && isGlobalThemeApplied(context)) || INSTANCE.getWallpaperBlurDisable()) ? false : true;
    }

    public static final boolean isIconNeedUseLightColorWhenDarkMode(Context context, boolean z) {
        return (!OplusDarkModeUtil.isNightMode(context) || (!z && isGlobalThemeApplied(context)) || INSTANCE.getWallpaperBlurDisable()) ? false : true;
    }

    public static final boolean isIconUseLightWhenGlobalThemeAndNotDarkMode(Context context) {
        return !OplusDarkModeUtil.isNightMode(context) && isGlobalThemeApplied(context) && Color.luminance(context.getColor(2131102929)) >= 0.4f;
    }

    public static final boolean isLowGaussianLevelColor(Context context, boolean z) {
        if (INSTANCE.getWallpaperBlurDisable()) {
            return (z || !isGlobalThemeApplied(context)) && !OplusDarkModeUtil.isNightMode(context);
        }
        return false;
    }

    public static final boolean isLowGaussianLevelColorWhenDarkMode(Context context, boolean z) {
        if (INSTANCE.getWallpaperBlurDisable()) {
            return (z || !isGlobalThemeApplied(context)) && OplusDarkModeUtil.isNightMode(context);
        }
        return false;
    }

    public static final boolean isMediaIconNeedUseLightColor(Context context, boolean z) {
        return (OplusDarkModeUtil.isNightMode(context) || (!z && isGlobalThemeApplied(context))) ? false : true;
    }

    public static final boolean isNeedDarkThemeColor(Context context, boolean z) {
        boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel(context);
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(context);
        boolean z2 = !z && isGlobalThemeApplied(context);
        QsColorUtil qsColorUtil = INSTANCE;
        return ((!qsColorUtil.getEnableSeparateNotificationAndQS() && zIsLowGaussianLevel) || zIsNightMode || z2 || qsColorUtil.getWallpaperBlurDisable()) ? false : true;
    }

    public static final boolean isNeedUseDarkThemeColor(Context context, boolean z) {
        boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel(context);
        boolean z2 = !z && isGlobalThemeApplied(context);
        QsColorUtil qsColorUtil = INSTANCE;
        return ((!qsColorUtil.getEnableSeparateNotificationAndQS() && zIsLowGaussianLevel) || z2 || qsColorUtil.getWallpaperBlurDisable()) ? false : true;
    }

    public static final boolean isTextNeedUseDarkColorWhenLight(Context context, int i) {
        return isIconNeedUseLightColor(context, false) && i == 0;
    }

    public static final boolean isTextNeedUseLightColorWhenLight(Context context, int i) {
        return isIconNeedUseLightColor(context, false) && i != 0;
    }

    public static final void setQsColorState(int i) {
        mQsColorState = i;
    }

    public final boolean getEnableSeparateNotificationAndQS() {
        return BaseQSResPool.isSeparateNotificationAndQS();
    }

    public final boolean getWallpaperBlurDisable() {
        return ((Boolean) wallpaperBlurDisable$delegate.getValue()).booleanValue();
    }

    public final void setSuperPowerSaveState(boolean z) {
        isSuperPowerSaveState = z;
    }

    public static final void compareAndUpdateTextColor(TextView textView, int i) {
        if (textView.getTextColors().getDefaultColor() != i) {
            textView.setTextColor(i);
        }
    }
}
