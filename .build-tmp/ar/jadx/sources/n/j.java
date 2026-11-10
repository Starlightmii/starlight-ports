package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j extends r0.o implements p1.o, p1.a1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f3936q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public y0.p f3937r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f3938s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public y0.q0 f3939t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f3940u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public k2.k f3941v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public y0.j0 f3942w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public y0.q0 f3943x;

    @Override // p1.a1
    public final void J() {
        this.f3940u = 9205357640488583168L;
        this.f3941v = null;
        this.f3942w = null;
        this.f3943x = null;
        p1.f.l(this);
    }

    @Override // p1.o
    public final void d(p1.f0 f0Var) {
        y0.k0 k0Var;
        a1.c cVar = f0Var.f4828d;
        if (this.f3939t == y0.l0.f6642a) {
            if (!y0.u.c(this.f3936q, y0.u.f6694g)) {
                a1.f.B(f0Var, this.f3936q, 0L, 126);
            }
            y0.p pVar = this.f3937r;
            if (pVar != null) {
                a1.f.n(f0Var, pVar, 0L, 0L, this.f3938s, null, 118);
            }
        } else {
            b4.w wVar = new b4.w();
            if (x0.f.a(cVar.b(), this.f3940u) && f0Var.getLayoutDirection() == this.f3941v && b4.l.a(this.f3943x, this.f3939t)) {
                y0.j0 j0Var = this.f3942w;
                b4.l.b(j0Var);
                wVar.f697d = j0Var;
            } else {
                p1.f.p(this, new i(wVar, this, f0Var, 0));
            }
            this.f3942w = (y0.j0) wVar.f697d;
            this.f3940u = cVar.b();
            this.f3941v = f0Var.getLayoutDirection();
            this.f3943x = this.f3939t;
            Object obj = wVar.f697d;
            b4.l.b(obj);
            y0.j0 j0Var2 = (y0.j0) obj;
            if (!y0.u.c(this.f3936q, y0.u.f6694g)) {
                y0.l0.m(f0Var, j0Var2, this.f3936q);
            }
            y0.p pVar2 = this.f3937r;
            if (pVar2 != null) {
                float f5 = this.f3938s;
                boolean z2 = j0Var2 instanceof y0.h0;
                a1.i iVar = a1.i.f33a;
                if (z2) {
                    x0.d dVar = ((y0.h0) j0Var2).f6633a;
                    f0Var.e(pVar2, p3.j.b(dVar.f6534a, dVar.f6535b), p3.j.f(dVar.c(), dVar.b()), f5, iVar);
                } else {
                    if (j0Var2 instanceof y0.i0) {
                        y0.i0 i0Var = (y0.i0) j0Var2;
                        k0Var = i0Var.f6637b;
                        if (k0Var == null) {
                            x0.e eVar = i0Var.f6636a;
                            float fB = x0.a.b(eVar.f6545h);
                            f0Var.g(pVar2, p3.j.b(eVar.f6538a, eVar.f6539b), p3.j.f(eVar.b(), eVar.a()), p3.p.a(fB, fB), f5, iVar);
                        }
                    } else {
                        if (!(j0Var2 instanceof y0.g0)) {
                            throw new i4.p();
                        }
                        k0Var = ((y0.g0) j0Var2).f6628a;
                    }
                    f0Var.V(k0Var, pVar2, f5, iVar, 3);
                }
            }
        }
        f0Var.c();
    }
}
