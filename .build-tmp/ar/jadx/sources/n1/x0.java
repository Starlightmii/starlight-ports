package n1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4187e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0 f4188f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(y0 y0Var, int i5) {
        super(2);
        this.f4187e = i5;
        this.f4188f = y0Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4187e) {
            case 0:
                this.f4188f.a().f4092e = (f0.b0) obj2;
                break;
            case 1:
                b0 b0VarA = this.f4188f.a();
                ((p1.d0) obj).V(new y(b0VarA, (a4.e) obj2, b0VarA.f4105r));
                break;
            default:
                p1.d0 d0Var = (p1.d0) obj;
                b0 b0Var = d0Var.A;
                if (b0Var == null) {
                    b0Var = new b0(d0Var);
                    d0Var.A = b0Var;
                }
                y0 y0Var = this.f4188f;
                y0Var.f4191a = b0Var;
                y0Var.a().e();
                y0Var.a();
                break;
        }
        return p3.n.f5026a;
    }
}
