package com.oplus.systemui.qs.base.util;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsSeekBarBlurManager {
    public static final QsSeekBarBlurManager INSTANCE = new QsSeekBarBlurManager();
    private static final String TAG = "QsSeekBarBlurManager";

    public final void applyBlurConfig(Rect rect, float f, float f2, AutoBlurDrawable autoBlurDrawable, boolean z, boolean z2, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate, BlurMixConfig blurMixConfig) {
        BlurConfig blurConfig = autoBlurDrawable.getViewBlurProxy().getBlurConfig();
        blurConfig.setRadiusWeight(Float.valueOf(f2));
        blurConfig.setCornerRadius(f);
        blurConfig.setPlatformMixConfig(blurMixConfig);
        blurConfig.setEnableStaticBlurCorner(z || blurConfig.getEnableStaticBlurCorner());
        GradientStrokeLineAdapter.INSTANCE.adaptGradientStrokeParams(blurConfig, f, blurConfig.getRadiusWeight(), rect.width(), rect.height(), z2, strokeParamsTemplate);
        autoBlurDrawable.getViewBlurProxy().applyBlurConfig();
    }

    public final void applySeekBarActiveBlurConfig(View view, Rect rect, float f, float f2, AutoBlurDrawable autoBlurDrawable, boolean z, float f3, boolean z2) {
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(view.getContext());
        applyBlurConfig(rect, f, f2, autoBlurDrawable, z2, false, GradientStrokeLineAdapter.getQsSeekBarPGBlurLightConfig(zIsNightMode, z2, z), QSBlurConfigProvider.getSeekBarActiveBlurConfig(zIsNightMode, f3));
    }

    public final void applySeekBarBgBlurConfig(Context context, Rect rect, float f, float f2, AutoBlurDrawable autoBlurDrawable, boolean z, boolean z2, float f3) {
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(context);
        applyBlurConfig(rect, f, f2, autoBlurDrawable, z, false, GradientStrokeLineAdapter.getQsSeekBarBGBlurLightConfig(zIsNightMode, z, z2), QSBlurConfigProvider.getSeekBarInactiveBlurConfig(zIsNightMode, z, f3));
    }

    public final Drawable createSeekBarBlurDrawable(View view, Rect rect, float f, float f2, boolean z, boolean z2, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate, BlurMixConfig blurMixConfig) {
        BlurConfig blurConfig = new BlurConfig(QSBlurConfigProvider.getDefaultBlurRadius(), 0, null, null, null, null, false, null, null, 0, null, 33554430);
        blurConfig.setCornerRadius(f);
        StaticBlurManager staticBlurManager = null;
        blurConfig.setRadiusWeight((z || f2 == 0.0f) ? null : Float.valueOf(f2));
        blurConfig.setEnableStaticBlurCorner(true);
        GradientStrokeLineAdapter.INSTANCE.adaptGradientStrokeParams(blurConfig, f, blurConfig.getRadiusWeight(), rect.width(), rect.height(), z, strokeParamsTemplate);
        if (!Intrinsics.areEqual(blurConfig.getPlatformMixConfig(), blurMixConfig)) {
            blurConfig.setPlatformMixConfig(blurMixConfig);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QsSeekBarBlurManager-->".concat("getSeekBarBackground: view:" + view + " isSupportStroke:" + z2 + " cornerRadius:" + f + " mixConfig = " + blurMixConfig + ", isCircleShape=" + z + ", radiusWeight=" + blurConfig.getRadiusWeight()));
        }
        ViewBlurProxy viewBlurProxy = new ViewBlurProxy(view, blurConfig, staticBlurManager, 28);
        viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
        viewBlurProxy.setBlurAmount(1.0f);
        return new AutoBlurDrawable(viewBlurProxy, null);
    }

    public final Drawable getSeekBarActiveDrawable(View view, Rect rect, float f, float f2, boolean z, boolean z2, float f3) {
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(view.getContext());
        return createSeekBarBlurDrawable(view, rect, f, f2, false, z, GradientStrokeLineAdapter.getQsSeekBarPGBlurLightConfig(zIsNightMode, z, z2), QSBlurConfigProvider.getSeekBarActiveBlurConfig(zIsNightMode, f3));
    }

    public final Drawable getSeekBarBackground(View view, Rect rect, float f, float f2, boolean z, boolean z2, float f3) {
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(view.getContext());
        return createSeekBarBlurDrawable(view, rect, f, f2, false, z, GradientStrokeLineAdapter.getQsSeekBarBGBlurLightConfig(zIsNightMode, z, z2), QSBlurConfigProvider.getSeekBarInactiveBlurConfig(zIsNightMode, z, f3));
    }
}
