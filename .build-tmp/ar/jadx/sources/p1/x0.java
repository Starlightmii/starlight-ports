package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x0 extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4989e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0 f4990f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ r0.o f4991g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ d f4992h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f4993i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ r f4994j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ boolean f4995k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4996l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f4997m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x0(y0 y0Var, r0.o oVar, d dVar, long j5, r rVar, boolean z2, boolean z3, float f5, int i5) {
        super(0);
        this.f4989e = i5;
        this.f4990f = y0Var;
        this.f4991g = oVar;
        this.f4992h = dVar;
        this.f4993i = j5;
        this.f4994j = rVar;
        this.f4995k = z2;
        this.f4996l = z3;
        this.f4997m = f5;
    }

    @Override // a4.a
    public final Object a() {
        switch (this.f4989e) {
            case 0:
                r0.o oVarD = f.d(this.f4991g, this.f4992h.a());
                y0 y0Var = this.f4990f;
                d dVar = this.f4992h;
                long j5 = this.f4993i;
                r rVar = this.f4994j;
                boolean z2 = this.f4995k;
                boolean z3 = this.f4996l;
                if (oVarD == null) {
                    y0Var.Q0(dVar, j5, rVar, z2, z3);
                } else {
                    float f5 = this.f4997m;
                    rVar.b(oVarD, f5, z3, new x0(y0Var, oVarD, dVar, j5, rVar, z2, z3, f5, 0));
                }
                break;
            default:
                this.f4990f.b1(f.d(this.f4991g, this.f4992h.a()), this.f4992h, this.f4993i, this.f4994j, this.f4995k, this.f4996l, this.f4997m);
                break;
        }
        return p3.n.f5026a;
    }
}
