package com.oplus.systemui.plugins.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MixColorPluginDrawable extends PluginDrawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MixColorPluginDrawable";
    private final AutoBlurDrawable autoBlurDrawable;
    private BuilderConfig config;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class BuilderConfig implements PluginDrawable.Builder {
        private final BlurMixConfig blurMixConfig;
        private final GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate;

        public BuilderConfig(BlurMixConfig blurMixConfig, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
            this.blurMixConfig = blurMixConfig;
            this.strokeParamsTemplate = strokeParamsTemplate;
        }

        @Override // com.oplus.systemui.plugins.drawable.PluginDrawable.Builder
        public PluginDrawable build(View view, boolean z) {
            Drawable blurDrawableTag = QSBlurConfigProvider.getBlurDrawableTag(view);
            MixColorPluginDrawable mixColorPluginDrawable = blurDrawableTag instanceof MixColorPluginDrawable ? (MixColorPluginDrawable) blurDrawableTag : null;
            if (mixColorPluginDrawable != null && z) {
                MixColorPluginDrawable.access$adaptToBuilder(mixColorPluginDrawable, this);
                return mixColorPluginDrawable;
            }
            ViewBlurProxy viewBlurProxy = new ViewBlurProxy(view, new BlurConfig(QSBlurConfigProvider.getDefaultBlurRadius(), 0, null, null, null, null, false, this.blurMixConfig, null, 0, null, 33488894), null, 28);
            viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE);
            LogUtil.i$default(LogUtil.TAG_QS, MixColorPluginDrawable.TAG, "build: config=" + this, null, 8);
            MixColorPluginDrawable mixColorPluginDrawable2 = new MixColorPluginDrawable(new AutoBlurDrawable(viewBlurProxy, null), this);
            QSBlurConfigProvider.setBlurDrawableTag(view, mixColorPluginDrawable2);
            return mixColorPluginDrawable2;
        }

        public final BlurMixConfig component1() {
            return this.blurMixConfig;
        }

        public final GradientStrokeLineAdapter.StrokeParamsTemplate component2() {
            return this.strokeParamsTemplate;
        }

        public final BuilderConfig copy(BlurMixConfig blurMixConfig, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
            return new BuilderConfig(blurMixConfig, strokeParamsTemplate);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BuilderConfig)) {
                return false;
            }
            BuilderConfig builderConfig = (BuilderConfig) obj;
            return Intrinsics.areEqual(this.blurMixConfig, builderConfig.blurMixConfig) && Intrinsics.areEqual(this.strokeParamsTemplate, builderConfig.strokeParamsTemplate);
        }

        public final BlurMixConfig getBlurMixConfig() {
            return this.blurMixConfig;
        }

        public final GradientStrokeLineAdapter.StrokeParamsTemplate getStrokeParamsTemplate() {
            return this.strokeParamsTemplate;
        }

        public final int hashCode() {
            int iHashCode = this.blurMixConfig.hashCode() * 31;
            GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate = this.strokeParamsTemplate;
            return iHashCode + (strokeParamsTemplate == null ? 0 : strokeParamsTemplate.hashCode());
        }

        public String toString() {
            return "BuilderConfig(blurMixConfig=" + this.blurMixConfig + ", strokeParamsTemplate=" + this.strokeParamsTemplate + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MixColorPluginDrawable(AutoBlurDrawable autoBlurDrawable, BuilderConfig builderConfig) {
        super(autoBlurDrawable);
        this.autoBlurDrawable = autoBlurDrawable;
        this.config = builderConfig;
    }

    public static final void access$adaptToBuilder(MixColorPluginDrawable mixColorPluginDrawable, BuilderConfig builderConfig) {
        if (builderConfig.equals(mixColorPluginDrawable.config)) {
            return;
        }
        mixColorPluginDrawable.config = builderConfig;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "updateDrawableConfig: builderConfig=" + builderConfig, null, 8);
        ViewBlurProxy viewBlurProxy = mixColorPluginDrawable.autoBlurDrawable.getViewBlurProxy();
        BlurMixConfig blurMixConfig = builderConfig.getBlurMixConfig();
        if (!Intrinsics.areEqual(viewBlurProxy.getBlurConfig().getPlatformMixConfig(), blurMixConfig)) {
            blurMixConfig.setAlphaWithBlurAmount(true);
            viewBlurProxy.getBlurConfig().setPlatformMixConfig(blurMixConfig);
            viewBlurProxy.applyBlurConfig();
        }
        mixColorPluginDrawable.invalidatePath();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (Intrinsics.areEqual(this.autoBlurDrawable.getViewBlurProxy().getBlurConfig().getPlatformMixConfig(), BlurMixConfig.None.INSTANCE)) {
            return;
        }
        super.draw(canvas);
    }

    @Override // com.oplus.systemui.plugins.drawable.PluginDrawable
    public final void invalidatePath() {
        ViewBlurProxy viewBlurProxy = this.autoBlurDrawable.getViewBlurProxy();
        RoundRectOutlineProvider roundRectOutlineProvider = (RoundRectOutlineProvider) getPathProvider();
        if (Intrinsics.areEqual(viewBlurProxy.getBlurConfig().getPathProvider(), roundRectOutlineProvider)) {
            viewBlurProxy.invalidatePath();
        } else {
            viewBlurProxy.getBlurConfig().setPathProvider(roundRectOutlineProvider);
        }
        RoundRectOutlineProvider roundRectOutlineProvider2 = (RoundRectOutlineProvider) getPathProvider();
        AutoBlurDrawable autoBlurDrawable = this.autoBlurDrawable;
        GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate = this.config.getStrokeParamsTemplate();
        if (strokeParamsTemplate == null || !this.isSupportStroke) {
            strokeParamsTemplate = null;
        }
        roundRectOutlineProvider2.updateStrokeParams(autoBlurDrawable, strokeParamsTemplate);
        this.config.getBlurMixConfig().setMirrorScale(getMirrorScaleValue());
        this.autoBlurDrawable.getViewBlurProxy().applyBlurConfig();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        invalidatePath();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        super.setAlpha(i);
        this.autoBlurDrawable.getViewBlurProxy().getBlurConfig().getPlatformMixConfig().setAlphaWithBlurAmount(true);
        this.autoBlurDrawable.getViewBlurProxy().setBlurAmount(RangesKt___RangesKt.coerceIn(i / 255.0f, 0.0f, 1.0f));
    }

    @Override // com.oplus.systemui.plugins.drawable.PluginDrawable
    public void setCornerRadius(float f, Float f2) {
        super.setCornerRadius(f, f2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setCornerRadius: radius=" + f + ", weight=" + f2, null, 8);
    }

    @Override // android.graphics.drawable.DrawableWrapper
    public void setDrawable(Drawable drawable) {
        if (getDrawable() == null) {
            super.setDrawable(drawable);
            return;
        }
        LogUtil.e$default(8, LogUtil.TAG_QS, TAG, "setDrawable: not allow update to " + drawable);
    }

    @Override // com.oplus.systemui.plugins.drawable.PluginDrawable
    public void setMaterialBlurEnable(boolean z) {
        super.setMaterialBlurEnable(z);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setMaterialBlur: materialBlur=", z), null, 8);
    }

    @Override // com.oplus.systemui.plugins.drawable.PluginDrawable
    public void setSupportStroke(boolean z) {
        super.setSupportStroke(z);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setSupportStroke: supportStroke=", z), null, 8);
    }
}
