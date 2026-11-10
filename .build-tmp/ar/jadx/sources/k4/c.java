package k4;

import b4.z;
import i4.p1;
import i4.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class c implements g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f3442e = AtomicLongFieldUpdater.newUpdater(c.class, "sendersAndCloseStatus$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f3443f = AtomicLongFieldUpdater.newUpdater(c.class, "receivers$volatile");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f3444g = AtomicLongFieldUpdater.newUpdater(c.class, "bufferEnd$volatile");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f3445h = AtomicLongFieldUpdater.newUpdater(c.class, "completedExpandBuffersAndPauseFlag$volatile");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3446i = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "sendSegment$volatile");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3447j = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "receiveSegment$volatile");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3448k = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "bufferEndSegment$volatile");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3449l = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "_closeCause$volatile");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3450m = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "closeHandler$volatile");
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3451d;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;

    public c(int i5) {
        this.f3451d = i5;
        if (i5 < 0) {
            throw new IllegalArgumentException(("Invalid channel capacity: " + i5 + ", should be >=0").toString());
        }
        k kVar = e.f3453a;
        this.bufferEnd$volatile = i5 != 0 ? i5 != Integer.MAX_VALUE ? i5 : Long.MAX_VALUE : 0L;
        this.completedExpandBuffersAndPauseFlag$volatile = f3444g.get(this);
        k kVar2 = new k(0L, null, this, 3);
        this.sendSegment$volatile = kVar2;
        this.receiveSegment$volatile = kVar2;
        if (w()) {
            kVar2 = e.f3453a;
            b4.l.c("null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>", kVar2);
        }
        this.bufferEndSegment$volatile = kVar2;
        this._closeCause$volatile = e.f3471s;
    }

    public static boolean B(Object obj) {
        if (!(obj instanceof i4.f)) {
            throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
        }
        i4.f fVar = (i4.f) obj;
        k kVar = e.f3453a;
        b3.r rVarR = fVar.r(p3.n.f5026a, null);
        if (rVarR == null) {
            return false;
        }
        fVar.w(rVarR);
        return true;
    }

    public static final k a(c cVar, long j5, k kVar) {
        Object objB;
        c cVar2;
        k kVar2 = e.f3453a;
        d dVar = d.f3452k;
        loop0: while (true) {
            objB = n4.a.b(kVar, j5, dVar);
            if (!n4.a.e(objB)) {
                n4.q qVarC = n4.a.c(objB);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3446i;
                    n4.q qVar = (n4.q) atomicReferenceFieldUpdater.get(cVar);
                    if (qVar.f4307c >= qVarC.f4307c) {
                        break loop0;
                    }
                    if (!qVarC.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(cVar, qVar, qVarC)) {
                            if (!qVar.e()) {
                                break loop0;
                            }
                            qVar.d();
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(cVar) == qVar);
                    if (qVarC.e()) {
                        qVarC.d();
                    }
                }
            } else {
                break;
            }
        }
        boolean zE = n4.a.e(objB);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3443f;
        if (zE) {
            cVar.i();
            if (kVar.f4307c * ((long) e.f3454b) < atomicLongFieldUpdater.get(cVar)) {
                kVar.a();
                return null;
            }
        } else {
            k kVar3 = (k) n4.a.c(objB);
            long j6 = kVar3.f4307c;
            if (j6 <= j5) {
                return kVar3;
            }
            long j7 = ((long) e.f3454b) * j6;
            while (true) {
                long j8 = f3442e.get(cVar);
                long j9 = 1152921504606846975L & j8;
                if (j9 >= j7) {
                    cVar2 = cVar;
                    break;
                }
                cVar2 = cVar;
                if (f3442e.compareAndSet(cVar2, j8, (((long) ((int) (j8 >> 60))) << 60) + j9)) {
                    break;
                }
                cVar = cVar2;
            }
            if (j6 * ((long) e.f3454b) < atomicLongFieldUpdater.get(cVar2)) {
                kVar3.a();
            }
        }
        return null;
    }

    public static final void c(c cVar, Object obj, i4.h hVar) {
        hVar.resumeWith(p3.j.r(cVar.r()));
    }

    public static final int e(c cVar, k kVar, int i5, Object obj, long j5, Object obj2, boolean z2) {
        kVar.m(i5, obj);
        if (z2) {
            return cVar.D(kVar, i5, obj, j5, obj2, z2);
        }
        Object objK = kVar.k(i5);
        if (objK == null) {
            if (cVar.f(j5)) {
                if (kVar.j(null, i5, e.f3456d)) {
                    return 1;
                }
            } else {
                if (obj2 == null) {
                    return 3;
                }
                if (kVar.j(null, i5, obj2)) {
                    return 2;
                }
            }
        } else if (objK instanceof p1) {
            kVar.m(i5, null);
            if (cVar.A(objK, obj)) {
                kVar.n(i5, e.f3461i);
                return 0;
            }
            b3.r rVar = e.f3463k;
            if (kVar.f3478f.getAndSet((i5 * 2) + 1, rVar) == rVar) {
                return 5;
            }
            kVar.l(i5, true);
            return 5;
        }
        return cVar.D(kVar, i5, obj, j5, obj2, z2);
    }

    public static void t(c cVar) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3445h;
        if ((atomicLongFieldUpdater.addAndGet(cVar, 1L) & 4611686018427387904L) != 0) {
            while ((atomicLongFieldUpdater.get(cVar) & 4611686018427387904L) != 0) {
            }
        }
    }

    public final boolean A(Object obj, Object obj2) {
        if (!(obj instanceof b)) {
            if (!(obj instanceof i4.f)) {
                throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
            }
            i4.f fVar = (i4.f) obj;
            k kVar = e.f3453a;
            b3.r rVarR = fVar.r(obj2, null);
            if (rVarR == null) {
                return false;
            }
            fVar.w(rVarR);
            return true;
        }
        b bVar = (b) obj;
        i4.h hVar = bVar.f3440e;
        b4.l.b(hVar);
        bVar.f3440e = null;
        bVar.f3439d = obj2;
        Boolean bool = Boolean.TRUE;
        bVar.f3441f.getClass();
        k kVar2 = e.f3453a;
        b3.r rVarR2 = hVar.r(bool, null);
        if (rVarR2 == null) {
            return false;
        }
        hVar.w(rVarR2);
        return true;
    }

    public final Object C(k kVar, int i5, long j5, Object obj) {
        AtomicReferenceArray atomicReferenceArray = kVar.f3478f;
        Object objK = kVar.k(i5);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3442e;
        if (objK == null) {
            if (j5 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return e.f3466n;
                }
                if (kVar.j(objK, i5, obj)) {
                    k();
                    return e.f3465m;
                }
            }
        } else if (objK == e.f3456d && kVar.j(objK, i5, e.f3461i)) {
            k();
            Object obj2 = atomicReferenceArray.get(i5 * 2);
            kVar.m(i5, null);
            return obj2;
        }
        while (true) {
            Object objK2 = kVar.k(i5);
            if (objK2 == null || objK2 == e.f3457e) {
                if (j5 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (kVar.j(objK2, i5, e.f3460h)) {
                        k();
                        return e.f3467o;
                    }
                } else {
                    if (obj == null) {
                        return e.f3466n;
                    }
                    if (kVar.j(objK2, i5, obj)) {
                        k();
                        return e.f3465m;
                    }
                }
            } else {
                if (objK2 != e.f3456d) {
                    b3.r rVar = e.f3462j;
                    if (objK2 != rVar && objK2 != e.f3460h) {
                        if (objK2 == e.f3464l) {
                            k();
                            return e.f3467o;
                        }
                        if (objK2 != e.f3459g && kVar.j(objK2, i5, e.f3458f)) {
                            boolean z2 = objK2 instanceof s;
                            if (z2) {
                                objK2 = ((s) objK2).f3481a;
                            }
                            if (B(objK2)) {
                                kVar.n(i5, e.f3461i);
                                k();
                                Object obj3 = atomicReferenceArray.get(i5 * 2);
                                kVar.m(i5, null);
                                return obj3;
                            }
                            kVar.n(i5, rVar);
                            kVar.h();
                            if (z2) {
                                k();
                            }
                            return e.f3467o;
                        }
                    }
                    return e.f3467o;
                }
                if (kVar.j(objK2, i5, e.f3461i)) {
                    k();
                    Object obj4 = atomicReferenceArray.get(i5 * 2);
                    kVar.m(i5, null);
                    return obj4;
                }
            }
        }
    }

    public final int D(k kVar, int i5, Object obj, long j5, Object obj2, boolean z2) {
        while (true) {
            Object objK = kVar.k(i5);
            if (objK == null) {
                if (!f(j5) || z2) {
                    if (z2) {
                        if (kVar.j(null, i5, e.f3462j)) {
                            kVar.h();
                            return 4;
                        }
                    } else {
                        if (obj2 == null) {
                            return 3;
                        }
                        if (kVar.j(null, i5, obj2)) {
                            return 2;
                        }
                    }
                } else if (kVar.j(null, i5, e.f3456d)) {
                    break;
                }
            } else {
                if (objK != e.f3457e) {
                    b3.r rVar = e.f3463k;
                    if (objK == rVar) {
                        kVar.m(i5, null);
                        return 5;
                    }
                    if (objK == e.f3460h) {
                        kVar.m(i5, null);
                        return 5;
                    }
                    if (objK == e.f3464l) {
                        kVar.m(i5, null);
                        i();
                        return 4;
                    }
                    kVar.m(i5, null);
                    if (objK instanceof s) {
                        objK = ((s) objK).f3481a;
                    }
                    if (A(objK, obj)) {
                        kVar.n(i5, e.f3461i);
                        return 0;
                    }
                    if (kVar.f3478f.getAndSet((i5 * 2) + 1, rVar) != rVar) {
                        kVar.l(i5, true);
                    }
                    return 5;
                }
                if (kVar.j(objK, i5, e.f3456d)) {
                    break;
                }
            }
        }
        return 1;
    }

    public final void E(long j5) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        c cVar = this;
        if (cVar.w()) {
            return;
        }
        while (true) {
            atomicLongFieldUpdater = f3444g;
            if (atomicLongFieldUpdater.get(cVar) > j5) {
                break;
            } else {
                cVar = this;
            }
        }
        int i5 = e.f3455c;
        int i6 = 0;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f3445h;
            if (i6 < i5) {
                long j6 = atomicLongFieldUpdater.get(cVar);
                if (j6 == (4611686018427387903L & atomicLongFieldUpdater2.get(cVar)) && j6 == atomicLongFieldUpdater.get(cVar)) {
                    return;
                } else {
                    i6++;
                }
            } else {
                while (true) {
                    long j7 = atomicLongFieldUpdater2.get(cVar);
                    if (atomicLongFieldUpdater2.compareAndSet(cVar, j7, (j7 & 4611686018427387903L) + 4611686018427387904L)) {
                        break;
                    } else {
                        cVar = this;
                    }
                }
                while (true) {
                    long j8 = atomicLongFieldUpdater.get(cVar);
                    long j9 = atomicLongFieldUpdater2.get(cVar);
                    long j10 = j9 & 4611686018427387903L;
                    boolean z2 = (j9 & 4611686018427387904L) != 0;
                    if (j8 == j10 && j8 == atomicLongFieldUpdater.get(cVar)) {
                        break;
                    }
                    if (z2) {
                        cVar = this;
                    } else {
                        cVar = this;
                        atomicLongFieldUpdater2.compareAndSet(cVar, j9, 4611686018427387904L + j10);
                    }
                }
                while (true) {
                    long j11 = atomicLongFieldUpdater2.get(cVar);
                    if (atomicLongFieldUpdater2.compareAndSet(cVar, j11, j11 & 4611686018427387903L)) {
                        return;
                    } else {
                        cVar = this;
                    }
                }
            }
        }
    }

    @Override // k4.q
    public final void b(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        g(cancellationException, true);
    }

    @Override // k4.q
    public final Object d(v3.i iVar) throws Throwable {
        k kVarM;
        c cVar = this;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3447j;
        k kVar = (k) atomicReferenceFieldUpdater.get(cVar);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f3442e;
            if (cVar.u(atomicLongFieldUpdater.get(cVar), true)) {
                Throwable thQ = q();
                int i5 = n4.r.f4308a;
                throw thQ;
            }
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f3443f;
            long andIncrement = atomicLongFieldUpdater2.getAndIncrement(cVar);
            long j5 = e.f3454b;
            long j6 = andIncrement / j5;
            int i6 = (int) (andIncrement % j5);
            if (kVar.f4307c != j6) {
                k kVarM2 = cVar.m(j6, kVar);
                if (kVarM2 == null) {
                    continue;
                } else {
                    kVar = kVarM2;
                }
            }
            Object objC = cVar.C(kVar, i6, andIncrement, null);
            b3.r rVar = e.f3465m;
            if (objC == rVar) {
                throw new IllegalStateException("unexpected");
            }
            b3.r rVar2 = e.f3467o;
            if (objC != rVar2) {
                if (objC != e.f3466n) {
                    kVar.a();
                    return objC;
                }
                i4.h hVarI = y.i(p3.j.B(iVar));
                c cVar2 = this;
                try {
                    Object objC2 = cVar2.C(kVar, i6, andIncrement, hVarI);
                    if (objC2 == rVar) {
                        hVarI.a(kVar, i6);
                    } else if (objC2 == rVar2) {
                        if (andIncrement < cVar2.s()) {
                            kVar.a();
                        }
                        k kVar2 = (k) atomicReferenceFieldUpdater.get(cVar2);
                        while (!cVar2.u(atomicLongFieldUpdater.get(cVar2), true)) {
                            long andIncrement2 = atomicLongFieldUpdater2.getAndIncrement(cVar2);
                            long j7 = e.f3454b;
                            long j8 = andIncrement2 / j7;
                            int i7 = (int) (andIncrement2 % j7);
                            if (kVar2.f4307c != j8) {
                                kVarM = cVar2.m(j8, kVar2);
                                if (kVarM == null) {
                                }
                            } else {
                                kVarM = kVar2;
                            }
                            Object objC3 = cVar2.C(kVarM, i7, andIncrement2, hVarI);
                            if (objC3 == e.f3465m) {
                                hVarI.a(kVarM, i7);
                            } else if (objC3 == e.f3467o) {
                                if (andIncrement2 < s()) {
                                    kVarM.a();
                                }
                                cVar2 = this;
                                kVar2 = kVarM;
                            } else {
                                if (objC3 == e.f3466n) {
                                    throw new IllegalStateException("unexpected");
                                }
                                kVarM.a();
                                hVarI.A(objC3, null);
                            }
                        }
                        hVarI.resumeWith(p3.j.r(cVar2.q()));
                    } else {
                        kVar.a();
                        hVarI.A(objC2, null);
                    }
                    return hVarI.o();
                } catch (Throwable th) {
                    hVarI.z();
                    throw th;
                }
            }
            if (andIncrement < s()) {
                kVar.a();
            }
            cVar = this;
        }
    }

    public final boolean f(long j5) {
        return j5 < f3444g.get(this) || j5 < f3443f.get(this) + ((long) this.f3451d);
    }

    public final boolean g(Throwable th, boolean z2) {
        c cVar;
        boolean z3;
        long j5;
        long j6;
        long j7;
        Object obj;
        long j8;
        long j9;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3442e;
        if (!z2) {
            cVar = this;
            break;
        }
        do {
            j9 = atomicLongFieldUpdater.get(this);
            if (((int) (j9 >> 60)) != 0) {
                cVar = this;
                break;
            }
            k kVar = e.f3453a;
            cVar = this;
        } while (!atomicLongFieldUpdater.compareAndSet(cVar, j9, (j9 & 1152921504606846975L) + (((long) 1) << 60)));
        b3.r rVar = e.f3471s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3449l;
            if (atomicReferenceFieldUpdater.compareAndSet(this, rVar, th)) {
                z3 = true;
                break;
            }
            if (atomicReferenceFieldUpdater.get(this) != rVar) {
                z3 = false;
                break;
            }
        }
        if (z2) {
            do {
                j8 = atomicLongFieldUpdater.get(this);
            } while (!atomicLongFieldUpdater.compareAndSet(cVar, j8, (((long) 3) << 60) + (j8 & 1152921504606846975L)));
        } else {
            do {
                j5 = atomicLongFieldUpdater.get(this);
                int i5 = (int) (j5 >> 60);
                if (i5 == 0) {
                    j6 = j5 & 1152921504606846975L;
                    j7 = 2;
                } else {
                    if (i5 != 1) {
                        break;
                    }
                    j6 = j5 & 1152921504606846975L;
                    j7 = 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(cVar, j5, (j7 << 60) + j6));
        }
        i();
        if (z3) {
            loop3: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f3450m;
                obj = atomicReferenceFieldUpdater2.get(this);
                b3.r rVar2 = obj == null ? e.f3469q : e.f3470r;
                do {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, rVar2)) {
                        break loop3;
                    }
                } while (atomicReferenceFieldUpdater2.get(this) == obj);
            }
            if (obj != null) {
                z.c(1, obj);
                ((a4.c) obj).c(p());
                return z3;
            }
        }
        return z3;
    }

    public final k h(long j5) {
        Object objF;
        long j6;
        Object obj = f3448k.get(this);
        k kVar = (k) f3446i.get(this);
        if (kVar.f4307c > ((k) obj).f4307c) {
            obj = kVar;
        }
        k kVar2 = (k) f3447j.get(this);
        if (kVar2.f4307c > ((k) obj).f4307c) {
            obj = kVar2;
        }
        n4.b bVar = (n4.b) obj;
        loop0: while (true) {
            bVar.getClass();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n4.b.f4274a;
            Object obj2 = atomicReferenceFieldUpdater.get(bVar);
            b3.r rVar = n4.a.f4267a;
            objF = null;
            if (obj2 == rVar) {
                break;
            }
            n4.b bVar2 = (n4.b) obj2;
            if (bVar2 == null) {
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(bVar, null, rVar)) {
                        break loop0;
                    }
                } while (atomicReferenceFieldUpdater.get(bVar) == null);
            } else {
                bVar = bVar2;
            }
        }
        k kVar3 = (k) bVar;
        if (v()) {
            k kVar4 = kVar3;
            loop2: while (true) {
                int i5 = e.f3454b - 1;
                while (true) {
                    if (-1 < i5) {
                        j6 = (kVar4.f4307c * ((long) e.f3454b)) + ((long) i5);
                        if (j6 >= f3443f.get(this)) {
                            while (true) {
                                Object objK = kVar4.k(i5);
                                if (objK != null && objK != e.f3457e) {
                                    if (objK != e.f3456d) {
                                        break;
                                    }
                                    break loop2;
                                }
                                if (kVar4.j(objK, i5, e.f3464l)) {
                                    kVar4.h();
                                    break;
                                }
                            }
                            i5--;
                        }
                    } else {
                        kVar4 = (k) ((n4.b) n4.b.f4275b.get(kVar4));
                        if (kVar4 == null) {
                        }
                    }
                    j6 = -1;
                    break;
                }
            }
            if (j6 != -1) {
                j(j6);
            }
        }
        loop5: for (k kVar5 = kVar3; kVar5 != null; kVar5 = (k) ((n4.b) n4.b.f4275b.get(kVar5))) {
            for (int i6 = e.f3454b - 1; -1 < i6; i6--) {
                if ((kVar5.f4307c * ((long) e.f3454b)) + ((long) i6) < j5) {
                    break loop5;
                }
                while (true) {
                    Object objK2 = kVar5.k(i6);
                    if (objK2 != null && objK2 != e.f3457e) {
                        if (!(objK2 instanceof s)) {
                            if (!(objK2 instanceof p1)) {
                                break;
                            }
                            if (kVar5.j(objK2, i6, e.f3464l)) {
                                objF = n4.a.f(objF, objK2);
                                kVar5.l(i6, true);
                                break;
                            }
                        } else {
                            if (kVar5.j(objK2, i6, e.f3464l)) {
                                objF = n4.a.f(objF, ((s) objK2).f3481a);
                                kVar5.l(i6, true);
                                break;
                            }
                        }
                    } else {
                        if (kVar5.j(objK2, i6, e.f3464l)) {
                            kVar5.h();
                            break;
                        }
                    }
                }
            }
        }
        if (objF != null) {
            if (!(objF instanceof ArrayList)) {
                z((p1) objF, true);
                return kVar3;
            }
            ArrayList arrayList = (ArrayList) objF;
            for (int size = arrayList.size() - 1; -1 < size; size--) {
                z((p1) arrayList.get(size), true);
            }
        }
        return kVar3;
    }

    public final void i() {
        u(f3442e.get(this), false);
    }

    @Override // k4.q
    public final b iterator() {
        return new b(this);
    }

    public final void j(long j5) {
        k kVar = (k) f3447j.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f3443f;
            long j6 = atomicLongFieldUpdater.get(this);
            if (j5 < Math.max(((long) this.f3451d) + j6, f3444g.get(this))) {
                return;
            }
            if (atomicLongFieldUpdater.compareAndSet(this, j6, 1 + j6)) {
                long j7 = e.f3454b;
                long j8 = j6 / j7;
                int i5 = (int) (j6 % j7);
                if (kVar.f4307c != j8) {
                    k kVarM = m(j8, kVar);
                    if (kVarM != null) {
                        kVar = kVarM;
                    }
                }
                k kVar2 = kVar;
                if (C(kVar2, i5, j6, null) != e.f3467o || j6 < s()) {
                    kVar2.a();
                }
                kVar = kVar2;
            }
        }
    }

    public final void k() {
        Object objB;
        if (w()) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3448k;
        k kVar = (k) atomicReferenceFieldUpdater.get(this);
        while (true) {
            long andIncrement = f3444g.getAndIncrement(this);
            long j5 = andIncrement / ((long) e.f3454b);
            if (s() <= andIncrement) {
                if (kVar.f4307c < j5 && kVar.b() != null) {
                    x(j5, kVar);
                }
                t(this);
                return;
            }
            if (kVar.f4307c != j5) {
                d dVar = d.f3452k;
                while (true) {
                    objB = n4.a.b(kVar, j5, dVar);
                    if (!n4.a.e(objB)) {
                        n4.q qVarC = n4.a.c(objB);
                        while (true) {
                            n4.q qVar = (n4.q) atomicReferenceFieldUpdater.get(this);
                            if (qVar.f4307c >= qVarC.f4307c) {
                                break;
                            }
                            if (!qVarC.i()) {
                                break;
                            }
                            do {
                                if (atomicReferenceFieldUpdater.compareAndSet(this, qVar, qVarC)) {
                                    if (!qVar.e()) {
                                        break;
                                    }
                                    qVar.d();
                                    break;
                                }
                            } while (atomicReferenceFieldUpdater.get(this) == qVar);
                            if (qVarC.e()) {
                                qVarC.d();
                            }
                        }
                    } else {
                        break;
                    }
                }
                k kVar2 = null;
                if (n4.a.e(objB)) {
                    i();
                    x(j5, kVar);
                    t(this);
                } else {
                    k kVar3 = (k) n4.a.c(objB);
                    long j6 = kVar3.f4307c;
                    if (j6 > j5) {
                        long j7 = j6 * ((long) e.f3454b);
                        if (f3444g.compareAndSet(this, 1 + andIncrement, j7)) {
                            AtomicLongFieldUpdater atomicLongFieldUpdater = f3445h;
                            if ((atomicLongFieldUpdater.addAndGet(this, j7 - andIncrement) & 4611686018427387904L) != 0) {
                                while ((atomicLongFieldUpdater.get(this) & 4611686018427387904L) != 0) {
                                }
                            }
                        } else {
                            t(this);
                        }
                    } else {
                        kVar2 = kVar3;
                    }
                }
                if (kVar2 == null) {
                    continue;
                } else {
                    kVar = kVar2;
                }
            }
            int i5 = (int) (andIncrement % ((long) e.f3454b));
            Object objK = kVar.k(i5);
            boolean z2 = objK instanceof p1;
            AtomicLongFieldUpdater atomicLongFieldUpdater2 = f3443f;
            if (!z2 || andIncrement < atomicLongFieldUpdater2.get(this) || !kVar.j(objK, i5, e.f3459g)) {
                while (true) {
                    Object objK2 = kVar.k(i5);
                    if (objK2 instanceof p1) {
                        if (andIncrement < atomicLongFieldUpdater2.get(this)) {
                            if (kVar.j(objK2, i5, new s((p1) objK2))) {
                                t(this);
                                return;
                            }
                        } else if (kVar.j(objK2, i5, e.f3459g)) {
                            if (!B(objK2)) {
                                kVar.n(i5, e.f3462j);
                                kVar.h();
                                break;
                            } else {
                                kVar.n(i5, e.f3456d);
                                t(this);
                                return;
                            }
                        }
                    } else {
                        if (objK2 == e.f3462j) {
                            break;
                        }
                        if (objK2 == null) {
                            if (kVar.j(objK2, i5, e.f3457e)) {
                                t(this);
                                return;
                            }
                        } else if (objK2 == e.f3456d || objK2 == e.f3460h || objK2 == e.f3461i || objK2 == e.f3463k || objK2 == e.f3464l) {
                            t(this);
                            return;
                        } else if (objK2 != e.f3458f) {
                            throw new IllegalStateException(("Unexpected cell state: " + objK2).toString());
                        }
                    }
                }
                t(this);
            } else if (B(objK)) {
                kVar.n(i5, e.f3456d);
                t(this);
                return;
            } else {
                kVar.n(i5, e.f3462j);
                kVar.h();
                t(this);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:93:0x0160  */
    /* JADX WARN: Code duplicated, block: B:95:0x0163 A[RETURN] */
    @Override // k4.r
    public Object l(Object obj, t3.c cVar) throws Throwable {
        p3.n nVar;
        Object objO;
        Object obj2;
        c cVar2;
        k kVar;
        int i5;
        c cVar3 = this;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3446i;
        k kVar2 = (k) atomicReferenceFieldUpdater.get(cVar3);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f3442e;
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(cVar3);
            long j5 = andIncrement & 1152921504606846975L;
            boolean zU = cVar3.u(andIncrement, false);
            int i6 = e.f3454b;
            long j6 = i6;
            long j7 = j5 / j6;
            int i7 = (int) (j5 % j6);
            long j8 = kVar2.f4307c;
            u3.a aVar = u3.a.f5838d;
            nVar = p3.n.f5026a;
            if (j8 != j7) {
                k kVarA = a(cVar3, j7, kVar2);
                if (kVarA != null) {
                    kVar2 = kVarA;
                } else if (zU) {
                    Object objY = y(obj, cVar);
                    if (objY != aVar) {
                        break;
                    }
                    return objY;
                }
            }
            int iE = e(cVar3, kVar2, i7, obj, j5, null, zU);
            if (iE == 0) {
                kVar2.a();
                return nVar;
            }
            if (iE == 1) {
                break;
            }
            if (iE != 2) {
                AtomicLongFieldUpdater atomicLongFieldUpdater2 = f3443f;
                if (iE == 3) {
                    i4.h hVarI = y.i(p3.j.B(cVar));
                    Object obj3 = obj;
                    try {
                        int iE2 = e(cVar3, kVar2, i7, obj3, j5, hVarI, false);
                        try {
                            if (iE2 != 0) {
                                if (iE2 == 1) {
                                    hVarI.resumeWith(nVar);
                                } else if (iE2 != 2) {
                                    if (iE2 != 4) {
                                        String str = "unexpected";
                                        if (iE2 != 5) {
                                            throw new IllegalStateException("unexpected");
                                        }
                                        kVar2.a();
                                        k kVar3 = (k) atomicReferenceFieldUpdater.get(cVar3);
                                        while (true) {
                                            long andIncrement2 = atomicLongFieldUpdater.getAndIncrement(cVar3);
                                            long j9 = andIncrement2 & 1152921504606846975L;
                                            boolean zU2 = cVar3.u(andIncrement2, false);
                                            int i8 = e.f3454b;
                                            long j10 = i8;
                                            str = str;
                                            long j11 = j9 / j10;
                                            int i9 = (int) (j9 % j10);
                                            if (kVar3.f4307c != j11) {
                                                k kVarA2 = a(cVar3, j11, kVar3);
                                                if (kVarA2 != null) {
                                                    i5 = i8;
                                                    kVar = kVarA2;
                                                } else if (zU2) {
                                                    c(cVar3, obj3, hVarI);
                                                    break;
                                                }
                                            } else {
                                                kVar = kVar3;
                                                i5 = i8;
                                            }
                                            int iE3 = e(cVar3, kVar, i9, obj3, j9, hVarI, zU2);
                                            Object obj4 = obj3;
                                            cVar2 = cVar3;
                                            k kVar4 = kVar;
                                            obj2 = obj4;
                                            if (iE3 == 0) {
                                                kVar4.a();
                                            } else if (iE3 != 1) {
                                                if (iE3 == 2) {
                                                    if (!zU2) {
                                                        hVarI.a(kVar4, i9 + i5);
                                                        break;
                                                    }
                                                    kVar4.h();
                                                } else {
                                                    if (iE3 == 3) {
                                                        throw new IllegalStateException(str);
                                                    }
                                                    if (iE3 != 4) {
                                                        if (iE3 == 5) {
                                                            kVar4.a();
                                                        }
                                                        kVar3 = kVar4;
                                                        cVar3 = cVar2;
                                                        obj3 = obj2;
                                                    } else if (j9 < atomicLongFieldUpdater2.get(cVar2)) {
                                                        kVar4.a();
                                                    }
                                                }
                                            }
                                        }
                                        hVarI.z();
                                        throw th;
                                    }
                                    obj2 = obj3;
                                    cVar2 = cVar3;
                                    if (j5 < atomicLongFieldUpdater2.get(cVar2)) {
                                        kVar2.a();
                                    }
                                    c(cVar2, obj2, hVarI);
                                    break;
                                } else {
                                    hVarI.a(kVar2, i7 + i6);
                                }
                                objO = hVarI.o();
                                if (objO != aVar) {
                                    objO = nVar;
                                }
                                if (objO == aVar) {
                                    return objO;
                                }
                            } else {
                                kVar2.a();
                            }
                            hVarI.resumeWith(nVar);
                            objO = hVarI.o();
                            if (objO != aVar) {
                                objO = nVar;
                            }
                            if (objO == aVar) {
                                return objO;
                            }
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } else {
                    if (iE == 4) {
                        if (j5 < atomicLongFieldUpdater2.get(cVar3)) {
                            kVar2.a();
                        }
                        Object objY2 = y(obj, cVar);
                        if (objY2 != aVar) {
                            break;
                        }
                        return objY2;
                    }
                    if (iE == 5) {
                        kVar2.a();
                    }
                }
            } else if (zU) {
                kVar2.h();
                Object objY3 = y(obj, cVar);
                if (objY3 == aVar) {
                    return objY3;
                }
            }
            return nVar;
        }
        return nVar;
    }

    public final k m(long j5, k kVar) {
        Object objB;
        long j6;
        k kVar2 = e.f3453a;
        d dVar = d.f3452k;
        loop0: while (true) {
            objB = n4.a.b(kVar, j5, dVar);
            if (!n4.a.e(objB)) {
                n4.q qVarC = n4.a.c(objB);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3447j;
                    n4.q qVar = (n4.q) atomicReferenceFieldUpdater.get(this);
                    if (qVar.f4307c >= qVarC.f4307c) {
                        break loop0;
                    }
                    if (!qVarC.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, qVar, qVarC)) {
                            if (!qVar.e()) {
                                break loop0;
                            }
                            qVar.d();
                            break loop0;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == qVar);
                    if (qVarC.e()) {
                        qVarC.d();
                    }
                }
            } else {
                break;
            }
        }
        if (n4.a.e(objB)) {
            i();
            if (kVar.f4307c * ((long) e.f3454b) < s()) {
                kVar.a();
                return null;
            }
        } else {
            k kVar3 = (k) n4.a.c(objB);
            long j7 = kVar3.f4307c;
            if (!w() && j5 <= f3444g.get(this) / ((long) e.f3454b)) {
                loop3: while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f3448k;
                    n4.q qVar2 = (n4.q) atomicReferenceFieldUpdater2.get(this);
                    if (qVar2.f4307c >= j7 || !kVar3.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater2.compareAndSet(this, qVar2, kVar3)) {
                            if (!qVar2.e()) {
                                break loop3;
                            }
                            qVar2.d();
                            break loop3;
                        }
                    } while (atomicReferenceFieldUpdater2.get(this) == qVar2);
                    if (kVar3.e()) {
                        kVar3.d();
                    }
                }
            }
            if (j7 <= j5) {
                return kVar3;
            }
            long j8 = j7 * ((long) e.f3454b);
            do {
                j6 = f3443f.get(this);
                if (j6 >= j8) {
                    break;
                }
            } while (!f3443f.compareAndSet(this, j6, j8));
            if (j7 * ((long) e.f3454b) < s()) {
                kVar3.a();
            }
        }
        return null;
    }

    @Override // k4.q
    public final Object n() {
        k kVar;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3443f;
        long j5 = atomicLongFieldUpdater.get(this);
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = f3442e;
        long j6 = atomicLongFieldUpdater2.get(this);
        if (u(j6, true)) {
            return new h(p());
        }
        long j7 = j6 & 1152921504606846975L;
        i iVar = j.f3476a;
        if (j5 >= j7) {
            return iVar;
        }
        Object obj = e.f3463k;
        k kVar2 = (k) f3447j.get(this);
        while (!u(atomicLongFieldUpdater2.get(this), true)) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j8 = e.f3454b;
            long j9 = andIncrement / j8;
            int i5 = (int) (andIncrement % j8);
            if (kVar2.f4307c != j9) {
                k kVarM = m(j9, kVar2);
                if (kVarM == null) {
                    continue;
                } else {
                    kVar = kVarM;
                }
            } else {
                kVar = kVar2;
            }
            Object objC = C(kVar, i5, andIncrement, obj);
            k kVar3 = kVar;
            if (objC == e.f3465m) {
                p1 p1Var = obj instanceof p1 ? (p1) obj : null;
                if (p1Var != null) {
                    p1Var.a(kVar3, i5);
                }
                E(andIncrement);
                kVar3.h();
                return iVar;
            }
            if (objC != e.f3467o) {
                if (objC == e.f3466n) {
                    throw new IllegalStateException("unexpected");
                }
                kVar3.a();
                return objC;
            }
            if (andIncrement < s()) {
                kVar3.a();
            }
            kVar2 = kVar3;
        }
        return new h(p());
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0068  */
    /* JADX WARN: Code duplicated, block: B:24:0x006b  */
    /* JADX WARN: Code duplicated, block: B:26:0x006e  */
    /* JADX WARN: Code duplicated, block: B:28:0x0071  */
    /* JADX WARN: Code duplicated, block: B:30:0x0074  */
    /* JADX WARN: Code duplicated, block: B:33:0x0078  */
    /* JADX WARN: Code duplicated, block: B:37:0x0087  */
    /* JADX WARN: Code duplicated, block: B:43:0x009e  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:47:0x00af  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:50:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:57:0x00be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x009c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:60:0x0094 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:61:0x007d A[SYNTHETIC] */
    @Override // k4.r
    public Object o(Object obj) {
        int iE;
        p3.n nVar;
        p1 p1Var;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3442e;
        long j5 = atomicLongFieldUpdater.get(this);
        boolean z2 = false;
        long j6 = 1152921504606846975L;
        boolean z3 = u(j5, false) ? false : !f(j5 & 1152921504606846975L);
        i iVar = j.f3476a;
        if (z3) {
            return iVar;
        }
        Object obj2 = e.f3462j;
        k kVar = (k) f3446i.get(this);
        while (true) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j7 = andIncrement & j6;
            boolean zU = u(andIncrement, z2);
            int i5 = e.f3454b;
            long j8 = i5;
            long j9 = j7 / j8;
            int i6 = (int) (j7 % j8);
            if (kVar.f4307c == j9) {
                iE = e(this, kVar, i6, obj, j7, obj2, zU);
                nVar = p3.n.f5026a;
                if (iE != 0) {
                    kVar.a();
                    return nVar;
                }
                if (iE != 1) {
                    return nVar;
                }
                if (iE != 2) {
                    if (zU) {
                        kVar.h();
                        return new h(r());
                    }
                    if (obj2 instanceof p1) {
                        p1Var = (p1) obj2;
                    } else {
                        p1Var = null;
                    }
                    if (p1Var != null) {
                        p1Var.a(kVar, i6 + i5);
                    }
                    kVar.h();
                    return iVar;
                }
                if (iE != 3) {
                    throw new IllegalStateException("unexpected");
                }
                if (iE != 4) {
                    if (j7 < f3443f.get(this)) {
                        kVar.a();
                    }
                    return new h(r());
                }
                if (iE == 5) {
                    kVar.a();
                }
                z2 = false;
            } else {
                k kVarA = a(this, j9, kVar);
                if (kVarA != null) {
                    kVar = kVarA;
                    iE = e(this, kVar, i6, obj, j7, obj2, zU);
                    nVar = p3.n.f5026a;
                    if (iE != 0) {
                        kVar.a();
                        return nVar;
                    }
                    if (iE != 1) {
                        return nVar;
                    }
                    if (iE != 2) {
                        if (zU) {
                            kVar.h();
                            return new h(r());
                        }
                        if (obj2 instanceof p1) {
                            p1Var = (p1) obj2;
                        } else {
                            p1Var = null;
                        }
                        if (p1Var != null) {
                            p1Var.a(kVar, i6 + i5);
                        }
                        kVar.h();
                        return iVar;
                    }
                    if (iE != 3) {
                        throw new IllegalStateException("unexpected");
                    }
                    if (iE != 4) {
                        if (j7 < f3443f.get(this)) {
                            kVar.a();
                        }
                        return new h(r());
                    }
                    if (iE == 5) {
                        kVar.a();
                    }
                    z2 = false;
                } else {
                    if (zU) {
                        return new h(r());
                    }
                    z2 = false;
                }
            }
            j6 = 1152921504606846975L;
        }
    }

    public final Throwable p() {
        return (Throwable) f3449l.get(this);
    }

    public final Throwable q() {
        Throwable thP = p();
        return thP == null ? new l("Channel was closed") : thP;
    }

    public final Throwable r() {
        Throwable thP = p();
        return thP == null ? new m("Channel was closed") : thP;
    }

    public final long s() {
        return f3442e.get(this) & 1152921504606846975L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        int i5 = (int) (f3442e.get(this) >> 60);
        if (i5 == 2) {
            sb.append("closed,");
        } else if (i5 == 3) {
            sb.append("cancelled,");
        }
        sb.append("capacity=" + this.f3451d + ',');
        sb.append("data=[");
        int i6 = 0;
        boolean z2 = true;
        List listH = p3.j.H(f3447j.get(this), f3446i.get(this), f3448k.get(this));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listH) {
            if (((k) obj) != e.f3453a) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Object next = it.next();
        if (it.hasNext()) {
            long j5 = ((k) next).f4307c;
            do {
                Object next2 = it.next();
                long j6 = ((k) next2).f4307c;
                if (j5 > j6) {
                    next = next2;
                    j5 = j6;
                }
            } while (it.hasNext());
        }
        k kVar = (k) next;
        long j7 = f3443f.get(this);
        long jS = s();
        loop2: while (true) {
            int i7 = e.f3454b;
            int i8 = i6;
            while (i8 < i7) {
                long j8 = (kVar.f4307c * ((long) e.f3454b)) + ((long) i8);
                if (j8 >= jS && j8 >= j7) {
                    break loop2;
                }
                Object objK = kVar.k(i8);
                boolean z3 = z2;
                Object obj2 = kVar.f3478f.get(i8 * 2);
                if (objK instanceof i4.f) {
                    string = (j8 >= j7 || j8 < jS) ? (j8 >= jS || j8 < j7) ? "cont" : "send" : "receive";
                } else if (objK instanceof s) {
                    string = "EB(" + objK + ')';
                } else if (b4.l.a(objK, e.f3458f) || b4.l.a(objK, e.f3459g)) {
                    string = "resuming_sender";
                } else {
                    if (objK != null && !objK.equals(e.f3457e) && !objK.equals(e.f3461i) && !objK.equals(e.f3460h) && !objK.equals(e.f3463k) && !objK.equals(e.f3462j) && !objK.equals(e.f3464l)) {
                        string = objK.toString();
                    }
                    i8++;
                    z2 = z3;
                }
                if (obj2 != null) {
                    sb.append("(" + string + ',' + obj2 + "),");
                } else {
                    sb.append(string + ',');
                }
                i8++;
                z2 = z3;
            }
            boolean z4 = z2;
            kVar = (k) kVar.b();
            if (kVar == null) {
                break;
            }
            z2 = z4;
            i6 = 0;
        }
        if (sb.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            b4.l.d("deleteCharAt(...)", sb.deleteCharAt(sb.length() - 1));
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean u(long j5, boolean z2) {
        int i5 = (int) (j5 >> 60);
        if (i5 != 0 && i5 != 1) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f3443f;
            if (i5 == 2) {
                h(1152921504606846975L & j5);
                if (z2) {
                    while (true) {
                        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3447j;
                        k kVarM = (k) atomicReferenceFieldUpdater.get(this);
                        long j6 = atomicLongFieldUpdater.get(this);
                        if (s() <= j6) {
                            break;
                        }
                        long j7 = e.f3454b;
                        long j8 = j6 / j7;
                        if (kVarM.f4307c != j8 && (kVarM = m(j8, kVarM)) == null) {
                            if (((k) atomicReferenceFieldUpdater.get(this)).f4307c < j8) {
                                break;
                            }
                        } else {
                            kVarM.a();
                            int i6 = (int) (j6 % j7);
                            while (true) {
                                Object objK = kVarM.k(i6);
                                if (objK != null && objK != e.f3457e) {
                                    if (objK != e.f3456d && (objK == e.f3462j || objK == e.f3464l || objK == e.f3461i || objK == e.f3460h || (objK != e.f3459g && (objK == e.f3458f || j6 != atomicLongFieldUpdater.get(this))))) {
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                        break;
                                    }
                                } else if (kVarM.j(objK, i6, e.f3460h)) {
                                    k();
                                    break;
                                }
                            }
                            f3443f.compareAndSet(this, j6, j6 + 1);
                        }
                    }
                }
            } else {
                if (i5 != 3) {
                    throw new IllegalStateException(("unexpected close status: " + i5).toString());
                }
                k kVarH = h(1152921504606846975L & j5);
                Object objF = null;
                loop0: do {
                    for (int i7 = e.f3454b - 1; -1 < i7; i7--) {
                        long j9 = (kVarH.f4307c * ((long) e.f3454b)) + ((long) i7);
                        while (true) {
                            Object objK2 = kVarH.k(i7);
                            if (objK2 == e.f3461i) {
                                break loop0;
                            }
                            if (objK2 != e.f3456d) {
                                if (objK2 != e.f3457e && objK2 != null) {
                                    if (!(objK2 instanceof p1) && !(objK2 instanceof s)) {
                                        b3.r rVar = e.f3459g;
                                        if (objK2 == rVar || objK2 == e.f3458f) {
                                            break loop0;
                                        }
                                        if (objK2 != rVar) {
                                            break;
                                        }
                                    } else {
                                        if (j9 < atomicLongFieldUpdater.get(this)) {
                                            break loop0;
                                        }
                                        p1 p1Var = objK2 instanceof s ? ((s) objK2).f3481a : (p1) objK2;
                                        if (kVarH.j(objK2, i7, e.f3464l)) {
                                            objF = n4.a.f(objF, p1Var);
                                            kVarH.m(i7, null);
                                            kVarH.h();
                                            break;
                                        }
                                    }
                                } else {
                                    if (kVarH.j(objK2, i7, e.f3464l)) {
                                        kVarH.h();
                                        break;
                                    }
                                }
                            } else {
                                if (j9 < atomicLongFieldUpdater.get(this)) {
                                    break loop0;
                                }
                                if (kVarH.j(objK2, i7, e.f3464l)) {
                                    kVarH.m(i7, null);
                                    kVarH.h();
                                    break;
                                }
                            }
                        }
                    }
                    kVarH = (k) ((n4.b) n4.b.f4275b.get(kVarH));
                } while (kVarH != null);
                if (objF != null) {
                    if (objF instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) objF;
                        for (int size = arrayList.size() - 1; -1 < size; size--) {
                            z((p1) arrayList.get(size), false);
                        }
                    } else {
                        z((p1) objF, false);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean v() {
        return false;
    }

    public final boolean w() {
        long j5 = f3444g.get(this);
        return j5 == 0 || j5 == Long.MAX_VALUE;
    }

    public final void x(long j5, k kVar) {
        k kVar2;
        k kVar3;
        while (kVar.f4307c < j5 && (kVar3 = (k) kVar.b()) != null) {
            kVar = kVar3;
        }
        while (true) {
            if (!kVar.c() || (kVar2 = (k) kVar.b()) == null) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3448k;
                    n4.q qVar = (n4.q) atomicReferenceFieldUpdater.get(this);
                    if (qVar.f4307c >= kVar.f4307c) {
                        return;
                    }
                    if (!kVar.i()) {
                        break;
                    }
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(this, qVar, kVar)) {
                            if (qVar.e()) {
                                qVar.d();
                                return;
                            }
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == qVar);
                    if (kVar.e()) {
                        kVar.d();
                    }
                }
            } else {
                kVar = kVar2;
            }
        }
    }

    public final Object y(Object obj, t3.c cVar) {
        i4.h hVar = new i4.h(1, p3.j.B(cVar));
        hVar.p();
        hVar.resumeWith(p3.j.r(r()));
        Object objO = hVar.o();
        return objO == u3.a.f5838d ? objO : p3.n.f5026a;
    }

    public final void z(p1 p1Var, boolean z2) {
        if (p1Var instanceof i4.f) {
            ((t3.c) p1Var).resumeWith(p3.j.r(z2 ? q() : r()));
            return;
        }
        if (!(p1Var instanceof b)) {
            throw new IllegalStateException(("Unexpected waiter: " + p1Var).toString());
        }
        b bVar = (b) p1Var;
        i4.h hVar = bVar.f3440e;
        b4.l.b(hVar);
        bVar.f3440e = null;
        bVar.f3439d = e.f3464l;
        Throwable thP = bVar.f3441f.p();
        if (thP == null) {
            hVar.resumeWith(Boolean.FALSE);
        } else {
            hVar.resumeWith(p3.j.r(thP));
        }
    }
}
