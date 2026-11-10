package com.oplus.systemui.wallpaperblur;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class WallpaperBlurDrawable extends Drawable {
    public static final Companion Companion = new Companion();
    public static final String TAG = "WallpaperBlurDrawable";
    public int currentAlpha;
    private BitmapDrawable currentWallpaperBlurDrawable;
    public int drawableColor;
    public boolean isOverlayEnabled;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        BitmapDrawable bitmapDrawable = this.currentWallpaperBlurDrawable;
        if (bitmapDrawable != null) {
            bitmapDrawable.setAlpha(this.currentAlpha);
            bitmapDrawable.setBounds(getBounds());
            bitmapDrawable.draw(canvas);
            if (!this.isOverlayEnabled || (i = this.drawableColor) == 0) {
                return;
            }
            canvas.drawColor(Color.argb((int) (((double) this.currentAlpha) * 0.3d), Color.red(i), Color.green(this.drawableColor), Color.blue(this.drawableColor)));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.currentAlpha = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public final void setDrawableConfig(BitmapDrawable bitmapDrawable, int i) {
        this.currentWallpaperBlurDrawable = bitmapDrawable;
        this.drawableColor = i;
    }

    public String toString() {
        return "WallpaperBlurDrawable, " + this.currentWallpaperBlurDrawable;
    }
}
