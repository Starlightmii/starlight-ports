package o2;

import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g implements Future {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f4390g = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Logger f4391h = Logger.getLogger(g.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d4.a f4392i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f4393j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile Object f4394d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile c f4395e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile f f4396f;

    static {
        d4.a eVar;
        try {
            eVar = new d(AtomicReferenceFieldUpdater.newUpdater(f.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(f.class, f.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g.class, f.class, "f"), AtomicReferenceFieldUpdater.newUpdater(g.class, c.class, "e"), AtomicReferenceFieldUpdater.newUpdater(g.class, Object.class, "d"));
            th = null;
        } catch (Throwable th) {
            th = th;
            eVar = new e();
        }
        f4392i = eVar;
        if (th != null) {
            f4391h.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f4393j = new Object();
    }

    public static void b(g gVar) {
        f fVar;
        c cVar;
        do {
            fVar = gVar.f4396f;
        } while (!f4392i.j(gVar, fVar, f.f4387c));
        while (fVar != null) {
            Thread thread = fVar.f4388a;
            if (thread != null) {
                fVar.f4388a = null;
                LockSupport.unpark(thread);
            }
            fVar = fVar.f4389b;
        }
        do {
            cVar = gVar.f4395e;
        } while (!f4392i.h(gVar, cVar));
        c cVar2 = null;
        while (cVar != null) {
            c cVar3 = cVar.f4381a;
            cVar.f4381a = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
        }
        while (cVar2 != null) {
            cVar2 = cVar2.f4381a;
            try {
                throw null;
            } catch (RuntimeException e5) {
                f4391h.log(Level.SEVERE, "RuntimeException while executing runnable null with executor null", (Throwable) e5);
            }
        }
    }

    public static Object c(Object obj) throws ExecutionException {
        if (obj instanceof a) {
            Throwable th = ((a) obj).f4379a;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof b) {
            throw new ExecutionException((Throwable) null);
        }
        if (obj == f4393j) {
            return null;
        }
        return obj;
    }

    public static Object d(g gVar) {
        Object obj;
        boolean z2 = false;
        while (true) {
            try {
                obj = gVar.get();
                break;
            } catch (InterruptedException unused) {
                z2 = true;
            } catch (Throwable th) {
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public final void a(StringBuilder sb) {
        try {
            Object objD = d(this);
            sb.append("SUCCESS, result=[");
            sb.append(objD == this ? "this future" : String.valueOf(objD));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e5) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e5.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e6) {
            sb.append("FAILURE, cause=[");
            sb.append(e6.getCause());
            sb.append("]");
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z2) {
        a aVar;
        Object obj = this.f4394d;
        if (obj != null) {
            return false;
        }
        if (f4390g) {
            aVar = new a(new CancellationException("Future.cancel() was called."), z2);
        } else {
            aVar = z2 ? a.f4377b : a.f4378c;
        }
        if (!f4392i.i(this, obj, aVar)) {
            return false;
        }
        b(this);
        return true;
    }

    public final void e(f fVar) {
        fVar.f4388a = null;
        while (true) {
            f fVar2 = this.f4396f;
            if (fVar2 == f.f4387c) {
                return;
            }
            f fVar3 = null;
            while (fVar2 != null) {
                f fVar4 = fVar2.f4389b;
                if (fVar2.f4388a != null) {
                    fVar3 = fVar2;
                } else if (fVar3 != null) {
                    fVar3.f4389b = fVar4;
                    if (fVar3.f4388a == null) {
                    }
                } else if (!f4392i.j(this, fVar2, fVar4)) {
                }
                fVar2 = fVar4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        f fVar = f.f4387c;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.f4394d;
        if (obj2 != null) {
            return c(obj2);
        }
        f fVar2 = this.f4396f;
        if (fVar2 != fVar) {
            f fVar3 = new f();
            do {
                d4.a aVar = f4392i;
                aVar.C(fVar3, fVar2);
                if (aVar.j(this, fVar2, fVar3)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            e(fVar3);
                            throw new InterruptedException();
                        }
                        obj = this.f4394d;
                    } while (obj == null);
                    return c(obj);
                }
                fVar2 = this.f4396f;
            } while (fVar2 != fVar);
        }
        return c(this.f4394d);
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j5, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        f fVar = f.f4387c;
        long nanos = timeUnit.toNanos(j5);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.f4394d;
        if (obj != null) {
            return c(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            f fVar2 = this.f4396f;
            if (fVar2 != fVar) {
                f fVar3 = new f();
                while (true) {
                    d4.a aVar = f4392i;
                    aVar.C(fVar3, fVar2);
                    if (aVar.j(this, fVar2, fVar3)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                e(fVar3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.f4394d;
                            if (obj2 != null) {
                                return c(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        e(fVar3);
                        break;
                    }
                    fVar2 = this.f4396f;
                    if (fVar2 == fVar) {
                    }
                }
            }
            return c(this.f4394d);
        }
        while (nanos > 0) {
            Object obj3 = this.f4394d;
            if (obj3 != null) {
                return c(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String str = "Waited " + j5 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String str2 = str + " (plus ";
            long j6 = -nanos;
            long jConvert = timeUnit.convert(j6, TimeUnit.NANOSECONDS);
            long nanos2 = j6 - timeUnit.toNanos(jConvert);
            boolean z2 = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String str3 = str2 + jConvert + " " + lowerCase;
                if (z2) {
                    str3 = str3 + ",";
                }
                str2 = str3 + " ";
            }
            if (z2) {
                str2 = str2 + nanos2 + " nanoseconds ";
            }
            str = str2 + "delay)";
        }
        if (isDone()) {
            throw new TimeoutException(str + " but future completed as timeout expired");
        }
        throw new TimeoutException(str + " for " + string);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.f4394d instanceof a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.f4394d != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.f4394d instanceof a) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e5) {
                str = "Exception thrown from implementation: " + e5.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
