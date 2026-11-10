package n2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class h extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s f4219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ a4.a f4220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ t f4221g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ n0.d f4222h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4223i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f4224j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(s sVar, a4.a aVar, t tVar, n0.d dVar, int i5, int i6) {
        super(2);
        this.f4219e = sVar;
        this.f4220f = aVar;
        this.f4221g = tVar;
        this.f4222h = dVar;
        this.f4223i = i5;
        this.f4224j = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        ((Number) obj2).intValue();
        j.a(this.f4219e, this.f4220f, this.f4221g, this.f4222h, (f0.p) obj, f0.l.z(this.f4223i | 1), this.f4224j);
        return p3.n.f5026a;
    }
}
