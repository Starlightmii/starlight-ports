package com.oplus.systemui.statusbar.notification.util;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.oplus.util.OplusRoundRectUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RoundRectDrawableUtil {
    public static final RoundRectDrawableUtil INSTANCE = new RoundRectDrawableUtil();
    private static final String TAG = "RoundRectDrawableUtil";

    public static final Drawable getRoundDrawable(Drawable drawable, float f, int i, int i2, Context context) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                return drawable;
            }
            if (f != 0.0f || intrinsicWidth != i || intrinsicHeight != i2) {
                return getTargetRoundRectDrawable(intrinsicWidth, intrinsicHeight, drawable, f, i, i2, context);
            }
        }
        return drawable;
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x007c: RETURN (r4 I:android.graphics.drawable.Drawable) A[SYNTHETIC], block:B:52:? */
    public static final Drawable getRoundRectDrawable(Drawable drawable, float f, int i, int i2, Context context) {
        Drawable drawable2;
        Bitmap bitmap;
        Bitmap bitmap2;
        if (drawable == null) {
            return null;
        }
        try {
            BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
            int intrinsicWidth = (bitmapDrawable == null || (bitmap2 = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicWidth() : bitmap2.getWidth();
            BitmapDrawable bitmapDrawable2 = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
            int intrinsicHeight = (bitmapDrawable2 == null || (bitmap = bitmapDrawable2.getBitmap()) == null) ? drawable.getIntrinsicHeight() : bitmap.getHeight();
            if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                Resources resources = context.getResources();
                int dimensionPixelSize = resources != null ? resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) : 192;
                try {
                    if (intrinsicWidth <= dimensionPixelSize && intrinsicHeight <= dimensionPixelSize) {
                        return getTargetRoundRectDrawable(intrinsicWidth, intrinsicHeight, drawable, f, i, i2, context);
                    }
                    Resources resources2 = context.getResources();
                    int dimensionPixelSize2 = resources2 != null ? resources2.getDimensionPixelSize(2131170766) : 194;
                    return getTargetRoundRectDrawable(intrinsicWidth, intrinsicHeight, drawable, f, dimensionPixelSize2, dimensionPixelSize2, context);
                } catch (Exception unused) {
                    return drawable2;
                }
            }
        } catch (Exception unused2) {
        }
        return drawable;
    }

    public static final Drawable getTargetRoundRectDrawable(int i, int i2, Drawable drawable, float f, int i3, int i4, Context context) {
        Bitmap bitmapCreateBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap().getConfig() == Bitmap.Config.HARDWARE) {
                bitmapDrawable.setBitmap(bitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true));
            }
        }
        try {
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(i, i2, config);
            if (bitmapCreateBitmap2 == null) {
                return drawable;
            }
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            float f2 = i3;
            try {
                float width = f2 / bitmapCreateBitmap2.getWidth();
                float f3 = i4;
                float height = f3 / bitmapCreateBitmap2.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(width, height);
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                BitmapShader bitmapShader = new BitmapShader(bitmapCreateBitmap2, tileMode, tileMode);
                bitmapCreateBitmap = Bitmap.createBitmap(i3, i4, config);
                bitmapShader.setLocalMatrix(matrix);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setShader(bitmapShader);
                new Canvas(bitmapCreateBitmap).drawPath(OplusRoundRectUtil.getInstance().getPath(new RectF(0.0f, 0.0f, f2, f3), f2 * f), paint);
            } catch (OutOfMemoryError e) {
                Log.e(TAG, " RoundRectBitmap OOM  " + e);
                bitmapCreateBitmap = bitmapCreateBitmap2;
            }
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
            bitmapCreateBitmap2.recycle();
            return bitmapDrawable2;
        } catch (OutOfMemoryError e2) {
            Log.e(TAG, "getTargetRoundRectDrawable OOM, drawable: " + drawable + ", exception: " + e2);
            return drawable;
        }
    }

    public static final boolean isLargeNotificationIcon(Drawable drawable, Context context) {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (drawable == null) {
            return false;
        }
        boolean z = drawable instanceof BitmapDrawable;
        BitmapDrawable bitmapDrawable = z ? (BitmapDrawable) drawable : null;
        int intrinsicWidth = (bitmapDrawable == null || (bitmap2 = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicWidth() : bitmap2.getWidth();
        BitmapDrawable bitmapDrawable2 = z ? (BitmapDrawable) drawable : null;
        int intrinsicHeight = (bitmapDrawable2 == null || (bitmap = bitmapDrawable2.getBitmap()) == null) ? drawable.getIntrinsicHeight() : bitmap.getHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            return false;
        }
        Resources resources = context.getResources();
        int dimensionPixelSize = resources != null ? resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) : 192;
        return intrinsicWidth > dimensionPixelSize || intrinsicHeight > dimensionPixelSize;
    }

    public static final void recycleOwnedRoundRectDrawable(Drawable drawable, Drawable drawable2) {
        Bitmap bitmap;
        if (drawable == null || drawable == drawable2) {
            return;
        }
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        if (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public static final Drawable getRoundRectDrawable(Drawable drawable, int i, int i2, int i3, Context context) {
        return getRoundRectDrawable(drawable, i / i2, i2, i3, context);
    }
}
