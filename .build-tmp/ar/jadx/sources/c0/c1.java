package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c1 extends r0.o implements p1.k, p1.w {
    @Override // p1.w
    public final n1.g0 c(n1.h0 h0Var, n1.e0 e0Var, long j5) {
        float f5 = ((k2.e) p1.f.h(this, r0.f1218a)).f3406d;
        float f6 = 0;
        if (f5 < f6) {
            f5 = f6;
        }
        n1.m0 m0VarC = e0Var.c(j5);
        boolean z2 = this.f5693p && !Float.isNaN(f5) && Float.compare(f5, f6) > 0;
        int iM = Float.isNaN(f5) ? 0 : h0Var.M(f5);
        int iMax = z2 ? Math.max(m0VarC.f4130d, iM) : m0VarC.f4130d;
        int iMax2 = z2 ? Math.max(m0VarC.f4131e, iM) : m0VarC.f4131e;
        return h0Var.F(iMax, iMax2, q3.t.f5478d, new b1(iMax, m0VarC, iMax2));
    }
}
