package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class u0 implements j0, x0, y0.q0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final u0 f4029e = new u0(0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final u0 f4030f = new u0(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4031d;

    public /* synthetic */ u0(int i5) {
        this.f4031d = i5;
    }

    @Override // n.j0
    public void a(p1.f0 f0Var) {
        f0Var.c();
    }

    @Override // n.x0
    public boolean b() {
        return false;
    }

    @Override // y0.q0
    public y0.j0 c(long j5, k2.k kVar, k2.b bVar) {
        switch (this.f4031d) {
            case 3:
                float fM = bVar.M(v.f4032a);
                return new y0.h0(new x0.d(0.0f, -fM, x0.f.d(j5), x0.f.b(j5) + fM));
            default:
                float fM2 = bVar.M(v.f4032a);
                return new y0.h0(new x0.d(-fM2, 0.0f, x0.f.d(j5) + fM2, x0.f.b(j5)));
        }
    }

    @Override // n.x0
    public long d(long j5, int i5, c0.g1 g1Var) {
        g1Var.getClass();
        p.i1 i1Var = (p.i1) g1Var.f871f;
        return new x0.c(p.i1.a(i1Var, i1Var.f4491h, j5, i1Var.f4490g)).f6532a;
    }

    @Override // n.x0
    public Object e(long j5, p.h1 h1Var, t3.c cVar) {
        p.h1 h1Var2 = new p.h1(h1Var.f4473g, cVar);
        h1Var2.f4472f = j5;
        p3.n nVar = p3.n.f5026a;
        Object objInvokeSuspend = h1Var2.invokeSuspend(nVar);
        return objInvokeSuspend == u3.a.f5838d ? objInvokeSuspend : nVar;
    }

    @Override // n.x0
    public r0.p f() {
        return r0.m.f5680a;
    }
}
