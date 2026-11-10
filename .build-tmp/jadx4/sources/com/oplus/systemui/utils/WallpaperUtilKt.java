package com.oplus.systemui.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.collection.FloatList$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.io.CloseableKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class WallpaperUtilKt {
    private static final String FEATURE_SEPARATE_WALLPAPER_FOR_MULTI_DISPLAY = "oplus.software.wallpaper.separate_wallpaper_for_multi_display";
    private static final String TAG = "WallpaperUtil";
    private static final Lazy isMTKPlatform$delegate = LazyKt__LazyJVMKt.lazy(new WallpaperUtilKt$$ExternalSyntheticLambda0());

    public static final boolean getSupportSeparateWallpaper() {
        return OplusFeatureConfigManager.getInstance().hasFeature(FEATURE_SEPARATE_WALLPAPER_FOR_MULTI_DISPLAY);
    }

    public static final boolean isMTKPlatform() {
        return ((Boolean) isMTKPlatform$delegate.getValue()).booleanValue();
    }

    public static final Rect peekWallpaperDimensions(Context context, WallpaperManager wallpaperManager, int i, int i2, int i3) {
        int i4;
        Rect rect;
        if ((131072 & i) != 0) {
            i4 = (-131073) & i;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i4, "peekWallpaperDimensions COTA update dim source=", "WallpaperUtil-->", "SystemUi--Wallpaper");
            }
        } else {
            i4 = i;
        }
        ParcelFileDescriptor wallpaperFile = wallpaperManager.getWallpaperFile(i4 | i3, i2);
        Rect rect2 = null;
        if (wallpaperFile != null) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(wallpaperFile.getFileDescriptor(), null, options);
                rect = new Rect(0, 0, options.outWidth, options.outHeight);
                CloseableKt.closeFinally(wallpaperFile, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(wallpaperFile, th);
                    throw th2;
                }
            }
        } else {
            rect = null;
        }
        if (rect != null && rect.width() != 0 && rect.height() != 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("peekWallpaperDimensions source=", i, i2, " user=", " display=");
                sbM.append(i3);
                sbM.append(" dimen=");
                sbM.append(rect);
                Log.d("SystemUi--Wallpaper", "WallpaperUtil-->".concat(sbM.toString()));
            }
            return rect;
        }
        InputStream inputStreamOpenDefaultWallpaper = WallpaperManager.openDefaultWallpaper(context, i);
        if (inputStreamOpenDefaultWallpaper != null) {
            try {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStreamOpenDefaultWallpaper, null, options2);
                rect2 = new Rect(0, 0, options2.outWidth, options2.outHeight);
                try {
                    inputStreamOpenDefaultWallpaper.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th3) {
                try {
                    inputStreamOpenDefaultWallpaper.close();
                } catch (IOException unused2) {
                }
                throw th3;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_WALLPAPER)) {
            Log.d("SystemUi--Wallpaper", "WallpaperUtil-->".concat("peekWallpaperDimensions from default, dimen=" + rect2));
        }
        return rect2;
    }
}
