package com.oplus.systemui.qs.detail.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.SepQSResPool;
import com.oplus.systemui.qs.base.res.StdQSResPool;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilder;
import com.oplus.systemui.qs.base.res.model.QSDrawableBuilderKt;
import com.oplus.systemui.qs.base.res.util.StrokeCornerUtils;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.widget.LightStyleStrokeDrawable;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusQsSmoothRoundUtil;
import com.oplusos.systemui.common.util.OplusRoundParams;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import com.oplusos.systemui.common.util.ScrimUtil;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsDetailBackgroundUtils {
    public static final QsDetailBackgroundUtils INSTANCE = new QsDetailBackgroundUtils();
    private static final String TAG = "QsDetailBackgroundUtils";

    public static final boolean isSupportBlurMix(Context context) {
        if (GaussBlurUtils.isGaussBlurDisabled()) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "isSupportBlurMix: isGaussBlurDisabled = true", null, 8);
            return false;
        }
        if (!ScrimUtil.isLowGaussianLevel(context)) {
            QSFeatureOption.INSTANCE.getClass();
            if (!QSFeatureOption.isDisableBGBlurNonAnim) {
                if (!BaseQSResPool.globalThemeApplied()) {
                    return true;
                }
                LogUtil.i$default(LogUtil.TAG_QS, TAG, "isSupportBlurMix: isGlobalTheme = true", null, 8);
                return false;
            }
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "isSupportBlurMix: isLowGaussianLevel = true", null, 8);
        return false;
    }

    public static final Drawable provideBlurBackground(View view, Drawable drawable, Float f, boolean z) {
        Context context = view.getContext();
        context.getClass();
        if (!isSupportBlurMix(context)) {
            return drawable;
        }
        BlurConfig blurConfig = new BlurConfig(0, 0, null, null, null, null, false, null, null, 0, null, 33554431);
        blurConfig.setBlurRadius(QSBlurConfigProvider.getDefaultDialogBlurRadius());
        float fFloatValue = f != null ? f.floatValue() : context.getResources().getDimensionPixelSize(2131171633);
        OplusRoundParams roundParams = OplusQsSmoothRoundUtil.getRoundParams(context, fFloatValue);
        if (roundParams != null) {
            blurConfig.setCornerRadius(roundParams.getRadius());
            blurConfig.setRadiusWeight(Float.valueOf(roundParams.getWeight()));
        } else {
            blurConfig.setCornerRadius(fFloatValue);
        }
        BlurMixConfig dialogPlatformBlurConfig = QSBlurConfigProvider.getDialogPlatformBlurConfig(context.getResources().getConfiguration().isNightModeActive(), StrokeCornerUtils.isSupportStroke(), BaseQSResPool.getMirrorBlurScaleValue());
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "QsDetailBackgroundUtils-->".concat("provideBlurBackground config=" + dialogPlatformBlurConfig + ", enableStroke=" + z));
        }
        blurConfig.setPlatformMixConfig(dialogPlatformBlurConfig);
        ViewBlurProxy viewBlurProxy = new ViewBlurProxy(view, blurConfig, null, 28);
        viewBlurProxy.setBlurType(z ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
        return new LightStyleStrokeDrawable(viewBlurProxy, drawable, null);
    }

    public static final Drawable provideBlurBackground(View view) {
        StateFlow detailPanelDrawable = QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() ? SepQSResPool.INSTANCE.getDetailPanelDrawable() : StdQSResPool.INSTANCE.getDetailPanelDrawable();
        Drawable background = view.getBackground();
        TileDrawableWrapper tileDrawableWrapper = background instanceof TileDrawableWrapper ? (TileDrawableWrapper) background : null;
        if (tileDrawableWrapper == null) {
            tileDrawableWrapper = new TileDrawableWrapper(null);
        }
        tileDrawableWrapper.setDrawable(QSDrawableBuilderKt.dialogBuild((QSDrawableBuilder) detailPanelDrawable.getValue(), view, true));
        tileDrawableWrapper.setPathProvider((CornerOutlineProvider) BaseQSResPool.INSTANCE.getDetailPanelOutline().getValue());
        return tileDrawableWrapper;
    }
}
