package p4;

import b3.r;
import i4.p;
import i4.y;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import n4.o;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Executor, Closeable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f5047k = AtomicLongFieldUpdater.newUpdater(c.class, "parkedWorkersStack$volatile");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f5048l = AtomicLongFieldUpdater.newUpdater(c.class, "controlState$volatile");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f5049m = AtomicIntegerFieldUpdater.newUpdater(c.class, "_isTerminated$volatile");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final r f5050n = new r("NOT_IN_STACK", 1);
    private volatile /* synthetic */ int _isTerminated$volatile;
    private volatile /* synthetic */ long controlState$volatile;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5051d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5052e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5053f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f5054g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f f5055h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f5056i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final o f5057j;
    private volatile /* synthetic */ long parkedWorkersStack$volatile;

    public c(int i5, int i6, long j5, String str) {
        this.f5051d = i5;
        this.f5052e = i6;
        this.f5053f = j5;
        this.f5054g = str;
        if (i5 < 1) {
            throw new IllegalArgumentException(("Core pool size " + i5 + " should be at least 1").toString());
        }
        if (i6 < i5) {
            throw new IllegalArgumentException(b.b.d("Max pool size ", i6, " should be greater than or equals to core pool size ", i5).toString());
        }
        if (i6 > 2097150) {
            throw new IllegalArgumentException(("Max pool size " + i6 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j5 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j5 + " must be positive").toString());
        }
        this.f5055h = new f();
        this.f5056i = new f();
        this.f5057j = new o((i5 + 1) * 2);
        this.controlState$volatile = ((long) i5) << 42;
        this._isTerminated$volatile = 0;
    }

    public static /* synthetic */ void c(c cVar, Runnable runnable, int i5) {
        cVar.b(runnable, false, (i5 & 4) == 0);
    }

    public final int a() {
        synchronized (this.f5057j) {
            try {
                if (f5049m.get(this) != 0) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = f5048l;
                long j5 = atomicLongFieldUpdater.get(this);
                int i5 = (int) (j5 & 2097151);
                int i6 = i5 - ((int) ((j5 & 4398044413952L) >> 21));
                if (i6 < 0) {
                    i6 = 0;
                }
                if (i6 >= this.f5051d) {
                    return 0;
                }
                if (i5 >= this.f5052e) {
                    return 0;
                }
                int i7 = ((int) (atomicLongFieldUpdater.get(this) & 2097151)) + 1;
                if (i7 <= 0 || this.f5057j.b(i7) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                a aVar = new a(this, i7);
                this.f5057j.c(i7, aVar);
                if (i7 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i8 = i6 + 1;
                aVar.start();
                return i8;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(Runnable runnable, boolean z2, boolean z3) {
        i jVar;
        b bVar;
        k.f5071f.getClass();
        long jNanoTime = System.nanoTime();
        if (runnable instanceof i) {
            jVar = (i) runnable;
            jVar.f5063d = jNanoTime;
            jVar.f5064e = z2;
        } else {
            jVar = new j(runnable, jNanoTime, z2);
        }
        boolean z4 = jVar.f5064e;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f5048l;
        long jAddAndGet = z4 ? atomicLongFieldUpdater.addAndGet(this, 2097152L) : 0L;
        Thread threadCurrentThread = Thread.currentThread();
        a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
        if (aVar == null || !b4.l.a(aVar.f5040k, this)) {
            aVar = null;
        }
        if (aVar != null && (bVar = aVar.f5035f) != b.f5045h && (jVar.f5064e || bVar != b.f5042e)) {
            aVar.f5039j = true;
            m mVar = aVar.f5033d;
            if (z3) {
                jVar = mVar.a(jVar);
            } else {
                mVar.getClass();
                i iVar = (i) m.f5073b.getAndSet(mVar, jVar);
                jVar = iVar == null ? null : mVar.a(iVar);
            }
        }
        if (jVar != null) {
            if (!(jVar.f5064e ? this.f5056i.a(jVar) : this.f5055h.a(jVar))) {
                throw new RejectedExecutionException(this.f5054g + " was terminated");
            }
        }
        boolean z5 = z3 && aVar != null;
        if (z4) {
            if (z5 || f() || e(jAddAndGet)) {
                return;
            }
            f();
            return;
        }
        if (z5 || f() || e(atomicLongFieldUpdater.get(this))) {
            return;
        }
        f();
    }

    /* JADX WARN: Code duplicated, block: B:39:0x008a  */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws InterruptedException {
        int i5;
        i iVarA;
        if (f5049m.compareAndSet(this, 0, 1)) {
            Thread threadCurrentThread = Thread.currentThread();
            a aVar = threadCurrentThread instanceof a ? (a) threadCurrentThread : null;
            if (aVar == null || !b4.l.a(aVar.f5040k, this)) {
                aVar = null;
            }
            synchronized (this.f5057j) {
                i5 = (int) (f5048l.get(this) & 2097151);
            }
            if (1 <= i5) {
                int i6 = 1;
                while (true) {
                    Object objB = this.f5057j.b(i6);
                    b4.l.b(objB);
                    a aVar2 = (a) objB;
                    if (aVar2 != aVar) {
                        while (aVar2.getState() != Thread.State.TERMINATED) {
                            LockSupport.unpark(aVar2);
                            aVar2.join(10000L);
                        }
                        m mVar = aVar2.f5033d;
                        f fVar = this.f5056i;
                        mVar.getClass();
                        i iVar = (i) m.f5073b.getAndSet(mVar, null);
                        if (iVar != null) {
                            fVar.a(iVar);
                        }
                        while (true) {
                            i iVarB = mVar.b();
                            if (iVarB == null) {
                                break;
                            } else {
                                fVar.a(iVarB);
                            }
                        }
                    }
                    if (i6 == i5) {
                        break;
                    } else {
                        i6++;
                    }
                }
            }
            this.f5056i.b();
            this.f5055h.b();
            while (true) {
                if (aVar != null) {
                    iVarA = aVar.a(true);
                    if (iVarA == null) {
                        iVarA = (i) this.f5055h.d();
                        if (iVarA == null) {
                            break;
                            break;
                        }
                    }
                } else {
                    iVarA = (i) this.f5055h.d();
                    if (iVarA == null && (iVarA = (i) this.f5056i.d()) == null) {
                        break;
                    }
                }
                try {
                    iVarA.run();
                } catch (Throwable th) {
                    Thread threadCurrentThread2 = Thread.currentThread();
                    threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
                }
            }
            if (aVar != null) {
                aVar.h(b.f5045h);
            }
            f5047k.set(this, 0L);
            f5048l.set(this, 0L);
        }
    }

    public final void d(a aVar, int i5, int i6) {
        while (true) {
            long j5 = f5047k.get(this);
            int i7 = (int) (2097151 & j5);
            long j6 = (2097152 + j5) & (-2097152);
            if (i7 == i5) {
                if (i6 == 0) {
                    Object objC = aVar.c();
                    while (true) {
                        if (objC == f5050n) {
                            i7 = -1;
                            break;
                        }
                        if (objC == null) {
                            i7 = 0;
                            break;
                        }
                        a aVar2 = (a) objC;
                        int iB = aVar2.b();
                        if (iB != 0) {
                            i7 = iB;
                            break;
                        }
                        objC = aVar2.c();
                    }
                } else {
                    i7 = i6;
                }
            }
            if (i7 >= 0) {
                if (f5047k.compareAndSet(this, j5, ((long) i7) | j6)) {
                    return;
                }
            }
        }
    }

    public final boolean e(long j5) {
        int i5 = ((int) (2097151 & j5)) - ((int) ((j5 & 4398044413952L) >> 21));
        if (i5 < 0) {
            i5 = 0;
        }
        int i6 = this.f5051d;
        if (i5 < i6) {
            int iA = a();
            if (iA == 1 && i6 > 1) {
                a();
            }
            if (iA > 0) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        c(this, runnable, 6);
    }

    public final boolean f() {
        r rVar;
        int iB;
        while (true) {
            long j5 = f5047k.get(this);
            a aVar = (a) this.f5057j.b((int) (2097151 & j5));
            if (aVar == null) {
                aVar = null;
            } else {
                long j6 = (2097152 + j5) & (-2097152);
                Object objC = aVar.c();
                while (true) {
                    rVar = f5050n;
                    if (objC == rVar) {
                        iB = -1;
                        break;
                    }
                    if (objC == null) {
                        iB = 0;
                        break;
                    }
                    a aVar2 = (a) objC;
                    iB = aVar2.b();
                    if (iB != 0) {
                        break;
                    }
                    objC = aVar2.c();
                }
                if (iB >= 0) {
                    if (f5047k.compareAndSet(this, j5, ((long) iB) | j6)) {
                        aVar.g(rVar);
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (aVar == null) {
                return false;
            }
            if (a.f5032l.compareAndSet(aVar, -1, 0)) {
                LockSupport.unpark(aVar);
                return true;
            }
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        o oVar = this.f5057j;
        int iA = oVar.a();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 1; i10 < iA; i10++) {
            a aVar = (a) oVar.b(i10);
            if (aVar != null) {
                m mVar = aVar.f5033d;
                mVar.getClass();
                int i11 = m.f5073b.get(mVar) != null ? (m.f5074c.get(mVar) - m.f5075d.get(mVar)) + 1 : m.f5074c.get(mVar) - m.f5075d.get(mVar);
                int iOrdinal = aVar.f5035f.ordinal();
                if (iOrdinal == 0) {
                    i5++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i11);
                    sb.append('c');
                    arrayList.add(sb.toString());
                } else if (iOrdinal == 1) {
                    i6++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(i11);
                    sb2.append('b');
                    arrayList.add(sb2.toString());
                } else if (iOrdinal == 2) {
                    i7++;
                } else if (iOrdinal == 3) {
                    i8++;
                    if (i11 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(i11);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else {
                    if (iOrdinal != 4) {
                        throw new p();
                    }
                    i9++;
                }
            }
        }
        long j5 = f5048l.get(this);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.f5054g);
        sb4.append('@');
        sb4.append(y.g(this));
        sb4.append("[Pool Size {core = ");
        int i12 = this.f5051d;
        sb4.append(i12);
        sb4.append(", max = ");
        sb4.append(this.f5052e);
        sb4.append("}, Worker States {CPU = ");
        sb4.append(i5);
        sb4.append(", blocking = ");
        sb4.append(i6);
        sb4.append(", parked = ");
        sb4.append(i7);
        sb4.append(", dormant = ");
        sb4.append(i8);
        sb4.append(", terminated = ");
        sb4.append(i9);
        sb4.append("}, running workers queues = ");
        sb4.append(arrayList);
        sb4.append(", global CPU queue size = ");
        sb4.append(this.f5055h.c());
        sb4.append(", global blocking queue size = ");
        sb4.append(this.f5056i.c());
        sb4.append(", Control State {created workers= ");
        sb4.append((int) (2097151 & j5));
        sb4.append(", blocking tasks = ");
        sb4.append((int) ((4398044413952L & j5) >> 21));
        sb4.append(", CPUs acquired = ");
        sb4.append(i12 - ((int) ((j5 & 9223367638808264704L) >> 42)));
        sb4.append("}]");
        return sb4.toString();
    }
}
