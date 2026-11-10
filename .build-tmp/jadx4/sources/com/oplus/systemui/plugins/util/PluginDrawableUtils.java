package com.oplus.systemui.plugins.util;

import android.content.Context;
import com.oplus.systemui.plugins.drawable.MixColorPluginDrawable;
import com.oplus.systemui.plugins.drawable.PluginDrawable;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PluginDrawableUtils {
    public static final PluginDrawableUtils INSTANCE = new PluginDrawableUtils();
    public static boolean supportStroke;

    public static final PluginDrawable.Builder getSepQSActiveBlurDrawableBuilder(Context context, boolean z) {
        return new MixColorPluginDrawable.BuilderConfig(QSBlurConfigProvider.getSepActiveBlurConfig(z, 1.0f), GradientStrokeLineAdapter.getQSPluginBlurLightConfig(z, true));
    }

    public static final PluginDrawable.Builder getSepQSInactiveBlurDrawableBuilder(Context context, boolean z) {
        return new MixColorPluginDrawable.BuilderConfig(QSBlurConfigProvider.getSepInactiveBlurConfig(z, supportStroke, 1.0f), GradientStrokeLineAdapter.getQSPluginBlurLightConfig(z, true));
    }

    public static final PluginDrawable.Builder getStdQSActiveBlurDrawableBuilder(Context context, boolean z) {
        return new MixColorPluginDrawable.BuilderConfig(QSBlurConfigProvider.getStdActiveBlurConfig(z, 1.0f), GradientStrokeLineAdapter.getQSPluginBlurLightConfig(z, true));
    }

    public static final PluginDrawable.Builder getStdQSInactiveBlurDrawableBuilder(Context context, boolean z) {
        return new MixColorPluginDrawable.BuilderConfig(QSBlurConfigProvider.getStdInactiveBlurConfig(z, supportStroke, 1.0f), GradientStrokeLineAdapter.getQSPluginBlurLightConfig(z, true));
    }

    public final Pair<Float, Float> getSmoothRoundParams(Context context, float f) {
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, f);
        return roundParams != null ? new Pair<>(Float.valueOf(roundParams.getRadius()), Float.valueOf(roundParams.getWeight())) : new Pair<>(Float.valueOf(f), (Object) null);
    }

    public final boolean getSupportStroke() {
        return supportStroke;
    }

    public final boolean isNightMode(Context context) {
        return context.getResources().getConfiguration().isNightModeActive();
    }

    public final void setSupportStroke(boolean z) {
        supportStroke = z;
    }

    public static final PluginDrawable.Builder getSepQSActiveBlurDrawableBuilder(Context context) {
        return getSepQSActiveBlurDrawableBuilder(context, INSTANCE.isNightMode(context));
    }

    public static final PluginDrawable.Builder getStdQSActiveBlurDrawableBuilder(Context context) {
        return getStdQSActiveBlurDrawableBuilder(context, INSTANCE.isNightMode(context));
    }

    public static final PluginDrawable.Builder getSepQSInactiveBlurDrawableBuilder(Context context) {
        return getSepQSInactiveBlurDrawableBuilder(context, INSTANCE.isNightMode(context));
    }

    public static final PluginDrawable.Builder getStdQSInactiveBlurDrawableBuilder(Context context) {
        return getStdQSInactiveBlurDrawableBuilder(context, INSTANCE.isNightMode(context));
    }
}
