package p4;

import b3.r;
import b4.w;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends Thread {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f5032l = AtomicIntegerFieldUpdater.newUpdater(a.class, "workerCtl$volatile");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f5033d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w f5034e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f5035f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f5036g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f5037h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5038i;
    private volatile int indexInArray;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5039j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ c f5040k;
    private volatile Object nextParkedWorker;
    private volatile /* synthetic */ int workerCtl$volatile;

    public a(c cVar, int i5) {
        this.f5040k = cVar;
        setDaemon(true);
        setContextClassLoader(c.class.getClassLoader());
        this.f5033d = new m();
        this.f5034e = new w();
        this.f5035f = b.f5044g;
        this.nextParkedWorker = c.f5050n;
        int iNanoTime = (int) System.nanoTime();
        this.f5038i = iNanoTime == 0 ? 42 : iNanoTime;
        f(i5);
    }

    public final i a(boolean z2) {
        i iVarE;
        i iVarE2;
        long j5;
        b bVar = this.f5035f;
        c cVar = this.f5040k;
        i iVar = null;
        m mVar = this.f5033d;
        b bVar2 = b.f5041d;
        if (bVar != bVar2) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = c.f5048l;
            do {
                j5 = atomicLongFieldUpdater.get(cVar);
                if (((int) ((9223367638808264704L & j5) >> 42)) == 0) {
                    mVar.getClass();
                    loop1: while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f5073b;
                        i iVar2 = (i) atomicReferenceFieldUpdater.get(mVar);
                        if (iVar2 == null || !iVar2.f5064e) {
                            int i5 = m.f5075d.get(mVar);
                            int i6 = m.f5074c.get(mVar);
                            while (i5 != i6 && m.f5076e.get(mVar) != 0) {
                                i6--;
                                i iVarC = mVar.c(i6, true);
                                if (iVarC != null) {
                                    iVar = iVarC;
                                    break;
                                }
                            }
                            break;
                        }
                        do {
                            if (atomicReferenceFieldUpdater.compareAndSet(mVar, iVar2, null)) {
                                iVar = iVar2;
                                break loop1;
                            }
                        } while (atomicReferenceFieldUpdater.get(mVar) == iVar2);
                    }
                    if (iVar != null) {
                        return iVar;
                    }
                    i iVar3 = (i) cVar.f5056i.d();
                    return iVar3 == null ? i(1) : iVar3;
                }
            } while (!c.f5048l.compareAndSet(cVar, j5, j5 - 4398046511104L));
            this.f5035f = bVar2;
        }
        if (z2) {
            boolean z3 = d(cVar.f5051d * 2) == 0;
            if (z3 && (iVarE2 = e()) != null) {
                return iVarE2;
            }
            mVar.getClass();
            i iVarB = (i) m.f5073b.getAndSet(mVar, null);
            if (iVarB == null) {
                iVarB = mVar.b();
            }
            if (iVarB != null) {
                return iVarB;
            }
            if (!z3 && (iVarE = e()) != null) {
                return iVarE;
            }
        } else {
            i iVarE3 = e();
            if (iVarE3 != null) {
                return iVarE3;
            }
        }
        return i(3);
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i5) {
        int i6 = this.f5038i;
        int i7 = i6 ^ (i6 << 13);
        int i8 = i7 ^ (i7 >> 17);
        int i9 = i8 ^ (i8 << 5);
        this.f5038i = i9;
        int i10 = i5 - 1;
        return (i10 & i5) == 0 ? i9 & i10 : (i9 & Integer.MAX_VALUE) % i5;
    }

    public final i e() {
        int iD = d(2);
        c cVar = this.f5040k;
        if (iD == 0) {
            i iVar = (i) cVar.f5055h.d();
            return iVar != null ? iVar : (i) cVar.f5056i.d();
        }
        i iVar2 = (i) cVar.f5056i.d();
        return iVar2 != null ? iVar2 : (i) cVar.f5055h.d();
    }

    public final void f(int i5) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5040k.f5054g);
        sb.append("-worker-");
        sb.append(i5 == 0 ? "TERMINATED" : String.valueOf(i5));
        setName(sb.toString());
        this.indexInArray = i5;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(b bVar) {
        b bVar2 = this.f5035f;
        boolean z2 = bVar2 == b.f5041d;
        if (z2) {
            c.f5048l.addAndGet(this.f5040k, 4398046511104L);
        }
        if (bVar2 != bVar) {
            this.f5035f = bVar;
        }
        return z2;
    }

    public final i i(int i5) {
        long j5;
        i iVarC;
        long j6;
        long j7;
        i iVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = c.f5048l;
        c cVar = this.f5040k;
        int i6 = (int) (atomicLongFieldUpdater.get(cVar) & 2097151);
        i iVar2 = null;
        if (i6 < 2) {
            return null;
        }
        int iD = d(i6);
        int i7 = 0;
        long jMin = Long.MAX_VALUE;
        while (i7 < i6) {
            iD++;
            if (iD > i6) {
                iD = 1;
            }
            a aVar = (a) cVar.f5057j.b(iD);
            if (aVar != null && aVar != this) {
                m mVar = aVar.f5033d;
                if (i5 != 3) {
                    mVar.getClass();
                    int i8 = m.f5075d.get(mVar);
                    int i9 = m.f5074c.get(mVar);
                    boolean z2 = i5 == 1;
                    while (true) {
                        if (i8 != i9) {
                            j5 = 0;
                            if (!z2 || m.f5076e.get(mVar) != 0) {
                                int i10 = i8 + 1;
                                iVarC = mVar.c(i8, z2);
                                if (iVarC != null) {
                                    break;
                                }
                                i8 = i10;
                            }
                        } else {
                            j5 = 0;
                        }
                        iVarC = iVar2;
                        break;
                    }
                } else {
                    iVarC = mVar.b();
                    j5 = 0;
                }
                w wVar = this.f5034e;
                if (iVarC == null) {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m.f5073b;
                        i iVar3 = (i) atomicReferenceFieldUpdater.get(mVar);
                        if (iVar3 == null) {
                            j6 = -1;
                        } else {
                            j6 = -1;
                            if (((iVar3.f5064e ? 1 : 2) & i5) != 0) {
                                k.f5071f.getClass();
                                m mVar2 = mVar;
                                long jNanoTime = System.nanoTime() - iVar3.f5063d;
                                long j8 = k.f5067b;
                                if (jNanoTime < j8) {
                                    j7 = j8 - jNanoTime;
                                    iVar = null;
                                    break;
                                }
                                do {
                                    iVar = null;
                                    if (atomicReferenceFieldUpdater.compareAndSet(mVar2, iVar3, null)) {
                                        wVar.f697d = iVar3;
                                        j7 = -1;
                                        break;
                                    }
                                } while (atomicReferenceFieldUpdater.get(mVar2) == iVar3);
                                mVar = mVar2;
                                iVar2 = null;
                            }
                        }
                        j7 = -2;
                        iVar = iVar2;
                        break;
                    }
                } else {
                    wVar.f697d = iVarC;
                    iVar = iVar2;
                    j7 = -1;
                    j6 = -1;
                }
                if (j7 == j6) {
                    i iVar4 = (i) wVar.f697d;
                    wVar.f697d = iVar;
                    return iVar4;
                }
                if (j7 > j5) {
                    jMin = Math.min(jMin, j7);
                }
            }
            i7++;
            iVar2 = null;
        }
        if (jMin == Long.MAX_VALUE) {
            jMin = 0;
        }
        this.f5037h = jMin;
        return null;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        long j5;
        loop0: while (true) {
            boolean z2 = false;
            while (true) {
                if (c.f5049m.get(this.f5040k) == 0) {
                    b bVar = this.f5035f;
                    b bVar2 = b.f5045h;
                    if (bVar == bVar2) {
                        break loop0;
                    }
                    i iVarA = a(this.f5039j);
                    if (iVarA != null) {
                        this.f5037h = 0L;
                        c cVar = this.f5040k;
                        this.f5036g = 0L;
                        if (this.f5035f == b.f5043f) {
                            this.f5035f = b.f5042e;
                        }
                        if (!iVarA.f5064e) {
                            try {
                                iVarA.run();
                                break;
                            } catch (Throwable th) {
                                Thread threadCurrentThread = Thread.currentThread();
                                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
                                break;
                            }
                        }
                        if (h(b.f5042e) && !cVar.f() && !cVar.e(c.f5048l.get(cVar))) {
                            cVar.f();
                        }
                        try {
                            iVarA.run();
                        } catch (Throwable th2) {
                            Thread threadCurrentThread2 = Thread.currentThread();
                            threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th2);
                        }
                        c.f5048l.addAndGet(cVar, -2097152L);
                        if (this.f5035f == bVar2) {
                            break;
                        }
                        this.f5035f = b.f5044g;
                        break;
                    }
                    this.f5039j = false;
                    if (this.f5037h == 0) {
                        Object obj = this.nextParkedWorker;
                        r rVar = c.f5050n;
                        if (obj != rVar) {
                            f5032l.set(this, -1);
                            while (this.nextParkedWorker != c.f5050n) {
                                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5032l;
                                if (atomicIntegerFieldUpdater.get(this) != -1) {
                                    break;
                                }
                                c cVar2 = this.f5040k;
                                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater2 = c.f5049m;
                                if (atomicIntegerFieldUpdater2.get(cVar2) != 0) {
                                    break;
                                }
                                b bVar3 = this.f5035f;
                                b bVar4 = b.f5045h;
                                if (bVar3 == bVar4) {
                                    break;
                                }
                                h(b.f5043f);
                                Thread.interrupted();
                                if (this.f5036g == 0) {
                                    j5 = 2097151;
                                    this.f5036g = System.nanoTime() + this.f5040k.f5053f;
                                } else {
                                    j5 = 2097151;
                                }
                                LockSupport.parkNanos(this.f5040k.f5053f);
                                if (System.nanoTime() - this.f5036g >= 0) {
                                    this.f5036g = 0L;
                                    c cVar3 = this.f5040k;
                                    synchronized (cVar3.f5057j) {
                                        try {
                                            if (!(atomicIntegerFieldUpdater2.get(cVar3) != 0)) {
                                                AtomicLongFieldUpdater atomicLongFieldUpdater = c.f5048l;
                                                if (((int) (atomicLongFieldUpdater.get(cVar3) & j5)) > cVar3.f5051d) {
                                                    if (atomicIntegerFieldUpdater.compareAndSet(this, -1, 1)) {
                                                        int i5 = this.indexInArray;
                                                        f(0);
                                                        cVar3.d(this, i5, 0);
                                                        int andDecrement = (int) (atomicLongFieldUpdater.getAndDecrement(cVar3) & j5);
                                                        if (andDecrement != i5) {
                                                            Object objB = cVar3.f5057j.b(andDecrement);
                                                            b4.l.b(objB);
                                                            a aVar = (a) objB;
                                                            cVar3.f5057j.c(i5, aVar);
                                                            aVar.f(i5);
                                                            cVar3.d(aVar, andDecrement, i5);
                                                        }
                                                        cVar3.f5057j.c(andDecrement, null);
                                                        this.f5035f = bVar4;
                                                    }
                                                }
                                            }
                                        } catch (Throwable th3) {
                                            throw th3;
                                        }
                                    }
                                }
                            }
                        } else {
                            c cVar4 = this.f5040k;
                            if (this.nextParkedWorker == rVar) {
                                AtomicLongFieldUpdater atomicLongFieldUpdater2 = c.f5047k;
                                while (true) {
                                    long j6 = atomicLongFieldUpdater2.get(cVar4);
                                    int i6 = this.indexInArray;
                                    this.nextParkedWorker = cVar4.f5057j.b((int) (j6 & 2097151));
                                    c cVar5 = cVar4;
                                    if (c.f5047k.compareAndSet(cVar5, j6, ((j6 + 2097152) & (-2097152)) | ((long) i6))) {
                                        break;
                                    } else {
                                        cVar4 = cVar5;
                                    }
                                }
                            }
                        }
                    } else {
                        if (z2) {
                            h(b.f5043f);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.f5037h);
                            this.f5037h = 0L;
                            break;
                        }
                        z2 = true;
                    }
                } else {
                    break loop0;
                }
            }
        }
        h(b.f5045h);
    }
}
