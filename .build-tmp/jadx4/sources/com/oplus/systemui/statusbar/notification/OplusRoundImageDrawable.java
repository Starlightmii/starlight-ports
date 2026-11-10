package com.oplus.systemui.statusbar.notification;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.oplus.systemui.notification.base.radius.NotificationCanvas;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusRoundImageDrawable extends Drawable {
    public static final Companion Companion = new Companion();
    private final Bitmap bitmap;
    private final BitmapShader bitmapShader;
    private float[] cornerRadii;
    public boolean isNightMode;
    private final Paint mBackgroundPaint;
    private final Paint mBitmapPaint;
    private final Matrix matrix;
    private final List<Integer> oplusBgColors;
    private RectF rectF;
    public float translateX;

    public OplusRoundImageDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        Paint paint = new Paint();
        this.mBitmapPaint = paint;
        Paint paint2 = new Paint();
        this.mBackgroundPaint = paint2;
        this.rectF = new RectF();
        this.cornerRadii = new float[8];
        this.oplusBgColors = new ArrayList();
        this.matrix = new Matrix();
        Shader.TileMode tileMode = Shader.TileMode.DECAL;
        this.bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int iIntValue;
        int iIntValue2;
        NotificationCanvas notificationCanvas = new NotificationCanvas(canvas);
        if (this.oplusBgColors.size() >= 2) {
            RectF rectF = this.rectF;
            float f = rectF.right - rectF.left;
            float f2 = rectF.bottom - rectF.top;
            if (!this.isNightMode || this.oplusBgColors.size() < 4) {
                iIntValue = this.oplusBgColors.get(0).intValue();
                iIntValue2 = this.oplusBgColors.get(1).intValue();
            } else {
                iIntValue = this.oplusBgColors.get(2).intValue();
                iIntValue2 = this.oplusBgColors.get(3).intValue();
            }
            int i = iIntValue;
            int i2 = iIntValue2;
            int iSave = canvas.save();
            try {
                canvas.translate(this.translateX, 0.0f);
                this.mBackgroundPaint.setShader(new LinearGradient(0.0f, 0.0f, f, f2, i, i2, Shader.TileMode.CLAMP));
                RectF rectF2 = this.rectF;
                float f3 = this.cornerRadii[0];
                notificationCanvas.drawSmoothRoundRect(rectF2, f3, f3, this.mBackgroundPaint);
                canvas.restoreToCount(iSave);
            } catch (Throwable th) {
                canvas.restoreToCount(iSave);
                throw th;
            }
        }
        int iSave2 = canvas.save();
        try {
            canvas.translate(this.translateX, 0.0f);
            RectF rectF3 = this.rectF;
            float f4 = this.cornerRadii[0];
            notificationCanvas.drawSmoothRoundRect(rectF3, f4, f4, this.mBitmapPaint);
        } finally {
            canvas.restoreToCount(iSave2);
        }
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final float[] getCornerRadii() {
        return this.cornerRadii;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.bitmap.getHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.bitmap.getWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final List<Integer> getOplusBgColors() {
        return this.oplusBgColors;
    }

    public final float getTranslateX() {
        return this.translateX;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mBitmapPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        RectF rectF = this.rectF;
        rectF.left = i;
        rectF.right = i3;
        rectF.top = i2;
        rectF.bottom = i4;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mBitmapPaint.setColorFilter(colorFilter);
    }

    public final void setCornerRadii(float[] fArr) {
        this.cornerRadii = fArr;
    }

    public final void setInitSize(float f, float f2) {
        if (this.bitmap.getWidth() <= 0 || this.bitmap.getHeight() <= 0) {
            return;
        }
        float fMin = Math.min(f / this.bitmap.getWidth(), f2 / this.bitmap.getHeight());
        this.matrix.reset();
        this.matrix.preTranslate(f - this.bitmap.getWidth(), 0.0f);
        this.matrix.postScale(fMin, fMin, f, 0.0f);
        this.bitmapShader.setLocalMatrix(this.matrix);
        this.mBitmapPaint.setShader(this.bitmapShader);
    }

    public final void setNightMode(boolean z) {
        this.isNightMode = z;
    }

    public final void setTranslateX(float f) {
        this.translateX = f;
    }
}
