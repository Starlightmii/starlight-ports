package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5504q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5505r;

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        int iK;
        int i5;
        int iH;
        int iM;
        if (!k2.a.e(j5) || this.f5504q == 1) {
            iK = k2.a.k(j5);
            i5 = k2.a.i(j5);
        } else {
            iK = d4.a.m(Math.round(k2.a.i(j5) * this.f5505r), k2.a.k(j5), k2.a.i(j5));
            i5 = iK;
        }
        if (!k2.a.d(j5) || this.f5504q == 2) {
            int iJ = k2.a.j(j5);
            iH = k2.a.h(j5);
            iM = iJ;
        } else {
            iM = d4.a.m(Math.round(k2.a.h(j5) * this.f5505r), k2.a.j(j5), k2.a.h(j5));
            iH = iM;
        }
        n1.m0 m0VarC = e0Var.c(h1.c.b(iK, i5, iM, iH));
        return h0Var.F(m0VarC.f4130d, m0VarC.f4131e, q3.t.f5478d, new n1.p0(m0VarC, 3));
    }
}
