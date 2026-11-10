package p1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public r0.o f4962a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4963b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h0.e f4964c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public h0.e f4965d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ t0 f4967f;

    public s0(t0 t0Var, r0.o oVar, int i5, h0.e eVar, h0.e eVar2, boolean z2) {
        this.f4967f = t0Var;
        this.f4962a = oVar;
        this.f4963b = i5;
        this.f4964c = eVar;
        this.f4965d = eVar2;
        this.f4966e = z2;
    }

    public final boolean a(int i5, int i6) {
        h0.e eVar = this.f4964c;
        int i7 = this.f4963b;
        r0.n nVar = (r0.n) eVar.f2576d[i5 + i7];
        r0.n nVar2 = (r0.n) this.f4965d.f2576d[i7 + i6];
        u0 u0Var = v0.f4980a;
        return b4.l.a(nVar, nVar2) || nVar.getClass() == nVar2.getClass();
    }
}
