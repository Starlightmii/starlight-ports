package n;

import c0.q4;
import c0.t2;
import p1.l1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends p1.m implements w0.c, l1, p1.p, w0.o {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public w0.q f3880s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b0 f3881t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final d0 f3882u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final e0 f3883v;

    public c0(q.k kVar) {
        b0 b0Var = new b0();
        b0Var.f3871q = kVar;
        y0(b0Var);
        this.f3881t = b0Var;
        d0 d0Var = new d0();
        y0(d0Var);
        this.f3882u = d0Var;
        e0 e0Var = new e0();
        y0(e0Var);
        this.f3883v = e0Var;
        y0(new w0.r());
    }

    public final void B0(q.k kVar) {
        q.d dVar;
        b0 b0Var = this.f3881t;
        if (b4.l.a(b0Var.f3871q, kVar)) {
            return;
        }
        q.k kVar2 = b0Var.f3871q;
        if (kVar2 != null && (dVar = b0Var.f3872r) != null) {
            kVar2.c(new q.e(dVar));
        }
        b0Var.f3872r = null;
        b0Var.f3871q = kVar;
    }

    @Override // p1.p
    public final void D(p1.y0 y0Var) {
        this.f3883v.D(y0Var);
    }

    @Override // p1.l1
    public final void Z(w1.i iVar) {
        w0.q qVar = this.f3880s;
        boolean z2 = false;
        if (qVar != null && qVar.a()) {
            z2 = true;
        }
        f4.d[] dVarArr = w1.s.f6363a;
        w1.t tVar = w1.q.f6346k;
        f4.d dVar = w1.s.f6363a[4];
        tVar.a(iVar, Boolean.valueOf(z2));
        iVar.c(w1.h.f6301t, new w1.a(null, new b0.a(12, this)));
    }

    @Override // w0.c
    public final void k0(w0.q qVar) {
        f0 f0VarY0;
        if (b4.l.a(this.f3880s, qVar)) {
            return;
        }
        boolean zA = qVar.a();
        t3.c cVar = null;
        if (zA) {
            i4.y.n(n0(), null, new q4(this, cVar, 3), 3);
        }
        if (this.f5693p) {
            p1.f.n(this);
        }
        b0 b0Var = this.f3881t;
        q.k kVar = b0Var.f3871q;
        if (kVar != null) {
            if (zA) {
                q.d dVar = b0Var.f3872r;
                if (dVar != null) {
                    b0Var.y0(kVar, new q.e(dVar));
                    b0Var.f3872r = null;
                }
                q.d dVar2 = new q.d();
                b0Var.y0(kVar, dVar2);
                b0Var.f3872r = dVar2;
            } else {
                q.d dVar3 = b0Var.f3872r;
                if (dVar3 != null) {
                    b0Var.y0(kVar, new q.e(dVar3));
                    b0Var.f3872r = null;
                }
            }
        }
        e0 e0Var = this.f3883v;
        if (zA != e0Var.f3894q) {
            if (zA) {
                p1.y0 y0Var = e0Var.f3895r;
                if (y0Var != null && y0Var.L0().f5693p && (f0VarY0 = e0Var.y0()) != null) {
                    f0VarY0.y0(e0Var.f3895r);
                }
            } else {
                f0 f0VarY1 = e0Var.y0();
                if (f0VarY1 != null) {
                    f0VarY1.y0(null);
                }
            }
            e0Var.f3894q = zA;
        }
        d0 d0Var = this.f3882u;
        if (zA) {
            d0Var.getClass();
            b4.w wVar = new b4.w();
            p1.f.p(d0Var, new t2(wVar, 2, d0Var));
            if (wVar.f697d != null) {
                throw new ClassCastException();
            }
        }
        d0Var.getClass();
        this.f3880s = qVar;
    }

    @Override // r0.o
    public final boolean o0() {
        return false;
    }
}
