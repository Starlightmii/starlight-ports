package i4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 extends w0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a1 f2796h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final z0 f2797i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final l f2798j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f2799k;

    public y0(a1 a1Var, z0 z0Var, l lVar, Object obj) {
        this.f2796h = a1Var;
        this.f2797i = z0Var;
        this.f2798j = lVar;
        this.f2799k = obj;
    }

    @Override // i4.w0
    public final boolean j() {
        return false;
    }

    @Override // i4.w0
    public final void k(Throwable th) {
        l lVar = this.f2798j;
        l lVarQ = a1.Q(lVar);
        a1 a1Var = this.f2796h;
        z0 z0Var = this.f2797i;
        Object obj = this.f2799k;
        if (lVarQ == null || !a1Var.Z(z0Var, lVarQ, obj)) {
            z0Var.f2805d.d(new n4.h(2), 2);
            l lVarQ2 = a1.Q(lVar);
            if (lVarQ2 == null || !a1Var.Z(z0Var, lVarQ2, obj)) {
                a1Var.u(a1Var.E(z0Var, obj));
            }
        }
    }
}
