package com.oplusos.systemui.common.blurability.ext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$;
import android.util.Log;
import com.oplus.launcher.graphic.GaussianBlur;
import com.oplus.wallpaper.sdk.ImageProcess;
import com.oplusos.systemui.common.blurability.report.ReportManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class BitmapExtKt {
    private static final String TAG = "BitmapExt";

    public static final Bitmap blur(Bitmap bitmap, int i) {
        Object obj;
        if (bitmap.isRecycled()) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[blur] is already recycled");
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            if (bitmap.hasAlpha()) {
                bitmap = bitmap.copy(Bitmap.Config.RGB_565, false);
            }
            obj = Result.constructor-impl(GaussianBlur.getInstance().generateGaussianBitmap(bitmap, i, 1.0f, false));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "[blur] exception " + th2.getMessage());
        }
        if (obj instanceof Result.Failure) {
            obj = null;
        }
        Bitmap bitmap2 = (Bitmap) obj;
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[blur] cost " + jCurrentTimeMillis2 + " ms, " + bitmap2, null, 8);
        ReportManager.INSTANCE.report("event_blur_cost", MapsKt__MapsKt.hashMapOf(new Pair[]{new Pair("key_blur_cost_in_milli", String.valueOf(jCurrentTimeMillis2))}));
        return bitmap2;
    }

    public static final boolean checkBitmapIsPureBlack(Bitmap bitmap) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Bitmap bitmapCopy = bitmap.getConfig() == Bitmap.Config.HARDWARE ? bitmap.copy(Bitmap.Config.RGB_565, false) : bitmap;
        int height = bitmap.getHeight() * bitmap.getWidth();
        int[] iArr = new int[height];
        bitmapCopy.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (!bitmapCopy.equals(bitmap)) {
            bitmapCopy.recycle();
        }
        for (int i = 0; i < height; i += 20) {
            if (iArr[i] != -16777216) {
                StringBuilder sb = new StringBuilder("[checkBitmapIsPureBlack] ");
                sb.append(Integer.toHexString(iArr[i]));
                sb.append(' ');
                sb.append(bitmap);
                sb.append(" = false, cost ");
                LogUtil.i$default(LogUtil.TAG_COMMON, TAG, MediaSessionCompat$QueueItem$.ExternalSyntheticOutline0.m(System.currentTimeMillis() - jCurrentTimeMillis, "ms", sb), null, 8);
                return false;
            }
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[checkBitmapIsPureBlack] " + bitmap + " = true, cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms", null, 8);
        return true;
    }

    public static final Bitmap compressAsBitmap(Bitmap bitmap, int i, int i2) {
        Object obj;
        if (bitmap.getWidth() == 0 || bitmap.getHeight() == 0 || bitmap.isRecycled()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "BitmapExt-->".concat("[compress] width=" + bitmap.getWidth() + ", height=" + bitmap.getHeight() + ", isRecycled = " + bitmap.isRecycled()));
            }
            return null;
        }
        if (bitmap.getHeight() <= i2 && bitmap.getWidth() <= i) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "[compress] width=" + bitmap.getWidth() + ", height=" + bitmap.getHeight() + ", dstHeight = " + i2 + ", dstHeight=" + i2);
            return bitmap;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        float fMax = Math.max(i2 / bitmap.getHeight(), i / bitmap.getWidth());
        try {
            Bitmap bitmapCopy = bitmap.getConfig() == Bitmap.Config.HARDWARE ? bitmap.copy(Bitmap.Config.RGB_565, false) : bitmap;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) (bitmap.getWidth() * fMax), (int) (bitmap.getHeight() * fMax), Bitmap.Config.RGB_565);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapCopy, (Rect) null, new Rect(0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight()), (Paint) null);
            obj = Result.constructor-impl(bitmapCreateBitmap);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "[compressAsBitmap] exception " + th2.getMessage());
        }
        Object obj2 = bitmap;
        if (!(obj instanceof Result.Failure)) {
            obj2 = obj;
        }
        Bitmap bitmap2 = (Bitmap) obj2;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "[compressAsBitmap] cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms, " + bitmap2, null, 8);
        return bitmap2;
    }

    public static final Integer getBitmapBrightness(Bitmap bitmap) {
        Object obj;
        if (bitmap.isRecycled()) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "[getBitmapBrightness] " + bitmap + " is isRecycled!");
            return null;
        }
        try {
            Bitmap bitmapCopy = bitmap.getConfig() == Bitmap.Config.HARDWARE ? bitmap.copy(Bitmap.Config.RGB_565, false) : bitmap;
            int bitmapBrightnessValue = ImageProcess.getBitmapBrightnessValue(bitmapCopy);
            if (!Intrinsics.areEqual(bitmapCopy, bitmap)) {
                bitmapCopy.recycle();
            }
            obj = Result.constructor-impl(Integer.valueOf(bitmapBrightnessValue));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "[getBitmapBrightness] " + th2.getMessage());
        }
        return (Integer) (Result.exceptionOrNull-impl(obj) == null ? obj : null);
    }

    public static final void saveLocal(Bitmap bitmap, Context context) {
        File file = new File(context.getFilesDir().getAbsolutePath() + "/images/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, System.currentTimeMillis() + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "BitmapExt-->".concat("[Bitmap.saveLocal] " + file2.getAbsoluteFile() + " is saved"));
            }
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }
}
