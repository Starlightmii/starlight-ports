package com.oplus.systemui.volume.utils.material;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeBlurDrawableFinder {
    public static final OplusVolumeBlurDrawableFinder INSTANCE = new OplusVolumeBlurDrawableFinder();

    public static void drawDrawableWithBounds(Drawable drawable, Canvas canvas, Rect rect) {
        Rect rect2 = new Rect(drawable.getBounds());
        try {
            drawable.setBounds(rect);
            drawable.draw(canvas);
        } finally {
            drawable.setBounds(rect2);
        }
    }

    public static final void drawPressBackgroundLayers(Drawable drawable, Canvas canvas, Rect rect, float f, int i) {
        Drawable drawable2;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            INSTANCE.getClass();
            BackgroundBlurDrawable backgroundBlurDrawableFindBackgroundBlurDrawable = findBackgroundBlurDrawable(layerDrawable);
            if (backgroundBlurDrawableFindBackgroundBlurDrawable != null) {
                syncBackgroundBlurCornerRadius(backgroundBlurDrawableFindBackgroundBlurDrawable, f, i);
                if (i <= 0) {
                    drawDrawableWithBounds(backgroundBlurDrawableFindBackgroundBlurDrawable, canvas, rect);
                } else {
                    Rect rect2 = new Rect(rect);
                    int i2 = -i;
                    rect2.inset(i2, i2);
                    drawDrawableWithBounds(backgroundBlurDrawableFindBackgroundBlurDrawable, canvas, rect2);
                }
            }
            if (layerDrawable.getNumberOfLayers() >= 2 && (drawable2 = layerDrawable.getDrawable(1)) != null) {
                drawDrawableWithBounds(drawable2, canvas, rect);
                return;
            }
            return;
        }
        if (!(drawable instanceof AutoBlurDrawable)) {
            INSTANCE.getClass();
            drawDrawableWithBounds(drawable, canvas, rect);
            return;
        }
        AutoBlurDrawable autoBlurDrawable = (AutoBlurDrawable) drawable;
        INSTANCE.getClass();
        if (f > 0.0f) {
            ViewBlurProxy viewBlurProxy = autoBlurDrawable.getViewBlurProxy();
            if (Math.abs(viewBlurProxy.getBlurConfig().getCornerRadius() - f) >= 1.0f) {
                viewBlurProxy.getBlurConfig().setCornerRadius(f);
                viewBlurProxy.applyBlurConfig();
            }
        }
        ViewBlurProxy viewBlurProxy2 = autoBlurDrawable.getViewBlurProxy();
        if (viewBlurProxy2.getBlurConfig().getGradientStrokeLineParam() != null || viewBlurProxy2.getBlurConfig().getEnableStaticBlurCorner()) {
            VolumeBlurLightParams.INSTANCE.adaptVolumeStrokeCorner(viewBlurProxy2.getBlurConfig(), f, viewBlurProxy2.getBlurConfig().getRadiusWeight(), rect.width(), rect.height(), false);
            viewBlurProxy2.getBlurConfig().setCornerRadius(f);
            viewBlurProxy2.applyBlurConfig();
        }
        drawDrawableWithBounds(drawable, canvas, rect);
        autoBlurDrawable.getViewBlurProxy().invalidatePath();
    }

    public static final AutoBlurDrawable findAutoBlurDrawable(Drawable drawable) {
        INSTANCE.getClass();
        if (drawable instanceof OplusVolumeBackgroundWrapper) {
            drawable = ((OplusVolumeBackgroundWrapper) drawable).getContent();
        }
        if (drawable != null && (drawable instanceof AutoBlurDrawable)) {
            return (AutoBlurDrawable) drawable;
        }
        return null;
    }

    public static final BackgroundBlurDrawable findBackgroundBlurDrawable(Drawable drawable) {
        INSTANCE.getClass();
        if (drawable instanceof OplusVolumeBackgroundWrapper) {
            drawable = ((OplusVolumeBackgroundWrapper) drawable).getContent();
        }
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BackgroundBlurDrawable) {
            return (BackgroundBlurDrawable) drawable;
        }
        if (!(drawable instanceof LayerDrawable)) {
            return null;
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        for (int i = 0; i < numberOfLayers; i++) {
            BackgroundBlurDrawable drawable2 = layerDrawable.getDrawable(i);
            if (drawable2 instanceof BackgroundBlurDrawable) {
                return drawable2;
            }
        }
        return null;
    }

    public static final AutoBlurDrawable findPlatformAutoBlurDrawable(Drawable drawable) {
        AutoBlurDrawable autoBlurDrawableFindAutoBlurDrawable = findAutoBlurDrawable(drawable);
        if (autoBlurDrawableFindAutoBlurDrawable != null && Intrinsics.areEqual(autoBlurDrawableFindAutoBlurDrawable.getViewBlurProxy().getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
            return autoBlurDrawableFindAutoBlurDrawable;
        }
        return null;
    }

    public static final boolean needsPressDrawOverride(Drawable drawable) {
        return (findBackgroundBlurDrawable(drawable) == null && findPlatformAutoBlurDrawable(drawable) == null) ? false : true;
    }

    public static final void syncBackgroundBlurCornerRadius(BackgroundBlurDrawable backgroundBlurDrawable, float f, int i) {
        if (f <= 0.0f) {
            return;
        }
        float f2 = f + i;
        backgroundBlurDrawable.setCornerRadius(f2, f2, f2, f2);
    }

    public static final void syncTintCornerRadius(Drawable drawable, float f) {
        if (f <= 0.0f) {
            return;
        }
        INSTANCE.getClass();
        if (drawable instanceof OplusVolumeBackgroundWrapper) {
            drawable = ((OplusVolumeBackgroundWrapper) drawable).getContent();
        }
        if (drawable == null) {
            return;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            if (layerDrawable.getNumberOfLayers() > 1) {
                Drawable drawable2 = layerDrawable.getDrawable(1);
                if (drawable2 instanceof GradientDrawable) {
                    GradientDrawable gradientDrawable = (GradientDrawable) drawable2;
                    gradientDrawable.mutate();
                    gradientDrawable.setCornerRadius(f);
                    return;
                }
                return;
            }
            return;
        }
        if (!(drawable instanceof AutoBlurDrawable)) {
            if (drawable instanceof GradientDrawable) {
                GradientDrawable gradientDrawable2 = (GradientDrawable) drawable;
                gradientDrawable2.mutate();
                gradientDrawable2.setCornerRadius(f);
                return;
            }
            return;
        }
        Drawable defaultDrawable = ((AutoBlurDrawable) drawable).getDefaultDrawable();
        if (defaultDrawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable3 = (GradientDrawable) defaultDrawable;
            gradientDrawable3.mutate();
            gradientDrawable3.setCornerRadius(f);
        }
    }
}
