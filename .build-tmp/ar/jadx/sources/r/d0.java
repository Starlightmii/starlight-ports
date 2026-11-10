package r;

import f0.s1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d0 implements n1.r, o1.c, r0.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z0 f5545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s1 f5546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s1 f5547c;

    public d0(z0 z0Var) {
        this.f5545a = z0Var;
        this.f5546b = f0.l.s(z0Var);
        this.f5547c = f0.l.s(z0Var);
    }

    @Override // n1.r
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        s1 s1Var = this.f5546b;
        int iD = ((z0) s1Var.getValue()).d(h0Var, h0Var.getLayoutDirection());
        int iB = ((z0) s1Var.getValue()).b(h0Var);
        int iC = ((z0) s1Var.getValue()).c(h0Var, h0Var.getLayoutDirection()) + iD;
        int iA = ((z0) s1Var.getValue()).a(h0Var) + iB;
        n1.m0 m0VarC = e0Var.c(h1.c.G(-iC, -iA, j5));
        return h0Var.F(h1.c.q(j5, m0VarC.f4130d + iC), h1.c.p(j5, m0VarC.f4131e + iA), q3.t.f5478d, new c0.b1(m0VarC, iD, iB, 1));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d0) {
            return b4.l.a(((d0) obj).f5545a, this.f5545a);
        }
        return false;
    }

    @Override // o1.c
    public final void f(o1.f fVar) {
        z0 z0Var = (z0) fVar.e(c1.f5542a);
        z0 z0Var2 = this.f5545a;
        this.f5546b.setValue(new z(z0Var2, z0Var));
        this.f5547c.setValue(new w0(z0Var, z0Var2));
    }

    public final int hashCode() {
        return this.f5545a.hashCode();
    }
}
