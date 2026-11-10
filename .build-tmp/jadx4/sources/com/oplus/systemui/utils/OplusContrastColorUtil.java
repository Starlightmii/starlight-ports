package com.oplus.systemui.utils;

import android.R;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.VectorDrawable;
import android.util.Pair;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.util.ContrastColorUtil;
import com.oplus.util.OplusDarkModeUtil;
import java.lang.invoke.VarHandle;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusContrastColorUtil {
    public static OplusContrastColorUtil sInstance;
    public static final Object sLock = new Object();
    public final WeakHashMap mGrayscaleBitmapCache;
    public final int mGrayscaleIconMaxSize;
    public final OplusImageUtils mImageUtils;

    public OplusContrastColorUtil(Context context) {
        OplusImageUtils oplusImageUtils = new OplusImageUtils();
        oplusImageUtils.mTempMatrix = new Matrix();
        VarHandle.storeStoreFence();
        this.mImageUtils = oplusImageUtils;
        this.mGrayscaleBitmapCache = new WeakHashMap();
        this.mGrayscaleIconMaxSize = context.getResources().getDimensionPixelSize(2131170645);
    }

    public static int calculateContrastColor(Context context, Notification notification) {
        int i;
        int color;
        boolean zIsColorized = false;
        if (context == null) {
            return 0;
        }
        if (notification != null) {
            i = notification.color;
            zIsColorized = notification.isColorized();
        } else {
            i = 0;
        }
        boolean zIsNightMode = OplusDarkModeUtil.isNightMode(context);
        if (notification == null || !notification.isColorized()) {
            color = context.getColor(R.color.material_deep_teal_500);
        } else {
            color = notification.color;
            if (color == 0) {
                color = context.getColor(R.color.materialColorTextSecondaryAndTertiaryInverseDisabled);
            }
        }
        if (zIsColorized) {
            return ContrastColorUtil.ensureContrast(ColorUtils.blendARGB(color, ContrastColorUtil.isColorDark(color) ? -1 : -16777216, 0.9f), color, zIsNightMode, 4.5d);
        }
        int iResolveDefaultColor = i == 0 ? ContrastColorUtil.resolveDefaultColor(context, color, zIsNightMode) : ContrastColorUtil.resolveContrastColor(context, i, color, zIsNightMode);
        return Color.alpha(iResolveDefaultColor) == 255 ? iResolveDefaultColor : ContrastColorUtil.compositeColors(iResolveDefaultColor, color);
    }

    public static OplusContrastColorUtil getInstance(Context context) {
        OplusContrastColorUtil oplusContrastColorUtil;
        synchronized (sLock) {
            try {
                if (sInstance == null) {
                    sInstance = new OplusContrastColorUtil(context);
                }
                oplusContrastColorUtil = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return oplusContrastColorUtil;
    }

    public final boolean isGrayscaleIcon(Drawable drawable) {
        boolean zBooleanValue;
        boolean zIsGrayscale;
        int generationId;
        if (drawable instanceof InsetDrawable) {
            drawable = ((InsetDrawable) drawable).getDrawable();
        }
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    if (bitmap.getWidth() > this.mGrayscaleIconMaxSize || bitmap.getHeight() > this.mGrayscaleIconMaxSize) {
                        zBooleanValue = false;
                    } else {
                        Object obj = sLock;
                        synchronized (obj) {
                            try {
                                Pair pair = (Pair) this.mGrayscaleBitmapCache.get(bitmap);
                                if (pair == null || ((Integer) pair.second).intValue() != bitmap.getGenerationId()) {
                                    synchronized (this.mImageUtils) {
                                        zIsGrayscale = this.mImageUtils.isGrayscale(bitmap);
                                        generationId = bitmap.getGenerationId();
                                    }
                                    synchronized (obj) {
                                        this.mGrayscaleBitmapCache.put(bitmap, Pair.create(Boolean.valueOf(zIsGrayscale), Integer.valueOf(generationId)));
                                    }
                                    zBooleanValue = zIsGrayscale;
                                } else {
                                    zBooleanValue = ((Boolean) pair.first).booleanValue();
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    if (zBooleanValue) {
                        return true;
                    }
                }
            } else if (drawable instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
                if (animationDrawable.getNumberOfFrames() > 0 && isGrayscaleIcon(animationDrawable.getFrame(0))) {
                    return true;
                }
            } else if (drawable instanceof VectorDrawable) {
                return true;
            }
        }
        return false;
    }

    public final boolean isVectorDrawable(Drawable drawable) {
        if (drawable != null) {
            if (!(drawable instanceof AnimationDrawable)) {
                return drawable instanceof VectorDrawable;
            }
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            if (animationDrawable.getNumberOfFrames() > 0 && isGrayscaleIcon(animationDrawable.getFrame(0))) {
                return true;
            }
        }
        return false;
    }
}
