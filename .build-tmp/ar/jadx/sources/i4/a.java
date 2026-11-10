package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends a1 implements t3.c, v {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t3.h f2713f;

    public a(t3.h hVar, boolean z2) {
        super(z2);
        L((s0) hVar.j(s.f2774e));
        this.f2713f = hVar.k(this);
    }

    @Override // i4.a1
    public final String A() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // i4.a1
    public final void K(p pVar) {
        y.j(pVar, this.f2713f);
    }

    @Override // i4.a1
    public final void S(Object obj) {
        if (!(obj instanceof o)) {
            b0(obj);
        } else {
            o oVar = (o) obj;
            a0(oVar.f2763a, o.f2762b.get(oVar) != 0);
        }
    }

    public void a0(Throwable th, boolean z2) {
    }

    public void b0(Object obj) {
    }

    public final void c0(w wVar, a aVar, a4.e eVar) {
        Object objInvoke;
        int iOrdinal = wVar.ordinal();
        p3.n nVar = p3.n.f5026a;
        if (iOrdinal == 0) {
            try {
                n4.a.h(nVar, p3.j.B(p3.j.q(eVar, aVar, this)));
                return;
            } catch (Throwable th) {
                resumeWith(p3.j.r(th));
                throw th;
            }
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                b4.l.e("<this>", eVar);
                p3.j.B(p3.j.q(eVar, aVar, this)).resumeWith(nVar);
                return;
            }
            if (iOrdinal != 3) {
                throw new p();
            }
            try {
                t3.h hVar = this.f2713f;
                Object objL = n4.a.l(hVar, null);
                try {
                    if (eVar instanceof v3.a) {
                        b4.z.c(2, eVar);
                        objInvoke = eVar.invoke(aVar, this);
                    } else {
                        objInvoke = p3.j.R(eVar, aVar, this);
                    }
                    n4.a.g(hVar, objL);
                    if (objInvoke != u3.a.f5838d) {
                        resumeWith(objInvoke);
                    }
                } catch (Throwable th2) {
                    n4.a.g(hVar, objL);
                    throw th2;
                }
            } catch (Throwable th3) {
                resumeWith(p3.j.r(th3));
            }
        }
    }

    @Override // t3.c
    public final t3.h getContext() {
        return this.f2713f;
    }

    @Override // i4.v
    public final t3.h h() {
        return this.f2713f;
    }

    @Override // t3.c
    public final void resumeWith(Object obj) {
        Throwable thA = p3.i.a(obj);
        if (thA != null) {
            obj = new o(thA, false);
        }
        Object objP = P(obj);
        if (objP == y.f2790e) {
            return;
        }
        v(objP);
    }
}
