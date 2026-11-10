package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v0 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5632q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5633r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5634s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f5635t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f5636u;

    @Override // p1.w
    public final int a0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        long jY0 = y0(m0Var);
        return k2.a.g(jY0) ? k2.a.i(jY0) : h1.c.q(jY0, e0Var.c0(i5));
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        int iK;
        int i5;
        int iJ;
        int iH;
        long jB;
        long jY0 = y0(h0Var);
        if (this.f5636u) {
            jB = h1.c.o(j5, jY0);
        } else {
            if (k2.e.a(this.f5632q, Float.NaN)) {
                iK = k2.a.k(j5);
                int i6 = k2.a.i(jY0);
                if (iK > i6) {
                    iK = i6;
                }
            } else {
                iK = k2.a.k(jY0);
            }
            if (k2.e.a(this.f5634s, Float.NaN)) {
                i5 = k2.a.i(j5);
                int iK2 = k2.a.k(jY0);
                if (i5 < iK2) {
                    i5 = iK2;
                }
            } else {
                i5 = k2.a.i(jY0);
            }
            if (k2.e.a(this.f5633r, Float.NaN)) {
                iJ = k2.a.j(j5);
                int iH2 = k2.a.h(jY0);
                if (iJ > iH2) {
                    iJ = iH2;
                }
            } else {
                iJ = k2.a.j(jY0);
            }
            if (k2.e.a(this.f5635t, Float.NaN)) {
                iH = k2.a.h(j5);
                int iJ2 = k2.a.j(jY0);
                if (iH < iJ2) {
                    iH = iJ2;
                }
            } else {
                iH = k2.a.h(jY0);
            }
            jB = h1.c.b(iK, i5, iJ, iH);
        }
        n1.m0 m0VarC = e0Var.c(jB);
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new n1.p0(m0VarC, 5));
    }

    @Override // p1.w
    public final int g0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        long jY0 = y0(m0Var);
        return k2.a.f(jY0) ? k2.a.h(jY0) : h1.c.p(jY0, e0Var.e0(i5));
    }

    @Override // p1.w
    public final int m0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        long jY0 = y0(m0Var);
        return k2.a.g(jY0) ? k2.a.i(jY0) : h1.c.q(jY0, e0Var.a0(i5));
    }

    @Override // p1.w
    public final int x(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        long jY0 = y0(m0Var);
        return k2.a.f(jY0) ? k2.a.h(jY0) : h1.c.p(jY0, e0Var.d(i5));
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0043  */
    public final long y0(n1.h0 h0Var) {
        int iM;
        int iM2;
        int iM3;
        int i5 = 0;
        if (k2.e.a(this.f5634s, Float.NaN)) {
            iM = Integer.MAX_VALUE;
        } else {
            iM = h0Var.M(this.f5634s);
            if (iM < 0) {
                iM = 0;
            }
        }
        if (k2.e.a(this.f5635t, Float.NaN)) {
            iM2 = Integer.MAX_VALUE;
        } else {
            iM2 = h0Var.M(this.f5635t);
            if (iM2 < 0) {
                iM2 = 0;
            }
        }
        if (k2.e.a(this.f5632q, Float.NaN)) {
            iM3 = 0;
        } else {
            iM3 = h0Var.M(this.f5632q);
            if (iM3 > iM) {
                iM3 = iM;
            }
            if (iM3 < 0) {
                iM3 = 0;
            }
            if (iM3 == Integer.MAX_VALUE) {
                iM3 = 0;
            }
        }
        if (!k2.e.a(this.f5633r, Float.NaN)) {
            int iM4 = h0Var.M(this.f5633r);
            if (iM4 > iM2) {
                iM4 = iM2;
            }
            if (iM4 < 0) {
                iM4 = 0;
            }
            if (iM4 != Integer.MAX_VALUE) {
                i5 = iM4;
            }
        }
        return h1.c.b(iM3, iM, i5, iM2);
    }
}
