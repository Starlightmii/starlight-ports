package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ v0 f1435e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1436f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n0.d f1437g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(v0 v0Var, boolean z2, n0.d dVar) {
        super(2);
        this.f1435e = v0Var;
        this.f1436f = z2;
        this.f1437g = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x002f  */
    /* JADX WARN: Code duplicated, block: B:11:0x0032  */
    /* JADX WARN: Code duplicated, block: B:8:0x001b  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        boolean z2;
        v0 v0Var;
        long j5;
        f0.p pVar = (f0.p) obj;
        if ((((Number) obj2).intValue() & 3) == 2) {
            f0.v vVar = (f0.v) pVar;
            if (vVar.z()) {
                vVar.Q();
            } else {
                f0.v vVar2 = (f0.v) pVar;
                vVar2.V(1264683960);
                vVar2.p(false);
                f0.j0 j0Var = b0.f762a;
                z2 = this.f1436f;
                v0Var = this.f1435e;
                if (z2) {
                    j5 = v0Var.f1360a;
                } else {
                    j5 = v0Var.f1363d;
                }
                f0.l.a(j0Var.a(new y0.u(j5)), n0.i.c(-1728894036, new q(this.f1437g, 1), vVar2), vVar2, 56);
            }
        } else {
            f0.v vVar3 = (f0.v) pVar;
            vVar3.V(1264683960);
            vVar3.p(false);
            f0.j0 j0Var2 = b0.f762a;
            z2 = this.f1436f;
            v0Var = this.f1435e;
            if (z2) {
                j5 = v0Var.f1360a;
            } else {
                j5 = v0Var.f1363d;
            }
            f0.l.a(j0Var2.a(new y0.u(j5)), n0.i.c(-1728894036, new q(this.f1437g, 1), vVar3), vVar3, 56);
        }
        return p3.n.f5026a;
    }
}
