package com.oplus.vfxsdk.common;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class COEDataCache {
    public static final COEDataCache INSTANCE = new COEDataCache();
    private static final Lazy dataCacheMap$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.vfxsdk.common.COEDataCache$dataCacheMap$2
        public final ConcurrentHashMap<String, COEData> invoke() {
            return new ConcurrentHashMap<>();
        }
    });

    private final ConcurrentHashMap<String, COEData> getDataCacheMap() {
        return (ConcurrentHashMap) dataCacheMap$delegate.getValue();
    }

    public final COEData getCache(String str) {
        return getDataCacheMap().get(str);
    }

    public final void preloadFromAssets(Context context, String str) throws IOException {
        if (getCache(str) == null) {
            InputStream inputStreamOpen = context.getAssets().open(str);
            try {
                COEParse cOEParse = new COEParse();
                inputStreamOpen.getClass();
                COEData cOEData = cOEParse.parse(ByteStreamsKt.readBytes(inputStreamOpen), StringsKt__StringsJVMKt.endsWith(str, ".coz", false));
                COEDataCache cOEDataCache = INSTANCE;
                cOEData.getClass();
                cOEDataCache.putCache(str, cOEData);
                CloseableKt.closeFinally(inputStreamOpen, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStreamOpen, th);
                    throw th2;
                }
            }
        }
    }

    public final void putCache(String str, COEData cOEData) {
        getDataCacheMap().put(str, cOEData);
    }
}
