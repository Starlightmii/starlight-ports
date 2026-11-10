package com.oplusos.systemui.common.blurability.wallpaper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.oplus.posteffect.BlendParam;
import com.oplus.posteffect.ForegroundBlurParam;
import com.oplus.posteffect.drawable.BlendDrawable;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.MixColor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BlendWallpaperBlurDrawable extends Drawable implements Drawable.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BlendWallpaperBlurDrawable";
    private final BlendDrawable blendDrawable;
    private BlurMixConfig currentBlurMixConfig = BlurMixConfig.None.INSTANCE;
    private final View hostView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BlendWallpaperBlurDrawable(BlendDrawable blendDrawable, View view) {
        this.blendDrawable = blendDrawable;
        this.hostView = view;
        blendDrawable.setCallback(this);
    }

    public static void applyMixColorAndScale(BlendParam blendParam, MixColor mixColor, float f) {
        int mode = mixColor.getMode();
        int topLayerColor = mixColor.getTopLayerColor();
        int alphaComponent = ColorUtils.setAlphaComponent(topLayerColor, (int) (Color.alpha(topLayerColor) * f));
        int bottomLayerColor = mixColor.getBottomLayerColor();
        int alphaComponent2 = ColorUtils.setAlphaComponent(bottomLayerColor, (int) (Color.alpha(bottomLayerColor) * f));
        blendParam.blendMode = mode;
        blendParam.blendColorA = alphaComponent;
        blendParam.blendColorB = alphaComponent2;
    }

    public final BlendDrawable applyBlurConfig(BlurConfig blurConfig, float f, Bitmap bitmap) {
        BlendDrawable blendDrawable = this.blendDrawable;
        this.currentBlurMixConfig = blurConfig.getPlatformMixConfig();
        BlurMixConfig platformMixConfig = blurConfig.getPlatformMixConfig();
        if (platformMixConfig instanceof BlurMixConfig.BlurMixSingle) {
            BlendParam blendParam = new BlendParam();
            applyMixColorAndScale(blendParam, ((BlurMixConfig.BlurMixSingle) platformMixConfig).getMixColor(), f);
            BlendDrawable.setBitmap$default(blendDrawable, bitmap, blendParam);
        } else if (platformMixConfig instanceof BlurMixConfig.BlurMixMulti) {
            BlendParam blendParam2 = new BlendParam();
            BlurMixConfig.BlurMixMulti blurMixMulti = (BlurMixConfig.BlurMixMulti) platformMixConfig;
            applyMixColorAndScale(blendParam2, blurMixMulti.getBackgroundMixColor(), f);
            blendParam2.setForegroundBlendParams(new ForegroundBlurParam(blurMixMulti.getForegroundMixColor().getMode(), blurMixMulti.getForegroundMixColor().getTopLayerColor(), blurMixMulti.getForegroundMixColor().getBottomLayerColor()));
            BlendDrawable.setBitmap$default(blendDrawable, bitmap, blendParam2);
        }
        Float radiusWeight = blurConfig.getRadiusWeight();
        if (radiusWeight != null) {
            blendDrawable.setSmoothCorner(blurConfig.getLeftTopCornerRadius(), blurConfig.getLeftBottomCornerRadius(), radiusWeight.floatValue(), false);
        } else if (blurConfig.getEnableStaticBlurCorner()) {
            blendDrawable.setCornerRadius(blurConfig.cornerRadiusFloatArray());
        } else {
            blendDrawable.setSmoothCorner(0.0f, 0.0f, 0.0f, false);
        }
        blendDrawable.setPlaceHolderColor(blurConfig.getPlatformMixConfig().getPlaceHolderColor());
        blendDrawable.invalidateSelf();
        return blendDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.blendDrawable.setBounds(getBounds());
        this.blendDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.blendDrawable.getAlpha();
    }

    public final BlendDrawable getBlendDrawable() {
        return this.blendDrawable;
    }

    public final BlurMixConfig getCurrentBlurMixConfig() {
        return this.currentBlurMixConfig;
    }

    public final int getDrawableId() {
        return this.blendDrawable.hashCode();
    }

    public final View getHostView() {
        return this.hostView;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.blendDrawable.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.blendDrawable.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.blendDrawable.setColorFilter(colorFilter);
    }

    public final void setCurrentBlurMixConfig(BlurMixConfig blurMixConfig) {
        this.currentBlurMixConfig = blurMixConfig;
    }

    public String toString() {
        return "BlendWallpaperBlurDrawable drawableId=" + getDrawableId() + " hostView=" + this.hostView + ", blendDrawable=" + this.blendDrawable + ", currentBlurMixConfig=" + this.currentBlurMixConfig;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
