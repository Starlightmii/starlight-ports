package k4;

import b4.x;
import i4.p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends c {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a f3479n;

    public n(int i5, a aVar) {
        super(i5);
        this.f3479n = aVar;
        if (aVar == a.f3435d) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + x.a(c.class).b() + " instead").toString());
        }
        if (i5 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i5 + " was specified").toString());
    }

    public final Object F(Object obj, boolean z2) {
        a aVar = this.f3479n;
        a aVar2 = a.f3437f;
        p3.n nVar = p3.n.f5026a;
        if (aVar == aVar2) {
            Object objO = super.o(obj);
            return (!(objO instanceof i) || (objO instanceof h)) ? objO : nVar;
        }
        Object obj2 = e.f3456d;
        k kVar = (k) c.f3446i.get(this);
        while (true) {
            long andIncrement = c.f3442e.getAndIncrement(this);
            long j5 = 1152921504606846975L & andIncrement;
            boolean zU = u(andIncrement, false);
            int i5 = e.f3454b;
            long j6 = i5;
            long j7 = j5 / j6;
            int i6 = (int) (j5 % j6);
            if (kVar.f4307c != j7) {
                k kVarA = c.a(this, j7, kVar);
                if (kVarA != null) {
                    kVar = kVarA;
                } else if (zU) {
                    return new h(r());
                }
            }
            int iE = c.e(this, kVar, i6, obj, j5, obj2, zU);
            if (iE == 0) {
                kVar.a();
                return nVar;
            }
            if (iE != 1) {
                if (iE != 2) {
                    if (iE == 3) {
                        throw new IllegalStateException("unexpected");
                    }
                    if (iE == 4) {
                        if (j5 < c.f3443f.get(this)) {
                            kVar.a();
                        }
                        return new h(r());
                    }
                    if (iE == 5) {
                        kVar.a();
                    }
                } else {
                    if (zU) {
                        kVar.h();
                        return new h(r());
                    }
                    p1 p1Var = obj2 instanceof p1 ? (p1) obj2 : null;
                    if (p1Var != null) {
                        p1Var.a(kVar, i6 + i5);
                    }
                    j((kVar.f4307c * j6) + ((long) i6));
                }
            }
            return nVar;
        }
    }

    @Override // k4.c, k4.r
    public final Object l(Object obj, t3.c cVar) throws Throwable {
        if (F(obj, true) instanceof h) {
            throw r();
        }
        return p3.n.f5026a;
    }

    @Override // k4.c, k4.r
    public final Object o(Object obj) {
        return F(obj, false);
    }

    @Override // k4.c
    public final boolean v() {
        return this.f3479n == a.f3436e;
    }
}
