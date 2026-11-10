package com.oplusos.systemui.common.blurability.staticblur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Deprecated;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class SubsetBitmapDrawable extends Drawable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SubsetBitmapDrawable";
    public float cornerRadiusBL;
    public float cornerRadiusBR;
    public float cornerRadiusTL;
    public float cornerRadiusTR;
    private Bitmap innerBitmap;
    private final Paint paint;
    private final Path rectPath;
    private final Rect subset;
    private Integer tempDegree;
    private Matrix tempMatrix;
    private final float[] tmpRadii;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SubsetBitmapDrawable(Bitmap bitmap, Rect rect) {
        this.innerBitmap = bitmap;
        this.subset = rect;
        Paint paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        this.paint = paint;
        this.rectPath = new Path();
        this.tmpRadii = new float[8];
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getAlpha() == 0 || !isVisible()) {
            return;
        }
        canvas.clipPath(this.rectPath);
        Bitmap bitmap = this.innerBitmap;
        if (bitmap != null) {
            Matrix matrix = this.tempMatrix;
            if (matrix != null) {
                canvas.drawBitmap(bitmap, matrix, this.paint);
            } else {
                canvas.drawBitmap(bitmap, this.subset.isEmpty() ? null : this.subset, getBounds(), this.paint);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.paint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.paint.getColorFilter();
    }

    public final Bitmap getInnerBitmap() {
        return this.innerBitmap;
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated
    public int getOpacity() {
        return -3;
    }

    public final Integer getTempDegree() {
        return this.tempDegree;
    }

    public boolean isValid() {
        Bitmap bitmap = this.innerBitmap;
        boolean z = false;
        if (bitmap != null && !bitmap.isRecycled()) {
            z = true;
        }
        if (!z) {
            StringBuilder sb = new StringBuilder("[isValid] innerBitmap?.isRecycled=");
            Bitmap bitmap2 = this.innerBitmap;
            sb.append(bitmap2 != null ? Boolean.valueOf(bitmap2.isRecycled()) : null);
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, sb.toString());
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (getAlpha() != i) {
            this.paint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setBitmap(Bitmap bitmap) {
        if (Intrinsics.areEqual(this.innerBitmap, bitmap)) {
            return;
        }
        this.innerBitmap = bitmap;
        setTempDegree(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        updatePath();
        updateTempMatrix();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (Intrinsics.areEqual(getColorFilter(), colorFilter)) {
            return;
        }
        this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final void setCornerRadius(float f, float f2, float f3, float f4) {
        if (this.cornerRadiusTL == f && this.cornerRadiusTR == f2 && this.cornerRadiusBL == f3 && this.cornerRadiusBR == f4) {
            return;
        }
        this.cornerRadiusTL = f;
        this.cornerRadiusTR = f2;
        this.cornerRadiusBL = f3;
        this.cornerRadiusBR = f4;
        updatePath();
        invalidateSelf();
    }

    public final void setInnerBitmap(Bitmap bitmap) {
        this.innerBitmap = bitmap;
    }

    public final void setTempDegree(Integer num) {
        if (Intrinsics.areEqual(this.tempDegree, num)) {
            return;
        }
        this.tempDegree = num;
        updateTempMatrix();
        invalidateSelf();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SubsetBitmapDrawable {innerBitmap=");
        sb.append(this.innerBitmap);
        sb.append(", subset=");
        sb.append(this.subset);
        sb.append(", alpha=");
        sb.append(getAlpha());
        sb.append(", bounds=");
        sb.append(getBounds());
        sb.append(", cornerRadiusTL=");
        sb.append(this.cornerRadiusTL);
        sb.append(", cornerRadiusTR=");
        sb.append(this.cornerRadiusTR);
        sb.append(",cornerRadiusBL=");
        sb.append(this.cornerRadiusBL);
        sb.append(", cornerRadiusBR=");
        return DpCornerSize$.ExternalSyntheticOutline0.m(this.cornerRadiusBR, " }", sb);
    }

    public final void updatePath() {
        float[] fArr = this.tmpRadii;
        float f = this.cornerRadiusTL;
        fArr[0] = f;
        fArr[1] = f;
        float f2 = this.cornerRadiusTR;
        fArr[2] = f2;
        fArr[3] = f2;
        float f3 = this.cornerRadiusBL;
        fArr[4] = f3;
        fArr[5] = f3;
        float f4 = this.cornerRadiusBR;
        fArr[6] = f4;
        fArr[7] = f4;
        Rect bounds = getBounds();
        this.rectPath.reset();
        this.rectPath.addRoundRect(bounds.left, bounds.top, bounds.right, bounds.bottom, this.tmpRadii, Path.Direction.CW);
    }

    public void updateSubset(Rect rect) {
        if (Intrinsics.areEqual(this.subset, rect)) {
            return;
        }
        this.subset.set(rect);
        updateTempMatrix();
    }

    public final void updateTempMatrix() {
        Rect rect;
        Integer num = this.tempDegree;
        Bitmap bitmap = this.innerBitmap;
        if (num == null || (!(num.intValue() == 90 || num.intValue() == 270) || bitmap == null)) {
            this.tempMatrix = null;
            return;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(num.intValue(), 0.0f, 0.0f);
        if (num.intValue() == 90) {
            matrix.postTranslate(bitmap.getHeight(), 0.0f);
        } else {
            matrix.postTranslate(0.0f, bitmap.getWidth());
        }
        if (this.subset.isEmpty()) {
            rect = new Rect(0, 0, bitmap.getHeight(), bitmap.getWidth());
        } else {
            Rect rect2 = this.subset;
            matrix.postTranslate(-rect2.left, -rect2.top);
            rect = this.subset;
        }
        matrix.postScale(getBounds().width() / rect.width(), getBounds().height() / rect.height(), 0.0f, 0.0f);
        this.tempMatrix = matrix;
    }

    public void setCornerRadius(float f) {
        setCornerRadius(f, f, f, f);
    }
}
