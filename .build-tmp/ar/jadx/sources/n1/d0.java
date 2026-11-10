package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p1.n0 f4113d;

    public d0(p1.n0 n0Var) {
        this.f4113d = n0Var;
    }

    @Override // n1.p
    public final long K(long j5) {
        return x0.c.h(this.f4113d.f4932o.K(j5), a());
    }

    @Override // n1.p
    public final boolean N() {
        return this.f4113d.f4932o.L0().f5693p;
    }

    @Override // n1.p
    public final void P(float[] fArr) {
        this.f4113d.f4932o.P(fArr);
    }

    @Override // n1.p
    public final void T(p pVar, float[] fArr) {
        this.f4113d.f4932o.T(pVar, fArr);
    }

    @Override // n1.p
    public final long Y() {
        p1.n0 n0Var = this.f4113d;
        return a.a.f(n0Var.f4130d, n0Var.f4131e);
    }

    @Override // n1.p
    public final long Z(long j5) {
        return this.f4113d.f4932o.Z(x0.c.h(j5, a()));
    }

    public final long a() {
        p1.n0 n0Var = this.f4113d;
        p1.n0 n0VarG = v0.g(n0Var);
        return x0.c.g(b(n0VarG.f4935r, 0L), n0Var.f4932o.T0(n0VarG.f4932o, 0L));
    }

    public final long b(p pVar, long j5) {
        boolean z2 = pVar instanceof d0;
        p1.n0 n0Var = this.f4113d;
        if (!z2) {
            p1.n0 n0VarG = v0.g(n0Var);
            long jB = b(n0VarG.f4935r, j5);
            p1.y0 y0Var = n0VarG.f4932o;
            y0Var.getClass();
            return x0.c.h(jB, y0Var.T0(pVar, 0L));
        }
        p1.n0 n0Var2 = ((d0) pVar).f4113d;
        p1.y0 y0Var2 = n0Var2.f4932o;
        y0Var2.U0();
        p1.n0 n0VarJ0 = n0Var.f4932o.H0(y0Var2).J0();
        if (n0VarJ0 != null) {
            long jB2 = k2.h.b(k2.h.c(n0Var2.C0(n0VarJ0, false), h1.c.M(j5)), n0Var.C0(n0VarJ0, false));
            return p3.j.b((int) (jB2 >> 32), (int) (jB2 & 4294967295L));
        }
        p1.n0 n0VarG2 = v0.g(n0Var2);
        long jC = k2.h.c(k2.h.c(n0Var2.C0(n0VarG2, false), n0VarG2.f4933p), h1.c.M(j5));
        p1.n0 n0VarG3 = v0.g(n0Var);
        long jB3 = k2.h.b(jC, k2.h.c(n0Var.C0(n0VarG3, false), n0VarG3.f4933p));
        long jB4 = p3.j.b((int) (jB3 >> 32), (int) (jB3 & 4294967295L));
        p1.y0 y0Var3 = n0VarG3.f4932o.f5000q;
        b4.l.b(y0Var3);
        p1.y0 y0Var4 = n0VarG2.f4932o.f5000q;
        b4.l.b(y0Var4);
        return y0Var3.T0(y0Var4, jB4);
    }

    @Override // n1.p
    public final long e(long j5) {
        return x0.c.h(this.f4113d.f4932o.e(j5), a());
    }

    @Override // n1.p
    public final long g(long j5) {
        return this.f4113d.f4932o.g(x0.c.h(j5, a()));
    }

    @Override // n1.p
    public final p i() {
        p1.n0 n0VarJ0;
        if (!N()) {
            d4.a.L("LayoutCoordinate operations are only valid when isAttached is true");
            throw null;
        }
        p1.y0 y0Var = this.f4113d.f4932o.f4998o.f4801y.f4970c.f5000q;
        if (y0Var == null || (n0VarJ0 = y0Var.J0()) == null) {
            return null;
        }
        return n0VarJ0.f4935r;
    }

    @Override // n1.p
    public final x0.d k(p pVar, boolean z2) {
        return this.f4113d.f4932o.k(pVar, z2);
    }

    @Override // n1.p
    public final long y(p pVar, long j5) {
        return b(pVar, j5);
    }
}
