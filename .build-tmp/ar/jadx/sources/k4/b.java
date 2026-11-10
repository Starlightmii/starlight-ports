package k4;

import i4.p1;
import i4.y;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b implements p1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f3439d = e.f3468p;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i4.h f3440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c f3441f;

    public b(c cVar) {
        this.f3441f = cVar;
    }

    @Override // i4.p1
    public final void a(n4.q qVar, int i5) {
        i4.h hVar = this.f3440e;
        if (hVar != null) {
            hVar.a(qVar, i5);
        }
    }

    public final Object b(v3.c cVar) throws Throwable {
        k kVarM;
        Object obj = this.f3439d;
        boolean z2 = true;
        if (obj == e.f3468p || obj == e.f3464l) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c.f3447j;
            c cVar2 = this.f3441f;
            k kVar = (k) atomicReferenceFieldUpdater.get(cVar2);
            while (true) {
                cVar2.getClass();
                if (cVar2.u(c.f3442e.get(cVar2), true)) {
                    this.f3439d = e.f3464l;
                    Throwable thP = cVar2.p();
                    if (thP == null) {
                        z2 = false;
                        break;
                    }
                    int i5 = n4.r.f4308a;
                    throw thP;
                }
                long andIncrement = c.f3443f.getAndIncrement(cVar2);
                long j5 = e.f3454b;
                long j6 = andIncrement / j5;
                int i6 = (int) (andIncrement % j5);
                if (kVar.f4307c != j6) {
                    kVarM = cVar2.m(j6, kVar);
                    if (kVarM == null) {
                        continue;
                    }
                } else {
                    kVarM = kVar;
                }
                Object objC = cVar2.C(kVarM, i6, andIncrement, null);
                b3.r rVar = e.f3465m;
                if (objC == rVar) {
                    throw new IllegalStateException("unreachable");
                }
                b3.r rVar2 = e.f3467o;
                if (objC != rVar2) {
                    if (objC != e.f3466n) {
                        kVarM.a();
                        this.f3439d = objC;
                        break;
                    }
                    c cVar3 = this.f3441f;
                    i4.h hVarI = y.i(p3.j.B(cVar));
                    try {
                        this.f3440e = hVarI;
                        Object objC2 = cVar3.C(kVarM, i6, andIncrement, this);
                        if (objC2 == rVar) {
                            a(kVarM, i6);
                        } else {
                            if (objC2 == rVar2) {
                                if (andIncrement < cVar3.s()) {
                                    kVarM.a();
                                }
                                k kVar2 = (k) c.f3447j.get(cVar3);
                                while (true) {
                                    if (cVar3.u(c.f3442e.get(cVar3), true)) {
                                        i4.h hVar = this.f3440e;
                                        b4.l.b(hVar);
                                        this.f3440e = null;
                                        this.f3439d = e.f3464l;
                                        Throwable thP2 = cVar2.p();
                                        if (thP2 == null) {
                                            hVar.resumeWith(Boolean.FALSE);
                                        } else {
                                            hVar.resumeWith(p3.j.r(thP2));
                                        }
                                    } else {
                                        long andIncrement2 = c.f3443f.getAndIncrement(cVar3);
                                        long j7 = e.f3454b;
                                        long j8 = andIncrement2 / j7;
                                        int i7 = (int) (andIncrement2 % j7);
                                        if (kVar2.f4307c != j8) {
                                            k kVarM2 = cVar3.m(j8, kVar2);
                                            if (kVarM2 != null) {
                                                kVar2 = kVarM2;
                                            }
                                        }
                                        Object objC3 = cVar3.C(kVar2, i7, andIncrement2, this);
                                        if (objC3 == e.f3465m) {
                                            a(kVar2, i7);
                                        } else {
                                            if (objC3 != e.f3467o) {
                                                if (objC3 == e.f3466n) {
                                                    throw new IllegalStateException("unexpected");
                                                }
                                                kVar2.a();
                                                this.f3439d = objC3;
                                                this.f3440e = null;
                                                break;
                                            }
                                            if (andIncrement2 < cVar3.s()) {
                                                kVar2.a();
                                            }
                                        }
                                    }
                                }
                            } else {
                                kVarM.a();
                                this.f3439d = objC2;
                                this.f3440e = null;
                            }
                            hVarI.A(Boolean.TRUE, null);
                        }
                        return hVarI.o();
                    } catch (Throwable th) {
                        hVarI.z();
                        throw th;
                    }
                }
                if (andIncrement < cVar2.s()) {
                    kVarM.a();
                }
                kVar = kVarM;
            }
        }
        return Boolean.valueOf(z2);
    }

    public final Object c() throws Throwable {
        Object obj = this.f3439d;
        b3.r rVar = e.f3468p;
        if (obj == rVar) {
            throw new IllegalStateException("`hasNext()` has not been invoked");
        }
        this.f3439d = rVar;
        if (obj != e.f3464l) {
            return obj;
        }
        Throwable thQ = this.f3441f.q();
        int i5 = n4.r.f4308a;
        throw thQ;
    }
}
