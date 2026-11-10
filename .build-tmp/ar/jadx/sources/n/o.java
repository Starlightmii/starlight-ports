package n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class o extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f3969e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0.p f3970f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f3971g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ float f3972h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ float f3973i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ long f3974j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ long f3975k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ a1.j f3976l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(boolean z2, y0.s0 s0Var, long j5, float f5, float f6, long j6, long j7, a1.j jVar) {
        super(1);
        this.f3969e = z2;
        this.f3970f = s0Var;
        this.f3971g = j5;
        this.f3972h = f5;
        this.f3973i = f6;
        this.f3974j = j6;
        this.f3975k = j7;
        this.f3976l = jVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        p1.f0 f0Var = (p1.f0) obj;
        f0Var.c();
        a1.c cVar = f0Var.f4828d;
        if (this.f3969e) {
            a1.f.p(f0Var, this.f3970f, 0L, 0L, this.f3971g, null, 246);
        } else {
            long j5 = this.f3971g;
            float fB = x0.a.b(j5);
            float f5 = this.f3972h;
            if (fB < f5) {
                float f6 = this.f3973i;
                float fD = x0.f.d(cVar.b());
                float f7 = this.f3973i;
                float f8 = fD - f7;
                float fB2 = x0.f.b(cVar.b()) - f7;
                y0.p pVar = this.f3970f;
                long j6 = this.f3971g;
                a1.b bVar = cVar.f27e;
                long jT = bVar.t();
                bVar.r().p();
                try {
                    ((a1.b) ((a1.d) bVar.f23d).f31e).r().i(f6, f6, f8, fB2, 0);
                    a1.f.p(f0Var, pVar, 0L, 0L, j6, null, 246);
                } finally {
                    bVar.r().m();
                    bVar.D(jT);
                }
            } else {
                a1.f.p(f0Var, this.f3970f, this.f3974j, this.f3975k, h1.c.N(f5, j5), this.f3976l, 208);
            }
        }
        return p3.n.f5026a;
    }
}
