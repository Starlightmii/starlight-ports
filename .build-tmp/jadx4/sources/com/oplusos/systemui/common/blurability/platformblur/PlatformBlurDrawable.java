package com.oplusos.systemui.common.blurability.platformblur;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.oplus.posteffect.BlendParam;
import com.oplus.posteffect.BlurDrawable;
import com.oplus.posteffect.BlurParam;
import com.oplus.posteffect.ForegroundBlurParam;
import com.oplus.posteffect.drawable.MetaBallBlurDrawable;
import com.oplus.posteffect.params.CornerParams;
import com.oplus.posteffect.params.CornerType;
import com.oplus.posteffect.path.BlurDrawablePathProvider;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.MixColor;
import com.oplusos.systemui.common.util.GlobalUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ShaderBlendParamHelper;
import kotlin.Deprecated;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlatformBlurDrawable extends Drawable implements Drawable.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PlatformBlurDrawable";
    private BlurMixConfig aodBlurMixConfig;
    private final BlurDrawable blurDrawable;
    public int blurMaskColor;
    private BlurParam blurParam;
    private BlurMixConfig currentBlurMixConfig = BlurMixConfig.None.INSTANCE;
    private final CornerParams defaultCornerParams = new CornerParams(7);
    public boolean enableBlurMaskColor = true;
    private final Function0 getHostViewName;
    public boolean hasDrawnBlurContent;
    public boolean hasReceivedBlurContent;
    public boolean isAodDirty;
    public boolean isAodState;
    private final Paint maskPaint;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PlatformBlurDrawable(BlurDrawable blurDrawable, Function0 function0) {
        this.blurDrawable = blurDrawable;
        this.getHostViewName = function0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.blurMaskColor);
        this.maskPaint = paint;
        blurDrawable.setCallback(this);
    }

    public static void applyMixColorAndScale(BlurParam blurParam, MixColor mixColor, float f, float f2) {
        int mode = mixColor.getMode();
        int topLayerColor = mixColor.getTopLayerColor();
        int alphaComponent = ColorUtils.setAlphaComponent(topLayerColor, (int) (Color.alpha(topLayerColor) * f2));
        int bottomLayerColor = mixColor.getBottomLayerColor();
        blurParam.setMaterialParams(mode, alphaComponent, ColorUtils.setAlphaComponent(bottomLayerColor, (int) (Color.alpha(bottomLayerColor) * f2)), BlurParam.Wrapping.MIRRORED_REPEAT, f, -1);
    }

    private final void setBlurMaskColor(int i) {
        LogBuffer logBuffer;
        if (this.blurMaskColor != i) {
            if (LogUtil.panicEnabled && (logBuffer = DebugTool.INSTANCE.getLogBuffer()) != null) {
                logBuffer.log(TAG, LogLevel.DEBUG, "[setBlurMaskColor] " + Integer.toHexString(i) + " for " + ((String) this.getHostViewName.invoke()), (Throwable) null);
            }
            this.blurMaskColor = i;
            this.maskPaint.setColor(i);
        }
    }

    public final BlurDrawable applyBlurConfig(BlurConfig blurConfig, float f) {
        int iCoerceIn;
        BlurDrawable blurDrawable = this.blurDrawable;
        setBlurMaskColor(0);
        float mirrorScale = blurConfig.getPlatformMixConfig().getMirrorScale();
        this.currentBlurMixConfig = blurConfig.getPlatformMixConfig();
        BlurMixConfig platformMixConfig = blurConfig.getPlatformMixConfig();
        if ((platformMixConfig instanceof BlurMixConfig.None) || (platformMixConfig instanceof BlurMixConfig.OverlayColor)) {
            BlurParam blurParam = new BlurParam(0, 63);
            blurParam.setBlurRadius(blurConfig.platformBlurRadiusForAmount(f));
            blurDrawable.setBlurParamsSync(blurParam);
            if (platformMixConfig instanceof BlurMixConfig.OverlayColor) {
                BlurMixConfig.OverlayColor overlayColor = (BlurMixConfig.OverlayColor) platformMixConfig;
                ShaderBlendParamHelper.INSTANCE.updateOverlayColorShaderParams(overlayColor, blurConfig.getBlurColor());
                blurDrawable.setShaderBlendParams(overlayColor.getMixOverlayColorShaderParams());
                setBlurMaskColor(blurConfig.getBlurColor());
            }
        } else if (platformMixConfig instanceof BlurMixConfig.BlurMixSingle) {
            BlurParam blurParam2 = new BlurParam(0, 63);
            applyMixColorAndScale(blurParam2, ((BlurMixConfig.BlurMixSingle) platformMixConfig).getMixColor(), mirrorScale, f);
            blurParam2.setBlurRadius(blurConfig.platformBlurRadiusForAmount(f));
            blurDrawable.setBlurParamsSync(blurParam2);
        } else if (platformMixConfig instanceof BlurMixConfig.BlurMixMulti) {
            BlurMixConfig.BlurMixMulti blurMixMulti = (BlurMixConfig.BlurMixMulti) platformMixConfig;
            BlurParam blurParam3 = new BlurParam(0, 63);
            applyMixColorAndScale(blurParam3, blurMixMulti.getBackgroundMixColor(), mirrorScale, f);
            blurParam3.setBlurRadius(blurConfig.platformBlurRadiusForAmount(f));
            blurParam3.setForegroundBlendParams(new ForegroundBlurParam(blurMixMulti.getForegroundMixColor().getMode(), blurMixMulti.getForegroundMixColor().getTopLayerColor(), blurMixMulti.getForegroundMixColor().getBottomLayerColor()));
            blurDrawable.setBlurParamsSync(blurParam3);
        } else if (platformMixConfig instanceof BlurMixConfig.BlurMixSingleWithShader) {
            BlurMixConfig.BlurMixSingleWithShader blurMixSingleWithShader = (BlurMixConfig.BlurMixSingleWithShader) platformMixConfig;
            if (this.blurParam == null) {
                this.blurParam = new BlurParam(0, 63);
            }
            BlurParam blurParam4 = this.blurParam;
            if (blurParam4 != null) {
                ShaderBlendParamHelper.INSTANCE.updateMixSingleShaderParams(blurMixSingleWithShader, f);
                blurParam4.getBaseParam().setZoomScale(mirrorScale);
                float f2 = GlobalUtils.panelClosedOnDown ? 25.0f : 15.0f;
                int iPlatformBlurRadiusForAmount = blurConfig.platformBlurRadiusForAmount(f);
                if (!blurConfig.getPlatformMixConfig().getAlphaWithBlurAmount()) {
                    if (f == 0.0f) {
                        iPlatformBlurRadiusForAmount = 0;
                    } else {
                        int i = (int) f2;
                        if (iPlatformBlurRadiusForAmount < i) {
                            iPlatformBlurRadiusForAmount = i;
                        }
                    }
                }
                blurParam4.setBlurRadius(iPlatformBlurRadiusForAmount);
                blurDrawable.setEnableBlurShader(true);
                blurDrawable.setShaderBlendParams(blurMixSingleWithShader.getMixSingleShaderParams());
                blurDrawable.setBlurParamsSync(blurParam4);
            }
        } else {
            if (!(platformMixConfig instanceof BlurMixConfig.BlurMixMultiWithShader)) {
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return null;
            }
            BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader = (BlurMixConfig.BlurMixMultiWithShader) platformMixConfig;
            if (this.blurParam == null) {
                this.blurParam = new BlurParam(0, 63);
            }
            BlurParam blurParam5 = this.blurParam;
            if (blurParam5 != null) {
                ShaderBlendParamHelper.INSTANCE.updateMixMultiShaderParams(blurMixMultiWithShader, f);
                blurParam5.getBaseParam().setZoomScale(mirrorScale);
                blurParam5.setBlurRadius(blurConfig.platformBlurRadiusForAmount(f));
                blurDrawable.setEnableBlurShader(true);
                blurDrawable.setShaderBlendParams(blurMixMultiWithShader.getMixMultiShaderParams());
                blurDrawable.setBlurParamsSync(blurParam5);
            }
        }
        BlurMixConfig blurMixConfig = this.aodBlurMixConfig;
        if (this.isAodState && this.isAodDirty && blurMixConfig != null && blurMixConfig.getClass().equals(this.currentBlurMixConfig.getClass())) {
            changeBlurColor(blurMixConfig, false);
        } else {
            this.isAodDirty = false;
        }
        blurDrawable.setInnerShadowParams(blurConfig.getInnerShadowParams());
        blurDrawable.setOpticsParams(blurConfig.getOpticsParams());
        int cardType = blurConfig.getCardType();
        BlurDrawable blurDrawable2 = this.blurDrawable;
        if (cardType != -1) {
            blurDrawable2.setGradientStrokeLineParams(blurConfig.getGradientStrokeLineParam());
            blurDrawable2.setCornerParams(blurConfig.getGradientStrokeCornerParam());
            BlurDrawablePathProvider pathProvider = blurConfig.getPathProvider();
            if (pathProvider != null) {
                blurDrawable2.setPathProvider(pathProvider);
            }
        } else {
            blurDrawable2.setGradientStrokeLineParams(blurConfig.getGradientStrokeLineParam());
            boolean z = blurConfig.getGradientStrokeLineParam() != null;
            int iWidth = this.blurDrawable.getBounds().width();
            int iHeight = this.blurDrawable.getBounds().height();
            if (!z) {
                Float radiusWeight = blurConfig.getRadiusWeight();
                if (blurConfig.getCornerRadius() < iWidth / 2 && blurConfig.getCornerRadius() < iHeight / 2 && radiusWeight != null && radiusWeight.floatValue() < 2.0f) {
                    blurConfig.setRadiusWeight(Float.valueOf(2.0f));
                }
            } else if (blurConfig.getGradientStrokeCornerParam().getType() == CornerType.G2) {
                CornerParams gradientStrokeCornerParam = blurConfig.getGradientStrokeCornerParam();
                if (gradientStrokeCornerParam.getRadius() < iWidth / 2 && gradientStrokeCornerParam.getRadius() < iHeight / 2 && gradientStrokeCornerParam.getWeight() < 2.0f) {
                    gradientStrokeCornerParam.setWeight(2.0f);
                }
            }
            if (z) {
                blurDrawable2.setCornerParams(blurConfig.getGradientStrokeCornerParam());
            } else {
                blurDrawable2.setCornerParams(this.defaultCornerParams);
            }
            BlurDrawablePathProvider pathProvider2 = blurConfig.getPathProvider();
            if (pathProvider2 != null) {
                blurDrawable2.setPathProvider(pathProvider2);
            } else {
                Float radiusWeight2 = blurConfig.getRadiusWeight();
                if (radiusWeight2 != null) {
                    float fFloatValue = radiusWeight2.floatValue();
                    if (!z) {
                        blurDrawable2.setSmoothCorner(blurConfig.cornerRadiusFloatArray(), fFloatValue, true);
                    }
                } else if (blurConfig.getEnableStaticBlurCorner()) {
                    blurDrawable2.setCornerRadius(blurConfig.cornerRadiusFloatArray());
                } else if (!z) {
                    blurDrawable2.setSmoothCorner(0.0f, 0.0f, 0.0f, true);
                }
            }
        }
        if (blurConfig.getPlatformBlurDrawableFullOpacity()) {
            iCoerceIn = 255;
        } else {
            iCoerceIn = blurConfig.getPlatformMixConfig().getAlphaWithBlurAmount() ? (int) (255.0f * f) : (int) (255.0d * RangesKt___RangesKt.coerceIn(((double) f) / 0.05d, 0.0d, 1.0d));
        }
        blurDrawable.setAlpha(iCoerceIn);
        blurDrawable.setPlaceHolderColor(blurConfig.getPlatformMixConfig().getPlaceHolderColor());
        Rect captureRegion = blurConfig.getCaptureRegion();
        if (captureRegion != null) {
            BlurParam blurParams = blurDrawable.getBlurParams();
            blurParams.setCaptureRegion(captureRegion);
            blurDrawable.setBlurParamsSync(blurParams);
        }
        blurDrawable.invalidateSelf();
        return blurDrawable;
    }

    public final boolean changeBlurColor(BlurMixConfig blurMixConfig, boolean z) {
        BlurDrawable blurDrawable = this.blurDrawable;
        if (blurMixConfig instanceof BlurMixConfig.BlurMixSingle) {
            BlurParam blurParams = blurDrawable.getBlurParams();
            BlurMixConfig.BlurMixSingle blurMixSingle = (BlurMixConfig.BlurMixSingle) blurMixConfig;
            blurDrawable.setBlurParams(BlurParam.copy$default(blurParams, BlendParam.copy$default(blurParams.getBlendParam(), blurMixSingle.getMixColor().getTopLayerColor(), blurMixSingle.getMixColor().getBottomLayerColor(), 0, 0, 57), 0, 61));
            this.isAodDirty = !z;
            if (z) {
                blurMixConfig = null;
            }
            this.aodBlurMixConfig = blurMixConfig;
            return true;
        }
        if (blurMixConfig instanceof BlurMixConfig.BlurMixMulti) {
            BlurParam blurParams2 = blurDrawable.getBlurParams();
            BlurMixConfig.BlurMixMulti blurMixMulti = (BlurMixConfig.BlurMixMulti) blurMixConfig;
            blurDrawable.setBlurParams(BlurParam.copy$default(blurParams2, BlendParam.copy$default(blurParams2.getBlendParam(), 0, 0, blurMixMulti.getForegroundMixColor().getTopLayerColor(), blurMixMulti.getForegroundMixColor().getBottomLayerColor(), 15), 0, 61));
            this.isAodDirty = !z;
            if (z) {
                blurMixConfig = null;
            }
            this.aodBlurMixConfig = blurMixConfig;
            return true;
        }
        if (!(blurMixConfig instanceof BlurMixConfig.BlurMixMultiWithShader)) {
            this.aodBlurMixConfig = null;
            return false;
        }
        BlurMixConfig.BlurMixMultiWithShader blurMixMultiWithShader = (BlurMixConfig.BlurMixMultiWithShader) blurMixConfig;
        ShaderBlendParamHelper.INSTANCE.updateMixMultiShaderParams(blurMixMultiWithShader, 1.0f);
        blurDrawable.setShaderBlendParams(blurMixMultiWithShader.getMixMultiShaderParams());
        this.isAodDirty = !z;
        if (z) {
            blurMixConfig = null;
        }
        this.aodBlurMixConfig = blurMixConfig;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.blurDrawable.setBounds(getBounds());
        this.blurDrawable.draw(canvas);
        this.hasDrawnBlurContent = true;
        if (this.blurMaskColor == 0 || this.maskPaint.getAlpha() == 0 || !this.enableBlurMaskColor) {
            return;
        }
        canvas.drawRect(getBounds(), this.maskPaint);
    }

    public final BlurDrawable getBlurDrawable() {
        return this.blurDrawable;
    }

    public final BlurMixConfig getCurrentBlurMixConfig() {
        return this.currentBlurMixConfig;
    }

    public final int getDrawableId() {
        return this.blurDrawable.hashCode();
    }

    public final boolean getEnableBlurMaskColor() {
        return this.enableBlurMaskColor;
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public int getOpacity() {
        return this.blurDrawable.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        if (drawable == this.blurDrawable && this.hasDrawnBlurContent) {
            this.hasReceivedBlurContent = true;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.blurDrawable.setAlpha(i);
    }

    public final void setAodDirty(boolean z) {
        this.isAodDirty = z;
    }

    public final void setAodState(boolean z) {
        this.isAodState = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.blurDrawable.setColorFilter(colorFilter);
    }

    public final void setCurrentBlurMixConfig(BlurMixConfig blurMixConfig) {
        this.currentBlurMixConfig = blurMixConfig;
    }

    public final void setEnableBlurMaskColor(boolean z) {
        LogBuffer logBuffer;
        if (this.enableBlurMaskColor != z) {
            this.enableBlurMaskColor = z;
            if (LogUtil.panicEnabled && (logBuffer = DebugTool.INSTANCE.getLogBuffer()) != null) {
                logBuffer.log(TAG, LogLevel.DEBUG, "[enableBlurMaskColor] value for " + ((String) this.getHostViewName.invoke()), (Throwable) null);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("PlatformBlurDrawable-->", "SystemUi--Notification", new StringBuilder("enableBlurMaskColor = "), this.enableBlurMaskColor);
            }
        }
    }

    public final void setOverlayColor(BlurConfig blurConfig, int i) {
        blurConfig.setPlatformMixConfig(BlurMixConfig.OverlayColor.INSTANCE);
        blurConfig.setBlurColor(i);
        MetaBallBlurDrawable metaBallBlurDrawable = this.blurDrawable;
        MetaBallBlurDrawable metaBallBlurDrawable2 = metaBallBlurDrawable instanceof MetaBallBlurDrawable ? metaBallBlurDrawable : null;
        if (metaBallBlurDrawable2 != null) {
            metaBallBlurDrawable2.setMetaColor(i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PlatformBlurDrawable drawableId=");
        sb.append(getDrawableId());
        sb.append(" {hostView=");
        sb.append((String) this.getHostViewName.invoke());
        sb.append(", {");
        sb.append(super.toString());
        sb.append(", blurDrawable=");
        sb.append(this.blurDrawable);
        sb.append(",isMetaBallEffective = ");
        MetaBallBlurDrawable metaBallBlurDrawable = this.blurDrawable;
        MetaBallBlurDrawable metaBallBlurDrawable2 = metaBallBlurDrawable instanceof MetaBallBlurDrawable ? metaBallBlurDrawable : null;
        sb.append(metaBallBlurDrawable2 != null ? Boolean.valueOf(metaBallBlurDrawable2.isMetaBallEffective()) : null);
        sb.append(", blurColor=");
        sb.append(Integer.toHexString(this.blurMaskColor));
        sb.append(",isAodDirty=");
        sb.append(this.isAodDirty);
        sb.append(",isAodState=");
        sb.append(this.isAodState);
        return sb.toString();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
