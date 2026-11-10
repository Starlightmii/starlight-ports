package i4;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class z extends k0 implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final z f2800m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final long f2801n;

    static {
        Long l5;
        z zVar = new z();
        f2800m = zVar;
        zVar.u(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l5 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l5 = 1000L;
        }
        f2801n = timeUnit.toNanos(l5.longValue());
    }

    public final synchronized void D() {
        int i5 = debugStatus;
        if (i5 == 2 || i5 == 3) {
            debugStatus = 3;
            k0.f2745j.set(this, null);
            k0.f2746k.set(this, null);
            notifyAll();
        }
    }

    @Override // i4.l0
    public final Thread r() {
        Thread thread;
        Thread thread2 = _thread;
        if (thread2 != null) {
            return thread2;
        }
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setContextClassLoader(f2800m.getClass().getClassLoader());
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    @Override // java.lang.Runnable
    public final void run() {
        k1.f2748a.set(this);
        try {
            synchronized (this) {
                int i5 = debugStatus;
                if (i5 == 2 || i5 == 3) {
                    _thread = null;
                    D();
                    if (B()) {
                        return;
                    }
                    r();
                    return;
                }
                debugStatus = 1;
                notifyAll();
                long j5 = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long jV = v();
                    if (jV == Long.MAX_VALUE) {
                        long jNanoTime = System.nanoTime();
                        if (j5 == Long.MAX_VALUE) {
                            j5 = f2801n + jNanoTime;
                        }
                        long j6 = j5 - jNanoTime;
                        if (j6 <= 0) {
                            _thread = null;
                            D();
                            if (B()) {
                                return;
                            }
                            r();
                            return;
                        }
                        if (jV > j6) {
                            jV = j6;
                        }
                    } else {
                        j5 = Long.MAX_VALUE;
                    }
                    if (jV > 0) {
                        int i6 = debugStatus;
                        if (i6 == 2 || i6 == 3) {
                            _thread = null;
                            D();
                            if (B()) {
                                return;
                            }
                            r();
                            return;
                        }
                        LockSupport.parkNanos(this, jV);
                    }
                }
            }
        } catch (Throwable th) {
            _thread = null;
            D();
            if (!B()) {
                r();
            }
            throw th;
        }
    }

    @Override // i4.k0, i4.l0
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    @Override // i4.r
    public final String toString() {
        return "DefaultExecutor";
    }

    @Override // i4.l0
    public final void x(long j5, i0 i0Var) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // i4.k0
    public final void y(Runnable runnable) {
        if (debugStatus == 4) {
            throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
        }
        super.y(runnable);
    }
}
