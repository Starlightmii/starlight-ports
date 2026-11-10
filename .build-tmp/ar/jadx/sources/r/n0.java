package r;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n0 extends r0.o implements p1.w {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public l0 f5595q;

    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        float f5 = 0;
        if (Float.compare(this.f5595q.c(h0Var.getLayoutDirection()), f5) < 0 || Float.compare(this.f5595q.b(), f5) < 0 || Float.compare(this.f5595q.d(h0Var.getLayoutDirection()), f5) < 0 || Float.compare(this.f5595q.a(), f5) < 0) {
            throw new IllegalArgumentException("Padding must be non-negative");
        }
        int iM = h0Var.M(this.f5595q.d(h0Var.getLayoutDirection())) + h0Var.M(this.f5595q.c(h0Var.getLayoutDirection()));
        int iM2 = h0Var.M(this.f5595q.a()) + h0Var.M(this.f5595q.b());
        n1.m0 m0VarC = e0Var.c(h1.c.G(-iM, -iM2, j5));
        return h0Var.F(h1.c.q(j5, m0VarC.f4130d + iM), h1.c.p(j5, m0VarC.f4131e + iM2), q3.t.f5478d, new c.b(m0VarC, h0Var, this, 6));
    }
}
