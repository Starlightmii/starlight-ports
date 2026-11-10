package com.oplusos.systemui.common.adapter;

import android.graphics.Color;
import com.oplus.posteffect.params.GradientStrokeLineParams;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColorTileStrokeLineAdapter {
    private static final GradientStrokeLineParams ACTIVE_STROKE_LINE_PARAMS;
    private static final GradientStrokeLineParams ACTIVE_STROKE_LINE_PARAMS_DARK;
    private static final Color DEFAULT_ACTIVE_STROKE_LINE_COLOR;
    private static final Color DEFAULT_DARK_ACTIVE_STROKE_LINE_COLOR;
    private static final Color DEFAULT_DARK_INACTIVE_STROKE_LINE_COLOR;
    private static final Color DEFAULT_INACTIVE_STROKE_LINE_COLOR;
    private static final GradientStrokeLineParams DIALOG_STROKE_LINE_PARAMS;
    private static final GradientStrokeLineParams DIALOG_STROKE_LINE_PARAMS_DARK;
    private static final GradientStrokeLineParams INACTIVE_STROKE_LINE_PARAMS;
    private static final GradientStrokeLineParams INACTIVE_STROKE_LINE_PARAMS_DARK;
    public static final MixColorTileStrokeLineAdapter INSTANCE;
    private static final GradientStrokeLineParams MEDIA_OUT_PUT_STROKE_LINE_PARAMS;
    private static final GradientStrokeLineParams MEDIA_OUT_PUT_STROKE_LINE_PARAMS_DARK;

    static {
        MixColorTileStrokeLineAdapter mixColorTileStrokeLineAdapter = new MixColorTileStrokeLineAdapter();
        INSTANCE = mixColorTileStrokeLineAdapter;
        Color colorValueOf = Color.valueOf(Color.parseColor("#C4FFFFFF"));
        DEFAULT_ACTIVE_STROKE_LINE_COLOR = colorValueOf;
        Color colorValueOf2 = Color.valueOf(Color.parseColor("#D8FFFFFF"));
        DEFAULT_INACTIVE_STROKE_LINE_COLOR = colorValueOf2;
        Color colorValueOf3 = Color.valueOf(Color.parseColor("#C4FFFFFF"));
        DEFAULT_DARK_ACTIVE_STROKE_LINE_COLOR = colorValueOf3;
        Color colorValueOf4 = Color.valueOf(Color.parseColor("#A5FFFFFF"));
        DEFAULT_DARK_INACTIVE_STROKE_LINE_COLOR = colorValueOf4;
        INACTIVE_STROKE_LINE_PARAMS = new GradientStrokeLineParams(colorValueOf2, 2.0f, 7.0f, 2.0f, 4.0f, 0.25f, 0.6f, 0.25f, 0.35f, 0.15f, 0.35f, 0.0f, 10.0f, 0.5f);
        ACTIVE_STROKE_LINE_PARAMS = new GradientStrokeLineParams(colorValueOf, 1.0f, 8.0f, 2.0f, 1.0f, 0.5f, 0.5f, 0.3f, 0.3f, 0.3f, 0.3f, 0.0f, 10.0f, 0.5f);
        INACTIVE_STROKE_LINE_PARAMS_DARK = mixColorTileStrokeLineAdapter.withAlpha(new GradientStrokeLineParams(colorValueOf4, 2.0f, 7.0f, 2.0f, 4.0f, 0.7f, 0.8f, 0.25f, 0.35f, 0.15f, 0.35f, 0.0f, 10.0f, 0.5f), 0.5f);
        ACTIVE_STROKE_LINE_PARAMS_DARK = new GradientStrokeLineParams(colorValueOf3, 1.0f, 8.0f, 2.0f, 1.0f, 0.5f, 0.5f, 0.3f, 0.3f, 0.3f, 0.3f, 0.0f, 10.0f, 0.5f);
        DIALOG_STROKE_LINE_PARAMS = new GradientStrokeLineParams(colorValueOf2, 2.0f, 7.0f, 2.0f, 4.0f, 0.25f, 0.6f, 0.2f, 0.25f, 0.05f, 0.35f, 0.0f, 10.0f, 0.5f);
        DIALOG_STROKE_LINE_PARAMS_DARK = mixColorTileStrokeLineAdapter.withAlpha(new GradientStrokeLineParams(colorValueOf4, 2.0f, 7.0f, 2.0f, 4.0f, 0.7f, 0.8f, 0.25f, 0.25f, 0.05f, 0.35f, 0.0f, 10.0f, 0.5f), 0.5f);
        MEDIA_OUT_PUT_STROKE_LINE_PARAMS = new GradientStrokeLineParams(colorValueOf2, 1.0f, 2.5f, 1.0f, 2.5f, 0.2f, 0.2f, 0.15f, 0.3f, 0.15f, 0.4f, 0.0f, 4.46f, 0.5f);
        MEDIA_OUT_PUT_STROKE_LINE_PARAMS_DARK = new GradientStrokeLineParams(colorValueOf4, 1.0f, 2.5f, 1.0f, 2.5f, 0.2f, 0.2f, 0.15f, 0.3f, 0.15f, 0.4f, 0.0f, 4.46f, 0.5f);
    }

    public final GradientStrokeLineParams getActiveDarkStrokeLineParams() {
        return ACTIVE_STROKE_LINE_PARAMS_DARK;
    }

    public final GradientStrokeLineParams getActiveStrokeLineParams() {
        return ACTIVE_STROKE_LINE_PARAMS;
    }

    public final GradientStrokeLineParams getDialogDarkStrokeLineParams() {
        return DIALOG_STROKE_LINE_PARAMS_DARK;
    }

    public final GradientStrokeLineParams getDialogStrokeLineParams() {
        return DIALOG_STROKE_LINE_PARAMS;
    }

    public final GradientStrokeLineParams getInactiveDarkStrokeLineParams() {
        return INACTIVE_STROKE_LINE_PARAMS_DARK;
    }

    public final GradientStrokeLineParams getInactiveStrokeLineParams() {
        return INACTIVE_STROKE_LINE_PARAMS;
    }

    public final GradientStrokeLineParams getMediaOutPutDarkStrokeLineParams() {
        return MEDIA_OUT_PUT_STROKE_LINE_PARAMS_DARK;
    }

    public final GradientStrokeLineParams getMediaOutPutStrokeLineParams() {
        return MEDIA_OUT_PUT_STROKE_LINE_PARAMS;
    }

    public final GradientStrokeLineParams withAlpha(GradientStrokeLineParams gradientStrokeLineParams, float f) {
        gradientStrokeLineParams.setStrokeLineAlphaNear(gradientStrokeLineParams.getStrokeLineAlphaNear() * f);
        gradientStrokeLineParams.setStrokeLineAlphaFar(gradientStrokeLineParams.getStrokeLineAlphaFar() * f);
        return gradientStrokeLineParams;
    }
}
