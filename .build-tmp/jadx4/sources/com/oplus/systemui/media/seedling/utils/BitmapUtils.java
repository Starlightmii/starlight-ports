package com.oplus.systemui.media.seedling.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import androidx.core.graphics.drawable.DrawableKt;
import com.oplus.systemui.media.seedling.OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BitmapUtils {
    public static final BitmapUtils INSTANCE = new BitmapUtils();
    private static final String TAG = "BitmapUtils";

    public static final boolean areBitmapsContentEqual(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == bitmap2) {
            return true;
        }
        if (bitmap == null || bitmap2 == null || bitmap.isRecycled() || bitmap2.isRecycled() || bitmap.getWidth() != bitmap2.getWidth() || bitmap.getHeight() != bitmap2.getHeight() || bitmap.getConfig() != bitmap2.getConfig()) {
            return false;
        }
        return bitmap.sameAs(bitmap2);
    }

    public static final Bitmap createBitmapFromColor(String str, int i, int i2) {
        Object obj;
        if (str == null || StringsKt__StringsKt.isBlank(str)) {
            return null;
        }
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.eraseColor(Color.parseColor(str));
            obj = Result.constructor-impl(bitmapCreateBitmap);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("createBitmapFromColor onFailure ", th2.getMessage(), TAG);
        }
        return (Bitmap) (obj instanceof Result.Failure ? null : obj);
    }

    public static final Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap.Config config = drawable.getOpacity() == -1 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap bitmapCreateBitmap = (intrinsicWidth <= 0 || intrinsicHeight <= 0) ? Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888) : Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (IllegalArgumentException e) {
            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("drawable To Bitmap IllegalArgumentException error:", e.getMessage(), TAG);
            return null;
        } catch (IllegalStateException e2) {
            OplusMediaSeedlingController$mediaDataUpdateListener$1$$ExternalSyntheticOutline0.m("drawable To Bitmap IllegalStateException error: ", e2.getMessage(), TAG);
            return null;
        }
    }

    public static final Bitmap loadBitmapFromUri(Context context, Uri uri, int i, int i2) {
        if (uri.getScheme() == null) {
            return null;
        }
        if (!StringsKt__StringsJVMKt.equals(uri.getScheme(), "content", false) && !StringsKt__StringsJVMKt.equals(uri.getScheme(), "android.resource", false) && !StringsKt__StringsJVMKt.equals(uri.getScheme(), "file", false)) {
            return null;
        }
        ImageDecoder.Source sourceCreateSource = ImageDecoder.createSource(context.getContentResolver(), uri);
        try {
            loadBitmapFromUri.1 r6 = new loadBitmapFromUri.1();
            r6.$targetWidth = i;
            r6.$targetHeight = i2;
            VarHandle.storeStoreFence();
            return ImageDecoder.decodeBitmap(sourceCreateSource, r6);
        } catch (IOException e) {
            OplusNotificationLogger.logMediaSeedling(TAG, "Unable to load bitmap " + e);
            return null;
        } catch (RuntimeException e2) {
            OplusNotificationLogger.logMediaSeedling(TAG, "Unable to load bitmap " + e2);
            return null;
        }
    }

    public static final Drawable loadDrawableSafely(Icon icon, Context context) {
        Drawable drawableLoadDrawable;
        Object obj;
        if (icon != null) {
            try {
                drawableLoadDrawable = icon.loadDrawable(context);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
        } else {
            drawableLoadDrawable = null;
        }
        obj = Result.constructor-impl(drawableLoadDrawable);
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "load drawable failed, " + th2);
        }
        return (Drawable) (obj instanceof Result.Failure ? null : obj);
    }

    public static final Bitmap overlayColorAtBottom(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return bitmap;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        paint.setColor(i);
        canvas.drawRect(0.0f, 0.0f, width, height, paint);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static final Bitmap toBitmapSafely(Drawable drawable) {
        Bitmap bitmap$default;
        Object obj;
        if (drawable != null) {
            try {
                bitmap$default = DrawableKt.toBitmap$default(drawable, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), 4);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
        } else {
            bitmap$default = null;
        }
        obj = Result.constructor-impl(bitmap$default);
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "load bitmap failed, " + th2);
        }
        return (Bitmap) (obj instanceof Result.Failure ? null : obj);
    }
}
