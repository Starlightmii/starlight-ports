package com.oplusos.systemui.common.blurability.staticblur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StaticBlurDrawable extends SubsetBitmapDrawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "StaticBlurDrawable";
    public int blurMaskColor;
    public final boolean drawFullBitmap;
    private final Paint maskPaint;
    private Pair<Float, Float> scalePair;
    private final Rect tmpVisibleRect;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public StaticBlurDrawable(Bitmap bitmap, boolean z, Pair<Float, Float> pair) {
        super(bitmap, new Rect());
        this.drawFullBitmap = z;
        this.scalePair = pair;
        this.tmpVisibleRect = new Rect();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.blurMaskColor);
        this.maskPaint = paint;
    }

    private final String getHostViewName() {
        Drawable.Callback callback = getCallback();
        View view = callback instanceof View ? (View) callback : null;
        if (view != null) {
            return DebugToolKt.getName(view, false);
        }
        return null;
    }

    @Override // com.oplusos.systemui.common.blurability.staticblur.SubsetBitmapDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!this.drawFullBitmap && (getCallback() instanceof View)) {
            Drawable.Callback callback = getCallback();
            View view = callback instanceof View ? (View) callback : null;
            if (view != null) {
                setBitmapSubsetForView(view);
            }
        }
        super.draw(canvas);
        if (this.maskPaint.getAlpha() != 0) {
            canvas.drawRect(getBounds(), this.maskPaint);
        }
        if (DebugTool.INSTANCE.getOpenDebug() && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "StaticBlurDrawable-->".concat("[draw] " + this));
        }
    }

    public final int getBlurMaskColor() {
        return this.blurMaskColor;
    }

    @Override // com.oplusos.systemui.common.blurability.staticblur.SubsetBitmapDrawable
    public final boolean isValid() {
        boolean z = (!super.isValid() || Intrinsics.areEqual((Float) this.scalePair.first, 0.0f) || Intrinsics.areEqual((Float) this.scalePair.second, 0.0f)) ? false : true;
        if (DebugTool.INSTANCE.getOpenDebug() && !z) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "[invalid] scalePair=" + this.scalePair + " of " + getHostViewName());
        }
        return z;
    }

    public final void setBitmapAndScale(Bitmap bitmap, Pair<Float, Float> pair) {
        super.setBitmap(bitmap);
        if (!Intrinsics.areEqual(this.scalePair, pair)) {
            this.scalePair = pair;
            invalidateSelf();
        }
        LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
        if (logBuffer != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, "[setBitmapAndScale] " + bitmap + ", " + this.scalePair + " for " + getHostViewName(), (Throwable) null);
        }
    }

    public final void setBitmapSubsetForView(View view) {
        view.getGlobalVisibleRect(this.tmpVisibleRect);
        Rect rect = this.tmpVisibleRect;
        rect.left = (int) (((Number) this.scalePair.first).floatValue() * rect.left);
        Rect rect2 = this.tmpVisibleRect;
        rect2.right = (int) (((Number) this.scalePair.first).floatValue() * rect2.right);
        Rect rect3 = this.tmpVisibleRect;
        rect3.top = (int) (((Number) this.scalePair.second).floatValue() * rect3.top);
        Rect rect4 = this.tmpVisibleRect;
        rect4.bottom = (int) (((Number) this.scalePair.second).floatValue() * rect4.bottom);
        updateSubset(this.tmpVisibleRect);
    }

    public final void setBlurMaskColor(int i) {
        if (this.blurMaskColor != i) {
            LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
            if (logBuffer != null) {
                logBuffer.log(TAG, LogLevel.DEBUG, "[setBlurMaskColor] " + Integer.toHexString(i) + " for " + getHostViewName(), (Throwable) null);
            }
            this.blurMaskColor = i;
            this.maskPaint.setColor(i);
            invalidateSelf();
        }
    }

    @Override // com.oplusos.systemui.common.blurability.staticblur.SubsetBitmapDrawable
    public String toString() {
        return "StaticBlurDrawable{hostView=" + getHostViewName() + ", {" + super.toString() + ", drawFullBitmap=" + this.drawFullBitmap + ",scalePair=" + this.scalePair + ", blurColor=" + Integer.toHexString(this.blurMaskColor) + ", isValid=" + isValid() + ", tmpVisibleRect=" + this.tmpVisibleRect + '}';
    }
}
