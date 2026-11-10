package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ n0.d f789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.a f790f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ r0.p f791g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ boolean f792h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ v0 f793i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ r.l0 f794j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(n0.d dVar, a4.a aVar, r0.p pVar, boolean z2, v0 v0Var, r.l0 l0Var, int i5) {
        super(2);
        this.f789e = dVar;
        this.f790f = aVar;
        this.f791g = pVar;
        this.f792h = z2;
        this.f793i = v0Var;
        this.f794j = l0Var;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(7);
        e.b(this.f789e, this.f790f, this.f791g, this.f792h, this.f793i, this.f794j, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}
