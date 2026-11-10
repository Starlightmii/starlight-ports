package p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a1 f4421f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c0(a1 a1Var, int i5) {
        super(2);
        this.f4420e = i5;
        this.f4421f = a1Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4420e) {
            case 0:
                j1.u uVar = (j1.u) obj;
                long j5 = ((x0.c) obj2).f6532a;
                a1 a1Var = this.f4421f;
                if (((Boolean) a1Var.f4408t.c(uVar)).booleanValue()) {
                    if (!a1Var.f4413y) {
                        if (a1Var.f4411w == null) {
                            a1Var.f4411w = k4.j.a(Integer.MAX_VALUE, 6, null);
                        }
                        a1Var.f4413y = true;
                        i4.y.n(a1Var.n0(), null, new g0(a1Var, null), 3);
                    }
                    long j6 = uVar.f3276c;
                    long jG = x0.c.g(j6, p3.j.b(x0.c.d(j5) * Math.signum(x0.c.d(j6)), x0.c.e(j5) * Math.signum(x0.c.e(j6))));
                    k4.c cVar = a1Var.f4411w;
                    if (cVar != null) {
                        cVar.o(new q(jG));
                    }
                }
                return p3.n.f5026a;
            default:
                float fFloatValue = ((Number) obj).floatValue();
                float fFloatValue2 = ((Number) obj2).floatValue();
                a1 a1Var2 = this.f4421f;
                i4.y.n(a1Var2.n0(), null, new z0(a1Var2, fFloatValue, fFloatValue2, null), 3);
                return Boolean.TRUE;
        }
    }
}
