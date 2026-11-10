package com.oplus.systemui.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.android.internal.util.ContrastColorUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusImageUtils {
    public int[] mTempBuffer;
    public Bitmap mTempCompactBitmap;
    public Canvas mTempCompactBitmapCanvas;
    public Paint mTempCompactBitmapPaint;
    public Matrix mTempMatrix;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OplusColorInfo {
        public final boolean isAlmostTransparent;
        public final boolean isGrey;
        public final String isLight;

        public OplusColorInfo(String str, boolean z, boolean z2) {
            this.isGrey = z;
            this.isAlmostTransparent = z2;
            this.isLight = str;
        }

        public String getIsLight() {
            return this.isLight;
        }
    }

    public static void recycleBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable == null || !(drawable instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public final boolean isGrayscale(Bitmap bitmap) {
        Bitmap bitmapCopy;
        boolean z;
        int i;
        int i2;
        OplusColorInfo oplusColorInfo;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        Bitmap.Config config = bitmap.getConfig();
        Bitmap.Config config2 = Bitmap.Config.HARDWARE;
        if (config == config2) {
            bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            z = true;
        } else {
            bitmapCopy = bitmap;
            z = false;
        }
        if (height > 64 || width > 64) {
            if (this.mTempCompactBitmap == null) {
                this.mTempCompactBitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
                this.mTempCompactBitmapCanvas = new Canvas(this.mTempCompactBitmap);
                Paint paint = new Paint(1);
                this.mTempCompactBitmapPaint = paint;
                paint.setFilterBitmap(true);
            }
            this.mTempMatrix.reset();
            this.mTempMatrix.setScale(64.0f / width, 64.0f / height, 0.0f, 0.0f);
            this.mTempCompactBitmapCanvas.drawColor(0, PorterDuff.Mode.SRC);
            this.mTempCompactBitmapCanvas.drawBitmap(bitmapCopy, this.mTempMatrix, this.mTempCompactBitmapPaint);
            if (z) {
                bitmapCopy.recycle();
            }
            bitmapCopy = this.mTempCompactBitmap;
            i = 64;
            i2 = 64;
        } else {
            i2 = height;
            i = width;
        }
        Bitmap bitmap2 = bitmapCopy;
        int i3 = i2 * i;
        int[] iArr = this.mTempBuffer;
        if (iArr == null || iArr.length < i3) {
            this.mTempBuffer = new int[i3];
        }
        if (bitmap2.getConfig() == config2) {
            bitmap2.copy(Bitmap.Config.RGBA_F16, true).getPixels(this.mTempBuffer, 0, i, 0, 0, i, i2);
        } else {
            bitmap2.getPixels(this.mTempBuffer, 0, i, 0, 0, i, i2);
        }
        int i4 = (i3 - 1) / 3;
        String str = "not";
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = this.mTempBuffer[3 * i5];
            String str2 = ContrastColorUtil.calculateLuminance(i6) > 0.949999988079071d ? "light" : ContrastColorUtil.calculateLuminance(i6) < 0.10000000149011612d ? "dark" : "not";
            if (((i6 >> 24) & 255) < 50) {
                oplusColorInfo = new OplusColorInfo("not", true, true);
            } else {
                int i7 = (i6 >> 16) & 255;
                int i8 = (i6 >> 8) & 255;
                int i9 = i6 & 255;
                oplusColorInfo = new OplusColorInfo(str2, Math.abs(i7 - i8) < 25 && Math.abs(i7 - i9) < 25 && Math.abs(i8 - i9) < 25, false);
            }
            String str3 = oplusColorInfo.isLight;
            if (!str3.equals("not") && str.equals("not")) {
                str = str3;
            }
            if (!oplusColorInfo.isGrey || (!str.equals("not") && !str3.equals("not") && !str.equals(str3) && !oplusColorInfo.isAlmostTransparent)) {
                return false;
            }
        }
        return true;
    }
}
