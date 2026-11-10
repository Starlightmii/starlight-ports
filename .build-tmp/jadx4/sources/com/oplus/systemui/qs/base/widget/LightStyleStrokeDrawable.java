package com.oplus.systemui.qs.base.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.oplus.systemui.qs.base.res.util.StrokeCornerUtils;
import com.oplus.systemui.qs.base.widget.strokeshader.GradientStrokeShader;
import com.oplus.systemui.qs.base.widget.strokeshader.StrokeShaderProxy;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.outline.CornerOutlineProvider;
import com.oplusos.systemui.common.util.GradientStrokeLineAdapter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LightStyleStrokeDrawable extends AutoBlurDrawable {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final boolean isSupportStroke;
    public float lastCornerRadius;
    private final GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate;
    private final StrokeShaderProxy strokeShaderProxy;

    public LightStyleStrokeDrawable(ViewBlurProxy viewBlurProxy, Drawable drawable, GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate) {
        StrokeShaderProxy strokeShaderProxy;
        super(viewBlurProxy, drawable);
        this.strokeParamsTemplate = strokeParamsTemplate;
        boolean zIsSupportStroke = StrokeCornerUtils.isSupportStroke();
        this.isSupportStroke = zIsSupportStroke;
        if (!zIsSupportStroke || Intrinsics.areEqual(viewBlurProxy.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
            strokeShaderProxy = null;
        } else {
            strokeShaderProxy = new StrokeShaderProxy(viewBlurProxy.getView().getContext(), strokeParamsTemplate == null ? GradientStrokeLineAdapter.getMediaOutPutStrokeParamsTemplate(OplusDarkModeUtil.isNightMode(viewBlurProxy.getView().getContext()), true) : strokeParamsTemplate);
            float cornerRadius = viewBlurProxy.getBlurConfig().getCornerRadius();
            Float radiusWeight = viewBlurProxy.getBlurConfig().getRadiusWeight();
            strokeShaderProxy.setStrokeEnable(true);
            if (viewBlurProxy.getBlurConfig().getEnableMotionSmoothCorner()) {
                strokeShaderProxy.setCorner(GradientStrokeShader.CornerType.G2, cornerRadius, radiusWeight != null ? radiusWeight.floatValue() : 1.0f);
            } else if (radiusWeight == null) {
                strokeShaderProxy.setCorner(GradientStrokeShader.CornerType.G2, cornerRadius, 1.0f);
            } else {
                strokeShaderProxy.setCorner(GradientStrokeShader.CornerType.CONIC, cornerRadius, radiusWeight.floatValue());
            }
            LightStyleStrokeDrawable$$ExternalSyntheticLambda0 lightStyleStrokeDrawable$$ExternalSyntheticLambda0 = new LightStyleStrokeDrawable$$ExternalSyntheticLambda0();
            lightStyleStrokeDrawable$$ExternalSyntheticLambda0.f$0 = viewBlurProxy;
            lightStyleStrokeDrawable$$ExternalSyntheticLambda0.f$1 = drawable;
            VarHandle.storeStoreFence();
            strokeShaderProxy.setStrokeBackground(lightStyleStrokeDrawable$$ExternalSyntheticLambda0);
        }
        this.strokeShaderProxy = strokeShaderProxy;
    }

    @Override // com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        StrokeShaderProxy strokeShaderProxy = this.strokeShaderProxy;
        if (strokeShaderProxy != null) {
            strokeShaderProxy.drawShader(canvas, null);
            return;
        }
        if (this.isSupportStroke && this.lastCornerRadius != getViewBlurProxy().getBlurConfig().getCornerRadius()) {
            updateBlurConfig(getBounds());
        }
        super.draw(canvas);
    }

    public final StrokeShaderProxy getStrokeShaderProxy() {
        return this.strokeShaderProxy;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        StrokeShaderProxy strokeShaderProxy = this.strokeShaderProxy;
        if (strokeShaderProxy == null) {
            updateBlurConfig(rect);
            return;
        }
        strokeShaderProxy.layoutShader(rect.width(), rect.height());
        if (getViewBlurProxy().getBlurConfig().getEnableMotionSmoothCorner()) {
            getViewBlurProxy().applyBlurConfig();
        }
    }

    public final void updateBlurConfig(Rect rect) {
        if (getViewBlurProxy().getBlurConfig().getPlatformMixConfig() instanceof BlurMixConfig.BlurMixMultiWithShader) {
            int iWidth = rect.width();
            int iHeight = rect.height();
            float cornerRadius = getViewBlurProxy().getBlurConfig().getCornerRadius();
            this.lastCornerRadius = cornerRadius;
            CornerOutlineProvider.Companion.getClass();
            boolean zIsShapeNearCircle = CornerOutlineProvider.Companion.isShapeNearCircle(cornerRadius, iHeight, iWidth);
            GradientStrokeLineAdapter gradientStrokeLineAdapter = GradientStrokeLineAdapter.INSTANCE;
            BlurConfig blurConfig = getViewBlurProxy().getBlurConfig();
            Float radiusWeight = getViewBlurProxy().getBlurConfig().getRadiusWeight();
            GradientStrokeLineAdapter.StrokeParamsTemplate strokeParamsTemplate = this.strokeParamsTemplate;
            if (!this.isSupportStroke) {
                strokeParamsTemplate = null;
            }
            gradientStrokeLineAdapter.adaptGradientStrokeParams(blurConfig, cornerRadius, radiusWeight, iWidth, iHeight, zIsShapeNearCircle, strokeParamsTemplate);
            getViewBlurProxy().applyBlurConfig();
        }
    }
}
