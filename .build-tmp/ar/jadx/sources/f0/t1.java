package f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t1 extends v3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a4.c f2329d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public /* synthetic */ Object f2330e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f2331f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2332g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t1(g gVar, t3.c cVar) {
        super(cVar);
        this.f2331f = gVar;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        this.f2330e = obj;
        this.f2332g |= Integer.MIN_VALUE;
        return this.f2331f.d(null, this);
    }
}
