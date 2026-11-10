package com.oplus.wallpaper.sdk;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ImageProcess {
    public static final int BLACK = -13619152;
    public static final int BRIGHT_WALLPAPER_BRIGHTNESS = 600;
    private static final boolean DEBUG = LogUtils.DEBUG;
    public static final int POSITION_B = 2;
    public static final int POSITION_G = 1;
    public static final int POSITION_R = 0;
    private static final String TAG = "WS.ImageProcess";
    private static final int VALIDITY_B = 180;
    private static final int VALIDITY_G = 180;
    private static final int VALIDITY_R = 180;
    public static final int WHITE = -1;

    static {
        System.loadLibrary("oplus_image_process");
    }

    public static int calculateTextColor(Bitmap bitmap, Rect rect, int i) {
        if (checkArguments(bitmap, rect) && !checkWhite(calculateTextColorNative(bitmap, rect.left, rect.top, rect.right, rect.bottom, i))) {
            return BLACK;
        }
        return -1;
    }

    private static native int[] calculateTextColorNative(Bitmap bitmap, int i, int i2, int i3, int i4, int i5);

    private static boolean checkArguments(Bitmap bitmap, Rect rect) {
        boolean z = false;
        if (bitmap == null || bitmap.isRecycled() || rect == null) {
            if (DEBUG) {
                Log.w(TAG, "Arguments illegal, bitmap or rect useless.");
            }
        } else if (new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()).contains(rect)) {
            z = true;
        } else if (DEBUG) {
            Log.w(TAG, "Arguments illegal, rect isn't in bitmap's region.");
        }
        if (!z && DEBUG) {
            Log.w(TAG, "Arguments illegal, do nothing about calculate, we will return Default Color.");
        }
        return z;
    }

    private static boolean checkWhite(int[] iArr) {
        int i = iArr[0];
        return (i < 180 && iArr[1] < 180) || (i + iArr[1]) + iArr[2] < 600;
    }

    public static native int getBitmapBrightnessValue(Bitmap bitmap);

    public static native int getBitmapBrightnessValue(Bitmap bitmap, int i, int i2, int i3, int i4);

    public static native int getBitmapBrightnessValue(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6);

    public static native boolean saveBitmap(Bitmap bitmap, String str);
}
