package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class v2 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1371e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ z2 f1372f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v2(z2 z2Var, int i5) {
        super(2);
        this.f1371e = i5;
        switch (i5) {
            case 1:
                this.f1372f = z2Var;
                super(2);
                break;
            default:
                n0.d dVar = z.f1462a;
                this.f1372f = z2Var;
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0027  */
    /* JADX WARN: Code duplicated, block: B:19:0x0068  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1371e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    } else {
                        n0.d dVar = z.f1462a;
                        z2 z2Var = this.f1372f;
                        b4.l.b(z2Var);
                        dVar.b(z2Var, pVar, 0);
                    }
                } else {
                    n0.d dVar2 = z.f1462a;
                    z2 z2Var2 = this.f1372f;
                    b4.l.b(z2Var2);
                    dVar2.b(z2Var2, pVar, 0);
                }
                break;
            default:
                f0.p pVar2 = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar2;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        n4.b(this.f1372f.f1489a.f745a, null, 0L, 0L, 0L, 0L, 0, false, 0, 0, null, pVar2, 0, 0, 131070);
                    }
                } else {
                    n4.b(this.f1372f.f1489a.f745a, null, 0L, 0L, 0L, 0L, 0, false, 0, 0, null, pVar2, 0, 0, 131070);
                }
                break;
        }
        return p3.n.f5026a;
    }
}
