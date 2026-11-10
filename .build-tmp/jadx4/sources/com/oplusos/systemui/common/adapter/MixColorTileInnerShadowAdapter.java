package com.oplusos.systemui.common.adapter;

import android.graphics.Color;
import com.oplus.posteffect.params.InnerShadowParams;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColorTileInnerShadowAdapter {
    private static final InnerShadowParams ACTIVE_HIGHLIGHT_ICON_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams ACTIVE_HIGHLIGHT_ICON_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams ACTIVE_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams ACTIVE_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams ACTIVE_SEEKBAR_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams ACTIVE_SEEKBAR_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams COMMON_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams COMMON_INNER_SHADOW_PARAMS_DARK;
    private static final Color DEFAULT_SHADOW_COLOR;
    private static final InnerShadowParams DIALOG_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams DIALOG_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams DIALOG_SEEKBAR_BG_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams DIALOG_SEEKBAR_BG_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams DIALOG_SEEKBAR_PG_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams DIALOG_SEEKBAR_PG_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams HIGHLIGHT_INACTIVE_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams HIGHLIGHT_INACTIVE_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams INACTIVE_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams INACTIVE_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams INACTIVE_SEEKBAR_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams INACTIVE_SEEKBAR_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams QSPLUGIN_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams QSPLUGIN_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams SEP_EDIT_RECT_BUTTON_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams SEP_EDIT_RECT_BUTTON_INNER_SHADOW_PARAMS_DARK;
    private static final InnerShadowParams SIMPLE_HEADER_INNER_SHADOW_PARAMS;
    private static final InnerShadowParams SIMPLE_HEADER_INNER_SHADOW_PARAMS_DARK;
    public static final MixColorTileInnerShadowAdapter INSTANCE = new MixColorTileInnerShadowAdapter();
    public static final boolean isEnableLightFeature = FeatureOption.getEnableLightFeature();

    static {
        Color colorValueOf = Color.valueOf(Color.parseColor("#FFFFFFFF"));
        DEFAULT_SHADOW_COLOR = colorValueOf;
        INACTIVE_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        INACTIVE_SEEKBAR_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 65.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        HIGHLIGHT_INACTIVE_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        DIALOG_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.05f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        ACTIVE_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        ACTIVE_SEEKBAR_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 80.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        ACTIVE_HIGHLIGHT_ICON_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        SIMPLE_HEADER_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.1f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        INACTIVE_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 25.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        ACTIVE_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        ACTIVE_SEEKBAR_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 80.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        INACTIVE_SEEKBAR_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 65.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        ACTIVE_HIGHLIGHT_ICON_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        SIMPLE_HEADER_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 30.0f, 0.0f, 20.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        DIALOG_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 25.0f, 1.3f, 1.05f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        DIALOG_SEEKBAR_BG_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 80.0f, 1.3f, 1.15f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        DIALOG_SEEKBAR_BG_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 75.0f, 1.3f, 1.15f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        DIALOG_SEEKBAR_PG_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 80.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        DIALOG_SEEKBAR_PG_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 80.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        COMMON_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 80.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        COMMON_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 65.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        HIGHLIGHT_INACTIVE_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        QSPLUGIN_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.15f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.05f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        QSPLUGIN_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.2f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        SEP_EDIT_RECT_BUTTON_INNER_SHADOW_PARAMS = new InnerShadowParams(colorValueOf, 0.1f, 0.0f, 0.0f, 40.0f, 0.0f, 36.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
        SEP_EDIT_RECT_BUTTON_INNER_SHADOW_PARAMS_DARK = new InnerShadowParams(colorValueOf, 0.07f, 0.0f, 0.0f, 30.0f, 0.0f, 20.0f, 1.3f, 1.3f, 20.0f, 0.5f, 6.0f, 20.0f, 0.3f);
    }

    public final InnerShadowParams getActiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return ACTIVE_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getActiveInnerShadowParams() {
        if (isEnableLightFeature) {
            return ACTIVE_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getCommonDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return COMMON_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getCommonInnerShadowParams() {
        if (isEnableLightFeature) {
            return COMMON_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getDialogDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return DIALOG_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getDialogInnerShadowParams() {
        if (isEnableLightFeature) {
            return DIALOG_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getDialogSeekBarBGInnerShadowParams() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_BG_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getDialogSeekbarPGDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_PG_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getDialogSeekbarPGInnerShadowParams() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_PG_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getHighlightIconActiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return ACTIVE_HIGHLIGHT_ICON_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getHighlightIconActiveInnerShadowParams() {
        if (isEnableLightFeature) {
            return ACTIVE_HIGHLIGHT_ICON_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getHighlightInactiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return HIGHLIGHT_INACTIVE_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getHighlightInactiveInnerShadowParams() {
        if (isEnableLightFeature) {
            return HIGHLIGHT_INACTIVE_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getInactiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return INACTIVE_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getInactiveInnerShadowParams() {
        if (isEnableLightFeature) {
            return INACTIVE_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getQSPluginDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return QSPLUGIN_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getQSPluginInnerShadowParams() {
        if (isEnableLightFeature) {
            return QSPLUGIN_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getSeekBarActiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return ACTIVE_SEEKBAR_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getSeekBarActiveInnerShadowParams() {
        if (isEnableLightFeature) {
            return ACTIVE_SEEKBAR_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getSeekBarBGInActiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_BG_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getSeekBarInActiveDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return INACTIVE_SEEKBAR_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getSeekBarInActiveInnerShadowParams() {
        if (isEnableLightFeature) {
            return INACTIVE_SEEKBAR_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getSepEditModeRectButtonDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return SEP_EDIT_RECT_BUTTON_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getSepEditModeRectButtonInnerShadowParams() {
        if (isEnableLightFeature) {
            return SEP_EDIT_RECT_BUTTON_INNER_SHADOW_PARAMS;
        }
        return null;
    }

    public final InnerShadowParams getSimpleHeaderDarkInnerShadowParams() {
        if (isEnableLightFeature) {
            return SIMPLE_HEADER_INNER_SHADOW_PARAMS_DARK;
        }
        return null;
    }

    public final InnerShadowParams getSimpleHeaderInnerShadowParams() {
        if (isEnableLightFeature) {
            return SIMPLE_HEADER_INNER_SHADOW_PARAMS;
        }
        return null;
    }
}
