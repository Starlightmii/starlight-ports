package k4;

import i4.p1;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends n4.q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f3477e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f3478f;

    public k(long j5, k kVar, c cVar, int i5) {
        super(j5, kVar, i5);
        this.f3477e = cVar;
        this.f3478f = new AtomicReferenceArray(e.f3454b * 2);
    }

    @Override // n4.q
    public final int f() {
        return e.f3454b;
    }

    @Override // n4.q
    public final void g(int i5, t3.h hVar) {
        c cVar;
        int i6 = e.f3454b;
        boolean z2 = i5 >= i6;
        if (z2) {
            i5 -= i6;
        }
        this.f3478f.get(i5 * 2);
        while (true) {
            Object objK = k(i5);
            boolean z3 = objK instanceof p1;
            cVar = this.f3477e;
            if (z3 || (objK instanceof s)) {
                if (j(objK, i5, z2 ? e.f3462j : e.f3463k)) {
                    m(i5, null);
                    l(i5, !z2);
                    if (z2) {
                        b4.l.b(cVar);
                        return;
                    }
                    return;
                }
            } else {
                if (objK == e.f3462j || objK == e.f3463k) {
                    break;
                }
                if (objK != e.f3459g && objK != e.f3458f) {
                    if (objK == e.f3461i || objK == e.f3456d || objK == e.f3464l) {
                        return;
                    }
                    throw new IllegalStateException(("unexpected state: " + objK).toString());
                }
            }
        }
        m(i5, null);
        if (z2) {
            b4.l.b(cVar);
        }
    }

    public final boolean j(Object obj, int i5, Object obj2) {
        AtomicReferenceArray atomicReferenceArray;
        int i6 = (i5 * 2) + 1;
        do {
            atomicReferenceArray = this.f3478f;
            if (atomicReferenceArray.compareAndSet(i6, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceArray.get(i6) == obj);
        return false;
    }

    public final Object k(int i5) {
        return this.f3478f.get((i5 * 2) + 1);
    }

    public final void l(int i5, boolean z2) {
        if (z2) {
            c cVar = this.f3477e;
            b4.l.b(cVar);
            cVar.E((this.f4307c * ((long) e.f3454b)) + ((long) i5));
        }
        h();
    }

    public final void m(int i5, Object obj) {
        this.f3478f.set(i5 * 2, obj);
    }

    public final void n(int i5, Object obj) {
        this.f3478f.set((i5 * 2) + 1, obj);
    }
}
