package com.oplusos.systemui.common.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.SurfaceControl;
import android.view.View;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.BlurParam;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColor;
import com.oplusos.systemui.common.blurability.MixColorWithShader;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSBlurConfigProvider {
    public static final int DEFAULT_ACTIVE_DARK_BOTTOM;
    public static final int DEFAULT_ACTIVE_DARK_TOP;
    public static final int DEFAULT_ACTIVE_LIGHT_BOTTOM;
    public static final int DEFAULT_ACTIVE_LIGHT_TOP;
    public static final int DEFAULT_DARK_BOTTOM;
    public static final int DEFAULT_DARK_BOTTOM_NO_STROKE;
    public static final int DEFAULT_DARK_TOP;
    public static final int DEFAULT_DARK_TOP_NO_STROKE;
    public static final int DEFAULT_LIGHT_BOTTOM;
    public static final int DEFAULT_LIGHT_BOTTOM_NO_STROKE;
    public static final int DEFAULT_LIGHT_TOP;
    public static final int DEFAULT_LIGHT_TOP_NO_STROKE;
    public static final QSBlurConfigProvider INSTANCE = new QSBlurConfigProvider();
    private static final MixColorWithShader QS_ACTIVE_LIGHT_MIX_COLOR;
    private static final MixColorWithShader QS_ACTIVE_NIGHT_MIX_COLOR;
    public static final int QS_INACTIVE_LIGHT_BG_COLOR;
    public static final int QS_INACTIVE_LIGHT_FG_COLOR;
    private static final MixColorWithShader QS_INACTIVE_LIGHT_MIX_COLOR;
    private static final MixColorWithShader QS_INACTIVE_LIGHT_MIX_COLOR_NO_STROKE;
    public static final int QS_INACTIVE_NIGHT_BG_COLOR;
    public static final int QS_INACTIVE_NIGHT_FG_COLOR;
    private static final MixColorWithShader QS_INACTIVE_NIGHT_MIX_COLOR;
    private static final MixColorWithShader QS_INACTIVE_NIGHT_MIX_COLOR_NO_STROKE;
    private static final MixColorWithShader QS_SCENARIO_INACTIVE_LIGHT_MIX_COLOR;
    private static final MixColorWithShader QS_SCENARIO_INACTIVE_NIGHT_MIX_COLOR;
    private static final MixColorWithShader SEP_QS_EMPTY_LIGHT_MIX_COLOR;
    private static final MixColorWithShader SEP_QS_EMPTY_NIGHT_MIX_COLOR;

    static {
        int color = Color.parseColor("#19404040");
        DEFAULT_LIGHT_TOP = color;
        int color2 = Color.parseColor("#4D737373");
        DEFAULT_LIGHT_BOTTOM = color2;
        int color3 = Color.parseColor("#80404040");
        DEFAULT_LIGHT_TOP_NO_STROKE = color3;
        int color4 = Color.parseColor("#B2737373");
        DEFAULT_LIGHT_BOTTOM_NO_STROKE = color4;
        int color5 = Color.parseColor("#73E6E6E6");
        DEFAULT_ACTIVE_LIGHT_TOP = color5;
        int color6 = Color.parseColor("#40CCCCCC");
        DEFAULT_ACTIVE_LIGHT_BOTTOM = color6;
        int color7 = Color.parseColor("#40404040");
        DEFAULT_DARK_TOP = color7;
        int color8 = Color.parseColor("#667B7B7B");
        DEFAULT_DARK_BOTTOM = color8;
        int color9 = Color.parseColor("#5A404040");
        DEFAULT_DARK_TOP_NO_STROKE = color9;
        int color10 = Color.parseColor("#B27B7B7B");
        DEFAULT_DARK_BOTTOM_NO_STROKE = color10;
        int color11 = Color.parseColor("#7DE6E6E6");
        DEFAULT_ACTIVE_DARK_TOP = color11;
        int color12 = Color.parseColor("#40CCCCCC");
        DEFAULT_ACTIVE_DARK_BOTTOM = color12;
        QS_ACTIVE_NIGHT_MIX_COLOR = new MixColorWithShader(5, color11, 3, color12);
        QS_ACTIVE_LIGHT_MIX_COLOR = new MixColorWithShader(5, color5, 3, color6);
        QS_INACTIVE_LIGHT_FG_COLOR = Color.parseColor("#4D8C8C8C");
        QS_INACTIVE_LIGHT_BG_COLOR = Color.parseColor("#80B2B2B2");
        QS_INACTIVE_NIGHT_FG_COLOR = Color.parseColor("#66262626");
        QS_INACTIVE_NIGHT_BG_COLOR = Color.parseColor("#66383838");
        QS_INACTIVE_LIGHT_MIX_COLOR = new MixColorWithShader(5, color, 3, color2);
        QS_INACTIVE_LIGHT_MIX_COLOR_NO_STROKE = new MixColorWithShader(5, color3, 3, color4);
        QS_INACTIVE_NIGHT_MIX_COLOR = new MixColorWithShader(5, color7, 3, color8);
        QS_INACTIVE_NIGHT_MIX_COLOR_NO_STROKE = new MixColorWithShader(5, color9, 3, color10);
        QS_SCENARIO_INACTIVE_LIGHT_MIX_COLOR = new MixColorWithShader(5, Color.parseColor("#B38C8C8C"), 2, Color.parseColor("#B3B2B2B2"));
        QS_SCENARIO_INACTIVE_NIGHT_MIX_COLOR = new MixColorWithShader(5, Color.parseColor("#0D262626"), 2, Color.parseColor("#0D383838"));
        int color13 = Color.parseColor("#1AE6E6E6");
        int color14 = Color.parseColor("#14838383");
        int color15 = Color.parseColor("#5C1F1F1F");
        int color16 = Color.parseColor("#3D383838");
        SEP_QS_EMPTY_LIGHT_MIX_COLOR = new MixColorWithShader(5, color13, 2, color14);
        SEP_QS_EMPTY_NIGHT_MIX_COLOR = new MixColorWithShader(5, color15, 2, color16);
    }

    public static final BlurMixConfig.BlurMixMultiWithShader createMixColorConfig(MixColorWithShader mixColorWithShader, boolean z, float f) {
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig = NotifiAndQsPlatformBlurExKt.panelElementPlatformMixWithShaderConfig(mixColorWithShader, z, true);
        blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig.setAlphaWithBlurAmount(true);
        blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig.setMirrorScale(f);
        return blurMixMultiWithShaderPanelElementPlatformMixWithShaderConfig;
    }

    public static final BlurMixConfig.BlurMixSingle createMotionBlurConfig(boolean z, int i, int i2, Integer num) {
        int iIntValue;
        if (num != null) {
            iIntValue = num.intValue();
        } else {
            iIntValue = z ? 4 : 5;
        }
        return new BlurMixConfig.BlurMixSingle(new MixColor(iIntValue, i, i2));
    }

    public static final BlurMixConfig getActiveBlurConfig(boolean z, float f) {
        return createMixColorConfig(z ? QS_ACTIVE_NIGHT_MIX_COLOR : QS_ACTIVE_LIGHT_MIX_COLOR, z, f);
    }

    public static final Drawable getBlurDrawableTag(View view) {
        Object tag = view.getTag(2131365552);
        if (tag instanceof Drawable) {
            return (Drawable) tag;
        }
        return null;
    }

    public static final int getDefaultBlurRadius() {
        return 250;
    }

    public static final int getDefaultDialogBlurRadius() {
        return 300;
    }

    public static final BlurMixConfig.BlurMixSingle getDialogMotionBlurConfig(boolean z) {
        return createMotionBlurConfig(z, z ? QS_INACTIVE_NIGHT_FG_COLOR : QS_INACTIVE_LIGHT_FG_COLOR, z ? QS_INACTIVE_NIGHT_BG_COLOR : QS_INACTIVE_LIGHT_BG_COLOR, 4);
    }

    public static final BlurMixConfig getDialogPlatformBlurConfig(boolean z, boolean z2, float f) {
        return getInactiveBlurConfig(z, z2, f);
    }

    public static final Drawable getGradientDrawableTag(View view) {
        Object tag = view.getTag(2131365556);
        if (tag instanceof Drawable) {
            return (Drawable) tag;
        }
        return null;
    }

    public static final BlurMixConfig getInactiveBlurConfig(boolean z, boolean z2, float f) {
        MixColorWithShader mixColorWithShader;
        if (z) {
            mixColorWithShader = z2 ? QS_INACTIVE_NIGHT_MIX_COLOR : QS_INACTIVE_NIGHT_MIX_COLOR_NO_STROKE;
        } else {
            mixColorWithShader = z2 ? QS_INACTIVE_LIGHT_MIX_COLOR : QS_INACTIVE_LIGHT_MIX_COLOR_NO_STROKE;
        }
        return createMixColorConfig(mixColorWithShader, z, f);
    }

    public static final BlurMixConfig.BlurMixSingle getPrivacyMotionBlurConfig(boolean z) {
        return createMotionBlurConfig(z, z ? QS_INACTIVE_NIGHT_FG_COLOR : QS_INACTIVE_LIGHT_FG_COLOR, z ? QS_INACTIVE_NIGHT_BG_COLOR : QS_INACTIVE_LIGHT_BG_COLOR, null);
    }

    public static final BlurMixConfig getScenarioInactiveBlurConfig(boolean z, float f) {
        return createMixColorConfig(z ? QS_SCENARIO_INACTIVE_NIGHT_MIX_COLOR : QS_SCENARIO_INACTIVE_LIGHT_MIX_COLOR, z, f);
    }

    public static final BlurMixConfig getSeekBarActiveBlurConfig(boolean z, float f) {
        return getActiveBlurConfig(z, f);
    }

    public static final BlurMixConfig getSeekBarInactiveBlurConfig(boolean z, boolean z2, float f) {
        return getInactiveBlurConfig(z, z2, f);
    }

    public static final BlurMixConfig getSepActiveBlurConfig(boolean z, float f) {
        return getActiveBlurConfig(z, f);
    }

    public static final BlurMixConfig.BlurMixMultiWithShader getSepEmptyHolderBlurConfig(boolean z, float f) {
        MixColorWithShader mixColorWithShader = SEP_QS_EMPTY_NIGHT_MIX_COLOR;
        if (!z) {
            mixColorWithShader = null;
        }
        if (mixColorWithShader == null) {
            mixColorWithShader = SEP_QS_EMPTY_LIGHT_MIX_COLOR;
        }
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShaderCreateMixColorConfig = createMixColorConfig(mixColorWithShader, z, f);
        ShaderBlendParamHelper.INSTANCE.updateMixMultiShaderParams(blurMixMultiWithShaderCreateMixColorConfig, 1.0f);
        return blurMixMultiWithShaderCreateMixColorConfig;
    }

    public static final BlurDrawable getSepEmptyHolderBlurDrawable(Context context, SurfaceControl surfaceControl, float f, boolean z, Float f2, float f3) {
        BlurMixConfig.BlurMixMultiWithShader sepEmptyHolderBlurConfig = getSepEmptyHolderBlurConfig(z, f3);
        BlurParam blurParam = new BlurParam(getDefaultBlurRadius(), 59);
        BlurDrawable blurDrawable = new BlurDrawable(context, surfaceControl);
        blurDrawable.setShaderBlendParams(sepEmptyHolderBlurConfig.getMixMultiShaderParams());
        blurDrawable.setBlurParams(blurParam);
        if (f2 == null) {
            blurDrawable.setCornerRadius(f);
            return blurDrawable;
        }
        blurDrawable.setSmoothCorner(f, f, f2.floatValue(), true);
        return blurDrawable;
    }

    public static /* synthetic */ BlurDrawable getSepEmptyHolderBlurDrawable$default(Context context, SurfaceControl surfaceControl, float f, boolean z, Float f2, int i) {
        if ((i & 8) != 0) {
            z = OplusDarkModeUtil.isNightMode(context);
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            f2 = null;
        }
        return getSepEmptyHolderBlurDrawable(context, surfaceControl, f, z2, f2, 1.0f);
    }

    public static final BlurMixConfig getSepInactiveBlurConfig(boolean z, boolean z2, float f) {
        return getInactiveBlurConfig(z, z2, f);
    }

    public static final BlurMixConfig getStdActiveBlurConfig(boolean z, float f) {
        return getActiveBlurConfig(z, f);
    }

    public static final BlurMixConfig getStdInactiveBlurConfig(boolean z, boolean z2, float f) {
        return getInactiveBlurConfig(z, z2, f);
    }

    public static final void setBlurDrawableTag(View view, Drawable drawable) {
        view.setTag(2131365552, drawable);
    }

    public static final void setGradientDrawableTag(View view, Drawable drawable) {
        view.setTag(2131365556, drawable);
    }

    public final int getDEFAULT_ACTIVE_DARK_BOTTOM() {
        return DEFAULT_ACTIVE_DARK_BOTTOM;
    }

    public final int getDEFAULT_ACTIVE_DARK_TOP() {
        return DEFAULT_ACTIVE_DARK_TOP;
    }

    public final int getDEFAULT_ACTIVE_LIGHT_BOTTOM() {
        return DEFAULT_ACTIVE_LIGHT_BOTTOM;
    }

    public final int getDEFAULT_ACTIVE_LIGHT_TOP() {
        return DEFAULT_ACTIVE_LIGHT_TOP;
    }

    public final int getDEFAULT_DARK_BOTTOM() {
        return DEFAULT_DARK_BOTTOM;
    }

    public final int getDEFAULT_DARK_BOTTOM_NO_STROKE() {
        return DEFAULT_DARK_BOTTOM_NO_STROKE;
    }

    public final int getDEFAULT_DARK_TOP() {
        return DEFAULT_DARK_TOP;
    }

    public final int getDEFAULT_DARK_TOP_NO_STROKE() {
        return DEFAULT_DARK_TOP_NO_STROKE;
    }

    public final int getDEFAULT_LIGHT_BOTTOM() {
        return DEFAULT_LIGHT_BOTTOM;
    }

    public final int getDEFAULT_LIGHT_BOTTOM_NO_STROKE() {
        return DEFAULT_LIGHT_BOTTOM_NO_STROKE;
    }

    public final int getDEFAULT_LIGHT_TOP() {
        return DEFAULT_LIGHT_TOP;
    }

    public final int getDEFAULT_LIGHT_TOP_NO_STROKE() {
        return DEFAULT_LIGHT_TOP_NO_STROKE;
    }

    public static final BlurMixConfig.BlurMixSingle createMotionBlurConfig(boolean z, int i, int i2) {
        return createMotionBlurConfig(z, i, i2, null);
    }

    public static final BlurDrawable getSepEmptyHolderBlurDrawable(Context context, SurfaceControl surfaceControl, float f, boolean z) {
        return getSepEmptyHolderBlurDrawable$default(context, surfaceControl, f, z, null, 48);
    }

    public static final BlurDrawable getSepEmptyHolderBlurDrawable(Context context, SurfaceControl surfaceControl, float f, boolean z, Float f2) {
        return getSepEmptyHolderBlurDrawable$default(context, surfaceControl, f, z, f2, 32);
    }

    public static final BlurDrawable getSepEmptyHolderBlurDrawable(Context context, SurfaceControl surfaceControl, float f) {
        return getSepEmptyHolderBlurDrawable$default(context, surfaceControl, f, false, null, 56);
    }
}
