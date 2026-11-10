package com.oplus.systemui.qs.utils;

import android.graphics.Bitmap;
import com.oplus.systemui.media.seedling.utils.BitmapUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaCacheUtil {
    public static final OplusQsMediaCacheUtil INSTANCE = new OplusQsMediaCacheUtil();
    private static volatile Bitmap bitmapCache;

    public final synchronized void clearCache() {
        bitmapCache = null;
    }

    public final synchronized boolean isSameArtWork(Bitmap bitmap) {
        if (bitmapCache == null && bitmap == null) {
            return true;
        }
        if (bitmapCache != null && bitmap != null) {
            return BitmapUtils.areBitmapsContentEqual(bitmapCache, bitmap);
        }
        return false;
    }

    public final synchronized void updateCache(Bitmap bitmap) {
        bitmapCache = bitmap;
    }
}
