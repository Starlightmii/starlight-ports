package com.oplus.systemui.qs.cache;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ServiceInfo;
import android.util.LruCache;
import com.oplusos.systemui.qs.base.BaseBinderCacheManager;
import kotlin.Result;
import kotlin.ResultKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BinderCacheManager extends BaseBinderCacheManager {
    public static final Companion Companion = new Companion();
    private final LruCache<String, ApplicationInfo> applicationInfoCache;
    private final LruCache<ComponentName, ServiceInfo> flag1ServiceInfoCache;
    private final LruCache<ComponentName, ServiceInfo> flag2ServiceInfoCache;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BinderCacheManager(Context context) {
        super(context);
        this.applicationInfoCache = new LruCache<>(50);
        this.flag1ServiceInfoCache = new LruCache<>(50);
        this.flag2ServiceInfoCache = new LruCache<>(50);
    }

    public final void cacheServiceInfo(LruCache lruCache, ComponentName componentName, int i) {
        Object obj;
        try {
            obj = Result.constructor-impl(getPackageManager().getServiceInfo(componentName, i));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        boolean z = obj instanceof Result.Failure;
        if (!z) {
            lruCache.put(componentName, (ServiceInfo) obj);
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            releaseServiceInfoInCache(componentName);
        }
        if (z) {
            obj = null;
        }
    }

    public void createServiceInfoCache(ComponentName componentName, boolean z) {
        if (z || this.flag1ServiceInfoCache.get(componentName) == null) {
            cacheServiceInfo(this.flag1ServiceInfoCache, componentName, 786432);
        }
        if (z || this.flag2ServiceInfoCache.get(componentName) == null) {
            cacheServiceInfo(this.flag2ServiceInfoCache, componentName, 794752);
        }
    }

    public ApplicationInfo getApplicationInfoCache(String str, boolean z) {
        Object obj;
        if (!z && this.applicationInfoCache.get(str) != null) {
            return this.applicationInfoCache.get(str);
        }
        LruCache<String, ApplicationInfo> lruCache = this.applicationInfoCache;
        try {
            obj = Result.constructor-impl(getPackageManager().getApplicationInfo(str, 128));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        boolean z2 = obj instanceof Result.Failure;
        if (!z2) {
            lruCache.put(str, (ApplicationInfo) obj);
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            lruCache.remove(str);
        }
        if (z2) {
            obj = null;
        }
        return (ApplicationInfo) obj;
    }

    public ServiceInfo getServiceInfoWithCache(ComponentName componentName, int i) {
        if (i == 786432) {
            return this.flag1ServiceInfoCache.get(componentName);
        }
        if (i != 794752) {
            return null;
        }
        return this.flag2ServiceInfoCache.get(componentName);
    }

    public void releaseServiceInfoInCache(ComponentName componentName) {
        this.flag1ServiceInfoCache.remove(componentName);
        this.flag2ServiceInfoCache.remove(componentName);
    }
}
