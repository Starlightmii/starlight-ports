package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5576q;

    public final long A0(long j5, boolean z2) {
        int iJ = k2.a.j(j5);
        int iRound = Math.round(iJ * this.f5576q);
        if (iRound <= 0) {
            return 0L;
        }
        long jF = a.a.f(iRound, iJ);
        if (!z2 || h1.c.A(j5, jF)) {
            return jF;
        }
        return 0L;
    }

    public final long B0(long j5, boolean z2) {
        int iK = k2.a.k(j5);
        int iRound = Math.round(iK / this.f5576q);
        if (iRound <= 0) {
            return 0L;
        }
        long jF = a.a.f(iK, iRound);
        if (!z2 || h1.c.A(j5, jF)) {
            return jF;
        }
        return 0L;
    }

    @Override // p1.w
    public final int a0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return i5 != Integer.MAX_VALUE ? Math.round(i5 * this.f5576q) : e0Var.c0(i5);
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        long jZ0 = z0(j5, true);
        if (k2.j.a(jZ0, 0L)) {
            jZ0 = y0(j5, true);
            if (k2.j.a(jZ0, 0L)) {
                jZ0 = B0(j5, true);
                if (k2.j.a(jZ0, 0L)) {
                    jZ0 = A0(j5, true);
                    if (k2.j.a(jZ0, 0L)) {
                        jZ0 = z0(j5, false);
                        if (k2.j.a(jZ0, 0L)) {
                            jZ0 = y0(j5, false);
                            if (k2.j.a(jZ0, 0L)) {
                                jZ0 = B0(j5, false);
                                if (k2.j.a(jZ0, 0L)) {
                                    jZ0 = A0(j5, false);
                                    if (k2.j.a(jZ0, 0L)) {
                                        jZ0 = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!k2.j.a(jZ0, 0L)) {
            int i5 = (int) (jZ0 >> 32);
            int i6 = (int) (jZ0 & 4294967295L);
            if (i5 < 0 || i6 < 0) {
                d4.a.J("width(" + i5 + ") and height(" + i6 + ") must be >= 0");
                throw null;
            }
            j5 = h1.c.r(i5, i5, i6, i6);
        }
        n1.m0 m0VarC = e0Var.c(j5);
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new n1.p0(m0VarC, 2));
    }

    @Override // p1.w
    public final int g0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return i5 != Integer.MAX_VALUE ? Math.round(i5 / this.f5576q) : e0Var.e0(i5);
    }

    @Override // p1.w
    public final int m0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return i5 != Integer.MAX_VALUE ? Math.round(i5 * this.f5576q) : e0Var.a0(i5);
    }

    @Override // p1.w
    public final int x(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return i5 != Integer.MAX_VALUE ? Math.round(i5 / this.f5576q) : e0Var.d(i5);
    }

    public final long y0(long j5, boolean z2) {
        int iRound;
        int iH = k2.a.h(j5);
        if (iH == Integer.MAX_VALUE || (iRound = Math.round(iH * this.f5576q)) <= 0) {
            return 0L;
        }
        long jF = a.a.f(iRound, iH);
        if (!z2 || h1.c.A(j5, jF)) {
            return jF;
        }
        return 0L;
    }

    public final long z0(long j5, boolean z2) {
        int iRound;
        int i5 = k2.a.i(j5);
        if (i5 == Integer.MAX_VALUE || (iRound = Math.round(i5 / this.f5576q)) <= 0) {
            return 0L;
        }
        long jF = a.a.f(i5, iRound);
        if (!z2 || h1.c.A(j5, jF)) {
            return jF;
        }
        return 0L;
    }
}
