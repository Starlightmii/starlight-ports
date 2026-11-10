package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e1 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5557q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public b4.m f5558r;

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        n1.m0 m0VarC = e0Var.c(h1.c.b(this.f5557q != 1 ? 0 : k2.a.k(j5), k2.a.i(j5), this.f5557q == 2 ? k2.a.j(j5) : 0, k2.a.h(j5)));
        int iM = d4.a.m(m0VarC.f4130d, k2.a.k(j5), k2.a.i(j5));
        int iM2 = d4.a.m(m0VarC.f4131e, k2.a.j(j5), k2.a.h(j5));
        return h0Var.F(iM, iM2, q3.t.f5478d, new d1(this, iM, m0VarC, iM2, h0Var));
    }
}
