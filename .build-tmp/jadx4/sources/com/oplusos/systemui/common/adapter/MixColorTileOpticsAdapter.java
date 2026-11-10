package com.oplusos.systemui.common.adapter;

import android.graphics.Color;
import com.oplus.posteffect.params.OpticsParams;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColorTileOpticsAdapter {
    private static final OpticsParams ACTIVE_HIGHLIGHT_ICON_OPTICS_PARAMS;
    private static final OpticsParams ACTIVE_HIGHLIGHT_ICON_OPTICS_PARAMS_DARK;
    private static final OpticsParams ACTIVE_OPTICS_PARAMS;
    private static final OpticsParams ACTIVE_OPTICS_PARAMS_DARK;
    private static final OpticsParams ACTIVE_SEEKBAR_OPTICS_PARAMS;
    private static final OpticsParams ACTIVE_SEEKBAR_OPTICS_PARAMS_DARK;
    private static final OpticsParams COMMON_OPTICS_PARAMS;
    private static final OpticsParams COMMON_OPTICS_PARAMS_DARK;
    private static final Color DEFAULT_OPTICS_COLOR;
    private static final OpticsParams DIALOG_OPTICS_PARAMS;
    private static final OpticsParams DIALOG_OPTICS_PARAMS_DARK;
    private static final OpticsParams DIALOG_SEEKBAR_BG_OPTICS_PARAMS;
    private static final OpticsParams DIALOG_SEEKBAR_BG_OPTICS_PARAMS_DARK;
    private static final OpticsParams DIALOG_SEEKBAR_PG_OPTICS_PARAMS;
    private static final OpticsParams DIALOG_SEEKBAR_PG_OPTICS_PARAMS_DARK;
    private static final OpticsParams HIGHLIGHT_INACTIVE_OPTICS_PARAMS;
    private static final OpticsParams HIGHLIGHT_OPTICS_PARAMS_DARK;
    private static final OpticsParams INACTIVE_OPTICS_PARAMS;
    private static final OpticsParams INACTIVE_OPTICS_PARAMS_DARK;
    private static final OpticsParams INACTIVE_SEEKBAR_OPTICS_PARAMS;
    private static final OpticsParams INACTIVE_SEEKBAR_OPTICS_PARAMS_DARK;
    private static final OpticsParams QSPLUGIN_OPTICS_PARAMS;
    private static final OpticsParams QSPLUGIN_OPTICS_PARAMS_DARK;
    private static final OpticsParams SCENARIO_CAPSULE_OPTICS_PARAMS;
    private static final OpticsParams SCENARIO_CAPSULE_OPTICS_PARAMS_DARK;
    private static final OpticsParams SEP_EDIT_RECT_BUTTON_OPTICS_PARAMS;
    private static final OpticsParams SEP_EDIT_RECT_BUTTON_OPTICS_PARAMS_DARK;
    private static final OpticsParams SIMPLE_HEADER_OPTICS_PARAMS;
    private static final OpticsParams SIMPLE_HEADER_OPTICS_PARAMS_DARK;
    public static final MixColorTileOpticsAdapter INSTANCE = new MixColorTileOpticsAdapter();
    public static final boolean isEnableLightFeature = FeatureOption.getEnableLightFeature();

    static {
        Color colorValueOf = Color.valueOf(-1);
        DEFAULT_OPTICS_COLOR = colorValueOf;
        INACTIVE_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.65f, 0.65f, 60.0f);
        INACTIVE_SEEKBAR_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.65f, 0.85f, 60.0f);
        HIGHLIGHT_INACTIVE_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.85f, 0.65f, 60.0f);
        COMMON_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.85f, 0.85f, 100.0f);
        DIALOG_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.9f, 0.95f, 130.0f);
        SIMPLE_HEADER_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.65f, 0.65f, 25.0f);
        SCENARIO_CAPSULE_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.9f, 0.6f, 25.0f);
        ACTIVE_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.65f, 60.0f);
        ACTIVE_SEEKBAR_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.85f, 60.0f);
        ACTIVE_HIGHLIGHT_ICON_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.65f, 60.0f);
        ACTIVE_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.65f, 80.0f);
        INACTIVE_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.65f, 0.65f, 60.0f);
        ACTIVE_SEEKBAR_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.85f, 80.0f);
        INACTIVE_SEEKBAR_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.65f, 0.85f, 60.0f);
        DIALOG_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.9f, 0.95f, 130.0f);
        SIMPLE_HEADER_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.65f, 0.65f, 25.0f);
        SCENARIO_CAPSULE_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.9f, 0.65f, 25.0f);
        HIGHLIGHT_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.85f, 0.65f, 60.0f);
        ACTIVE_HIGHLIGHT_ICON_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.65f, 80.0f);
        DIALOG_SEEKBAR_BG_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.65f, 0.85f, 150.0f);
        DIALOG_SEEKBAR_BG_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.65f, 0.85f, 150.0f);
        DIALOG_SEEKBAR_PG_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.85f, 150.0f);
        DIALOG_SEEKBAR_PG_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.1f, 0.0f, 0.0f, 0.65f, 0.85f, 130.0f);
        COMMON_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.85f, 0.85f, 100.0f);
        QSPLUGIN_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.9f, 0.95f, 80.0f);
        QSPLUGIN_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.9f, 0.95f, 80.0f);
        SEP_EDIT_RECT_BUTTON_OPTICS_PARAMS = new OpticsParams(colorValueOf, 0.05f, 0.0f, 0.0f, 0.85f, 0.6f, 25.0f);
        SEP_EDIT_RECT_BUTTON_OPTICS_PARAMS_DARK = new OpticsParams(colorValueOf, 0.01f, 0.0f, 0.0f, 0.85f, 0.65f, 25.0f);
    }

    public final OpticsParams getCommonDarkOpticsParams() {
        if (isEnableLightFeature) {
            return COMMON_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getCommonOpticsParams() {
        if (isEnableLightFeature) {
            return COMMON_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getDialogDarkOpticsParams() {
        if (isEnableLightFeature) {
            return DIALOG_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getDialogOpticsParams() {
        if (isEnableLightFeature) {
            return DIALOG_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getDialogSeekbarBGOpticsParams() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_BG_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getDialogSeekbarBGOpticsParamsDark() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_BG_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getDialogSeekbarPGOpticsParams() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_PG_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getDialogSeekbarPGOpticsParamsDark() {
        if (isEnableLightFeature) {
            return DIALOG_SEEKBAR_PG_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getHighlightIconActiveDarkParams() {
        if (isEnableLightFeature) {
            return ACTIVE_HIGHLIGHT_ICON_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getHighlightIconActiveParams() {
        if (isEnableLightFeature) {
            return ACTIVE_HIGHLIGHT_ICON_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getHighlightInactiveDarkOpticsParams() {
        if (isEnableLightFeature) {
            return HIGHLIGHT_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getHighlightInactiveOpticsParams() {
        if (isEnableLightFeature) {
            return HIGHLIGHT_INACTIVE_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getQSPluginDarkOpticsParams() {
        if (isEnableLightFeature) {
            return QSPLUGIN_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getQSPluginOpticsParams() {
        if (isEnableLightFeature) {
            return QSPLUGIN_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getSeekbarActiveDarkOpticsParams() {
        if (isEnableLightFeature) {
            return ACTIVE_SEEKBAR_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getSeekbarActiveOpticsParams() {
        if (isEnableLightFeature) {
            return ACTIVE_SEEKBAR_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getSeekbarInActiveDarkOpticsParams() {
        if (isEnableLightFeature) {
            return INACTIVE_SEEKBAR_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getSeekbarInActiveOpticsParams() {
        if (isEnableLightFeature) {
            return INACTIVE_SEEKBAR_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getSepEditModeRectButtonDarkOpticsParams() {
        if (isEnableLightFeature) {
            return SEP_EDIT_RECT_BUTTON_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getSepEditModeRectButtonOpticsParams() {
        if (isEnableLightFeature) {
            return SEP_EDIT_RECT_BUTTON_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getSepScenarioCapsuleDarkOpticsParams() {
        if (isEnableLightFeature) {
            return SCENARIO_CAPSULE_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getSepScenarioCapsuleOpticsParams() {
        if (isEnableLightFeature) {
            return SCENARIO_CAPSULE_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getSimpleHeaderDarkOpticsParams() {
        if (isEnableLightFeature) {
            return SIMPLE_HEADER_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getSimpleHeaderOpticsParams() {
        if (isEnableLightFeature) {
            return SIMPLE_HEADER_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getTileActiveDarkOpticsParams() {
        if (isEnableLightFeature) {
            return ACTIVE_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getTileActiveOpticsParams() {
        if (isEnableLightFeature) {
            return ACTIVE_OPTICS_PARAMS;
        }
        return null;
    }

    public final OpticsParams getTileInactiveDarkOpticsParams() {
        if (isEnableLightFeature) {
            return INACTIVE_OPTICS_PARAMS_DARK;
        }
        return null;
    }

    public final OpticsParams getTileInactiveOpticsParams() {
        if (isEnableLightFeature) {
            return INACTIVE_OPTICS_PARAMS;
        }
        return null;
    }
}
