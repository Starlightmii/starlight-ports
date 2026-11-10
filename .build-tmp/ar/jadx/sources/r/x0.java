package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5640q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5641r;

    @Override // p1.w
    public final int a0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        int iC0 = e0Var.c0(i5);
        int iM = !k2.e.a(this.f5640q, Float.NaN) ? m0Var.M(this.f5640q) : 0;
        return iC0 < iM ? iM : iC0;
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        int iK;
        int iJ = 0;
        if (k2.e.a(this.f5640q, Float.NaN) || k2.a.k(j5) != 0) {
            iK = k2.a.k(j5);
        } else {
            iK = h0Var.M(this.f5640q);
            int i5 = k2.a.i(j5);
            if (iK > i5) {
                iK = i5;
            }
            if (iK < 0) {
                iK = 0;
            }
        }
        int i6 = k2.a.i(j5);
        if (k2.e.a(this.f5641r, Float.NaN) || k2.a.j(j5) != 0) {
            iJ = k2.a.j(j5);
        } else {
            int iM = h0Var.M(this.f5641r);
            int iH = k2.a.h(j5);
            if (iM > iH) {
                iM = iH;
            }
            if (iM >= 0) {
                iJ = iM;
            }
        }
        n1.m0 m0VarC = e0Var.c(h1.c.b(iK, i6, iJ, k2.a.h(j5)));
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new n1.p0(m0VarC, 6));
    }

    @Override // p1.w
    public final int g0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        int iE0 = e0Var.e0(i5);
        int iM = !k2.e.a(this.f5641r, Float.NaN) ? m0Var.M(this.f5641r) : 0;
        return iE0 < iM ? iM : iE0;
    }

    @Override // p1.w
    public final int m0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        int iA0 = e0Var.a0(i5);
        int iM = !k2.e.a(this.f5640q, Float.NaN) ? m0Var.M(this.f5640q) : 0;
        return iA0 < iM ? iM : iA0;
    }

    @Override // p1.w
    public final int x(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        int iD = e0Var.d(i5);
        int iM = !k2.e.a(this.f5641r, Float.NaN) ? m0Var.M(this.f5641r) : 0;
        return iD < iM ? iM : iD;
    }
}
