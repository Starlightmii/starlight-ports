package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k0 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f5577q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f5578r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f5579s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f5580t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f5581u;

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        int iM = h0Var.M(this.f5579s) + h0Var.M(this.f5577q);
        int iM2 = h0Var.M(this.f5580t) + h0Var.M(this.f5578r);
        n1.m0 m0VarC = e0Var.c(h1.c.G(-iM, -iM2, j5));
        return h0Var.F(h1.c.q(j5, m0VarC.f4130d + iM), h1.c.p(j5, m0VarC.f4131e + iM2), q3.t.f5478d, new c.b(this, m0VarC, h0Var, 5));
    }
}
