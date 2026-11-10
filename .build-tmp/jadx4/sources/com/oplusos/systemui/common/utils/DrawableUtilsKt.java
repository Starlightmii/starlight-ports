package com.oplusos.systemui.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.oplus.util.OplusRoundRectUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class DrawableUtilsKt {
    /* JADX WARN: Code duplicated, block: B:11:0x0021  */
    public static final Drawable getRoundRectDrawable(Drawable drawable, int i, int i2, int i3, Context context) {
        float f = i2;
        float f2 = i / f;
        if (drawable == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable2 = (BitmapDrawable) drawable;
            if (bitmapDrawable2.getBitmap().getConfig() != Bitmap.Config.HARDWARE) {
                bitmapDrawable = bitmapDrawable2;
            } else {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                bitmapDrawable.setBitmap(bitmapCreateBitmap);
            }
        } else {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
            drawable.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
            drawable.draw(canvas2);
            bitmapDrawable.setBitmap(bitmapCreateBitmap2);
        }
        int width = bitmapDrawable.getBitmap().getWidth();
        if (width <= i2) {
            i2 = width;
        }
        int height = bitmapDrawable.getBitmap().getHeight();
        if (height > i3) {
            height = i3;
        }
        if (i2 <= 0 || height <= 0) {
            return drawable;
        }
        Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(i2, height, Bitmap.Config.ARGB_8888);
        Canvas canvas3 = new Canvas(bitmapCreateBitmap3);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        canvas3.drawPath(OplusRoundRectUtil.getInstance().getPath(new RectF(0.0f, 0.0f, f, i3), f * f2), paint);
        return new BitmapDrawable(context.getResources(), bitmapCreateBitmap3);
    }

    public static final Bitmap getScaleDrawable(Bitmap bitmap, int i, int i2) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width <= 0 || height <= 0) {
                return bitmap;
            }
            if (width != i || height != i2) {
                Matrix matrix = new Matrix();
                matrix.postScale(i / width, i2 / height);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                return bitmapCreateBitmap;
            }
        }
        return bitmap;
    }

    public static final void recycleImageViewBitmap(ImageView imageView) {
        Bitmap bitmap;
        Drawable drawable = imageView != null ? imageView.getDrawable() : null;
        if ((drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }
}
