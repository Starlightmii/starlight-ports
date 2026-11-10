package com.oplusos.systemui.common.blurability.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class AutoBlurDrawable extends Drawable implements Drawable.Callback {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AutoBlurDrawable";
    public int blurColor;
    private final Drawable defaultDrawable;
    private final ViewBlurProxy viewBlurProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AutoBlurDrawable(ViewBlurProxy viewBlurProxy, Drawable drawable) {
        this.viewBlurProxy = viewBlurProxy;
        this.defaultDrawable = drawable;
        viewBlurProxy.setMyCallback$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon(this);
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        BackgroundBlurDrawable blurDrawable = this.viewBlurProxy.getBlurDrawable(this.defaultDrawable);
        if (blurDrawable != null) {
            if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "AutoBlurDrawable-->".concat("[draw] setBounds for " + blurDrawable + ' ' + getBounds()));
            }
            if (blurDrawable.equals(this.viewBlurProxy.getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon()) && !this.viewBlurProxy.getBlurConfig().isFullScreen) {
                this.viewBlurProxy.getStaticBlurDrawable$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon().setBitmapSubsetForView(this.viewBlurProxy.getView());
            }
            blurDrawable.setBounds(getBounds());
            boolean z = blurDrawable instanceof BackgroundBlurDrawable;
            if (z && !canvas.isHardwareAccelerated()) {
                Log.w(TAG, "Software rendering doesn't support drawRenderNode");
                return;
            }
            if (z && (i = this.blurColor) != 0) {
                blurDrawable.setColor(i);
            }
            blurDrawable.draw(canvas);
        }
    }

    public final int getBlurColor() {
        return this.blurColor;
    }

    public final Drawable getDefaultDrawable() {
        return this.defaultDrawable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
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
    }

    public final void setBlurColor(int i) {
        this.blurColor = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        this.viewBlurProxy.onVisibilityAggregated(z);
        return visible;
    }

    public String toString() {
        return "AutoBlurDrawable { " + this.viewBlurProxy + ", " + this.defaultDrawable + " }";
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
