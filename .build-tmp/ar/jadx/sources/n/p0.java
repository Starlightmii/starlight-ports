package n;

import android.view.View;
import c0.q4;
import f0.s1;
import p1.l1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p0 extends r0.o implements p1.p, p1.o, l1, p1.a1 {
    public k4.c A;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public w1.l f3984q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public z.i0 f3985r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public z0 f3986s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f3987t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public k2.b f3988u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public y0 f3989v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public f0.n0 f3991x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public k2.j f3993z;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final s1 f3990w = new s1(null, f0.h.f2186g);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f3992y = 9205357640488583168L;

    public p0(w1.l lVar, z.i0 i0Var, z0 z0Var) {
        this.f3984q = lVar;
        this.f3985r = i0Var;
        this.f3986s = z0Var;
    }

    public final void A0() {
        k2.b bVar = this.f3988u;
        if (bVar == null) {
            bVar = p1.f.s(this).f4796t;
            this.f3988u = bVar;
        }
        long j5 = ((x0.c) this.f3984q.c(bVar)).f6532a;
        if (!p3.j.E(j5) || !p3.j.E(y0())) {
            this.f3992y = 9205357640488583168L;
            y0 y0Var = this.f3989v;
            if (y0Var != null) {
                ((a1) y0Var).b();
                return;
            }
            return;
        }
        this.f3992y = x0.c.h(y0(), j5);
        if (this.f3989v == null) {
            z0();
        }
        y0 y0Var2 = this.f3989v;
        if (y0Var2 != null) {
            y0Var2.a(this.f3992y, 9205357640488583168L);
        }
        B0();
    }

    public final void B0() {
        k2.b bVar;
        y0 y0Var = this.f3989v;
        if (y0Var == null || (bVar = this.f3988u) == null) {
            return;
        }
        a1 a1Var = (a1) y0Var;
        long jC = a1Var.c();
        k2.j jVar = this.f3993z;
        if (jVar != null && jC == jVar.f3415a) {
            return;
        }
        this.f3985r.c(new k2.g(bVar.u(a.a.O(a1Var.c()))));
        this.f3993z = new k2.j(a1Var.c());
    }

    @Override // p1.p
    public final void D(p1.y0 y0Var) {
        this.f3990w.setValue(y0Var);
    }

    @Override // p1.a1
    public final void J() {
        p1.f.p(this, new o0(this, 2));
    }

    @Override // p1.l1
    public final void Z(w1.i iVar) {
        iVar.c(q0.f3996a, new o0(this, 1));
    }

    @Override // p1.o
    public final void d(p1.f0 f0Var) {
        f0Var.c();
        k4.c cVar = this.A;
        if (cVar != null) {
            cVar.o(p3.n.f5026a);
        }
    }

    @Override // r0.o
    public final void r0() {
        J();
        this.A = k4.j.a(0, 7, null);
        i4.y.n(n0(), null, new q4(this, null, 4), 3);
    }

    @Override // r0.o
    public final void s0() {
        y0 y0Var = this.f3989v;
        if (y0Var != null) {
            ((a1) y0Var).b();
        }
        this.f3989v = null;
    }

    public final long y0() {
        if (this.f3991x == null) {
            this.f3991x = f0.l.n(new o0(this, 0));
        }
        f0.n0 n0Var = this.f3991x;
        if (n0Var != null) {
            return ((x0.c) n0Var.getValue()).f6532a;
        }
        return 9205357640488583168L;
    }

    public final void z0() {
        y0 y0Var = this.f3989v;
        if (y0Var != null) {
            ((a1) y0Var).b();
        }
        View viewV = this.f3987t;
        if (viewV == null) {
            viewV = p1.f.v(this);
        }
        this.f3987t = viewV;
        k2.b bVar = this.f3988u;
        if (bVar == null) {
            bVar = p1.f.s(this).f4796t;
        }
        this.f3988u = bVar;
        this.f3989v = this.f3986s.b(viewV, bVar);
        B0();
    }
}
