package p4;

import java.util.concurrent.TimeUnit;
import n4.s;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5066a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f5067b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f5068c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f5069d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f5070e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final g f5071f;

    static {
        String property;
        int i5 = s.f4309a;
        try {
            property = System.getProperty("kotlinx.coroutines.scheduler.default.name");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            property = "DefaultDispatcher";
        }
        f5066a = property;
        f5067b = n4.a.i("kotlinx.coroutines.scheduler.resolution.ns", 100000L, 1L, Long.MAX_VALUE);
        int i6 = s.f4309a;
        if (i6 < 2) {
            i6 = 2;
        }
        f5068c = n4.a.j(i6, 8, "kotlinx.coroutines.scheduler.core.pool.size");
        f5069d = n4.a.j(2097150, 4, "kotlinx.coroutines.scheduler.max.pool.size");
        f5070e = TimeUnit.SECONDS.toNanos(n4.a.i("kotlinx.coroutines.scheduler.keep.alive.sec", 60L, 1L, Long.MAX_VALUE));
        f5071f = g.f5061a;
    }
}
