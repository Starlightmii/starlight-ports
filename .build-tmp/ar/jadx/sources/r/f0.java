package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5561q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5562r;

    @Override // p1.w
    public final int a0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return this.f5561q == 1 ? e0Var.a0(i5) : e0Var.c0(i5);
    }

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        int iA0 = this.f5561q == 1 ? e0Var.a0(k2.a.h(j5)) : e0Var.c0(k2.a.h(j5));
        if (iA0 < 0) {
            iA0 = 0;
        }
        if (iA0 >= 0) {
            long jR = h1.c.r(iA0, iA0, 0, Integer.MAX_VALUE);
            if (this.f5562r) {
                jR = h1.c.o(j5, jR);
            }
            n1.m0 m0VarC = e0Var.c(jR);
            return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new n1.p0(m0VarC, 4));
        }
        d4.a.J("width(" + iA0 + ") must be >= 0");
        throw null;
    }

    @Override // p1.w
    public final int g0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return e0Var.e0(i5);
    }

    @Override // p1.w
    public final int m0(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return this.f5561q == 1 ? e0Var.a0(i5) : e0Var.c0(i5);
    }

    @Override // p1.w
    public final int x(p1.m0 m0Var, n1.e0 e0Var, int i5) {
        return e0Var.d(i5);
    }
}
