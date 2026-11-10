package com.oplusos.systemui.common.blurability.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import kotlin.Deprecated;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaskBlurDrawable extends Drawable implements Drawable.Callback {
    private Drawable defaultDrawable;
    public int maskColor;
    private ViewBlurProxy viewBlurProxy;

    public MaskBlurDrawable(ViewBlurProxy viewBlurProxy, Drawable drawable, int i) {
        this.viewBlurProxy = viewBlurProxy;
        this.defaultDrawable = drawable;
        this.maskColor = i;
        viewBlurProxy.setMyCallback$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this);
        Drawable drawable2 = this.defaultDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(this);
        }
    }

    private final Drawable getDrawable() {
        return this.viewBlurProxy.getBlurDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setBounds(getBounds());
            drawable.draw(canvas);
            int i = this.maskColor;
            if (i != 0) {
                canvas.drawColor(i);
                return;
            }
            return;
        }
        Drawable drawable2 = this.defaultDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(getBounds());
        }
        Drawable drawable3 = this.defaultDrawable;
        if (drawable3 != null) {
            drawable3.draw(canvas);
        }
    }

    public final Drawable getDefaultDrawable() {
        return this.defaultDrawable;
    }

    public final int getMaskColor() {
        return this.maskColor;
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public int getOpacity() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            drawable = this.defaultDrawable;
        }
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    public final ViewBlurProxy getViewBlurProxy() {
        return this.viewBlurProxy;
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
        Drawable drawable = getDrawable();
        if (drawable == null) {
            drawable = this.defaultDrawable;
        }
        if (drawable != null) {
            drawable.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            drawable = this.defaultDrawable;
        }
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
    }

    public final void setDefaultDrawable(Drawable drawable) {
        this.defaultDrawable = drawable;
    }

    public final void setMaskColor(int i) {
        this.maskColor = i;
    }

    public final void setViewBlurProxy(ViewBlurProxy viewBlurProxy) {
        this.viewBlurProxy = viewBlurProxy;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        this.viewBlurProxy.onVisibilityAggregated(z);
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
