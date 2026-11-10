package c0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class r extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ r0.p f1213e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ y0.q0 f1214f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ o f1215g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ p f1216h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ n0.d f1217i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(r0.p pVar, y0.q0 q0Var, o oVar, p pVar2, n0.d dVar, int i5) {
        super(2);
        this.f1213e = pVar;
        this.f1214f = q0Var;
        this.f1215g = oVar;
        this.f1216h = pVar2;
        this.f1217i = dVar;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        int iZ = f0.l.z(196609);
        h2.b(this.f1213e, this.f1214f, this.f1215g, this.f1216h, this.f1217i, (f0.p) obj, iZ);
        return p3.n.f5026a;
    }
}
