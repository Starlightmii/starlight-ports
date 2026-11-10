package com.oplus.systemui.util;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BitmapCache {
    public static final Companion Companion = new Companion();
    private final LruCache<String, Bitmap> memoryCache = new LruCache<>(10485760);
    private final ConcurrentHashMap<String, Runnable> cacheTimers = new ConcurrentHashMap<>();
    private final Handler handler = new Handler(Looper.getMainLooper());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void addToCache(String str, Bitmap bitmap) {
        if (getBitmapFromCache(str) == null) {
            this.memoryCache.put(str, bitmap);
            Runnable runnable = new addToCache.timerRunnable.1();
            ((addToCache.timerRunnable.1) runnable).this$0 = this;
            ((addToCache.timerRunnable.1) runnable).$key = str;
            VarHandle.storeStoreFence();
            this.cacheTimers.put(str, runnable);
            this.handler.postDelayed(runnable, 5000L);
        }
    }

    public final void clearCache() {
        this.memoryCache.evictAll();
        Iterator<Runnable> it = this.cacheTimers.values().iterator();
        while (it.hasNext()) {
            this.handler.removeCallbacks(it.next());
        }
        this.cacheTimers.clear();
    }

    public final Bitmap getBitmapFromCache(String str) {
        return this.memoryCache.get(str);
    }

    public final void updateCacheTimer(String str) {
        Runnable runnable = this.cacheTimers.get(str);
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            this.handler.postDelayed(runnable, 5000L);
        }
    }
}
