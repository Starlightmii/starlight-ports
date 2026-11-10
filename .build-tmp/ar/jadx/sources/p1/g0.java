package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j0 f4832e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e1 f4833f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f4834g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(j0 j0Var, e1 e1Var, long j5) {
        super(0);
        this.f4832e = j0Var;
        this.f4833f = e1Var;
        this.f4834g = j5;
    }

    @Override // a4.a
    public final Object a() {
        n0 n0VarJ0;
        j0 j0Var = this.f4832e;
        n1.l0 placementScope = null;
        if (f.o(j0Var.f4893a)) {
            y0 y0Var = j0Var.a().f5000q;
            if (y0Var != null) {
                placementScope = y0Var.f4925l;
            }
        } else {
            y0 y0Var2 = j0Var.a().f5000q;
            if (y0Var2 != null && (n0VarJ0 = y0Var2.J0()) != null) {
                placementScope = n0VarJ0.f4925l;
            }
        }
        if (placementScope == null) {
            placementScope = ((q1.u) this.f4833f).getPlacementScope();
        }
        n0 n0VarJ1 = j0Var.a().J0();
        b4.l.b(n0VarJ1);
        n1.l0.e(placementScope, n0VarJ1, this.f4834g);
        return p3.n.f5026a;
    }
}
